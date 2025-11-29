package top.ug666.ug.tools.dao;

import top.ug666.ug.tools.domain.TQuickNote;

import java.util.List;

public interface TQuickNoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TQuickNote record);

    int insertSelective(TQuickNote record);

    TQuickNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TQuickNote record);

    int updateByPrimaryKey(TQuickNote record);

    List<TQuickNote> selectAll();

    TQuickNote selectByName(String name);

    int updateByName(TQuickNote tQuickNote);

    int updateAll(TQuickNote tQuickNote);

    List<TQuickNote> selectAllByFilter(String titleFilterKeyWord);

    List<TQuickNote> selectAllByFilterContainsContent(String titleFilterKeyWord);
}
