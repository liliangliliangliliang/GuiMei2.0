package service.impl;

import dao.impl.AllImplDao;
import pojo.Customer;
import pojo.Page;
import service.CustomerServiceDao;

public class CustomerServiceImplDao implements CustomerServiceDao {
    private static CustomerServiceImplDao ourInstance = new CustomerServiceImplDao();
    public static CustomerServiceImplDao getInstance() {
        return ourInstance;
    }
    private CustomerServiceImplDao() {
    }

    AllImplDao allDao= AllImplDao.getInstance();
    String sql;
    String sql2;
    Object[]parameter;

    //查询所有顾客
    public Page<Customer> cusQuery(int pageSize, int pageNumber) {
        sql="select count(1) from customer";
        sql2="select * from customer";
        return allDao.cusQuery(sql,sql2,null,pageSize,pageNumber);
    }
    //级联查询顾客
    public Page<Customer> cusQuery(int pageSize, int pageNumber, String id, String cusName, String cusSex) {
        if(cusName==""){
            if(cusSex==""){
                if(id==""){
                    return cusQuery(pageSize,pageNumber);
                }else {
                    sql="select count(1) from customer where id=?";
                    sql2="select * from customer where id=?";
                    parameter=new Object[]{id};
                }
            }else {
                if(id==""){
                    sql="select count(1) from customer where cusSex=?";
                    sql2="select * from customer where cusSex=?";
                    parameter=new Object[]{cusSex};
                }else {
                    sql="select count(1) from customer where cusSex=? and id=?";
                    sql2="select * from customer where cusSex=? and id=?";
                    parameter=new Object[]{cusSex,id};
                }
            }
        }else {
            if(cusSex==""){
                if(id==""){
                    sql="select count(1) from customer where cusName like ?";
                    sql2="select * from customer where cusName like ?";
                    parameter=new Object[]{"%"+cusName+"%"};
                }else {
                    sql="select count(1) from customer where cusName like ? and id=?";
                    sql2="select * from customer where cusName like ? and id=?";
                    parameter=new Object[]{"%"+cusName+"%",id};
                }
            }else {
                if(id==""){
                    sql="select count(1) from customer where cusName like ? and cusSex=?";
                    sql2="select * from customer where cusName like ? and cusSex=?";
                    parameter=new Object[]{"%"+cusName+"%",cusSex};
                }else {
                    sql="select count(1) from customer where cusName like ? and cusSex=? and id=?";
                    sql2="select * from customer where cusName like ? and cusSex=? and id=?";
                    parameter=new Object[]{"%"+cusName+"%",cusSex,id};
                }
            }
        }
        return allDao.cusQuery(sql,sql2,parameter,pageSize,pageNumber);
    }
    //按ID查一个顾客
    public Customer cusQueryById(String id) {
        sql="select * from customer where id=?";
        parameter=new Object[]{id};
        return allDao.cusQuery(sql,parameter);
    }

    public Customer cusQueryById(String cusName, String cusCode) {
        sql="select * from customer where cusName=? and cusCode=? ";
        parameter=new Object[]{cusName,cusCode};
        return allDao.cusQuery(sql,parameter);
    }

    public Customer cusLogin(String cusLoginName, String cusPassword) {
        sql="select * from customer where cusLoginName=? and cusPassword=?";
        parameter=new Object[]{cusLoginName,cusPassword};
        return allDao.cusQuery(sql,parameter);
    }

    //修改一个顾客信息
    public int cusUpdate(Customer cus) {
        sql="update customer set cusName=?,cusLoginName=?,cusEmail=?,cusSex=?,cusPhoto=?," +
                "cusHobby=?,cusCode=?,cusBirthday=? where id=?" ;
        parameter=new Object[]{cus.getCusName(),cus.getCusLoginName(), cus.getCusEmail(),
                cus.getCusSex(),cus.getCusPhoto(),cus.getCusHobby(),cus.getCusCode(),cus.getCusBirthday(),cus.getId()};
        return allDao.cusUpdate(sql,parameter);
    }

    public int cusUpdatePwd(String id, String cusPassword) {
        sql="update customer set cusPassword=? where id=?" ;
        parameter=new Object[]{cusPassword,id};
        return allDao.cusUpdate(sql,parameter);
    }

    //添加一个顾客
    public int cusAdd(Customer cus) {
        sql="insert into customer values(0,?,?,?,?,?,?,?,?,?)";
        parameter=new Object[]{cus.getCusName(),cus.getCusLoginName(),cus.getCusPassword(),
                cus.getCusEmail(),cus.getCusSex(),cus.getCusPhoto(),cus.getCusHobby(),cus.getCusCode(),cus.getCusBirthday()};
        return allDao.cusUpdate(sql,parameter);
    }
    //根据ID删除顾客
    public int cusDelById(String id) {
        sql="delete from customer where id=?";
        parameter=new Object[]{id};
        return allDao.cusUpdate(sql,parameter);
    }
}
