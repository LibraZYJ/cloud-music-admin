package com.soft1851.music.admin.service.impl;

import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.soft1851.music.admin.mapper.SysAdminMapper;
import com.soft1851.music.admin.service.SysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yujie_Zhao
 * @since 2020-04-21
 */
@Service
@Slf4j
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Resource
    private SysAdminMapper sysAdminMapper;

    @Override
    public boolean login(LoginDto loginDto) {
        String name = loginDto.getName();
        String password = loginDto.getPassword();
        SysAdmin sysAdmin;
        sysAdmin = sysAdminMapper.getSysadminByName(name);
        if (sysAdmin != null){
            if (sysAdmin.getPassword().equals(Md5Util.getMd5(password,true,32))){
                log.info("登录成功");
                return true;
            }else{
                log.error("密码错误");
                return false;
            }
        }
        log.error("用户不存在");
        return false;
    }

    @Override
    public SysAdmin getAdmin(String name) {
        Map<String,Object> params = new HashMap<>(8);
        params.put("name",name);
        List<SysAdmin> admins = sysAdminMapper.selectByMap(params);
        if (admins.size() >0){
            return sysAdminMapper.selectByMap(params).get(0);
        }else {
            return null;
        }

    }
}
