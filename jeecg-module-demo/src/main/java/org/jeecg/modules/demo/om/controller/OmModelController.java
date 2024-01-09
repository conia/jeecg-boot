package org.jeecg.modules.demo.om.controller;

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
import org.jeecg.modules.demo.om.entity.OmModelData;
import org.jeecg.modules.demo.om.entity.OmModel;
import org.jeecg.modules.demo.om.vo.OmModelPage;
import org.jeecg.modules.demo.om.service.IOmModelService;
import org.jeecg.modules.demo.om.service.IOmModelDataService;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;


 /**
 * @Description: 模型
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Api(tags="模型")
@RestController
@RequestMapping("/om/omModel")
@Slf4j
public class OmModelController {
	@Autowired
	private IOmModelService omModelService;
	@Autowired
	private IOmModelDataService omModelDataService;
	
	/**
	 * 分页列表查询
	 *
	 * @param omModel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "模型-分页列表查询")
	@ApiOperation(value="模型-分页列表查询", notes="模型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OmModel>> queryPageList(OmModel omModel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OmModel> queryWrapper = QueryGenerator.initQueryWrapper(omModel, req.getParameterMap());
		Page<OmModel> page = new Page<OmModel>(pageNo, pageSize);
		IPage<OmModel> pageList = omModelService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param omModelPage
	 * @return
	 */
	@AutoLog(value = "模型-添加")
	@ApiOperation(value="模型-添加", notes="模型-添加")
    @RequiresPermissions("om:om_model:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OmModelPage omModelPage) {
		OmModel omModel = new OmModel();
		BeanUtils.copyProperties(omModelPage, omModel);
		omModelService.saveMain(omModel, omModelPage.getOmModelDataList());
		return Result.OK("添加成功！");
	}

	 /**
	  *   添加本地模型
	  *
	  * @param omModelPage
	  * @return
	  */
	 @AutoLog(value = "模型-添加（本地）")
	 @ApiOperation(value="模型-添加（本地）", notes="模型-添加（本地）")
	 @RequiresPermissions("om:om_model:add")
	 @PostMapping(value = "/addLocal")
	 public Result<String> addLocal(@RequestBody OmModelPage omModelPage) {
		 OmModel omModel = new OmModel();
		 BeanUtils.copyProperties(omModelPage, omModel);
		 // default
//		 if(omModel.getModelSrc() == null ||  "1".equals(omModel.getModelSrc())) {
//			 omModel.setModelSrc("1");
//			 omModel.setModelTrainStatus("3");
//			 omModel.setModelStatus("1");
//		 }

		 omModelService.saveMainNew(omModel, omModelPage.getOmModelDataList());
		 return Result.OK("添加成功！");
	 }

	 /**
	  *   定义模型
	  *
	  * @param omModelPage
	  * @return
	  */
	 @AutoLog(value = "模型-添加（生成模型）")
	 @ApiOperation(value="模型-添加（生成模型）", notes="模型-添加（生成模型）")
	 @RequiresPermissions("om:om_model:add")
	 @PostMapping(value = "/addNew")
	 public Result<String> addNew(@RequestBody OmModelPage omModelPage) {
		 OmModel omModel = new OmModel();
		 BeanUtils.copyProperties(omModelPage, omModel);
		 // default
//		 omModel.setModelSrc("2");
//		 omModel.setModelTrainStatus("1");
//		 omModel.setModelStatus("1");

		 omModelService.saveMainNew(omModel, omModelPage.getOmModelDataList());
		 return Result.OK("添加成功！");
	 }


	 /**
	 *  编辑
	 *
	 * @param omModelPage
	 * @return
	 */
	@AutoLog(value = "模型-编辑")
	@ApiOperation(value="模型-编辑", notes="模型-编辑")
    @RequiresPermissions("om:om_model:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OmModelPage omModelPage) {
		OmModel omModel = new OmModel();
		BeanUtils.copyProperties(omModelPage, omModel);
		OmModel omModelEntity = omModelService.getById(omModel.getId());
		if(omModelEntity==null) {
			return Result.error("未找到对应数据");
		}
		omModelService.updateMain(omModel, omModelPage.getOmModelDataList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "模型-通过id删除")
	@ApiOperation(value="模型-通过id删除", notes="模型-通过id删除")
    @RequiresPermissions("om:om_model:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		omModelService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "模型-批量删除")
	@ApiOperation(value="模型-批量删除", notes="模型-批量删除")
    @RequiresPermissions("om:om_model:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.omModelService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "模型-通过id查询")
	@ApiOperation(value="模型-通过id查询", notes="模型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OmModel> queryById(@RequestParam(name="id",required=true) String id) {
		OmModel omModel = omModelService.getById(id);
		if(omModel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(omModel);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "模型训练数据通过主表ID查询")
	@ApiOperation(value="模型训练数据主表ID查询", notes="模型训练数据-通主表ID查询")
	@GetMapping(value = "/queryOmModelDataByMainId")
	public Result<List<OmModelData>> queryOmModelDataListByMainId(@RequestParam(name="id",required=true) String id) {
		List<OmModelData> omModelDataList = omModelDataService.selectByMainId(id);
		return Result.OK(omModelDataList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param omModel
    */
    @RequiresPermissions("om:om_model:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OmModel omModel) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<OmModel> queryWrapper = QueryGenerator.initQueryWrapper(omModel, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<OmModel> omModelList = omModelService.list(queryWrapper);

      // Step.3 组装pageList
      List<OmModelPage> pageList = new ArrayList<OmModelPage>();
      for (OmModel main : omModelList) {
          OmModelPage vo = new OmModelPage();
          BeanUtils.copyProperties(main, vo);
          List<OmModelData> omModelDataList = omModelDataService.selectByMainId(main.getId());
          vo.setOmModelDataList(omModelDataList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "模型列表");
      mv.addObject(NormalExcelConstants.CLASS, OmModelPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("模型数据", "导出人:"+sysUser.getRealname(), "模型"));
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
    @RequiresPermissions("om:om_model:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<OmModelPage> list = ExcelImportUtil.importExcel(file.getInputStream(), OmModelPage.class, params);
              for (OmModelPage page : list) {
                  OmModel po = new OmModel();
                  BeanUtils.copyProperties(page, po);
                  omModelService.saveMain(po, page.getOmModelDataList());
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
