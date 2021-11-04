package org.jeecg.modules.demo.ospTable.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;

import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.FcUtils.api.FcAPI;
import org.jeecg.modules.demo.ospTable.entity.OrganizationalStructureParameters;
import org.jeecg.modules.demo.ospTable.service.IOrganizationalStructureParametersService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date: 2021-08-19
 * @Version: V1.0
 */
@Api(tags = "组织结构参数")
@RestController
@RequestMapping("/ospTable/organizationalStructureParameters")
@Slf4j
public class OrganizationalStructureParametersController extends JeecgController<OrganizationalStructureParameters, IOrganizationalStructureParametersService> {
    @Autowired
    private IOrganizationalStructureParametersService organizationalStructureParametersService;
    @Autowired
    private FcAPI fcAPI;

    /**
     * 分页列表查询
     *
     * @param organizationalStructureParameters
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "组织结构参数-分页列表查询")
    @ApiOperation(value = "组织结构参数-分页列表查询", notes = "组织结构参数-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(OrganizationalStructureParameters organizationalStructureParameters,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<OrganizationalStructureParameters> queryWrapper = QueryGenerator.initQueryWrapper(organizationalStructureParameters, req.getParameterMap());
        Page<OrganizationalStructureParameters> page = new Page<OrganizationalStructureParameters>(pageNo, pageSize);
        IPage<OrganizationalStructureParameters> pageList = organizationalStructureParametersService.page(page, queryWrapper);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<String> uvFields = fcAPI.getUnAuthFields(loginUser.getId(), OrganizationalStructureParameters.class.getDeclaredFields(), "osp:form:V-");
        for(OrganizationalStructureParameters organizationalStructureParameters1:pageList.getRecords()){
            for(String string:uvFields){
                fcAPI.setObjProVal(organizationalStructureParameters1,string,null);
            }
        }
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param organizationalStructureParameters
     * @return
     */
    @AutoLog(value = "组织结构参数-添加")
    @ApiOperation(value = "组织结构参数-添加", notes = "组织结构参数-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody OrganizationalStructureParameters organizationalStructureParameters) {
        organizationalStructureParametersService.save(organizationalStructureParameters);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param organizationalStructureParameters
     * @return
     */
    @AutoLog(value = "组织结构参数-编辑")
    @ApiOperation(value = "组织结构参数-编辑", notes = "组织结构参数-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody OrganizationalStructureParameters organizationalStructureParameters) {
        organizationalStructureParametersService.updateById(organizationalStructureParameters);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "组织结构参数-通过id删除")
    @ApiOperation(value = "组织结构参数-通过id删除", notes = "组织结构参数-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        organizationalStructureParametersService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "组织结构参数-批量删除")
    @ApiOperation(value = "组织结构参数-批量删除", notes = "组织结构参数-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.organizationalStructureParametersService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "组织结构参数-通过id查询")
    @ApiOperation(value = "组织结构参数-通过id查询", notes = "组织结构参数-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        OrganizationalStructureParameters organizationalStructureParameters = organizationalStructureParametersService.getById(id);
        if (organizationalStructureParameters == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(organizationalStructureParameters);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param organizationalStructureParameters
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OrganizationalStructureParameters organizationalStructureParameters) {
        //请求的合法字段
        List<String> requestFields = fcAPI.getLegalFields(request);
        //获取拥有查看权限的字段
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<String> vFields = fcAPI.getAuthFields(loginUser.getId(), OrganizationalStructureParameters.class.getDeclaredFields(), "osp:form:V-");
        //允许导出的字段
        List<String> exportFields = new ArrayList<String>();
        for (String string : requestFields) {
            if (vFields.contains(string)) {
                exportFields.add(string);
            }
        }
        return super.exportXls(request, organizationalStructureParameters, OrganizationalStructureParameters.class, "组织结构参数", StringUtils.join(exportFields, ","));
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
        return super.importExcel(request, response, OrganizationalStructureParameters.class);
    }

}
