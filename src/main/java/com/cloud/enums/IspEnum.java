package com.cloud.enums;

import com.alibaba.druid.util.StringUtils;
import lombok.Getter;

/**
 * @author QFu
 * @date 2020/1/17 17:28
 * @description
 */
@Getter
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
    public static  IspEnum getIspDescByIsp(String isp){
        for(IspEnum ispEnum : IspEnum.values()){
            if(StringUtils.equals(isp, ispEnum.getIsp())){
                return ispEnum;
            }
        }
        return null;
    }
}
