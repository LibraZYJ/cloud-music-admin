package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SongList;
import com.soft1851.music.admin.mapper.SongListMapper;
import com.soft1851.music.admin.service.SongListService;
import com.soft1851.music.admin.util.ExcelConsumer;
import com.soft1851.music.admin.util.ExportDataAdapter;
import com.soft1851.music.admin.util.ThreadPool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mq_xu
 * @since 2020-04-22
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {
    @Resource
    private SongListMapper songListMapper;

    @Override
    public List<SongList> getTopSongList() {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("song_count", 1000);
        return songListMapper.selectList(queryWrapper);
    }

    @SneakyThrows
    @Override
    public void exportData() {
        String excelPath = "D:\\temp\\songList.xlsx";
        //导出excel对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        //数据缓冲
        ExportDataAdapter<SongList> exportDataAdapter = new ExportDataAdapter<>();
        //线程同步对象
        CountDownLatch latch = new CountDownLatch(2);
        //启动线程获取数据(生产者)
        ThreadPool.getExecutor().submit(() -> produceExportData(exportDataAdapter, latch));
        //启动线程导出数据（消费者）
        ThreadPool.getExecutor().submit(() -> new ExcelConsumer<>(SongList.class, exportDataAdapter, sxssfWorkbook, latch, "歌单数据").run());
        latch.await();
        //使用字节流写数据
        OutputStream outputStream = new FileOutputStream(excelPath);
        sxssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public ResponseResult updateSongList(SongList songList) {
        SongList songList1 = songListMapper.selectById(songList.getSongListId());
        songList1.setSongListName(songList.getSongListName());
        songList1.setThumbnail(songList.getThumbnail());
        songList1.setType(songList.getType());
        songListMapper.updateById(songList1);
        return ResponseResult.success(songList1);
    }

    /**
     * 批量删除
     * @param idLists
     * @return
     */
    @Override
    public ResponseResult batchDeleteById(String idLists) {
        List<String> allIdList = new ArrayList<>();
        String[] ids = idLists.split(",");
        List<String> allIds = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            allIds.add(ids[i]);
        }
        songListMapper.deleteBatchIds(allIds);
        return ResponseResult.success();
    }

    /**
     * 生产者生产数据
     *
     * @param exportDataAdapter
     * @param latch
     */
    private void produceExportData(ExportDataAdapter<SongList> exportDataAdapter, CountDownLatch latch) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("song_count");
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        songLists.forEach(exportDataAdapter::addData);
        log.info("数据生产完成");
        //数据生产结束
        latch.countDown();
    }
}
