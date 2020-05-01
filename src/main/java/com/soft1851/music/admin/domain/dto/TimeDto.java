package com.soft1851.music.admin.domain.dto;

import lombok.Data;

/**
 * @author Yujie_Zhao
 * @ClassName TimeDto
 * @Description TODO
 * @Date 2020/4/25  12:41
 * @Version 1.0
 **/
@Data
public class TimeDto {
    private String yesterday;
    private String week;
    private String month;
    private String quarter;
}