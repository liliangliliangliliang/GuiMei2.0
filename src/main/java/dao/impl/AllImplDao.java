package dao.impl;

import core.util.BaseDao;
import core.util.PageUtil;
import dao.AllDao;
import pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllImplDao extends BaseDao implements AllDao {
    private static AllImplDao ourInstance = new AllImplDao();
    public static AllImplDao getInstance() {
        return ourInstance;
    }
    private AllImplDao() {
    }


    //顾客
    public Page<Customer> cusQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<Customer> page=new Page<Customer>();
        List<Customer> list=new ArrayList<Customer>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2,pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                Customer cus=new Customer();
                cus.setId(rs.getLong("id"));
                cus.setCusName(rs.getString("cusName"));
                cus.setCusLoginName(rs.getString("cusLoginName"));
                cus.setCusPassword(rs.getString("cusPassword"));
                cus.setCusEmail(rs.getString("cusEmail"));
                cus.setCusSex(rs.getString("cusSex"));
                cus.setCusPhoto(rs.getString("cusPhoto"));
                cus.setCusHobby(rs.getString("cusHobby"));
                cus.setCusCode(rs.getString("cusCode"));
                cus.setCusBirthday(rs.getDate("cusBirthday"));
                list.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public Customer cusQuery(String sql,Object[]parameter) {
        Customer ctm=new Customer();
        ResultSet rs=getQuery(sql,parameter);
        try {
           while ( rs.next()){
               ctm.setId(rs.getLong("id"));
               ctm.setCusName(rs.getString("cusName"));
               ctm.setCusLoginName(rs.getString("cusLoginName"));
               ctm.setCusPassword(rs.getString("cusPassword"));
               ctm.setCusEmail(rs.getString("cusEmail"));
               ctm.setCusSex(rs.getString("cusSex"));
               ctm.setCusPhoto(rs.getString("cusPhoto"));
               ctm.setCusHobby(rs.getString("cusHobby"));
               ctm.setCusCode(rs.getString("cusCode"));
               ctm.setCusBirthday(rs.getDate("cusBirthday"));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return ctm;
    }

    public int cusUpdate(String sql,Object[]parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    //管理员
    public Superuser userQuery(String sql, Object[] parameter) {
        ResultSet rs=getQuery(sql,parameter);
        Superuser user=new Superuser();
        try {
            while (rs.next()){
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setUserImage(rs.getString("userImage"));
                user.setUserStatus(rs.getLong("userStatus"));
                user.setUserId(rs.getString("userID"));
                user.setUserLoginName(rs.getString("userLoginName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return user;
    }

    public int userUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    //大分类
    public Page<Bigclass> bigQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<Bigclass> page=new Page<Bigclass>();
        List<Bigclass> list=new ArrayList<Bigclass>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2,pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                Bigclass big=new Bigclass();
                big.setId(rs.getLong("id"));
                big.setBigName(rs.getString("bigName"));
                big.setBigText(rs.getString("bigText"));
                list.add(big);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public List<Bigclass> bigQuery(String sql, Object[] parameter) {
        List<Bigclass> list=new ArrayList<Bigclass>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                Bigclass big=new Bigclass();
                big.setId(rs.getLong("id"));
                big.setBigName(rs.getString("bigName"));
                big.setBigText(rs.getString("bigText"));
                list.add(big);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public int bigUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    public Boolean QueryName(String sql, Object[] parameter) {
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return false;
    }


    public Page<All> smallQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<All> page=new Page<All>();
        List<All> list=new ArrayList<All>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2,pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                All all=new All();
                Bigclass big=new Bigclass();
                big.setBigName(rs.getString("bigName"));
                all.setBigclass(big);
                all.setId(rs.getLong("id"));
                all.setSmallName(rs.getString("smallName"));
                all.setSmallText(rs.getString("smallText"));
                list.add(all);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public List<All> smallQuery(String sql, Object[] parameter) {
        List<All> list=new ArrayList<All>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                All all=new All();
                Bigclass big=new Bigclass();
                big.setBigName(rs.getString("bigName"));
                big.setId(rs.getLong("bigclass.id"));
                all.setBigclass(big);
                all.setId(rs.getLong("smallclass.id"));
                all.setSmallName(rs.getString("smallName"));
                all.setSmallText(rs.getString("smallText"));
                list.add(all);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public int smallUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    public Page<All> goodsQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<All> page=new Page<All>();
        List<All> list=new ArrayList<All>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2+" order by goods.id desc",pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                All all=new All();
                Goods goods=new Goods();
                goods.setId(rs.getLong("goods.id"));
                goods.setGoodsName(rs.getString("goodsName"));
                goods.setGoodsMoney(rs.getDouble("goodsMoney"));
                goods.setGoodsNumber(rs.getLong("goodsNumber"));
                goods.setGoodsImage(rs.getString("goodsImage"));
                goods.setGoodsCarriage(rs.getDouble("goodsCarriage"));
                goods.setGoodsType(rs.getLong("goodsType"));
                all.setGoods(goods);
                Seller sel=new Seller();
                sel.setSellerName(rs.getString("sellerName"));
                all.setSeller(sel);
                Discount dis=new Discount();
                dis.setDiscRate(rs.getDouble("discRate"));
                all.setDiscount(dis);
                all.setSmallName(rs.getString("smallName"));
                list.add(all);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public List<All> goodsQuery(String sql, Object[] parameter) {
        List<All> list=new ArrayList<All>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                All all=new All();
                Goods goods=new Goods();
                goods.setId(rs.getLong("id"));
                goods.setGoodsName(rs.getString("goodsName"));
                goods.setGoodsMoney(rs.getDouble("goodsMoney"));
                goods.setGoodsNumber(rs.getLong("goodsNumber"));
                goods.setGoodsImage(rs.getString("goodsImage"));
                goods.setGoodsCarriage(rs.getDouble("goodsCarriage"));
                goods.setGoodsType(rs.getLong("goodsType"));
                all.setGoods(goods);
                Seller sel=new Seller();
                sel.setSellerName(rs.getString("sellerName"));
                sel.setId(rs.getLong("seller.id"));
                all.setSeller(sel);
                Discount dis=new Discount();
                dis.setDiscRate(rs.getDouble("discRate"));
                dis.setId(rs.getLong("discount.id"));
                all.setDiscount(dis);
                all.setSmallName(rs.getString("smallName"));
                all.setId(rs.getLong("smallclass.id"));
                list.add(all);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public int goodsUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    public Page<Seller> selQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<Seller> page=new Page<Seller>();
        List<Seller> list=new ArrayList<Seller>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2,pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                Seller sel=new Seller();
                sel.setId(rs.getLong("id"));
                sel.setSellerName(rs.getString("sellerName"));
                sel.setSellerUser(rs.getString("sellerUser"));
                sel.setSellerPassword(rs.getString("sellerPassword"));
                sel.setSellerBirthday(rs.getDate("sellerBirthday"));
                sel.setSellerSex(rs.getString("sellerSex"));
                sel.setSellerIdCard(rs.getString("sellerIdCard"));
                sel.setSellerEmail(rs.getString("sellerEmail"));
                sel.setSellerTel(rs.getString("sellerTel"));
                sel.setSellerAddress(rs.getString("sellerAddress"));
                list.add(sel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public int selUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    public List<Seller> selQuery(String sql, Object[] parameter) {
        List<Seller> list=new ArrayList<Seller>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                Seller sel=new Seller();
                sel.setId(rs.getLong("id"));
                sel.setSellerName(rs.getString("sellerName"));
                sel.setSellerUser(rs.getString("sellerUser"));
                sel.setSellerSex(rs.getString("sellerSex"));
                sel.setSellerPassword(rs.getString("sellerPassword"));
                sel.setSellerBirthday(rs.getDate("sellerBirthday"));
                sel.setSellerIdCard(rs.getString("sellerIdCard"));
                sel.setSellerEmail(rs.getString("sellerEmail"));
                sel.setSellerTel(rs.getString("sellerTel"));
                sel.setSellerAddress(rs.getString("sellerAddress"));
                list.add(sel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public List<Discount> disQuery(String sql, Object[] parameter) {
        List<Discount> list=new ArrayList<Discount>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                Discount dis=new Discount();
                dis.setId(rs.getLong("id"));
                dis.setDiscRate(rs.getDouble("discRate"));
                list.add(dis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public Page<Announcement> anQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<Announcement> page=new Page<Announcement>();
        List<Announcement> list=new ArrayList<Announcement>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2,pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                Announcement an=new Announcement();
                an.setId(rs.getLong("id"));
                an.setATitle(rs.getString("aTitle"));
                an.setAText(rs.getString("aText"));
                an.setADate(rs.getDate("aDate"));
                list.add(an);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public List<Announcement> anQuery(String sql, Object[] parameter) {
        List<Announcement> list=new ArrayList<Announcement>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                Announcement an=new Announcement();
                an.setId(rs.getLong("id"));
                an.setATitle(rs.getString("aTitle"));
                an.setAText(rs.getString("aText"));
                an.setADate(rs.getDate("aDate"));
                list.add(an);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public int anUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    public Page<All> shopQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<All> page=new Page<All>();
        List<All> list=new ArrayList<All>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2,pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                All all=new All();
                Shoppingcar shop=new Shoppingcar();
                shop.setId(rs.getLong("id"));
                shop.setScNumber(rs.getLong("scNumber"));
                all.setShoppingcar(shop);
                Customer cus=new Customer();
                cus.setCusName(rs.getString("cusName"));
                all.setCustomer(cus);
                Goods goods=new Goods();
                goods.setGoodsName(rs.getString("goodsName"));
                all.setGoods(goods);
                list.add(all);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public List<All> shopQueryAll(String sql, Object[] parameter) {
        List<All> list=new ArrayList<All>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                All all=new All();
                Shoppingcar shop=new Shoppingcar();
                shop.setId(rs.getLong("id"));
                shop.setScNumber(rs.getLong("scNumber"));
                all.setShoppingcar(shop);
                Customer cus=new Customer();
                cus.setCusName(rs.getString("cusName"));
                all.setCustomer(cus);
                Goods goods=new Goods();
                goods.setGoodsName(rs.getString("goodsName"));
                all.setGoods(goods);
                list.add(all);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public List<Shoppingcar> shopQuery(String sql, Object[] parameter) {
        List<Shoppingcar> list=new ArrayList<Shoppingcar>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                Shoppingcar shop=new Shoppingcar();
                shop.setId(rs.getLong("id"));
                shop.setScCusId(rs.getLong("scCusId"));
                shop.setScGoodsId(rs.getLong("scGoodsId"));
                shop.setScNumber(rs.getLong("scNumber"));
                list.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return list;
    }

    public int shopUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }

    public Page<All> ordQuery(String sql, String sql2, Object[] parameter, int pageSize, int pageNumber) {
        Page<All> page=new Page<All>();
        List<All> list=new ArrayList<All>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql,parameter));
        ResultSet rs=PageUtil.getPageData(sql2,pageSize,pageNumber,parameter);
        try {
            while (rs.next()){
                All all=new All();
                Orderse ord=new Orderse();
                ord.setId(rs.getLong("id"));
                ord.setOrderseDate(rs.getDate("orderseDate"));
                ord.setOrderseAddress(rs.getString("orderseAddress"));
                ord.setOrderseMoney(rs.getDouble("orderseMoney"));
                ord.setOrderseStatus(rs.getLong("orderseStatus"));
                all.setOrderse(ord);
                Customer cus=new Customer();
                cus.setCusName(rs.getString("cusName"));
                all.setCustomer(cus);
                Goods goods=new Goods();
                goods.setGoodsName(rs.getString("goodsName"));
                all.setGoods(goods);
                list.add(all);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(list);
        return page;
    }

    public All ordQueryById(String sql, Object[] parameter) {
        All all=new All();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while (rs.next()){
                Orderse ord=new Orderse();
                ord.setId(rs.getLong("id"));
                ord.setOrderseDate(rs.getDate("orderseDate"));
                ord.setOrderseAddress(rs.getString("orderseAddress"));
                ord.setOrderseMoney(rs.getDouble("orderseMoney"));
                ord.setOrderseStatus(rs.getLong("orderseStatus"));
                all.setOrderse(ord);
                Customer cus=new Customer();
                cus.setCusName(rs.getString("cusName"));
                all.setCustomer(cus);
                Goods goods=new Goods();
                goods.setGoodsName(rs.getString("goodsName"));
                all.setGoods(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return all;
    }

    public int ordUpdate(String sql, Object[] parameter) {
        int i=getUpdate(sql,parameter);
        dbClose();
        return i;
    }


}
