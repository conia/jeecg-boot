package org.jeecg.modules.demo.om.service.impl;

import org.jeecg.modules.demo.om.entity.OmModelData;
import org.jeecg.modules.demo.om.mapper.OmModelDataMapper;
import org.jeecg.modules.demo.om.service.IOmModelDataService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 模型训练数据
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class OmModelDataServiceImpl extends ServiceImpl<OmModelDataMapper, OmModelData> implements IOmModelDataService {
	
	@Autowired
	private OmModelDataMapper omModelDataMapper;
	
	@Override
	public List<OmModelData> selectByMainId(String mainId) {
		return omModelDataMapper.selectByMainId(mainId);
	}
}
