package top.ug666.ug.tools.dao;

import top.ug666.ug.tools.domain.TMsgHttp;

import java.util.List;

public interface TMsgHttpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMsgHttp record);

    int insertSelective(TMsgHttp record);

    TMsgHttp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMsgHttp record);

    int updateByPrimaryKey(TMsgHttp record);

    TMsgHttp selectByMsgName(String msgName);

    int updateByMsgName(TMsgHttp tMsgHttp);

    List<TMsgHttp> selectAll();

    List<TMsgHttp> selectByFilter(String titleFilterKeyWord);
}
