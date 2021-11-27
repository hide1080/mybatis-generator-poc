package com.example.mybatisgeneratorpoc.mapper;

import com.example.mybatisgeneratorpoc.model.Todo;
import com.example.mybatisgeneratorpoc.model.TodoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TodoMapper {
    long countByExample(TodoExample example);

    int deleteByExample(TodoExample example);

    int insert(Todo record);

    int insertSelective(Todo record);

    List<Todo> selectByExampleWithRowbounds(TodoExample example, RowBounds rowBounds);

    List<Todo> selectByExample(TodoExample example);

    int updateByExampleSelective(@Param("record") Todo record, @Param("example") TodoExample example);

    int updateByExample(@Param("record") Todo record, @Param("example") TodoExample example);
}