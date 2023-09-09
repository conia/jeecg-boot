package org.jeecg.modules.demo.om.service;

import org.jeecg.modules.demo.om.entity.OmModelData;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 模型训练数据
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface IOmModelDataService extends IService<OmModelData> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<OmModelData>
	 */
	public List<OmModelData> selectByMainId(String mainId);
}
