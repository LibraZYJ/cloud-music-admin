package com.soft1851.music.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName LoginDto
 * @Description TODO
 * @Date 2020/4/21  14:14
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String name;
    private String password;
    private String verifyCode;
}
