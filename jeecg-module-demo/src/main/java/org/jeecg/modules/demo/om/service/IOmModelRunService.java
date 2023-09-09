package org.jeecg.modules.demo.om.service;

import org.jeecg.modules.demo.om.entity.OmModelRun;

public interface IOmModelRunService {

    OmModelRun getModelTrainInfo(String taskId);

    OmModelRun getModelEvalInfo(String taskId);
}
