package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.utils.IdGen;
import com.glacier.sys.dao.ConfigDao;
import com.glacier.sys.entity.Config;
import com.glacier.sys.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-21 17:37
 */
@Slf4j
@Transactional(readOnly = true)
@Service("ConfigService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfigServiceImpl implements ConfigService {

    private final ConfigDao configDao;

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Config record) {
        if (record.isNewRecord()) {
            if (!record.isNewRecord()) {
                record.setId(IdGen.uuid());
            }
            return configDao.insert(record);
        } else {
            return configDao.update(record);
        }
    }

    /**
     * 删除
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(Config record) {
        return configDao.delete(record);
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<Config> list) {
        int delCount = 0;
        if (list != null && !list.isEmpty()) {
            for (Config entity : list) {
                delCount += configDao.delete(entity);
            }
        }
        return delCount;
    }

    /**
     * 根据id 查询
     *
     * @param id
     * @return
     */
    @Override
    public Config findById(String id) {
        return configDao.findById(id);
    }

    /**
     * 查询列表
     *
     * @param record
     * @return
     */
    @Override
    public List<Config> findList(Config record) {
        return configDao.findList(record);
    }

    /**
     * 分页查找
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<Config> findPage(PageRequest<Config> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Config> list = configDao.findList(pageRequest.getParams());
        return new PageInfo<>(list);
    }
}
