package org.jeecg.modules.demo.om.service.impl;

import org.jeecg.modules.demo.om.entity.OmModel;
import org.jeecg.modules.demo.om.entity.OmModelData;
import org.jeecg.modules.demo.om.mapper.OmModelDataMapper;
import org.jeecg.modules.demo.om.mapper.OmModelMapper;
import org.jeecg.modules.demo.om.service.IOmModelService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 模型
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class OmModelServiceImpl extends ServiceImpl<OmModelMapper, OmModel> implements IOmModelService {

	@Autowired
	private OmModelMapper omModelMapper;
	@Autowired
	private OmModelDataMapper omModelDataMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(OmModel omModel, List<OmModelData> omModelDataList) {
		omModelMapper.insert(omModel);
		if(omModelDataList!=null && omModelDataList.size()>0) {
			for(OmModelData entity:omModelDataList) {
				//外键设置
				entity.setModelId(omModel.getId());
				omModelDataMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMainNew(OmModel omModel, List<OmModelData> omModelDataList) {


		if(omModel.getModelSrc() == null ||  "1".equals(omModel.getModelSrc())) {
			// default local
			omModel.setModelSrc("1");
			omModel.setModelTrainStatus("3");
			omModel.setModelStatus("1");
		}else{
			// default new
			omModel.setModelSrc("2");
			omModel.setModelTrainStatus("1");
			omModel.setModelStatus("1");
		}

		omModelMapper.insert(omModel);
		if(omModelDataList!=null && omModelDataList.size()>0) {
			for(OmModelData entity:omModelDataList) {
				//外键设置
				entity.setModelId(omModel.getId());
				omModelDataMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(OmModel omModel,List<OmModelData> omModelDataList) {
		omModelMapper.updateById(omModel);
		
		//1.先删除子表数据
		omModelDataMapper.deleteByMainId(omModel.getId());
		
		//2.子表数据重新插入
		if(omModelDataList!=null && omModelDataList.size()>0) {
			for(OmModelData entity:omModelDataList) {
				//外键设置
				entity.setModelId(omModel.getId());
				omModelDataMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		omModelDataMapper.deleteByMainId(id);
		omModelMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			omModelDataMapper.deleteByMainId(id.toString());
			omModelMapper.deleteById(id);
		}
	}
	
}
