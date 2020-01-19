package com.cloud.utils;


import com.cloud.entity.vo.ipAddressVo;
import com.cloud.enums.IspEnum;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import com.cloud.config.Constant;

/**
 * @author Lee
 * @date 2020/1/9 23:54
 */
public class HttpContextUtil {

    public static HttpServletRequest getRequest() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    /**
     * 获取IP地址的方法
     *
     * @paramrequest 传一个request对象下来
     * @return
     */
    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader(Constant.HTTP_HEAD);
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getBrowser() {
        HttpServletRequest request = getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    /**
     * getQueryString
     * @return
     */
    public static String getParam() {
        HttpServletRequest request = getRequest();
        return request.getRequestURI();
    }

    /**
     * 更具请求ip获取真实地址
     * @param ip 目标IP地址
     * @return 返回resultVo
     */
    public static String getRealAddress(String ip){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("ak",Constant.BAIDU_MAP_AK);
        params.add("ip","101.231.189.62");
        params.add("coor","bd09ll");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<ipAddressVo> response = client.exchange(Constant.BAIDU_MAP_URL,method,requestEntity, ipAddressVo.class);
        //获取运营商
        String type= response.getBody().getAddress().split("[|]")[4];
        String a=IspEnum.getIspDescByIsp(type).getIspDesc();
        //返回的是省市区的字符串
        return response.getBody().getContent().getAddress_detail().getProvince()+"|"+response.getBody().getContent().getAddress_detail().getCity()+"|"+response.getBody().getContent().getAddress_detail().getDistrict()+"  "+ IspEnum.getIspDescByIsp(type).getIspDesc();
    }

}
