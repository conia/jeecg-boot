package org.jeecg.modules.demo.om.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.om.entity.OmModel;
import org.jeecg.modules.demo.om.entity.OmModelData;
import org.jeecg.modules.demo.om.entity.OmTask;
import java.util.List;

/**
 * @Description: 模型训练记录
 * @Author: hejunxiang
 * @Date:   2023-11-17
 * @Version: V1.0
 */
public interface IOmService extends IService<OmTask> {

    public void saveModelAndTask(OmModel omModel, List<OmModelData> omModelDataList, OmTask task);

}
