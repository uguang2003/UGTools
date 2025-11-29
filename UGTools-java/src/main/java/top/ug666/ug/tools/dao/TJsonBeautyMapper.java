package top.ug666.ug.tools.dao;

import top.ug666.ug.tools.domain.TJsonBeauty;

import java.util.List;

public interface TJsonBeautyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TJsonBeauty record);

    int insertSelective(TJsonBeauty record);

    TJsonBeauty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TJsonBeauty record);

    int updateByPrimaryKey(TJsonBeauty record);

    List<TJsonBeauty> selectAll();

    TJsonBeauty selectByName(String name);

    int updateByName(TJsonBeauty tJsonBeauty);

    List<TJsonBeauty> selectByFilter(String titleFilterKeyWord);
}
