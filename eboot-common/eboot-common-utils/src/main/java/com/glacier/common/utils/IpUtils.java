package com.glacier.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-18 10:32
 */
public class IpUtils {

    private IpUtils() {
    }

    /**
     * 从requset  获取访问ip
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String unknown = "unknown";
        String ip1 = "0:0:0:0:0:0:0:1";
        String ip2 = "127.0.0.1";
        String comma = ",";
        // 获取ip
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.isEmpty() || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ip2.equals(ipAddress) || ip1.equals(ipAddress)) {
                //根据网卡取本机配置的IP  
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                }
                if (inet != null) {
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        // 多个路由时，取第一个非unknown的ip
        if (ipAddress != null && ipAddress.indexOf(comma) > 0) {

            String[] arr = ipAddress.split(comma);
            for (String str : arr) {
                if (!unknown.equalsIgnoreCase(str)) {
                    ipAddress = str;
                    break;
                }
            }
        }
        return ipAddress;
    }
}
