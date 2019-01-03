package service.impl;

import dao.impl.AllImplDao;
import pojo.All;
import pojo.Page;
import pojo.Shoppingcar;
import service.ShoppingcarServiceDao;

import java.util.List;

public class ShoppingcarServiceImplDao implements ShoppingcarServiceDao

{
    private static ShoppingcarServiceImplDao ourInstance = new ShoppingcarServiceImplDao();
    public static ShoppingcarServiceImplDao getInstance() {
        return ourInstance;
    }
    private ShoppingcarServiceImplDao() {
    }
    AllImplDao allDao=AllImplDao.getInstance();


    public Page<All> shopQuery(int pageSize, int pageNumber,String scCusId) {
        String sql="select  count(1) from shoppingcar as s,goods as g,customer as c  where scCusId=c.id and scGoodsId=g.id and scCusId=?";
        String sql2="select  s.id,scNumber,goodsName,cusName from shoppingcar as s,goods as g,customer as c  where scCusId=c.id and scGoodsId=g.id and scCusId=?";
        Object[]parameter={scCusId};
        return allDao.shopQuery(sql,sql2,parameter,pageSize,pageNumber);
    }

    public Shoppingcar shopQuery(long scCusId, String scGoodsId) {
            String sql="select * from shoppingcar where scCusId=? and scGoodsId=?";
            Object[]parameter={scCusId,scGoodsId};
            List<Shoppingcar> list=allDao.shopQuery(sql,parameter);
            if(list.size()>0){
                return list.get(0);
            }
            return null;
    }

    public All shopQuery(String id) {
        String sql="select  s.id,scNumber,goodsName,cusName from shoppingcar as s,goods as g,customer as c  where scCusId=c.id and scGoodsId=g.id and s.id=?";
        Object[]parameter={id};
        return allDao.shopQueryAll(sql,parameter).get(0);
    }

    public Shoppingcar shopQuery(long id) {
        String sql="select * from shoppingcar where id=?";
        Object[]parameter={id};
        List<Shoppingcar> list=allDao.shopQuery(sql,parameter);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public int shopUpdate(Shoppingcar shop) {
        String sql="update shoppingcar set scNumber=? where id=?";
        Object[]parameter={shop.getScNumber(),shop.getId()};
        return allDao.shopUpdate(sql,parameter);
    }

    public int shopAdd(Shoppingcar shop) {
        String sql="insert into shoppingcar values(0,?,?,?)";
        Object[]parameter={shop.getScCusId(),shop.getScGoodsId(),shop.getScNumber()};
        return allDao.shopUpdate(sql,parameter);
    }

    public int shopDel(String id) {
        String sql="delete from shoppingcar where id=?";
        Object[]parameter={id};
        return allDao.shopUpdate(sql,parameter);
    }
}
