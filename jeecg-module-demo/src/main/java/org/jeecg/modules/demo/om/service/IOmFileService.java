package org.jeecg.modules.demo.om.service;

import org.jeecg.modules.demo.om.entity.OmFile;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 数据集内数据文件
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface IOmFileService extends IService<OmFile> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<OmFile>
	 */
	public List<OmFile> selectByMainId(String mainId);
}
