package org.jeecg.modules.demo.om.mapper;

import java.util.List;
import org.jeecg.modules.demo.om.entity.OmFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 数据集内数据文件
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface OmFileMapper extends BaseMapper<OmFile> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<OmFile>
   */
	public List<OmFile> selectByMainId(@Param("mainId") String mainId);
}
