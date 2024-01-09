package org.jeecg.modules.demo.om.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.om.entity.OmModel;
import org.jeecg.modules.demo.om.entity.OmModelData;
import org.jeecg.modules.demo.om.entity.OmTask;
import org.jeecg.modules.demo.om.mapper.OmTaskMapper;
import org.jeecg.modules.demo.om.service.IOmModelService;
import org.jeecg.modules.demo.om.service.IOmService;
import org.jeecg.modules.demo.om.service.IOmTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 模型训练记录
 * @Author: jeecg-boot
 * @Date:   2023-09-01
 * @Version: V1.0
 */
@Service
public class OmServiceImpl extends ServiceImpl<OmTaskMapper, OmTask> implements IOmService {

    @Autowired
    private IOmModelService omModelService;
    @Autowired
    private IOmTaskService omTaskService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveModelAndTask(OmModel omModel, List<OmModelData> omModelDataList,OmTask task) {

        omModelService.saveMainNew(omModel,omModelDataList);
        task.setModelId(omModel.getId());
        omTaskService.save(task);

    }
}
