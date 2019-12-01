package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.utils.IdGen;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.dao.DictDao;
import com.glacier.sys.entity.Dict;
import com.glacier.sys.service.DictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 字典业务类
 * @date 2019-12-01 21:36
 */
@Service("dictService")
public class DictServiceImpl implements DictService {
    @Resource
    private DictDao dictDao;

    @Transactional(rollbackFor = {})
    @Override
    public int save(Dict record) {
        if (record.newRecord()) {
            if (!record.isNewRecord()) {
                record.setId(IdGen.uuid());
            }
            return dictDao.insert(record);
        } else {
            return dictDao.update(record);
        }
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(Dict record) {
        return dictDao.delete(record);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<Dict> list) {
        int delCount = 0;
        if (list != null && !list.isEmpty()) {
            for (Dict entity : list) {
                delCount += dictDao.delete(entity);
            }
        }
        return delCount;
    }

    @Override
    public Dict findById(String id) {
        return dictDao.findById(id);
    }

    @Override
    public List<Dict> findList(Dict record) {
        return dictDao.findList(record);
    }

    /**
     * 查找字典目录
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<Dict> findPage(PageRequest<Dict> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        Dict dict = pageRequest.getParams();
        // 目录
        dict.setType("1");
        List<Dict> list = dictDao.findList(dict);
        return new PageInfo<>(list);
    }
}
