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
 * @Description: ??????????????????
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Api(tags="??????????????????")
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
	 * ??????????????????
	 *
	 * @param experimentMainNormal
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "??????????????????-??????????????????")
	@ApiOperation(value="??????????????????-??????????????????", notes="??????????????????-??????????????????")
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
	 *   ??????
	 *
	 * @param experimentMainNormalPage
	 * @return
	 */
	@AutoLog(value = "??????????????????-??????")
	@ApiOperation(value="??????????????????-??????", notes="??????????????????-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ExperimentMainNormalPage experimentMainNormalPage) {
		ExperimentMainNormal experimentMainNormal = new ExperimentMainNormal();
		BeanUtils.copyProperties(experimentMainNormalPage, experimentMainNormal);
		experimentMainNormalService.saveMain(experimentMainNormal, experimentMainNormalPage.getOrgStrucParamNormalList(),experimentMainNormalPage.getSyntheticProcessNormalList(),experimentMainNormalPage.getPerformanceParamNormalList());
		return Result.OK("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param experimentMainNormalPage
	 * @return
	 */
	@AutoLog(value = "??????????????????-??????")
	@ApiOperation(value="??????????????????-??????", notes="??????????????????-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ExperimentMainNormalPage experimentMainNormalPage) {
		ExperimentMainNormal experimentMainNormal = new ExperimentMainNormal();
		BeanUtils.copyProperties(experimentMainNormalPage, experimentMainNormal);
		ExperimentMainNormal experimentMainNormalEntity = experimentMainNormalService.getById(experimentMainNormal.getId());
		if(experimentMainNormalEntity==null) {
			return Result.error("?????????????????????");
		}
		experimentMainNormalService.updateMain(experimentMainNormal, experimentMainNormalPage.getOrgStrucParamNormalList(),experimentMainNormalPage.getSyntheticProcessNormalList(),experimentMainNormalPage.getPerformanceParamNormalList());
		return Result.OK("????????????!");
	}
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "??????????????????-??????id??????")
	@ApiOperation(value="??????????????????-??????id??????", notes="??????????????????-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		experimentMainNormalService.delMain(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "??????????????????-????????????")
	@ApiOperation(value="??????????????????-????????????", notes="??????????????????-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.experimentMainNormalService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("?????????????????????");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "??????????????????-??????id??????")
	@ApiOperation(value="??????????????????-??????id??????", notes="??????????????????-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ExperimentMainNormal experimentMainNormal = experimentMainNormalService.getById(id);
		if(experimentMainNormal==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(experimentMainNormal);

	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "??????????????????????????????ID??????")
	@ApiOperation(value="????????????????????????ID??????", notes="??????????????????-?????????ID??????")
	@GetMapping(value = "/queryOrgStrucParamNormalByMainId")
	public Result<?> queryOrgStrucParamNormalListByMainId(@RequestParam(name="id",required=true) String id) {
		List<OrgStrucParamNormal> orgStrucParamNormalList = orgStrucParamNormalService.selectByMainId(id);
		return Result.OK(orgStrucParamNormalList);
	}
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "????????????????????????ID??????")
	@ApiOperation(value="??????????????????ID??????", notes="????????????-?????????ID??????")
	@GetMapping(value = "/querySyntheticProcessNormalByMainId")
	public Result<?> querySyntheticProcessNormalListByMainId(@RequestParam(name="id",required=true) String id) {
		List<SyntheticProcessNormal> syntheticProcessNormalList = syntheticProcessNormalService.selectByMainId(id);
		return Result.OK(syntheticProcessNormalList);
	}
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "????????????????????????ID??????")
	@ApiOperation(value="??????????????????ID??????", notes="????????????-?????????ID??????")
	@GetMapping(value = "/queryPerformanceParamNormalByMainId")
	public Result<?> queryPerformanceParamNormalListByMainId(@RequestParam(name="id",required=true) String id) {
		List<PerformanceParamNormal> performanceParamNormalList = performanceParamNormalService.selectByMainId(id);
		return Result.OK(performanceParamNormalList);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param experimentMainNormal
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ExperimentMainNormal experimentMainNormal) {
      // Step.1 ??????????????????????????????
		QueryWrapper<ExperimentMainNormal> queryWrapper = QueryGenerator.initQueryWrapper(experimentMainNormal, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 ??????????????????
      List<ExperimentMainNormal> queryList = experimentMainNormalService.list(queryWrapper);
      // ??????????????????
      String selections = request.getParameter("selections");
      List<ExperimentMainNormal> experimentMainNormalList = new ArrayList<ExperimentMainNormal>();
      if(oConvertUtils.isEmpty(selections)) {
          experimentMainNormalList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          experimentMainNormalList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 ??????pageList
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

      // Step.4 AutoPoi ??????Excel
      ModelAndView mv = new ModelAndView(new myJeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "????????????????????????");
      mv.addObject(NormalExcelConstants.CLASS, ExperimentMainNormalPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("????????????????????????", "?????????:"+sysUser.getRealname(), "??????????????????"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);

      mv.addObject(NormalExcelConstants.EXPORT_FIELDS,"orgStrucParamNormalList");
      return mv;
    }

    /**
    * ??????excel????????????
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
          MultipartFile file = entity.getValue();// ????????????????????????
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
              return Result.OK("?????????????????????????????????:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("??????????????????:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("?????????????????????");
    }

}
