package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SongList;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yujie_Zhao
 * @since 2020-04-22
 */
public interface SongListService extends IService<SongList> {
    /**
     * 热门歌单
     *
     * @return
     */
    List<SongList> getTopSongList();

    /**
     * 导出歌单
     */
    void exportData();

    /**
     *修改歌单数据
     * @param songList
     * @return
     */
    ResponseResult updateSongList(SongList songList);

    /**
     * 批量删除
     * @param idLists
     * @return
     */
    ResponseResult batchDeleteById(String idLists);

}
