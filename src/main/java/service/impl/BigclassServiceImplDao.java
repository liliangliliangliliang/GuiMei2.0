package service.impl;

import dao.impl.AllImplDao;
import org.apache.tomcat.util.digester.SetTopRule;
import pojo.Bigclass;
import pojo.Page;
import service.BigclassServiceDao;

import java.util.List;
import java.util.Map;

public class BigclassServiceImplDao implements BigclassServiceDao {
    private static BigclassServiceImplDao ourInstance = new BigclassServiceImplDao();
    public static BigclassServiceImplDao getInstance() {
        return ourInstance;
    }
    private BigclassServiceImplDao() {
    }

    AllImplDao allDao= AllImplDao.getInstance();

    public Page<Bigclass> bigQuery(int pageSize, int pageNumber) {
        String sql="select count(1) from bigclass";
        String sql2="select * from bigclass";
        return allDao.bigQuery(sql,sql2,null,pageSize,pageNumber);
    }

    public Bigclass bigQuery(String id) {
        String sql="select * from bigclass where id=?";
        Object[]parameter={id};
        return allDao.bigQuery(sql,parameter).get(0);
    }

    public int bigUpdate(Bigclass big) {
        String sql="update bigclass set bigName=?,bigText=? where id=?";
        Object[]parameter={big.getBigName(),big.getBigText(),big.getId()};
        return allDao.bigUpdate(sql,parameter);
    }

    public int bigDel(String id) {
        String sql="delete from bigclass where id=? and id not in(select smallBigId from smallclass)";
        Object[]parameter={id};
        return allDao.bigUpdate(sql,parameter);
    }

    public int bigAdd(Bigclass big) {
        String sql="insert into bigclass values(0,?,?)";
        Object[]parameter={big.getBigName(),big.getBigText()};
        return  allDao.bigUpdate(sql,parameter);
    }

    public Boolean bigQueryName(String bigName) {
        String sql="select bigName from bigclass where bigName=?";
        Object[]parameter={bigName};
        if(bigName!=null && bigName.length()>0){
            return allDao.QueryName(sql,parameter);
        }
        return true;
    }

    public List<Bigclass> bigNameAll() {
        String sql="select * from bigclass";
        return allDao.bigQuery(sql,null);
    }
}
