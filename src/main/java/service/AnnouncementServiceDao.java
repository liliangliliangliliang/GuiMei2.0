package service;

import pojo.Announcement;
import pojo.Page;

public interface AnnouncementServiceDao {

    //分页查所有公告
    Page<Announcement> anQuery(int pageSize, int pageNumber);
    //按ID查公告
    Announcement anQuery(String id);
    //公告修改
    int anUpdate(Announcement an);
    //公告增加
    int anAdd(Announcement an);
    //公告删除
    int anDel(String id);
    //公共标题是否相同
    Boolean anName(String aTitle);
}
