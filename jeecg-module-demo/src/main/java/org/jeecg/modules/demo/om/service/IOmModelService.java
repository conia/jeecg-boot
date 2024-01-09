package org.jeecg.modules.demo.om.service;

import org.jeecg.modules.demo.om.entity.OmModelData;
import org.jeecg.modules.demo.om.entity.OmModel;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 模型
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface IOmModelService extends IService<OmModel> {

	/**
	 * 添加一对多
	 *
	 * @param omModel
	 * @param omModelDataList
	 */
	public void saveMain(OmModel omModel,List<OmModelData> omModelDataList) ;


	/**
	 * 添加一对多 (new)
	 *
	 * @param omModel
	 * @param omModelDataList
	 */
	public void saveMainNew(OmModel omModel,List<OmModelData> omModelDataList) ;

	
	/**
	 * 修改一对多
	 *
   * @param omModel
   * @param omModelDataList
	 */
	public void updateMain(OmModel omModel,List<OmModelData> omModelDataList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);


	
}
