package core.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName core.util.PageUtil
 * @Author LILIANG
 * @Date 2018/12/21 13:48
 * @Version 1.0
 **/
public class PageUtil extends BaseDao {

    public static int getTotalRecode(String sql ,Object[]parameter){
        int totalRecode=0;
        ResultSet rs=getQuery(sql,parameter);
        try {
            if(rs.next()){
                totalRecode=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return totalRecode;
    }

    public static ResultSet getPageData(String sql,int pageSize,int pageNumber,Object[]parameter){
       int index =(pageNumber-1)*pageSize;
        sql=sql+" limit "+index+","+pageSize;
        ResultSet rs=getQuery(sql,parameter);
        return rs;
    }

}
