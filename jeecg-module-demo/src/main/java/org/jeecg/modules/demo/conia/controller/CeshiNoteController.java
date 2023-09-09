package org.jeecg.modules.demo.conia.controller;

import java.util.Arrays;
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
import org.jeecg.modules.demo.conia.entity.CeshiNote;
import org.jeecg.modules.demo.conia.service.ICeshiNoteService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 测试请假表
 * @Author: jeecg-boot
 * @Date:   2023-08-29
 * @Version: V1.0
 */
@Api(tags="测试请假表")
@RestController
@RequestMapping("/conia/ceshiNote")
@Slf4j
public class CeshiNoteController extends JeecgController<CeshiNote, ICeshiNoteService> {
	@Autowired
	private ICeshiNoteService ceshiNoteService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ceshiNote
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "测试请假表-分页列表查询")
	@ApiOperation(value="测试请假表-分页列表查询", notes="测试请假表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CeshiNote>> queryPageList(CeshiNote ceshiNote,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CeshiNote> queryWrapper = QueryGenerator.initQueryWrapper(ceshiNote, req.getParameterMap());
		Page<CeshiNote> page = new Page<CeshiNote>(pageNo, pageSize);
		IPage<CeshiNote> pageList = ceshiNoteService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ceshiNote
	 * @return
	 */
	@AutoLog(value = "测试请假表-添加")
	@ApiOperation(value="测试请假表-添加", notes="测试请假表-添加")
	@RequiresPermissions("conia:ceshi_note:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CeshiNote ceshiNote) {
		ceshiNoteService.save(ceshiNote);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ceshiNote
	 * @return
	 */
	@AutoLog(value = "测试请假表-编辑")
	@ApiOperation(value="测试请假表-编辑", notes="测试请假表-编辑")
	@RequiresPermissions("conia:ceshi_note:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CeshiNote ceshiNote) {
		ceshiNoteService.updateById(ceshiNote);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试请假表-通过id删除")
	@ApiOperation(value="测试请假表-通过id删除", notes="测试请假表-通过id删除")
	@RequiresPermissions("conia:ceshi_note:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ceshiNoteService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试请假表-批量删除")
	@ApiOperation(value="测试请假表-批量删除", notes="测试请假表-批量删除")
	@RequiresPermissions("conia:ceshi_note:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ceshiNoteService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "测试请假表-通过id查询")
	@ApiOperation(value="测试请假表-通过id查询", notes="测试请假表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CeshiNote> queryById(@RequestParam(name="id",required=true) String id) {
		CeshiNote ceshiNote = ceshiNoteService.getById(id);
		if(ceshiNote==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ceshiNote);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ceshiNote
    */
    @RequiresPermissions("conia:ceshi_note:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CeshiNote ceshiNote) {
        return super.exportXls(request, ceshiNote, CeshiNote.class, "测试请假表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("conia:ceshi_note:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CeshiNote.class);
    }

}
