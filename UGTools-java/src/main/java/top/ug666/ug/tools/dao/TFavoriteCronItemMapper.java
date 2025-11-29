package top.ug666.ug.tools.dao;

import top.ug666.ug.tools.domain.TFavoriteCronItem;

import java.util.List;

public interface TFavoriteCronItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFavoriteCronItem record);

    int insertSelective(TFavoriteCronItem record);

    TFavoriteCronItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFavoriteCronItem record);

    int updateByPrimaryKey(TFavoriteCronItem record);

    List<TFavoriteCronItem> selectByListId(Integer listId);
}
