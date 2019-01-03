package dao;

import pojo.*;

import java.util.List;
import java.util.Map;

public interface AllDao {
    //分页查询顾客
    Page<Customer> cusQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //查询单个顾客
    Customer cusQuery(String sql,Object[]parameter);
    //顾客增删改
    int cusUpdate(String sql,Object[]parameter);





    //查询单个管理员
    Superuser userQuery(String sql,Object[]parameter);
    //管理员增删改
    int userUpdate(String sql,Object[]parameter);




    //分页查询所有大分类
    Page<Bigclass> bigQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //查询单个大分类
    List<Bigclass> bigQuery(String sql,Object[]parameter);
    //大分类增删改
    int bigUpdate(String sql,Object[]parameter);




    //查询名字是否重复
    Boolean QueryName(String sql,Object[]parameter);





    //分页查询小分类降序
    Page<All> smallQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //查询单个小分类
    List<All> smallQuery(String sql,Object[]parameter);
    //小分类增删改
    int smallUpdate(String sql,Object[]parameter);





    //分页查询商品降序
    Page<All> goodsQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //查商品
    List<All> goodsQuery(String sql,Object[]parameter);
    //商品增删改
    int goodsUpdate(String sql,Object[]parameter);






    //分页查询商家降序
    Page<Seller> selQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //商家增删改
    int selUpdate(String sql,Object[]parameter);
    //商家查询
    List<Seller> selQuery(String sql,Object[]parameter);





    //折扣查询
    List<Discount> disQuery(String sql,Object[]parameter);





    //分页查所有公告
    Page<Announcement> anQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //查询公告
    List<Announcement> anQuery(String sql,Object[]parameter);
    //公告增删改
    int anUpdate(String sql,Object[]parameter);






    //分页查购物车所有信息
    Page<All> shopQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //查购物车所有信息
    List<All> shopQueryAll(String sql,Object[]parameter);
    //查询购物车
    List<Shoppingcar> shopQuery(String sql,Object[]parameter);
    //购物车增删改
    int shopUpdate(String sql,Object[]parameter);






    //分页订单所有信息
    Page<All> ordQuery(String sql,String sql2,Object[]parameter, int pageSize,int pageNumber);
    //查询单个订单
    All ordQueryById(String sql,Object[]parameter);
    //订单增删改
    int ordUpdate(String sql,Object[]parameter);





}
