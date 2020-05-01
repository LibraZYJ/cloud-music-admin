package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.service.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * SysAdminController
 * @author Yujie_Zhao
 * @since 2020-04-22
 */
@RestController
@RequestMapping(value = "/sysAdmin")
@Slf4j
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 管理员登录
     * @param loginDto
     * @return String
     */
    @PostMapping("/login")
    public Map login(@RequestBody LoginDto loginDto) {
        log.info(loginDto.toString());
        return sysAdminService.login(loginDto);
    }
    /**
     * 修改个人信息
     * @param sysAdmin
     * @return
     */
    @PutMapping("/update")
    ResponseResult updateSysAdmin(@RequestBody @Valid SysAdmin sysAdmin){
        return sysAdminService.updateSysAdmin(sysAdmin);
    }
}