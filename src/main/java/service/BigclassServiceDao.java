package service;

import pojo.Bigclass;
import pojo.Page;

import java.util.List;
import java.util.Map;

public interface BigclassServiceDao {

    //查询所有大分类信息
    Page<Bigclass> bigQuery(int pageSize, int pageNumber);
    //根据ID查询单个大分类
    Bigclass bigQuery(String id);
    //根据ID修改大分类信息
    int bigUpdate(Bigclass big);
    //根据ID删除没有下级小分类的大分类信息
    int bigDel(String id);
    //添加不重复的大分类信息
    int bigAdd(Bigclass big);
    //查询大分类名字是否重复
    Boolean bigQueryName(String bigName);
    //查询所有大分类名字与ID
    List<Bigclass> bigNameAll();
}
