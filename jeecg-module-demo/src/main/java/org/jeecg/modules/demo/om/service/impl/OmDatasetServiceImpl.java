package org.jeecg.modules.demo.om.service.impl;

import org.jeecg.modules.demo.om.entity.OmDataset;
import org.jeecg.modules.demo.om.entity.OmFile;
import org.jeecg.modules.demo.om.mapper.OmFileMapper;
import org.jeecg.modules.demo.om.mapper.OmDatasetMapper;
import org.jeecg.modules.demo.om.service.IOmDatasetService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 数据集
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class OmDatasetServiceImpl extends ServiceImpl<OmDatasetMapper, OmDataset> implements IOmDatasetService {

	@Autowired
	private OmDatasetMapper omDatasetMapper;
	@Autowired
	private OmFileMapper omFileMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(OmDataset omDataset, List<OmFile> omFileList) {
		omDatasetMapper.insert(omDataset);
		if(omFileList!=null && omFileList.size()>0) {
			for(OmFile entity:omFileList) {
				//外键设置
				entity.setDsId(omDataset.getId());
				omFileMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(OmDataset omDataset,List<OmFile> omFileList) {
		omDatasetMapper.updateById(omDataset);
		
		//1.先删除子表数据
		omFileMapper.deleteByMainId(omDataset.getId());
		
		//2.子表数据重新插入
		if(omFileList!=null && omFileList.size()>0) {
			for(OmFile entity:omFileList) {
				//外键设置
				entity.setDsId(omDataset.getId());
				omFileMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		omFileMapper.deleteByMainId(id);
		omDatasetMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			omFileMapper.deleteByMainId(id.toString());
			omDatasetMapper.deleteById(id);
		}
	}
	
}
