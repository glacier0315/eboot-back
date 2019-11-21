package com.glacier.sys.service.impl;

import com.glacier.common.utils.IdGen;
import com.glacier.sys.dao.ConfigDao;
import com.glacier.sys.entity.Config;
import com.glacier.sys.service.ConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-21 17:37
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigDao configDao;

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Config record) {
        if (record.newRecord()) {
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
}
