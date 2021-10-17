package org.jeecg.modules.demo.surfaceTable.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.modules.FcUtils.api.FcAPI;
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
import org.jeecg.modules.demo.surfaceTable.entity.SurfaceSon;
import org.jeecg.modules.demo.surfaceTable.entity.SurfaceMain;
import org.jeecg.modules.demo.surfaceTable.vo.SurfaceMainPage;
import org.jeecg.modules.demo.surfaceTable.service.ISurfaceMainService;
import org.jeecg.modules.demo.surfaceTable.service.ISurfaceSonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 金属界面数据主表
 * @Author: jeecg-boot
 * @Date: 2021-08-26
 * @Version: V1.0
 */
@Api(tags = "金属界面数据主表")
@RestController
@RequestMapping("/surfaceTable/surfaceMain")
@Slf4j
public class SurfaceMainController {
    @Autowired
    private ISurfaceMainService surfaceMainService;
    @Autowired
    private ISurfaceSonService surfaceSonService;
    @Autowired
    private FcAPI fcAPI;
    /**
     * 分页列表查询
     *
     * @param surfaceMain
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "金属界面数据主表-分页列表查询")
    @ApiOperation(value = "金属界面数据主表-分页列表查询", notes = "金属界面数据主表-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("surfaceTable:button:search")
    public Result<?> queryPageList(SurfaceMain surfaceMain,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SurfaceMain> queryWrapper = QueryGenerator.initQueryWrapper(surfaceMain, req.getParameterMap());
        Page<SurfaceMain> page = new Page<SurfaceMain>(pageNo, pageSize);
        IPage<SurfaceMain> pageList = surfaceMainService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param surfaceMainPage
     * @return
     */
    @AutoLog(value = "金属界面数据主表-添加")
    @ApiOperation(value = "金属界面数据主表-添加", notes = "金属界面数据主表-添加")
    @PostMapping(value = "/add")
    @RequiresPermissions("surfaceTable:button:add")
    public Result<?> add(@RequestBody SurfaceMainPage surfaceMainPage) {
        SurfaceMain surfaceMain = new SurfaceMain();
        BeanUtils.copyProperties(surfaceMainPage, surfaceMain);
        surfaceMainService.saveMain(surfaceMain, surfaceMainPage.getSurfaceSonList());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param surfaceMainPage
     * @return
     */
    @AutoLog(value = "金属界面数据主表-编辑")
    @ApiOperation(value = "金属界面数据主表-编辑", notes = "金属界面数据主表-编辑")
    @PutMapping(value = "/edit")
    @RequiresPermissions("surfaceTable:button:edit")
    public Result<?> edit(@RequestBody SurfaceMainPage surfaceMainPage) {
        SurfaceMain surfaceMain = new SurfaceMain();
        BeanUtils.copyProperties(surfaceMainPage, surfaceMain);
        SurfaceMain surfaceMainEntity = surfaceMainService.getById(surfaceMain.getId());
        if (surfaceMainEntity == null) {
            return Result.error("未找到对应数据");
        }
        Date date = new Date();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        for (SurfaceSon orgStrucParam : surfaceMainPage.getSurfaceSonList()) {
            orgStrucParam.setUpdateBy(loginUser.getUsername());
            orgStrucParam.setUpdateTime(date);
        }
        surfaceMainService.updateMain(surfaceMain, surfaceMainPage.getSurfaceSonList());
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "金属界面数据主表-通过id删除")
    @ApiOperation(value = "金属界面数据主表-通过id删除", notes = "金属界面数据主表-通过id删除")
    @DeleteMapping(value = "/delete")
    @RequiresPermissions("surfaceTable:button:delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        surfaceMainService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "金属界面数据主表-批量删除")
    @ApiOperation(value = "金属界面数据主表-批量删除", notes = "金属界面数据主表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    @RequiresPermissions("surfaceTable:button:deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.surfaceMainService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "金属界面数据主表-通过id查询")
    @ApiOperation(value = "金属界面数据主表-通过id查询", notes = "金属界面数据主表-通过id查询")
    @GetMapping(value = "/queryById")
    @RequiresPermissions("surfaceTable:button:search")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SurfaceMain surfaceMain = surfaceMainService.getById(id);
        if (surfaceMain == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(surfaceMain);

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "金属界面数据附表通过主表ID查询")
    @ApiOperation(value = "金属界面数据附表主表ID查询", notes = "金属界面数据附表-通主表ID查询")
    @GetMapping(value = "/querySurfaceSonByMainId")
    @RequiresPermissions("surfaceTable:button:search")
    public Result<?> querySurfaceSonListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<SurfaceSon> surfaceSonList = surfaceSonService.selectByMainId(id);
        return Result.OK(surfaceSonList);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param surfaceMain
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("surfaceTable:button:export")
    public ModelAndView exportXls(HttpServletRequest request, SurfaceMain surfaceMain) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<SurfaceMain> queryWrapper = QueryGenerator.initQueryWrapper(surfaceMain, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //Step.2 获取导出数据
        List<SurfaceMain> queryList = surfaceMainService.list(queryWrapper);
        // 过滤选中数据
        String selections = request.getParameter("selections");
        List<SurfaceMain> surfaceMainList = new ArrayList<SurfaceMain>();
        if (oConvertUtils.isEmpty(selections)) {
            surfaceMainList = queryList;
        } else {
            List<String> selectionList = Arrays.asList(selections.split(","));
            surfaceMainList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
        }

        // Step.3 组装pageList
        List<SurfaceMainPage> pageList = new ArrayList<SurfaceMainPage>();
        for (SurfaceMain main : surfaceMainList) {
            SurfaceMainPage vo = new SurfaceMainPage();
            BeanUtils.copyProperties(main, vo);
            List<SurfaceSon> surfaceSonList = surfaceSonService.selectByMainId(main.getId());
            vo.setSurfaceSonList(surfaceSonList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "金属界面数据主表列表");
        mv.addObject(NormalExcelConstants.CLASS, SurfaceMainPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("金属界面数据主表数据", "导出人:" + sysUser.getRealname(), "金属界面数据主表"));
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
    @RequiresPermissions("surfaceTable:button:import")
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
                List<SurfaceMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), SurfaceMainPage.class, params);
                for (SurfaceMainPage page : list) {
                    SurfaceMain po = new SurfaceMain();
                    BeanUtils.copyProperties(page, po);
                    surfaceMainService.saveMain(po, page.getSurfaceSonList());
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
    @Value(value="${jeecg.uploadType}")
    private String uploadType;
    /**
     * 通过json导入数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importJson", method = RequestMethod.POST)
    @RequiresPermissions("surfaceTable:button:import")
    public Result<?> importJson(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
//            ImportParams params = new ImportParams();
//            params.setTitleRows(2);
//            params.setHeadRows(1);
//            params.setNeedSave(true);
            try {
                Date date = new Date();
                LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                String theString = IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
                JSONArray jsonArray = JSONArray.parseArray(theString);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SurfaceMain surfaceMain = new SurfaceMain();
                    //surfaceMain.setId();
                    surfaceMain.setCreateBy(loginUser.getUsername());
                    surfaceMain.setCreateTime(date);
                    surfaceMain.setSysOrgCode(loginUser.getOrgCode());
                    surfaceMain.setAtomName(jsonObject.getString("pretty_formula"));
                    surfaceMain.setPolymorph(jsonObject.getInteger("polymorph"));
                    surfaceMain.setWeightedSurfaceEnergyEpa(jsonObject.getBigDecimal("weighted_surface_energy_EV_PER_ANG2"));
                    surfaceMain.setPrettyFormula(jsonObject.getString("pretty_formula"));
                    surfaceMain.setMaterialId(jsonObject.getString("material_id"));
                    surfaceMain.setWeightedSurfaceEnergy(jsonObject.getBigDecimal("weighted_surface_energy"));
                    surfaceMain.setSpacegroupNumber(jsonObject.getJSONObject("spacegroup").getInteger("number"));
                    surfaceMain.setEaboveHull(jsonObject.getBigDecimal("e_above_hull"));
                    surfaceMain.setSurfaceAnisotropy(jsonObject.getBigDecimal("surface_anisotropy"));
                    surfaceMain.setShapeFactor(jsonObject.getBigDecimal("shape_factor"));
                    surfaceMain.setWeightedWorkFunction(jsonObject.getBigDecimal("weighted_work_function"));
                    surfaceMain.setMillerUnits(jsonObject.getString("miller_units"));
                    surfaceMain.setHasReconstructed(jsonObject.getString("has_reconstructed").equals("true") ? 1 : 0);
                    //surfaceMain.setFile();
                    //获取子表数据
                    List<SurfaceSon> surfaceSons = new ArrayList<SurfaceSon>();
                    JSONArray surfacesJSONArray = jsonObject.getJSONArray("surfaces");
                    for (int j = 0; j < surfacesJSONArray.size(); j++) {
                        JSONObject surfaceObject = surfacesJSONArray.getJSONObject(j);
                        SurfaceSon surfaceSon = new SurfaceSon();
                        surfaceSon.setCreateBy(loginUser.getUsername());
                        surfaceSon.setCreateTime(date);
                        surfaceSon.setSysOrgCode(loginUser.getOrgCode());
                        surfaceSon.setSurfaceEnergy(surfaceObject.getBigDecimal("surface_energy"));
                        surfaceSon.setIsReconstructed(surfaceObject.getString("is_reconstructed").equals("true") ? 1 : 0);
                        surfaceSon.setStructure(surfaceObject.getString("structure"));
                        surfaceSon.setInitialStructure(surfaceObject.getString("initial_structure"));
                        surfaceSon.setWorkFunction(surfaceObject.getBigDecimal("work_function"));
                        surfaceSon.setLocpotAlongC(surfaceObject.getJSONArray("locpot_along_c").toJavaList(String.class).toString());
                        surfaceSon.setEfermi(surfaceObject.getBigDecimal("efermi"));
                        surfaceSon.setAreaFraction(surfaceObject.getBigDecimal("area_fraction"));
                        surfaceSon.setHasWulff(surfaceObject.getString("has_wulff").equals("true") ? 1 : 0);
                        surfaceSons.add(surfaceSon);
                    }

                    //存储文件
                    String filePath=fcAPI.uploadLocal(file,"temp");
                    if(!filePath.isEmpty()){
                        surfaceMain.setFile(filePath);
                    }
                    surfaceMainService.saveMain(surfaceMain, surfaceSons);

                }
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
