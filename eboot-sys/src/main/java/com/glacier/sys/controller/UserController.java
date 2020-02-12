package com.glacier.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.http.HttpResult;
import com.glacier.common.core.page.PageRequest;
import com.glacier.sys.entity.User;
import com.glacier.sys.entity.dto.IdDto;
import com.glacier.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 用户管理
 * @date 2019-08-04 22:13
 */
@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("findPage")
    public HttpResult<Page<User>> findPage(@RequestBody PageRequest<User> pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("save")
    public HttpResult<Integer> save(@RequestBody User user) {
        return HttpResult.ok(userService.save(user));
    }

    /**
     * 删除指定用户
     *
     * @param idDtos
     * @return
     */
    @PostMapping("delete")
    public HttpResult<Integer> delete(@RequestBody List<IdDto> idDtos) {
        return HttpResult.ok(userService.batchDelete(idDtos));
    }
}
