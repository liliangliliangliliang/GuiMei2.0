package service.impl;

import dao.AllDao;
import dao.impl.AllImplDao;
import pojo.Announcement;
import pojo.Page;
import service.AnnouncementServiceDao;

public class AnnouncementServiceImplDao implements AnnouncementServiceDao {
    private static AnnouncementServiceImplDao ourInstance = new AnnouncementServiceImplDao();
    public static AnnouncementServiceImplDao getInstance() {
        return ourInstance;
    }
    private AnnouncementServiceImplDao() {
    }
    AllImplDao allDao=AllImplDao.getInstance();


    public Page<Announcement> anQuery(int pageSize, int pageNumber) {
        String sql="select count(1) from announcement";
        String sql2="select * from announcement";
        System.out.println(sql2);
        return allDao.anQuery(sql,sql2,null,pageSize,pageNumber);
    }

    public Announcement anQuery(String id) {
        String sql="select * from announcement where id=?";
        Object[]parameter={id};
        return allDao.anQuery(sql,parameter).get(0);
    }

    public int anUpdate(Announcement an) {
        String sql="update announcement set aTitle=?,aText=?,aDate=? where id=?";
        Object[]parameter={an.getATitle(),an.getAText(),an.getADate(),an.getId()};
        return allDao.anUpdate(sql,parameter);
    }

    public int anAdd(Announcement an) {
        String sql="insert into announcement values(0,?,?,?)";
        Object[]parameter={an.getATitle(),an.getAText(),an.getADate()};
        return allDao.anUpdate(sql,parameter);
    }

    public int anDel(String id) {
        String sql="delete from announcement where id=?";
        Object[]parameter={id};
        return allDao.anUpdate(sql,parameter);
    }

    public Boolean anName(String aTitle) {
        String sql="select aTitle from announcement where aTitle=?";
        Object []parameter={aTitle};
        if(aTitle!=null && aTitle.length()>0){
            return allDao.QueryName(sql,parameter);
        }
        return true;
    }
}
