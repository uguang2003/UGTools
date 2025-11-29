package top.ug666.ug.tools.dao;

import top.ug666.ug.tools.domain.TFuncContent;

public interface TFuncContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFuncContent record);

    int insertSelective(TFuncContent record);

    TFuncContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFuncContent record);

    int updateByPrimaryKey(TFuncContent record);

    TFuncContent selectByFunc(String func);
}
