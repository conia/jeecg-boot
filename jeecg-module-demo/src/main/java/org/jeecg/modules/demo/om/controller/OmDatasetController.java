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
import org.jeecg.modules.demo.om.entity.OmFile;
import org.jeecg.modules.demo.om.entity.OmDataset;
import org.jeecg.modules.demo.om.vo.OmDatasetPage;
import org.jeecg.modules.demo.om.service.IOmDatasetService;
import org.jeecg.modules.demo.om.service.IOmFileService;
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
 * @Description: 数据集
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Api(tags="数据集")
@RestController
@RequestMapping("/om/omDataset")
@Slf4j
public class OmDatasetController {
	@Autowired
	private IOmDatasetService omDatasetService;
	@Autowired
	private IOmFileService omFileService;
	
	/**
	 * 分页列表查询
	 *
	 * @param omDataset
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "数据集-分页列表查询")
	@ApiOperation(value="数据集-分页列表查询", notes="数据集-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OmDataset>> queryPageList(OmDataset omDataset,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OmDataset> queryWrapper = QueryGenerator.initQueryWrapper(omDataset, req.getParameterMap());
		Page<OmDataset> page = new Page<OmDataset>(pageNo, pageSize);
		IPage<OmDataset> pageList = omDatasetService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param omDatasetPage
	 * @return
	 */
	@AutoLog(value = "数据集-添加")
	@ApiOperation(value="数据集-添加", notes="数据集-添加")
    @RequiresPermissions("om:om_dataset:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OmDatasetPage omDatasetPage) {
		OmDataset omDataset = new OmDataset();
		BeanUtils.copyProperties(omDatasetPage, omDataset);
		omDatasetService.saveMain(omDataset, omDatasetPage.getOmFileList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param omDatasetPage
	 * @return
	 */
	@AutoLog(value = "数据集-编辑")
	@ApiOperation(value="数据集-编辑", notes="数据集-编辑")
    @RequiresPermissions("om:om_dataset:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OmDatasetPage omDatasetPage) {
		OmDataset omDataset = new OmDataset();
		BeanUtils.copyProperties(omDatasetPage, omDataset);
		OmDataset omDatasetEntity = omDatasetService.getById(omDataset.getId());
		if(omDatasetEntity==null) {
			return Result.error("未找到对应数据");
		}
		omDatasetService.updateMain(omDataset, omDatasetPage.getOmFileList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据集-通过id删除")
	@ApiOperation(value="数据集-通过id删除", notes="数据集-通过id删除")
    @RequiresPermissions("om:om_dataset:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		omDatasetService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "数据集-批量删除")
	@ApiOperation(value="数据集-批量删除", notes="数据集-批量删除")
    @RequiresPermissions("om:om_dataset:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.omDatasetService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "数据集-通过id查询")
	@ApiOperation(value="数据集-通过id查询", notes="数据集-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OmDataset> queryById(@RequestParam(name="id",required=true) String id) {
		OmDataset omDataset = omDatasetService.getById(id);
		if(omDataset==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(omDataset);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "数据集内数据文件通过主表ID查询")
	@ApiOperation(value="数据集内数据文件主表ID查询", notes="数据集内数据文件-通主表ID查询")
	@GetMapping(value = "/queryOmFileByMainId")
	public Result<List<OmFile>> queryOmFileListByMainId(@RequestParam(name="id",required=true) String id) {
		List<OmFile> omFileList = omFileService.selectByMainId(id);
		return Result.OK(omFileList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param omDataset
    */
    @RequiresPermissions("om:om_dataset:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OmDataset omDataset) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<OmDataset> queryWrapper = QueryGenerator.initQueryWrapper(omDataset, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<OmDataset> omDatasetList = omDatasetService.list(queryWrapper);

      // Step.3 组装pageList
      List<OmDatasetPage> pageList = new ArrayList<OmDatasetPage>();
      for (OmDataset main : omDatasetList) {
          OmDatasetPage vo = new OmDatasetPage();
          BeanUtils.copyProperties(main, vo);
          List<OmFile> omFileList = omFileService.selectByMainId(main.getId());
          vo.setOmFileList(omFileList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "数据集列表");
      mv.addObject(NormalExcelConstants.CLASS, OmDatasetPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("数据集数据", "导出人:"+sysUser.getRealname(), "数据集"));
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
    @RequiresPermissions("om:om_dataset:importExcel")
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
              List<OmDatasetPage> list = ExcelImportUtil.importExcel(file.getInputStream(), OmDatasetPage.class, params);
              for (OmDatasetPage page : list) {
                  OmDataset po = new OmDataset();
                  BeanUtils.copyProperties(page, po);
                  omDatasetService.saveMain(po, page.getOmFileList());
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
