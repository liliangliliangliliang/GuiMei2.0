package service.impl;

import dao.impl.AllImplDao;
import pojo.All;
import pojo.Goods;
import pojo.Page;
import service.GoodsServiceDao;

public class GoodsServiceImplDao implements GoodsServiceDao {
    private static GoodsServiceImplDao ourInstance = new GoodsServiceImplDao();
    public static GoodsServiceImplDao getInstance() {
        return ourInstance;
    }
    private GoodsServiceImplDao() {
    }
    AllImplDao allDao= AllImplDao.getInstance();
    String sql;
    String sql2;
    Object[]parameter;


    public Page<All> goodsQuery(int pageSize, int pageNumber) {
        sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId";
        sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId";
        return allDao.goodsQuery(sql,sql2,null,pageSize,pageNumber);
    }

    public Page<All> goodsQuery(int pageSize, int pageNumber, String id) {
        sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and seller.id=? ";
        sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and seller.id=? ";
        Object[]parameter={id};
        return allDao.goodsQuery(sql,sql2,parameter,pageSize,pageNumber);
    }

    public Page<All> goodsQuery(int pageSize, int pageNumber, String goodsName, String sellerName, String smallName) {
        if(goodsName==""){
            if(sellerName==""){
                if(smallName==""){
                    return goodsQuery(pageSize,pageNumber);
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=?";
                    parameter=new Object[]{smallName};
                }
            }else {
                if(smallName==""){
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=?";
                    parameter=new Object[]{sellerName};
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=?";
                    parameter=new Object[]{smallName,sellerName};
                }
            }
        }else {
            if(sellerName==""){
                if(smallName==""){
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and goodsName like ?";
                    parameter=new Object[]{"%"+goodsName+"%"};
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and goodsName like ?";
                    parameter=new Object[]{smallName,"%"+goodsName+"%"};
                }
            }else {
                if(smallName==""){
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=? and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=? and goodsName like ?";
                    parameter=new Object[]{sellerName,"%"+goodsName+"%"};
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=? and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=? and goodsName like ?";
                    parameter=new Object[]{smallName,sellerName,"%"+goodsName+"%"};
                }
            }
        }
        return allDao.goodsQuery(sql,sql2,parameter,pageSize,pageNumber);
    }

    public Page<All> goodsQuery(int pageSize, int pageNumber, String goodsName, String sellerName, String smallName, String id) {
        if(goodsName==""){
            if(sellerName==""){
                if(smallName==""){
                    return goodsQuery(pageSize,pageNumber,id);
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=?";
                    parameter=new Object[]{smallName,id};
                }
            }else {
                if(smallName==""){
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=?";
                    parameter=new Object[]{sellerName,id};
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=?";
                    parameter=new Object[]{smallName,sellerName,id};
                }
            }
        }else {
            if(sellerName==""){
                if(smallName==""){
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and goodsName like ?";
                    parameter=new Object[]{"%"+goodsName+"%",id};
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and goodsName like ?";
                    parameter=new Object[]{smallName,"%"+goodsName+"%",id};
                }
            }else {
                if(smallName==""){
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=? and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and sellerName=? and goodsName like ?";
                    parameter=new Object[]{sellerName,"%"+goodsName+"%",id};
                }else {
                    sql="select count(1) from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=? and goodsName like ?";
                    sql2="select goods.*,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and smallName=? and sellerName=? and goodsName like ?";
                    parameter=new Object[]{smallName,sellerName,"%"+goodsName+"%",id};
                }
            }
        }
        return allDao.goodsQuery(sql+" and seller.id=? ",sql2+" and seller.id=? ",parameter,pageSize,pageNumber);
    }

    public All goodsQuery(String id) {
        String sql="select goods.*,seller.id,smallclass.id,discount.id,sellerName,smallName,discRate from goods,discount,seller,smallclass where smallclass.id=goodsSmalId and seller.id=goodsSeId and discount.id=goodsDiscId and goods.id=?";
        Object[]parameter={id};
        return allDao.goodsQuery(sql,parameter).get(0);
    }

    public int goodsUpdate(Goods goods) {
        String sql="update goods set goodsName=?,goodsSmalId=?,goodsMoney=?,goodsNumber=?," +
                "goodsCarriage=?,goodsType=?,goodsSeId=?,goodsDiscId=? where id=?";
        Object[]parameter={goods.getGoodsName(),goods.getGoodsSmalId(),goods.getGoodsMoney(),
        goods.getGoodsNumber(),goods.getGoodsCarriage(),goods.getGoodsType(),
        goods.getGoodsSeId(),goods.getGoodsDiscId(),goods.getId()};
        return allDao.goodsUpdate(sql,parameter);
    }

    public int goodsAdd(Goods goods) {
        String sql="insert into goods values(0,?,?,?,?,?,?,?,?,?)";
        Object[]parameter={goods.getGoodsName(),goods.getGoodsSmalId(),goods.getGoodsMoney(),
                goods.getGoodsNumber(),goods.getGoodsImage(),goods.getGoodsCarriage(),goods.getGoodsType(),
                goods.getGoodsSeId(),goods.getGoodsDiscId()};
        return allDao.goodsUpdate(sql,parameter);
    }

    public int goodsDel(String id) {
        String sql="delete from goods where id=?";
        Object[]parameter={id};
        return allDao.goodsUpdate(sql,parameter);
    }

    public Boolean goodsName(String goodsName, String goodsSeId) {
        String sql="select goodsName from goods where goodsName=? and goodsSeId=?";
        Object[]parameter={goodsName,goodsSeId};
        if(goodsSeId!=null && goodsSeId.length()>0 && goodsName!=null && goodsName.length()>0){
            return allDao.QueryName(sql,parameter);
        }
        return true;
    }
}
