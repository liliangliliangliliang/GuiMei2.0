package service;

import pojo.All;
import pojo.Orderse;
import pojo.Page;

public interface OrderseServiceDao {

    //分页订单顾客所有信息
    Page<All> ordQuery(int pageSize, int pageNumber,String orderseCusId);
    //分页订单商家所有信息
    Page<All> ordQuerySel(int pageSize, int pageNumber,String goodsSeId);
    //查询单个订单
    All ordQueryById(String id);
    //订单修改
    int ordUpdate(Orderse ord);
    //订单增加
    int ordAdd(Orderse ord);
}
