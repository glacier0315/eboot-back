package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.utils.IdGen;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.dao.LogDao;
import com.glacier.sys.entity.Log;
import com.glacier.sys.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-18 15:29
 */
@Slf4j
@Service("SysLogService")
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    public PageInfo<Log> findPage(PageRequest<Log> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Log> list = logDao.findList(pageRequest.getParams());
        return new PageInfo<>(list);
    }

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Override
    public int insert(Log record) {
        // 生成id
        record.setId(IdGen.uuid());
        return logDao.insert(record);
    }

    /**
     * 异步调用保存
     *
     * @param userId
     * @param url
     * @param ip
     * @param method
     * @param params
     * @param userAgent
     * @param useTime
     * @return
     */
    @Async
    @Override
    public void insert(String userId, String url, String ip, String method, String params, String userAgent, long useTime) {
        Log log = new Log();
        // 生成id
        log.setId(IdGen.uuid());
        log.setUserId(userId);
        log.setIp(ip);
        log.setMethod(method);
        log.setParams(params);
        log.setUrl(url);
        log.setUserAgent(userAgent);
        log.setUseTime(useTime);
        log.setCreateDate(Calendar.getInstance().getTime());
        logDao.insert(log);
    }
}
