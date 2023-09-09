package org.jeecg.modules.demo.om.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.om.entity.OmModelRun;

import java.util.List;

/**
 * @Description: 模型训练信息
 * @Author: jxhe
 * @Date:   2023-09-04
 * @Version: V1.0
 */
public interface OmModelRunMapper extends BaseMapper<OmModelRun> {

      List<OmModelRun> findTrainInfoByTaskId(@Param("taskId") String taskId);

      OmModelRun findEvalInfoByTaskId(@Param("taskId") String taskId);



}
