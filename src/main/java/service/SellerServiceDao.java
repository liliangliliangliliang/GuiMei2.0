package service;

import pojo.Page;
import pojo.Seller;

import java.util.List;

public interface SellerServiceDao {

    //分页查所有商家
    Page<Seller> selQuery( int pageSize, int pageNumber);
    //添加商家
    int selAdd(Seller sel);
    //删除商家
    int selDel(String id);
    //修改商家
    int selUpdate(Seller sel);
    //修改商家密码
    int selUpdatePwd(Seller sel);
    //查看商家是否 重名
    Boolean selQueryName(String sellerName);
    //查看所有商家
    List<Seller> selQuery();
    //按ID查一个商家
    Seller selQueryBuId(String id);
    //商家登录
    Seller selLogin(String sellerUser,String sellerPassword);
}
