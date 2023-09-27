package org.jeecg.modules.demo.om.entity;

import java.util.List;


public class OmModelRun {

    private String taskId;
    private String taskName;
    private String modelId;
    private String modelName;
    private String baseModelId;
    private String baseModelName;
    private String baseModelPath;


    private String baseModelDirName;
    private String params;
    private String envs;

    private Integer port;

    private List<OmModelRunData> trainData;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBaseModelId() {
        return baseModelId;
    }

    public void setBaseModelId(String baseModelId) {
        this.baseModelId = baseModelId;
    }

    public String getBaseModelName() {
        return baseModelName;
    }

    public void setBaseModelName(String baseModelName) {
        this.baseModelName = baseModelName;
    }

    public String getBaseModelPath() {
        return baseModelPath;
    }

    public void setBaseModelPath(String baseModelPath) {
        this.baseModelPath = baseModelPath;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getEnvs() {
        return envs;
    }

    public void setEnvs(String envs) {
        this.envs = envs;
    }

    public List<OmModelRunData> getTrainData() {
        return trainData;
    }

    public void setTrainData(List<OmModelRunData> trainData) {
        this.trainData = trainData;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    public String getBaseModelDirName() {
        return baseModelDirName;
    }

    public void setBaseModelDirName(String baseModelDirName) {
        this.baseModelDirName = baseModelDirName;
    }
}
