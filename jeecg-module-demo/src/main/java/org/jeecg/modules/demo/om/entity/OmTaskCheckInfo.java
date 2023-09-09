package org.jeecg.modules.demo.om.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OmTaskCheckInfo {

    private String taskId;
    private String taskType;
    private String dirName;

}
