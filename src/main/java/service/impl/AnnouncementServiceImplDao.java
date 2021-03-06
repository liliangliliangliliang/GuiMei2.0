package service.impl;

import core.util.BaseDao;
import dao.AllDao;
import dao.impl.AllImplDao;
import pojo.Announcement;
import pojo.Bigclass;
import pojo.Goods;
import pojo.Page;
import service.AnnouncementServiceDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Announcement> annList() {
        List<Announcement> announcements = new ArrayList<Announcement>();
        String sql = "select * from announcement ORDER BY aDate DESC LIMIT 3 ";
        return allDao.anQuery(sql,null);
    }

    public List<Goods> goodsList() {
        List<Goods> goodsList = new ArrayList<Goods>();
        String sql = "SELECT * FROM goods ORDER BY goodsOnShelfDate DESC LIMIT 4";
        ResultSet rs = BaseDao.getQuery(sql,null);
        try {
            while (rs.next()){
                Goods goods = new Goods();
                goods.setGoodsName(rs.getString("goodsName"));
                goods.setGoodsImage(rs.getString("goodsImage"));
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.dbClose();
        }
        return goodsList;
    }
}
