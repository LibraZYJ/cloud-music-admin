package com.soft1851.music.admin.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yujie_Zhao
 * @ClassName PageDto
 * @Description TODO
 * @Date 2020/4/25  9:58
 * @Version 1.0
 **/
@Data
@Builder
public class PageDto {
    private Object field;
    private int currentPage;
    private int pageSize;
}
