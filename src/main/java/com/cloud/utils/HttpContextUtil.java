package com.cloud.utils;

import com.cloud.config.Constant;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
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
    //getQueryString
    public static String getParam() {
        HttpServletRequest request = getRequest();
        return request.getRequestURI();
    }

    /**
     * @content 请求的参数 格式为：name=xxx&pwd=xxx
     * @encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    public static String getAddresses(String ip) {
        // 这里调用pconline的接口
        String urlStr = "http://whois.pconline.sscom.cn/ipJson.jsp";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String encoding = getRequest().getCharacterEncoding();
        String returnStr = getResult(urlStr, "ip="+ip,encoding);
        if (returnStr != null) {
        // 处理返回的省市区信息
            int startIndex = returnStr.indexOf("\"pro");
            int endIndex = returnStr.indexOf(",\"addr");
            if (startIndex > 0 && endIndex > 0 && endIndex > startIndex) {
                returnStr = returnStr.substring(startIndex, endIndex);
                returnStr = returnStr.replaceAll("\"", "").replace("pro:", "")
                        .replace("city:", "").replace("region:", "");
            }
        }
        return returnStr;
    }

    /**
     * @param urlStr
     *            请求的地址
     * @paramcontent
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String ip, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒

            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口

            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(ip);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }
}
