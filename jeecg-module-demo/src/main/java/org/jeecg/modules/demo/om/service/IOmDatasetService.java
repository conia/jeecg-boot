package org.jeecg.modules.demo.om.service;

import org.jeecg.modules.demo.om.entity.OmFile;
import org.jeecg.modules.demo.om.entity.OmDataset;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 数据集
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface IOmDatasetService extends IService<OmDataset> {

	/**
	 * 添加一对多
	 *
	 * @param omDataset
	 * @param omFileList
	 */
	public void saveMain(OmDataset omDataset,List<OmFile> omFileList) ;
	
	/**
	 * 修改一对多
	 *
   * @param omDataset
   * @param omFileList
	 */
	public void updateMain(OmDataset omDataset,List<OmFile> omFileList);
	
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
