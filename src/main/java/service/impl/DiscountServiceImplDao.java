package service.impl;

import dao.impl.AllImplDao;
import pojo.Discount;
import service.DiscountServiceDao;

import java.util.List;

public class DiscountServiceImplDao implements DiscountServiceDao {
    private static DiscountServiceImplDao ourInstance = new DiscountServiceImplDao();
    public static DiscountServiceImplDao getInstance() {
        return ourInstance;
    }
    private DiscountServiceImplDao() {
    }
    AllImplDao allDao=AllImplDao.getInstance();


    public List<Discount> disQuery() {
        String sql="select * from discount";
        return allDao.disQuery(sql,null);
    }
}
