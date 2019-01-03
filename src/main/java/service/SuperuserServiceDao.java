package service;

import pojo.Superuser;

public interface SuperuserServiceDao {

    //管理员登录
    Superuser userQuery(String userLoginName ,String userPassword);
    //按ID查用户
    Superuser userQuery(String id);
    //修改管理员密码
    int userUpdate(String userPassword,String id);
    //修改管理员信息 除密码
    int userUpdate(Superuser user);
}
