package com.glacier.sys.service.impl;

import com.glacier.common.utils.IdGen;
import com.glacier.sys.dao.DictDao;
import com.glacier.sys.entity.Dict;
import com.glacier.sys.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 字典业务类
 * @date 2019-12-01 21:36
 */
@Slf4j
@Transactional(readOnly = true)
@Service("DictService")
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

    @Override
    public List<Dict> findDictTree() {
        Dict entity = new Dict();
        List<Dict> list = dictDao.findList(entity);
        return this.findDictTree(list);
    }

    /**
     * 组装字典树
     *
     * @param dicts
     * @return
     */
    private List<Dict> findDictTree(List<Dict> dicts) {
        List<Dict> dictList = new ArrayList<>(10);
        //
        if (dicts != null && !dicts.isEmpty()) {
            Iterator<Dict> iterator = dicts.iterator();
            while (iterator.hasNext()) {
                Dict menu = iterator.next();
                if (menu.getParentId() == null || "".equals(menu.getParentId().trim())
                        || "0".equals(menu.getParentId())) {
                    dictList.add(menu);
                    // 删除
                    iterator.remove();
                }
            }
        }
        // 排序
        dictList.sort(Comparator.comparingInt(Dict::getOrderNum));
        // 组装子类菜单
        findChildren(dictList, dicts);
        return dictList;
    }

    /**
     * 递归组装菜单
     *
     * @param dictList 当前顶级父级字典
     * @param dicts    待查询字典
     */
    private void findChildren(List<Dict> dictList, List<Dict> dicts) {
        // 为空则返回
        if (dictList == null || dictList.isEmpty() || dicts == null || dicts.isEmpty()) {
            return;
        }
        for (Dict parent : dictList) {
            List<Dict> children = new ArrayList<>(10);
            Iterator<Dict> iterator = dicts.iterator();
            while (iterator.hasNext()) {
                Dict dict = iterator.next();
                if (parent.getId() != null && parent.getId().equals(dict.getParentId())) {
                    dict.setParentName(parent.getName());
                    dict.setLevel(parent.getLevel() + 1);
                    children.add(dict);
                    iterator.remove();
                }
            }
            children.sort(Comparator.comparingInt(Dict::getOrderNum));
            parent.setChildren(children);
            findChildren(children, dicts);
        }
    }
}
