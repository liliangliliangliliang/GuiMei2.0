package service;

import pojo.All;
import pojo.Page;
import pojo.Smallclass;

import java.util.List;

public interface SmallclassServiceDao {
    //分页查询小分类
    Page<All> smallQuery(int pageSize, int pageNumber);
    //分页级联查询小分类
    Page<All> smallQuery( int pageSize,int pageNumber,String smallName,String bigName);
    //按ID查一个小分类
    Smallclass smallQueryById(String id);
    //查小分类名是否重复
    Boolean smallQueryName(String smallName);
    //修改小分类
    int smallUpdate(Smallclass small);
    //删除小分类
    int smallDel(String id);
    //添加小分类
    int smallAdd(Smallclass small);
    //查所有名与ID
    List<All> smallQuery();
}
