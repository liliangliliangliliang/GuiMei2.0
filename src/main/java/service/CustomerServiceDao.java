package service;

import pojo.Customer;
import pojo.Page;

public interface CustomerServiceDao {
    //查询所有顾客
    Page<Customer> cusQuery( int pageSize, int pageNumber);
    //级联查询顾客
    Page<Customer> cusQuery( int pageSize, int pageNumber,String id,String cusName,String cusSex);
    //按ID查一个顾客
    Customer cusQueryById(String id);
    //顾客忘记密码
    Customer cusQueryById(String cusName,String cusCode);
    //顾客登录
    Customer cusLogin(String cusLoginName,String cusPassword);
    //修改一个顾客信息
    int cusUpdate(Customer cus);
    //修改一个顾客密码
    int cusUpdatePwd(String id,String cusPassword);
    //添加一个顾客
    int cusAdd(Customer cus);
    //根据ID删除顾客
    int cusDelById(String id);
    // 顾客注册
    boolean addCus(Customer customer);
    // 注销
    boolean delCus(long id);
}
