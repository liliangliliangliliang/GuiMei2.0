package service.impl;

import dao.impl.AllImplDao;
import pojo.All;
import pojo.Page;
import pojo.Smallclass;
import service.SmallclassServiceDao;

import java.util.List;

public class SmallclassServiceImplDao implements SmallclassServiceDao {
    private static SmallclassServiceImplDao ourInstance = new SmallclassServiceImplDao();
    public static SmallclassServiceImplDao getInstance() {
        return ourInstance;
    }
    private SmallclassServiceImplDao() {
    }
    AllImplDao allDao=AllImplDao.getInstance();
    String sql;
    String sql2;
    Object[]parameter;


    public Page<All> smallQuery(int pageSize, int pageNumber) {
        sql="select count(1) from smallclass ";
        sql2="select smallclass.*,bigName from smallclass,bigclass where smallBigId=bigclass.id order by smallclass.id desc ";
        return allDao.smallQuery(sql,sql2,null,pageSize,pageNumber);
    }

    public Page<All> smallQuery(int pageSize, int pageNumber, String smallName,String bigName) {
        if(smallName==""){
            if(bigName==""){
                return smallQuery(pageSize,pageNumber);
            }else {
                sql="select count(1) from smallclass,bigclass where smallBigId=bigclass.id and bigName=?";
                sql2="select smallclass.*,bigName from smallclass,bigclass where smallBigId=bigclass.id and bigName=? order by smallclass.id desc";
                parameter=new Object[]{bigName};
            }
        }else {
            if(bigName==""){
                sql="select count(1) from smallclass,bigclass where smallBigId=bigclass.id and smallName like ?";
                sql2="select smallclass.*,bigName from smallclass,bigclass where smallBigId=bigclass.id and smallName like ? order by smallclass.id desc";
                parameter=new Object[]{"%"+smallName+"%"};
            }else {
                sql="select count(1) from smallclass,bigclass where smallBigId=bigclass.id and bigName=? and smallName like ?";
                sql2="select smallclass.*,bigName from smallclass,bigclass where smallBigId=bigclass.id and bigName=? and smallName like ? order by smallclass.id desc";
                parameter=new Object[]{bigName,"%"+smallName+"%"};
            }
        }
        return allDao.smallQuery(sql,sql2,parameter,pageSize,pageNumber);
    }

    public Smallclass smallQueryById(String id) {
        String sql="select * from smallclass,bigclass where smallBigId=bigclass.id and smallclass.id=? ";
        Object[]parameter={id};
        return allDao.smallQuery(sql,parameter).get(0);
    }

    public Boolean smallQueryName(String smallName) {
        String sql="select smallName from smallclass where smallName=?";
        Object[]parameter={smallName};
        if(smallName!=null && smallName.length()>0){
            return allDao.QueryName(sql,parameter);
        }
        return true;
    }

    public int smallUpdate(Smallclass small) {
        String sql="update smallclass set smallName=?,smallBigId=?,smallText=? where id=?";
        Object []parameter={small.getSmallName(),small.getSmallBigId(),small.getSmallText(),small.getId()};
        return allDao.smallUpdate(sql,parameter);
    }

    public int smallDel(String id) {
        String sql="delete from smallclass where id=? and id not in(select goodsSmalId from goods)";
        Object[]parameter={id};
        return allDao.smallUpdate(sql,parameter);
    }

    public int smallAdd(Smallclass small) {
        String sql="insert into smallclass values(0,?,?,?)";
        Object[]parameter={small.getSmallName(),small.getSmallBigId(),small.getSmallText()};
        return allDao.smallUpdate(sql,parameter);
    }

    public List<All> smallQuery() {
        String sql="select * from smallclass,bigclass where smallBigId=bigclass.id ";
        return allDao.smallQuery(sql,null);
    }
}
