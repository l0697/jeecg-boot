package org.jeecg.modules.demo.exTableNormal.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jeecg.modules.demo.exTableNormal.entity.OrgStrucParamNormal;
import org.jeecg.modules.demo.exTableNormal.entity.SyntheticProcessNormal;
import org.jeecg.modules.demo.exTableNormal.entity.PerformanceParamNormal;
import org.jeecg.modules.demo.exTableNormal.entity.ExperimentMainNormal;
import org.jeecg.modules.demo.exTableNormal.vo.ExperimentMainNormalPage;
import org.jeecg.modules.demo.exTableNormal.service.IExperimentMainNormalService;
import org.jeecg.modules.demo.exTableNormal.service.IOrgStrucParamNormalService;
import org.jeecg.modules.demo.exTableNormal.service.ISyntheticProcessNormalService;
import org.jeecg.modules.demo.exTableNormal.service.IPerformanceParamNormalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Api(tags="实验数据主表")
@RestController
@RequestMapping("/exTableNormal/experimentMainNormal")
@Slf4j
public class ExperimentMainNormalController {
	@Autowired
	private IExperimentMainNormalService experimentMainNormalService;
	@Autowired
	private IOrgStrucParamNormalService orgStrucParamNormalService;
	@Autowired
	private ISyntheticProcessNormalService syntheticProcessNormalService;
	@Autowired
	private IPerformanceParamNormalService performanceParamNormalService;
	
	/**
	 * 分页列表查询
	 *
	 * @param experimentMainNormal
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "实验数据主表-分页列表查询")
	@ApiOperation(value="实验数据主表-分页列表查询", notes="实验数据主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ExperimentMainNormal experimentMainNormal,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ExperimentMainNormal> queryWrapper = QueryGenerator.initQueryWrapper(experimentMainNormal, req.getParameterMap());
		Page<ExperimentMainNormal> page = new Page<ExperimentMainNormal>(pageNo, pageSize);
		IPage<ExperimentMainNormal> pageList = experimentMainNormalService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param experimentMainNormalPage
	 * @return
	 */
	@AutoLog(value = "实验数据主表-添加")
	@ApiOperation(value="实验数据主表-添加", notes="实验数据主表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ExperimentMainNormalPage experimentMainNormalPage) {
		ExperimentMainNormal experimentMainNormal = new ExperimentMainNormal();
		BeanUtils.copyProperties(experimentMainNormalPage, experimentMainNormal);
		experimentMainNormalService.saveMain(experimentMainNormal, experimentMainNormalPage.getOrgStrucParamNormalList(),experimentMainNormalPage.getSyntheticProcessNormalList(),experimentMainNormalPage.getPerformanceParamNormalList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param experimentMainNormalPage
	 * @return
	 */
	@AutoLog(value = "实验数据主表-编辑")
	@ApiOperation(value="实验数据主表-编辑", notes="实验数据主表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ExperimentMainNormalPage experimentMainNormalPage) {
		ExperimentMainNormal experimentMainNormal = new ExperimentMainNormal();
		BeanUtils.copyProperties(experimentMainNormalPage, experimentMainNormal);
		ExperimentMainNormal experimentMainNormalEntity = experimentMainNormalService.getById(experimentMainNormal.getId());
		if(experimentMainNormalEntity==null) {
			return Result.error("未找到对应数据");
		}
		experimentMainNormalService.updateMain(experimentMainNormal, experimentMainNormalPage.getOrgStrucParamNormalList(),experimentMainNormalPage.getSyntheticProcessNormalList(),experimentMainNormalPage.getPerformanceParamNormalList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "实验数据主表-通过id删除")
	@ApiOperation(value="实验数据主表-通过id删除", notes="实验数据主表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		experimentMainNormalService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "实验数据主表-批量删除")
	@ApiOperation(value="实验数据主表-批量删除", notes="实验数据主表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.experimentMainNormalService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "实验数据主表-通过id查询")
	@ApiOperation(value="实验数据主表-通过id查询", notes="实验数据主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ExperimentMainNormal experimentMainNormal = experimentMainNormalService.getById(id);
		if(experimentMainNormal==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(experimentMainNormal);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "组织结构参数通过主表ID查询")
	@ApiOperation(value="组织结构参数主表ID查询", notes="组织结构参数-通主表ID查询")
	@GetMapping(value = "/queryOrgStrucParamNormalByMainId")
	public Result<?> queryOrgStrucParamNormalListByMainId(@RequestParam(name="id",required=true) String id) {
		List<OrgStrucParamNormal> orgStrucParamNormalList = orgStrucParamNormalService.selectByMainId(id);
		return Result.OK(orgStrucParamNormalList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "合成工艺通过主表ID查询")
	@ApiOperation(value="合成工艺主表ID查询", notes="合成工艺-通主表ID查询")
	@GetMapping(value = "/querySyntheticProcessNormalByMainId")
	public Result<?> querySyntheticProcessNormalListByMainId(@RequestParam(name="id",required=true) String id) {
		List<SyntheticProcessNormal> syntheticProcessNormalList = syntheticProcessNormalService.selectByMainId(id);
		return Result.OK(syntheticProcessNormalList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "性能参数通过主表ID查询")
	@ApiOperation(value="性能参数主表ID查询", notes="性能参数-通主表ID查询")
	@GetMapping(value = "/queryPerformanceParamNormalByMainId")
	public Result<?> queryPerformanceParamNormalListByMainId(@RequestParam(name="id",required=true) String id) {
		List<PerformanceParamNormal> performanceParamNormalList = performanceParamNormalService.selectByMainId(id);
		return Result.OK(performanceParamNormalList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param experimentMainNormal
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ExperimentMainNormal experimentMainNormal) {
      // Step.1 组装查询条件查询数据
		QueryWrapper<ExperimentMainNormal> queryWrapper = QueryGenerator.initQueryWrapper(experimentMainNormal, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ExperimentMainNormal> queryList = experimentMainNormalService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<ExperimentMainNormal> experimentMainNormalList = new ArrayList<ExperimentMainNormal>();
      if(oConvertUtils.isEmpty(selections)) {
          experimentMainNormalList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          experimentMainNormalList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<ExperimentMainNormalPage> pageList = new ArrayList<ExperimentMainNormalPage>();
      for (ExperimentMainNormal main : experimentMainNormalList) {
          ExperimentMainNormalPage vo = new ExperimentMainNormalPage();
          BeanUtils.copyProperties(main, vo);
          List<OrgStrucParamNormal> orgStrucParamNormalList = orgStrucParamNormalService.selectByMainId(main.getId());
          vo.setOrgStrucParamNormalList(orgStrucParamNormalList);
          List<SyntheticProcessNormal> syntheticProcessNormalList = syntheticProcessNormalService.selectByMainId(main.getId());
          vo.setSyntheticProcessNormalList(syntheticProcessNormalList);
          List<PerformanceParamNormal> performanceParamNormalList = performanceParamNormalService.selectByMainId(main.getId());
          vo.setPerformanceParamNormalList(performanceParamNormalList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new myJeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "实验数据主表列表");
      mv.addObject(NormalExcelConstants.CLASS, ExperimentMainNormalPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("实验数据主表数据", "导出人:"+sysUser.getRealname(), "实验数据主表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);

      mv.addObject(NormalExcelConstants.EXPORT_FIELDS,"orgStrucParamNormalList");
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
              List<ExperimentMainNormalPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ExperimentMainNormalPage.class, params);
              for (ExperimentMainNormalPage page : list) {
                  ExperimentMainNormal po = new ExperimentMainNormal();
                  BeanUtils.copyProperties(page, po);
                  experimentMainNormalService.saveMain(po, page.getOrgStrucParamNormalList(),page.getSyntheticProcessNormalList(),page.getPerformanceParamNormalList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
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
