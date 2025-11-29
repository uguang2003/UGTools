package top.ug666.ug.tools.dao;

import top.ug666.ug.tools.domain.THost;

import java.util.List;

public interface THostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THost record);

    int insertSelective(THost record);

    THost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(THost record);

    int updateByPrimaryKey(THost record);

    List<THost> selectAll();

    THost selectByName(String name);

    int updateByName(THost tHost);

    List<THost> selectByFilter(String titleFilterKeyWord);
}
