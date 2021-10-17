package org.jeecg.modules.demo.exTableERP.controller;

import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.exTableERP.entity.OrgStrucParamERP;
import org.jeecg.modules.demo.exTableERP.entity.SyntheticProcessERP;
import org.jeecg.modules.demo.exTableERP.entity.PerformanceParamERP;
import org.jeecg.modules.demo.exTableERP.entity.ExperimentMainERP;
import org.jeecg.modules.demo.exTableERP.service.IExperimentMainERPService;
import org.jeecg.modules.demo.exTableERP.service.IOrgStrucParamERPService;
import org.jeecg.modules.demo.exTableERP.service.ISyntheticProcessERPService;
import org.jeecg.modules.demo.exTableERP.service.IPerformanceParamERPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Api(tags="实验数据主表")
@RestController
@RequestMapping("/exTableERP/experimentMainERP")
@Slf4j
public class ExperimentMainERPController extends JeecgController<ExperimentMainERP, IExperimentMainERPService> {

	@Autowired
	private IExperimentMainERPService experimentMainERPService;

	@Autowired
	private IOrgStrucParamERPService orgStrucParamERPService;

	@Autowired
	private ISyntheticProcessERPService syntheticProcessERPService;

	@Autowired
	private IPerformanceParamERPService performanceParamERPService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param experimentMainERP
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "实验数据主表-分页列表查询")
	@ApiOperation(value="实验数据主表-分页列表查询", notes="实验数据主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ExperimentMainERP experimentMainERP,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ExperimentMainERP> queryWrapper = QueryGenerator.initQueryWrapper(experimentMainERP, req.getParameterMap());
		Page<ExperimentMainERP> page = new Page<ExperimentMainERP>(pageNo, pageSize);
		IPage<ExperimentMainERP> pageList = experimentMainERPService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
     *   添加
     * @param experimentMainERP
     * @return
     */
    @AutoLog(value = "实验数据主表-添加")
    @ApiOperation(value="实验数据主表-添加", notes="实验数据主表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ExperimentMainERP experimentMainERP) {
        experimentMainERPService.save(experimentMainERP);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param experimentMainERP
     * @return
     */
    @AutoLog(value = "实验数据主表-编辑")
    @ApiOperation(value="实验数据主表-编辑", notes="实验数据主表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ExperimentMainERP experimentMainERP) {
        experimentMainERPService.updateById(experimentMainERP);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "实验数据主表-通过id删除")
    @ApiOperation(value="实验数据主表-通过id删除", notes="实验数据主表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        experimentMainERPService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "实验数据主表-批量删除")
    @ApiOperation(value="实验数据主表-批量删除", notes="实验数据主表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.experimentMainERPService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ExperimentMainERP experimentMainERP) {
        return super.exportXls(request, experimentMainERP, ExperimentMainERP.class, "实验数据主表");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ExperimentMainERP.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-组织结构参数-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "组织结构参数-通过主表ID查询")
	@ApiOperation(value="组织结构参数-通过主表ID查询", notes="组织结构参数-通过主表ID查询")
	@GetMapping(value = "/listOrgStrucParamERPByMainId")
    public Result<?> listOrgStrucParamERPByMainId(OrgStrucParamERP orgStrucParamERP,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<OrgStrucParamERP> queryWrapper = QueryGenerator.initQueryWrapper(orgStrucParamERP, req.getParameterMap());
        Page<OrgStrucParamERP> page = new Page<OrgStrucParamERP>(pageNo, pageSize);
        IPage<OrgStrucParamERP> pageList = orgStrucParamERPService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param orgStrucParamERP
	 * @return
	 */
	@AutoLog(value = "组织结构参数-添加")
	@ApiOperation(value="组织结构参数-添加", notes="组织结构参数-添加")
	@PostMapping(value = "/addOrgStrucParamERP")
	public Result<?> addOrgStrucParamERP(@RequestBody OrgStrucParamERP orgStrucParamERP) {
		orgStrucParamERPService.save(orgStrucParamERP);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param orgStrucParamERP
	 * @return
	 */
	@AutoLog(value = "组织结构参数-编辑")
	@ApiOperation(value="组织结构参数-编辑", notes="组织结构参数-编辑")
	@PutMapping(value = "/editOrgStrucParamERP")
	public Result<?> editOrgStrucParamERP(@RequestBody OrgStrucParamERP orgStrucParamERP) {
		orgStrucParamERPService.updateById(orgStrucParamERP);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "组织结构参数-通过id删除")
	@ApiOperation(value="组织结构参数-通过id删除", notes="组织结构参数-通过id删除")
	@DeleteMapping(value = "/deleteOrgStrucParamERP")
	public Result<?> deleteOrgStrucParamERP(@RequestParam(name="id",required=true) String id) {
		orgStrucParamERPService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "组织结构参数-批量删除")
	@ApiOperation(value="组织结构参数-批量删除", notes="组织结构参数-批量删除")
	@DeleteMapping(value = "/deleteBatchOrgStrucParamERP")
	public Result<?> deleteBatchOrgStrucParamERP(@RequestParam(name="ids",required=true) String ids) {
	    this.orgStrucParamERPService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportOrgStrucParamERP")
    public ModelAndView exportOrgStrucParamERP(HttpServletRequest request, OrgStrucParamERP orgStrucParamERP) {
		 // Step.1 组装查询条件
		 QueryWrapper<OrgStrucParamERP> queryWrapper = QueryGenerator.initQueryWrapper(orgStrucParamERP, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<OrgStrucParamERP> pageList = orgStrucParamERPService.list(queryWrapper);
		 List<OrgStrucParamERP> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "组织结构参数"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, OrgStrucParamERP.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("组织结构参数报表", "导出人:" + sysUser.getRealname(), "组织结构参数"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importOrgStrucParamERP/{mainId}")
    public Result<?> importOrgStrucParamERP(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<OrgStrucParamERP> list = ExcelImportUtil.importExcel(file.getInputStream(), OrgStrucParamERP.class, params);
				 for (OrgStrucParamERP temp : list) {
                    temp.setMainId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 orgStrucParamERPService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
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
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-组织结构参数-end----------------------------------------------*/

    /*--------------------------------子表处理-合成工艺-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "合成工艺-通过主表ID查询")
	@ApiOperation(value="合成工艺-通过主表ID查询", notes="合成工艺-通过主表ID查询")
	@GetMapping(value = "/listSyntheticProcessERPByMainId")
    public Result<?> listSyntheticProcessERPByMainId(SyntheticProcessERP syntheticProcessERP,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<SyntheticProcessERP> queryWrapper = QueryGenerator.initQueryWrapper(syntheticProcessERP, req.getParameterMap());
        Page<SyntheticProcessERP> page = new Page<SyntheticProcessERP>(pageNo, pageSize);
        IPage<SyntheticProcessERP> pageList = syntheticProcessERPService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param syntheticProcessERP
	 * @return
	 */
	@AutoLog(value = "合成工艺-添加")
	@ApiOperation(value="合成工艺-添加", notes="合成工艺-添加")
	@PostMapping(value = "/addSyntheticProcessERP")
	public Result<?> addSyntheticProcessERP(@RequestBody SyntheticProcessERP syntheticProcessERP) {
		syntheticProcessERPService.save(syntheticProcessERP);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param syntheticProcessERP
	 * @return
	 */
	@AutoLog(value = "合成工艺-编辑")
	@ApiOperation(value="合成工艺-编辑", notes="合成工艺-编辑")
	@PutMapping(value = "/editSyntheticProcessERP")
	public Result<?> editSyntheticProcessERP(@RequestBody SyntheticProcessERP syntheticProcessERP) {
		syntheticProcessERPService.updateById(syntheticProcessERP);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "合成工艺-通过id删除")
	@ApiOperation(value="合成工艺-通过id删除", notes="合成工艺-通过id删除")
	@DeleteMapping(value = "/deleteSyntheticProcessERP")
	public Result<?> deleteSyntheticProcessERP(@RequestParam(name="id",required=true) String id) {
		syntheticProcessERPService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "合成工艺-批量删除")
	@ApiOperation(value="合成工艺-批量删除", notes="合成工艺-批量删除")
	@DeleteMapping(value = "/deleteBatchSyntheticProcessERP")
	public Result<?> deleteBatchSyntheticProcessERP(@RequestParam(name="ids",required=true) String ids) {
	    this.syntheticProcessERPService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportSyntheticProcessERP")
    public ModelAndView exportSyntheticProcessERP(HttpServletRequest request, SyntheticProcessERP syntheticProcessERP) {
		 // Step.1 组装查询条件
		 QueryWrapper<SyntheticProcessERP> queryWrapper = QueryGenerator.initQueryWrapper(syntheticProcessERP, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<SyntheticProcessERP> pageList = syntheticProcessERPService.list(queryWrapper);
		 List<SyntheticProcessERP> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "合成工艺"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, SyntheticProcessERP.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("合成工艺报表", "导出人:" + sysUser.getRealname(), "合成工艺"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importSyntheticProcessERP/{mainId}")
    public Result<?> importSyntheticProcessERP(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<SyntheticProcessERP> list = ExcelImportUtil.importExcel(file.getInputStream(), SyntheticProcessERP.class, params);
				 for (SyntheticProcessERP temp : list) {
                    temp.setMainId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 syntheticProcessERPService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
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
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-合成工艺-end----------------------------------------------*/

    /*--------------------------------子表处理-性能参数-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "性能参数-通过主表ID查询")
	@ApiOperation(value="性能参数-通过主表ID查询", notes="性能参数-通过主表ID查询")
	@GetMapping(value = "/listPerformanceParamERPByMainId")
    public Result<?> listPerformanceParamERPByMainId(PerformanceParamERP performanceParamERP,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<PerformanceParamERP> queryWrapper = QueryGenerator.initQueryWrapper(performanceParamERP, req.getParameterMap());
        Page<PerformanceParamERP> page = new Page<PerformanceParamERP>(pageNo, pageSize);
        IPage<PerformanceParamERP> pageList = performanceParamERPService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param performanceParamERP
	 * @return
	 */
	@AutoLog(value = "性能参数-添加")
	@ApiOperation(value="性能参数-添加", notes="性能参数-添加")
	@PostMapping(value = "/addPerformanceParamERP")
	public Result<?> addPerformanceParamERP(@RequestBody PerformanceParamERP performanceParamERP) {
		performanceParamERPService.save(performanceParamERP);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param performanceParamERP
	 * @return
	 */
	@AutoLog(value = "性能参数-编辑")
	@ApiOperation(value="性能参数-编辑", notes="性能参数-编辑")
	@PutMapping(value = "/editPerformanceParamERP")
	public Result<?> editPerformanceParamERP(@RequestBody PerformanceParamERP performanceParamERP) {
		performanceParamERPService.updateById(performanceParamERP);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "性能参数-通过id删除")
	@ApiOperation(value="性能参数-通过id删除", notes="性能参数-通过id删除")
	@DeleteMapping(value = "/deletePerformanceParamERP")
	public Result<?> deletePerformanceParamERP(@RequestParam(name="id",required=true) String id) {
		performanceParamERPService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "性能参数-批量删除")
	@ApiOperation(value="性能参数-批量删除", notes="性能参数-批量删除")
	@DeleteMapping(value = "/deleteBatchPerformanceParamERP")
	public Result<?> deleteBatchPerformanceParamERP(@RequestParam(name="ids",required=true) String ids) {
	    this.performanceParamERPService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportPerformanceParamERP")
    public ModelAndView exportPerformanceParamERP(HttpServletRequest request, PerformanceParamERP performanceParamERP) {
		 // Step.1 组装查询条件
		 QueryWrapper<PerformanceParamERP> queryWrapper = QueryGenerator.initQueryWrapper(performanceParamERP, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<PerformanceParamERP> pageList = performanceParamERPService.list(queryWrapper);
		 List<PerformanceParamERP> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "性能参数"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, PerformanceParamERP.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("性能参数报表", "导出人:" + sysUser.getRealname(), "性能参数"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importPerformanceParamERP/{mainId}")
    public Result<?> importPerformanceParamERP(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<PerformanceParamERP> list = ExcelImportUtil.importExcel(file.getInputStream(), PerformanceParamERP.class, params);
				 for (PerformanceParamERP temp : list) {
                    temp.setMainId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 performanceParamERPService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
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
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-性能参数-end----------------------------------------------*/




}
