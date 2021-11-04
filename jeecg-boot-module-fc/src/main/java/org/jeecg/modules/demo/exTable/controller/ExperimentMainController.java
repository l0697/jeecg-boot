package org.jeecg.modules.demo.exTable.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.common.base.CaseFormat;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.modules.FcUtils.api.FcAPI;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.exTable.entity.OrgStrucParam;
import org.jeecg.modules.demo.exTable.entity.SyntheticProcess;
import org.jeecg.modules.demo.exTable.entity.PerformanceParam;
import org.jeecg.modules.demo.exTable.entity.ExperimentMain;
import org.jeecg.modules.demo.exTable.vo.ExperimentMainPage;
import org.jeecg.modules.demo.exTable.service.IExperimentMainService;
import org.jeecg.modules.demo.exTable.service.IOrgStrucParamService;
import org.jeecg.modules.demo.exTable.service.ISyntheticProcessService;
import org.jeecg.modules.demo.exTable.service.IPerformanceParamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date: 2021-08-30
 * @Version: V1.0
 */
@Api(tags = "实验数据主表")
@RestController
@RequestMapping("/exTable/experimentMain")
@Slf4j
public class ExperimentMainController {
    @Autowired
    private IExperimentMainService experimentMainService;
    @Autowired
    private IOrgStrucParamService orgStrucParamService;
    @Autowired
    private ISyntheticProcessService syntheticProcessService;
    @Autowired
    private IPerformanceParamService performanceParamService;
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private FcAPI fcAPI;

    /**
     * 分页列表查询
     *
     * @param experimentMain
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "实验数据主表-分页列表查询")
    @ApiOperation(value = "实验数据主表-分页列表查询", notes = "实验数据主表-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("exTable:button:search")
    public Result<?> queryPageList(ExperimentMain experimentMain,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Map queryAns = this.onlCgformFieldService.queryAutolistPage("experiment_main", "fec0f46baac9461094acddfa17ad5cb7", fcAPI.getQueryMap(req), (List) null);

        return Result.OK(queryAns);
        /*
        QueryWrapper<ExperimentMain> queryWrapper = QueryGenerator.initQueryWrapper(experimentMain, req.getParameterMap());
        Page<ExperimentMain> page = new Page<ExperimentMain>(pageNo, pageSize);
        IPage<ExperimentMain> pageList = experimentMainService.page(page, queryWrapper);
        return Result.OK(pageList);
        */
    }


    /**
     * 添加
     *
     * @param experimentMainPage
     * @return
     */
    @AutoLog(value = "实验数据主表-添加")
    @ApiOperation(value = "实验数据主表-添加", notes = "实验数据主表-添加")
    @PostMapping(value = "/add")
    @RequiresPermissions("exTable:button:add")
    public Result<?> add(@RequestBody ExperimentMainPage experimentMainPage) {
        ExperimentMain experimentMain = new ExperimentMain();
        BeanUtils.copyProperties(experimentMainPage, experimentMain);
        experimentMainService.saveMain(experimentMain, experimentMainPage.getOrgStrucParamList(), experimentMainPage.getSyntheticProcessList(), experimentMainPage.getPerformanceParamList());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param experimentMainPage
     * @return
     */
    @AutoLog(value = "实验数据主表-编辑")
    @ApiOperation(value = "实验数据主表-编辑", notes = "实验数据主表-编辑")
    @PutMapping(value = "/edit")
    @RequiresPermissions("exTable:button:edit")
    public Result<?> edit(@RequestBody ExperimentMainPage experimentMainPage) {
        ExperimentMain experimentMain = new ExperimentMain();
        BeanUtils.copyProperties(experimentMainPage, experimentMain);
        ExperimentMain experimentMainEntity = experimentMainService.getById(experimentMain.getId());
        if (experimentMainEntity == null) {
            return Result.error("未找到对应数据");
        }
        Date date = new Date();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        for (OrgStrucParam orgStrucParam : experimentMainPage.getOrgStrucParamList()) {
            orgStrucParam.setUpdateBy(loginUser.getUsername());
            orgStrucParam.setUpdateTime(date);
        }
        for (SyntheticProcess orgStrucParam : experimentMainPage.getSyntheticProcessList()) {
            orgStrucParam.setUpdateBy(loginUser.getUsername());
            orgStrucParam.setUpdateTime(date);
        }
        for (PerformanceParam orgStrucParam : experimentMainPage.getPerformanceParamList()) {
            orgStrucParam.setUpdateBy(loginUser.getUsername());
            orgStrucParam.setUpdateTime(date);
        }
        experimentMainService.updateMain(experimentMain, experimentMainPage.getOrgStrucParamList(), experimentMainPage.getSyntheticProcessList(), experimentMainPage.getPerformanceParamList());
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "实验数据主表-通过id删除")
    @ApiOperation(value = "实验数据主表-通过id删除", notes = "实验数据主表-通过id删除")
    @DeleteMapping(value = "/delete")
    @RequiresPermissions("exTable:button:delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        experimentMainService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "实验数据主表-批量删除")
    @ApiOperation(value = "实验数据主表-批量删除", notes = "实验数据主表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    @RequiresPermissions("exTable:button:deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.experimentMainService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "实验数据主表-通过id查询")
    @ApiOperation(value = "实验数据主表-通过id查询", notes = "实验数据主表-通过id查询")
    @GetMapping(value = "/queryById")
    @RequiresPermissions("exTable:button:search")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ExperimentMain experimentMain = experimentMainService.getById(id);
        if (experimentMain == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(experimentMain);

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "组织结构参数通过主表ID查询")
    @ApiOperation(value = "组织结构参数主表ID查询", notes = "组织结构参数-通主表ID查询")
    @GetMapping(value = "/queryOrgStrucParamByMainId")
    @RequiresPermissions("exTable:button:search")
    public Result<?> queryOrgStrucParamListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<OrgStrucParam> orgStrucParamList = orgStrucParamService.selectByMainId(id);
        return Result.OK(orgStrucParamList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "合成工艺通过主表ID查询")
    @ApiOperation(value = "合成工艺主表ID查询", notes = "合成工艺-通主表ID查询")
    @GetMapping(value = "/querySyntheticProcessByMainId")
    @RequiresPermissions("exTable:button:search")
    public Result<?> querySyntheticProcessListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<SyntheticProcess> syntheticProcessList = syntheticProcessService.selectByMainId(id);
        return Result.OK(syntheticProcessList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "性能参数通过主表ID查询")
    @ApiOperation(value = "性能参数主表ID查询", notes = "性能参数-通主表ID查询")
    @GetMapping(value = "/queryPerformanceParamByMainId")
    @RequiresPermissions("exTable:button:search")
    public Result<?> queryPerformanceParamListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<PerformanceParam> performanceParamList = performanceParamService.selectByMainId(id);
        return Result.OK(performanceParamList);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param experimentMain
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("exTable:button:export")
    public ModelAndView exportXls(HttpServletRequest request, ExperimentMain experimentMain) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<ExperimentMain> queryWrapper = QueryGenerator.initQueryWrapper(experimentMain, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //Step.2 获取导出数据
        List<ExperimentMain> queryList = experimentMainService.list(queryWrapper);
        // 过滤选中数据
        String selections = request.getParameter("selections");
        List<ExperimentMain> experimentMainList = new ArrayList<ExperimentMain>();
        if (oConvertUtils.isEmpty(selections)) {
            experimentMainList = queryList;
        } else {
            List<String> selectionList = Arrays.asList(selections.split(","));
            experimentMainList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
        }

        // Step.3 组装pageList
        List<ExperimentMainPage> pageList = new ArrayList<ExperimentMainPage>();
        for (ExperimentMain main : experimentMainList) {
            ExperimentMainPage vo = new ExperimentMainPage();
            BeanUtils.copyProperties(main, vo);
            List<OrgStrucParam> orgStrucParamList = orgStrucParamService.selectByMainId(main.getId());
            vo.setOrgStrucParamList(orgStrucParamList);
            List<SyntheticProcess> syntheticProcessList = syntheticProcessService.selectByMainId(main.getId());
            vo.setSyntheticProcessList(syntheticProcessList);
            List<PerformanceParam> performanceParamList = performanceParamService.selectByMainId(main.getId());
            vo.setPerformanceParamList(performanceParamList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "实验数据主表列表");
        mv.addObject(NormalExcelConstants.CLASS, ExperimentMainPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("实验数据主表数据", "导出人:" + sysUser.getRealname(), "实验数据主表"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("exTable:button:import")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<ExperimentMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ExperimentMainPage.class, params);
                for (ExperimentMainPage page : list) {
                    ExperimentMain po = new ExperimentMain();
                    BeanUtils.copyProperties(page, po);
                    experimentMainService.saveMain(po, page.getOrgStrucParamList(), page.getSyntheticProcessList(), page.getPerformanceParamList());
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.OK("文件导入失败！");
    }

}
