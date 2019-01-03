package service.impl;

import dao.impl.AllImplDao;
import pojo.Superuser;
import service.SuperuserServiceDao;

public class
SuperuserServiceImplDao implements SuperuserServiceDao {
    private static SuperuserServiceImplDao ourInstance = new SuperuserServiceImplDao();
    public static SuperuserServiceImplDao getInstance() {
        return ourInstance;
    }
    private SuperuserServiceImplDao() {
    }

    AllImplDao allDao=AllImplDao.getInstance();

    public Superuser userQuery(String userLoginName, String userPassword) {
        String sql="select * from superuser where userLoginName=? and userPassword=?";
        Object[]parameter={userLoginName,userPassword};
        return allDao.userQuery(sql,parameter);
    }

    public Superuser userQuery(String id) {
        String sql="select * from superuser where id=?";
        Object[]parameter={id};
        return allDao.userQuery(sql,parameter);
    }

    public int userUpdate(String userPassword,String id) {
        String sql="update superuser set userPassword=? where id=?";
        Object[]parameter={userPassword,id};
        return allDao.userUpdate(sql,parameter);
    }

    public int userUpdate(Superuser user) {
        String sql="update superuser set userName=?,userID=?,userLoginName=? where id=?";
        Object[]parameter={user.getUserName(), user.getUserId(),user.getUserLoginName(),user.getId()};
        return allDao.userUpdate(sql,parameter);
    }
}
