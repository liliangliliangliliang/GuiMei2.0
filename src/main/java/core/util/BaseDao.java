package core.util;

import java.sql.*;

/**
 * @ClassName JDBCBaseDao.BaseDao
 * @Author LILIANG
 * @Date 2018/12/11 13:47
 * @Version 1.0
 **/
public class BaseDao {

    private static final String URL ="jdbc:mysql://localhost:3306/guimeidb?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER="root";
    private static final String PASSWORD="root";
    private static Connection conn=null;
    private static PreparedStatement psmt=null;
    private static Statement stmt=null;
    private static ResultSet rs=null;
    /**
     * 加载数据库驱动程序
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建MYSQL数据库连接
     */
    public static Connection getCon(){
        try {
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 执行查询的方法
     */
    public static ResultSet getQuery(String sql,Object parameter[]){
        try {
            psmt=getCon().prepareStatement(sql);
            //判断是否带条件查询
            if(parameter!=null && parameter.length>0){
                //带条件查询 将paramete中参数遍历
                for(int i=0;i<parameter.length;i++){
                    psmt.setObject(i+1,parameter[i]);
                }
            }
            rs=psmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /**
     *增删改
     */
    public static int getUpdate(String sql,Object parameter[]){
        int i=0;
        try {
            psmt=getCon().prepareStatement(sql);
            //判断是否带条件增删改
            if(parameter!=null && parameter.length>0){
                //带条件 将paramete中参数遍历
                for(int j=1;j<=parameter.length;j++){
                    psmt.setObject(j,parameter[j-1]);
                }
            }
            i=psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //关闭
    public static void dbClose(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs=null;
        }
        if(psmt!=null){
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            psmt=null;
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt=null;
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn=null;
        }
    }

}
