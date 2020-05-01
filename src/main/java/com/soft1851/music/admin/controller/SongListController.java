package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SongList;
import com.soft1851.music.admin.service.SongListService;
import org.springframework.web.bind.annotation.*;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yujie_Zhao
 * @since 2020-04-22
 */
@RestController
@RequestMapping("/songList")
public class SongListController {
    @Resource
    private SongListService songListService;

    @GetMapping(value = "/all")
    public List<SongList> getSongList() {
        return songListService.getTopSongList();
    }

    @GetMapping(value = "/export")
    public void export() {
        songListService.exportData();
    }

    /**
     * 修改歌单信息
     * @param songList
     * @return
     */
    @PutMapping("/update")
    ResponseResult updateSongList(@RequestBody @Valid SongList songList){
        return songListService.updateSongList(songList);
    }

    @DeleteMapping("/batchDelete")
    ResponseResult batchDeleteById(@Param("ids") String ids){
        return songListService.batchDeleteById(ids);
    }

}
