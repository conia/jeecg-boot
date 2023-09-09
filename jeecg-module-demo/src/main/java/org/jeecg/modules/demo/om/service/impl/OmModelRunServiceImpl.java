package org.jeecg.modules.demo.om.service.impl;

import org.jeecg.modules.demo.om.entity.OmModelRun;
import org.jeecg.modules.demo.om.mapper.OmModelRunMapper;
import org.jeecg.modules.demo.om.service.IOmModelRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OmModelRunServiceImpl implements IOmModelRunService {

    @Autowired
    OmModelRunMapper modelTrainMapper;

    @Override
    public OmModelRun getModelTrainInfo(String taskId) {
        List<OmModelRun> list = modelTrainMapper.findTrainInfoByTaskId(taskId);
        if(list.size()>0)
            return list.get(0);
        return null;
    }
    @Override
    public OmModelRun getModelEvalInfo(String taskId) {
        OmModelRun info = modelTrainMapper.findEvalInfoByTaskId(taskId);
        return info;
    }
}
