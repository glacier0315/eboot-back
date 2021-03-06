package com.glacier.sys.service.impl;

import com.glacier.sys.entity.Dict;
import com.glacier.sys.entity.dto.IdDto;
import com.glacier.sys.mapper.DictMapper;
import com.glacier.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author glacier
 * @version 1.0
 * @description 字典业务类
 * @date 2019-12-01 21:36
 */
@Slf4j
@Transactional(readOnly = true)
@Service("DictService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DictServiceImpl implements DictService {

    private final DictMapper dictMapper;

    @Transactional(rollbackFor = {})
    @Override
    public int save(Dict record) {
        int update = 0;
        if (record.getId() != null && !record.getId().isEmpty()) {
            update = dictMapper.updateById(record);
        } else {
            update = dictMapper.insert(record);
        }
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idDtos
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdDto> idDtos) {
        if (idDtos != null && !idDtos.isEmpty()) {
            List<String> list = idDtos.stream()
                    .map(IdDto::getId)
                    .collect(Collectors.toList());
            return dictMapper.deleteBatchIds(list);
        }
        return 0;
    }

    @Override
    public List<Dict> findDictTree() {
        List<Dict> list = dictMapper.selectList(null);
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
                    // 处理层级
                    if (parent.getLevel() == null) {
                        parent.setLevel(0);
                    }
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
