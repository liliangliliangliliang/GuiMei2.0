package service.impl;

import dao.impl.AllImplDao;
import pojo.All;
import pojo.Orderse;
import pojo.Page;
import service.OrderseServiceDao;

public class OrderseServiceImplDao implements OrderseServiceDao {
    private static OrderseServiceImplDao ourInstance = new OrderseServiceImplDao();
    public static OrderseServiceImplDao getInstance() {
        return ourInstance;
    }
    private OrderseServiceImplDao() {
    }
    AllImplDao allDao=AllImplDao.getInstance();


    public Page<All> ordQuery(int pageSize, int pageNumber,String orderseCusId) {
        String sql="select count(1) from orderse,goods,customer where orderseGoodsId=goods.id and orderseCusId=customer.id and customer.id=?";
        String sql2="select orderse.*,goodsName,cusName from orderse,goods,customer where orderseGoodsId=goods.id and orderseCusId=customer.id and customer.id=?";
        Object[]parameter={orderseCusId};
        return allDao.ordQuery(sql,sql2,parameter,pageSize,pageNumber);
    }

    public Page<All> ordQuerySel(int pageSize, int pageNumber, String goodsSeId) {
        String sql="select count(1) from orderse,goods,customer where orderseGoodsId=goods.id and orderseCusId=customer.id and goodsSeId=?";
        String sql2="select orderse.*,goodsName,cusName from orderse,goods,customer where orderseGoodsId=goods.id and orderseCusId=customer.id and goodsSeId=?";
        Object[]parameter={goodsSeId};
        return allDao.ordQuery(sql,sql2,parameter,pageSize,pageNumber);
    }

    public All ordQueryById(String id) {
        String sql="select orderse.*,goodsName,cusName from orderse,goods,customer where orderseGoodsId=goods.id and orderseCusId=customer.id and orderse.id=?";
        Object[]parameter={id};
        return allDao.ordQueryById(sql,parameter);
    }

    public int ordUpdate(Orderse ord) {
        String sql="update orderse set orderseAddress=?,orderseMoney=?,orderseStatus=? where id=?";
        Object[]parameter={ord.getOrderseAddress(),ord.getOrderseMoney(),ord.getOrderseStatus(),ord.getId()};
        return allDao.ordUpdate(sql,parameter);
    }

    public int ordAdd(Orderse ord) {
        String sql="insert into orderse values(0,?,?,?,?,?,0)";
        Object[]parameter={ord.getOrderseGoodsId(),ord.getOrderseCusId(),ord.getOrderseDate(),ord.getOrderseAddress(),ord.getOrderseMoney()};
        return allDao.ordUpdate(sql,parameter);
    }
}
