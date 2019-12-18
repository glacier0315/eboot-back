package com.glacier.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.glacier.security.util.SecurityUtils;
import com.glacier.sys.controller.LogController;
import com.glacier.sys.service.LogService;
import com.glacier.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-18 10:12
 */
@Slf4j
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    /**
     * 单例多线程 开始时间绑定在线程上
     */
    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();
    @Resource
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        startTimeThreadLocal.set(start);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod method = (HandlerMethod) handler;
                String className = method.getBeanType().getName();
                String methodName = method.getMethod().getName();
                Long startTime = startTimeThreadLocal.get();
                //可在此处获取当前用户放日志信息里
                String remoteIp = IpUtils.getRemoteIp(request);
                Long endTime = System.currentTimeMillis();
                String url = request.getServletPath();
                String userId = SecurityUtils.geUserId();

                long time = endTime - startTime;
                if (!className.equals(LogController.class.getName())) {
                    logService.insert(userId, url, remoteIp, request.getMethod(), JSONUtils.toJSONString(request.getParameterMap()), request.getHeader("user-agent"), time);
                }
                log.info("用户 {} 访问 {} ,IP： {}, {}::{}, 耗时: {} ms", userId, url, remoteIp, className, methodName, time);
            }
        } finally {
            //清理开始时间
            startTimeThreadLocal.remove();
        }
    }
}
