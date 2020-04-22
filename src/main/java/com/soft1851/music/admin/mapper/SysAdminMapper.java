package com.soft1851.music.admin.mapper;

import com.soft1851.music.admin.entity.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yujie_Zhao
 * @since 2020-04-21
 */
@Mapper
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     *
     * @param name
     * @return
     */
    SysAdmin getSysadminByName(String name);
}
