package org.jeecg.modules.demo.om.util;

import lombok.extern.slf4j.Slf4j;

/**
 * model工具类
 * @author: jxhe
 */
@Slf4j
public class ModelUtil {
    private static int trainConcurrent;

    private static String taskUrl;


    private static String agentDir;

    private static String trainTaskUrl;
    private static String evalTaskUrl;
    private static String deployTaskUrl;



    private static String trainTaskCheckerUrl;

    public static void setTrainConcurrent(int trainConcurrent) {
        ModelUtil.trainConcurrent = trainConcurrent;
    }

    public static int getTrainConcurrent() {
        return trainConcurrent;
    }

    public static void setTaskUrl(String taskUrl) {
        ModelUtil.taskUrl = taskUrl;
        ModelUtil.trainTaskUrl = String.format("%s/task/train",taskUrl);
        ModelUtil.evalTaskUrl = String.format("%s/task/eval",taskUrl);
        ModelUtil.deployTaskUrl = String.format("%s/task/deploy",taskUrl);
        ModelUtil.trainTaskCheckerUrl = String.format("%s/task/check",taskUrl);

    }

    public static String getTrainTaskUrl() {
        return trainTaskUrl;
    }

    public static String getEvalTaskUrl() {
        return evalTaskUrl;
    }

    public static String getDeployTaskUrl() {
        return deployTaskUrl;
    }

    public static String getTrainTaskCheckerUrl() {
        return trainTaskCheckerUrl;
    }

    public static String getAgentDir() {
        return agentDir;
    }

    public static void setAgentDir(String agentDir) {
        ModelUtil.agentDir = agentDir;
    }

    public static boolean isTrainTask(String taskType){
        return "1".equals(taskType);
    }

    public static boolean isEvalTask(String taskType){
        return "2".equals(taskType);
    }

    public static boolean isDeployTask(String taskType){
        return "3".equals(taskType);
    }

}
