package com.cloud.enums;

/**
 * @author QFu
 * @date 2020/1/17 17:28
 * @description
 */
public enum IspEnum {
            CHINANET("CHINANET","中国电信"),
            UNICOM("UNICOM","中国联通"),
            CMNET("CMNET","中国移动"),
            CRTC ("CRTC","中国铁通"),
            COLNET("COLNET","有线通"),
            CERNET("CERNET","教育网"),
            CNCGROUP("CNCGROUP","网通");
    private  String isp;

    private  String ispDesc;

    IspEnum(String isp, String ispDesc) {
        this.isp = isp;
        this.ispDesc = ispDesc;
    }
}
