package service.impl;

import dao.impl.AllImplDao;
import pojo.Page;
import pojo.Seller;
import service.SellerServiceDao;

import java.util.List;

public class SellerServiceImplDao implements SellerServiceDao {
    private static SellerServiceImplDao ourInstance = new SellerServiceImplDao();
    public static SellerServiceImplDao getInstance() {
        return ourInstance;
    }
    private SellerServiceImplDao() {
    }
    AllImplDao allDao= AllImplDao.getInstance();


    public Page<Seller> selQuery(int pageSize, int pageNumber) {
        String sql="select count(1) from seller";
        String sql2="select * from seller";
        return allDao.selQuery(sql,sql2,null,pageSize,pageNumber);
    }

    public int selAdd(Seller sel) {
        String sql="insert into seller values(0,?,?,?,?,?,?,?,?,?)";
        Object[]parameter={sel.getSellerName(),sel.getSellerUser(),sel.getSellerPassword(),
        sel.getSellerSex(),sel.getSellerBirthday(),sel.getSellerIdCard(),sel.getSellerEmail(),
        sel.getSellerTel(),sel.getSellerAddress()};
        return  allDao.selUpdate(sql,parameter);
    }

    public int selDel(String id) {
        String sql="delete from seller where id=? and id not in(select goodsSeId from goods)";
        Object[]parameter={id};
        return  allDao.selUpdate(sql,parameter);
    }

    public int selUpdate(Seller sel) {
        String sql="update seller set sellerName=?,sellerUser=?,sellerSex=?," +
                "sellerBirthday=?,sellerIdCard=?,sellerEmail=?,sellerTel=?,sellerAddress=? where id=?";
        Object[]parameter={sel.getSellerName(),sel.getSellerUser(), sel.getSellerSex(),
                sel.getSellerBirthday(),sel.getSellerIdCard(),sel.getSellerEmail(),
                sel.getSellerTel(),sel.getSellerAddress(),sel.getId()};
        return  allDao.selUpdate(sql,parameter);
    }

    public int selUpdatePwd(Seller sel) {
        String sql="update seller set sellerPassword=? where id=?";
        Object[]parameter={sel.getSellerPassword(),sel.getId()};
        return  allDao.selUpdate(sql,parameter);
    }

    public Boolean selQueryName(String sellerName) {
        String sql="select sellerName from seller where sellerName=?";
        Object[]parameter={sellerName};
        if(sellerName!=null && sellerName.length()>0){
            return allDao.QueryName(sql,parameter);
        }
        return true;
    }

    public List<Seller> selQuery() {
        String sql="select * from seller";
        return allDao.selQuery(sql,null);
    }

    public Seller selQueryBuId(String id) {
        String sql="select * from seller where id=?";
        Object[]parameter={id};
        return allDao.selQuery(sql,parameter).get(0);
    }

    public Seller selLogin(String sellerUser, String sellerPassword) {
        String sql="select * from seller where sellerUser=? and sellerPassword=?";
        Object[]parameter={sellerUser,sellerPassword};
        List<Seller> list=allDao.selQuery(sql,parameter);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
