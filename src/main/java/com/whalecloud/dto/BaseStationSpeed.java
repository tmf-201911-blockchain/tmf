package com.whalecloud.dto;

/**
 *
 * 基站速度
 *
 * @author zhaoyanac
 * @date 2019/11/7
 */
public class BaseStationSpeed {

    /**
     * 上传速率
     */
    private String UploadRate;
    /**
     * 下载速率
     */
    private String DownloadRate;
    /**
     * 网络延迟
     */
    private String NetworkDelay;
    /**
     * 调用连接速率
     */
    private String CallingConnectionRate;
    /**
     * 语音拨号速率
     */
    private String VoiceDropRate;


    public String getUploadRate() {
        return UploadRate;
    }

    public void setUploadRate(String uploadRate) {
        UploadRate = uploadRate;
    }

    public String getDownloadRate() {
        return DownloadRate;
    }

    public void setDownloadRate(String downloadRate) {
        DownloadRate = downloadRate;
    }

    public String getNetworkDelay() {
        return NetworkDelay;
    }

    public void setNetworkDelay(String networkDelay) {
        NetworkDelay = networkDelay;
    }

    public String getCallingConnectionRate() {
        return CallingConnectionRate;
    }

    public void setCallingConnectionRate(String callingConnectionRate) {
        CallingConnectionRate = callingConnectionRate;
    }

    public String getVoiceDropRate() {
        return VoiceDropRate;
    }

    public void setVoiceDropRate(String voiceDropRate) {
        VoiceDropRate = voiceDropRate;
    }
}


