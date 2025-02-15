package org.jeecg.modules.demo.om.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.om.entity.OmModel;
import org.jeecg.modules.demo.om.entity.OmTask;
import org.jeecg.modules.demo.om.service.IOmModelService;
import org.jeecg.modules.demo.om.service.IOmTaskService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.demo.om.util.ModelUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 模型训练记录
 * @Author: jeecg-boot
 * @Date:   2023-09-01
 * @Version: V1.0
 */
@Api(tags="模型训练记录")
@RestController
@RequestMapping("/om/omTask")
@Slf4j
public class OmTaskController extends JeecgController<OmTask, IOmTaskService> {
	@Autowired
	private IOmTaskService omTaskService;

	 @Autowired
	 private IOmModelService omModelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param omTask
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "模型训练记录-分页列表查询")
	@ApiOperation(value="模型训练记录-分页列表查询", notes="模型训练记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OmTask>> queryPageList(OmTask omTask,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OmTask> queryWrapper = QueryGenerator.initQueryWrapper(omTask, req.getParameterMap());
		Page<OmTask> page = new Page<OmTask>(pageNo, pageSize);
		IPage<OmTask> pageList = omTaskService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param omTask
	 * @return
	 */
	@AutoLog(value = "模型训练记录-添加")
	@ApiOperation(value="模型训练记录-添加", notes="模型训练记录-添加")
	@RequiresPermissions("om:om_task:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OmTask omTask) {
		omTaskService.save(omTask);
		OmModel model = new OmModel();
		model.setId(omTask.getModelId());
		if(ModelUtil.isTrainTask(omTask.getTaskType())){
			model.setTrainTaskId(omTask.getId());
		}else if(ModelUtil.isEvalTask(omTask.getTaskType())){
			model.setEvalTaskId(omTask.getId());
		}else if(ModelUtil.isDeployTask(omTask.getTaskType())){
			model.setDeployTaskId(omTask.getId());
		}
		omModelService.updateById(model);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param omTask
	 * @return
	 */
	@AutoLog(value = "模型训练记录-编辑")
	@ApiOperation(value="模型训练记录-编辑", notes="模型训练记录-编辑")
	@RequiresPermissions("om:om_task:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OmTask omTask) {
		omTaskService.updateById(omTask);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "模型训练记录-通过id删除")
	@ApiOperation(value="模型训练记录-通过id删除", notes="模型训练记录-通过id删除")
	@RequiresPermissions("om:om_task:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		omTaskService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "模型训练记录-批量删除")
	@ApiOperation(value="模型训练记录-批量删除", notes="模型训练记录-批量删除")
	@RequiresPermissions("om:om_task:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.omTaskService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "模型训练记录-通过id查询")
	@ApiOperation(value="模型训练记录-通过id查询", notes="模型训练记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OmTask> queryById(@RequestParam(name="id",required=true) String id) {
		OmTask omTask = omTaskService.getById(id);
		if(omTask==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(omTask);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param omTask
    */
    @RequiresPermissions("om:om_task:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OmTask omTask) {
        return super.exportXls(request, omTask, OmTask.class, "模型训练记录");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("om:om_task:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, OmTask.class);
    }

	 /**
	  *  编辑状态
	  *
	  * @param omTask
	  * @return
	  */
	 @AutoLog(value = "模型任务记录-编辑")
	 @ApiOperation(value="模型任务记录-编辑", notes="模型任务记录-编辑")
	 @RequestMapping(value = "/editStatus", method = {RequestMethod.PUT,RequestMethod.POST})
	 public Result<String> editStatus(@RequestBody OmTask omTask) {
		 omTask.setEndTime(new Date());
		 omTaskService.updateById(omTask);
		 return Result.OK("模型任务记录-编辑状态成功!: "+omTask.getStatus());
	 }

}
