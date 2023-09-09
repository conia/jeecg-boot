package org.jeecg.modules.demo.om.service.impl;

import org.jeecg.modules.demo.om.entity.OmFile;
import org.jeecg.modules.demo.om.mapper.OmFileMapper;
import org.jeecg.modules.demo.om.service.IOmFileService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 数据集内数据文件
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class OmFileServiceImpl extends ServiceImpl<OmFileMapper, OmFile> implements IOmFileService {
	
	@Autowired
	private OmFileMapper omFileMapper;
	
	@Override
	public List<OmFile> selectByMainId(String mainId) {
		return omFileMapper.selectByMainId(mainId);
	}
}
