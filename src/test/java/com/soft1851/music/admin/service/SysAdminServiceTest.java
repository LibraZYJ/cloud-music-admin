package com.soft1851.music.admin.service;

import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SysAdminServiceTest {
    @Resource
    private SysAdminService sysAdminService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder().name("ZYJ").password("123456").build();
        System.out.println(sysAdminService.login(loginDto));
    }

    @Test
    void selectByName() {
        System.out.println(sysAdminService.getAdminAndRolesByName("ZYJ"));
    }

    @Test
    void updateSysAdmin() {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setId("22516FB6A9D389D7FC21420806150A7B");
        sysAdmin.setName("taoranran");
        sysAdmin.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        sysAdmin.setAvatar("测试头像");
        sysAdminService.updateSysAdmin(sysAdmin);
    }
}