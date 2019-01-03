package service;

import pojo.All;
import pojo.Page;
import pojo.Shoppingcar;

public interface ShoppingcarServiceDao {

    //分页查购物车所有信息
    Page<All> shopQuery(int pageSize, int pageNumber,String scCusId);
    //按用户ID与商品ID查看信息
    Shoppingcar  shopQuery(long scCusId,String scGoodsId);
    //按ID查看信息
    All  shopQuery(String id);
    //按ID查看信息
    Shoppingcar  shopQuery(long id);
    //购物车改
    int shopUpdate(Shoppingcar shop);
    //购物车增
    int shopAdd(Shoppingcar shop);
    //购物车删
    int shopDel(String id);
}
