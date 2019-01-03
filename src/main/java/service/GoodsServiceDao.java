package service;

import pojo.All;
import pojo.Goods;
import pojo.Page;

public interface GoodsServiceDao {

    //分页显示商品信息降序
    Page<All> goodsQuery(int pageSize, int pageNumber);
    //分页按ID显示商品信息降序
    Page<All> goodsQuery(int pageSize, int pageNumber,String id );
    //分页查询商品级联信息
    Page<All> goodsQuery(int pageSize, int pageNumber,String goodsName,String sellerName,String smallName);
    //分页按ID查询商品级联信息
    Page<All> goodsQuery(int pageSize, int pageNumber,String goodsName,String sellerName,String smallName,String id);
    //按ID查一个商品
    All goodsQuery(String id);
    //修改商品信息
    int goodsUpdate(Goods goods);
    //添加
    int goodsAdd(Goods goods);
    //删除
    int goodsDel(String id);
    //重名
    Boolean goodsName(String goodsName,String goodsSeId);
}
