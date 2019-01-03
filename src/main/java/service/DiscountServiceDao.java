package service;

import pojo.Discount;

import java.util.List;

public interface DiscountServiceDao {


    //查询折扣
    List<Discount> disQuery();
}
