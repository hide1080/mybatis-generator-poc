package com.example.mybatisgeneratorpoc.mapper.mybatisgeneratorpoc;

import com.example.mybatisgeneratorpoc.entity.mybatisgeneratorpoc.Todo;
import com.example.mybatisgeneratorpoc.entity.mybatisgeneratorpoc.TodoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TodoMapper {
    long countByExample(TodoExample example);

    int deleteByExample(TodoExample example);

    int insert(Todo record);

    int insertSelective(Todo record);

    List<Todo> selectByExample(TodoExample example);

    int updateByExampleSelective(@Param("record") Todo record, @Param("example") TodoExample example);

    int updateByExample(@Param("record") Todo record, @Param("example") TodoExample example);
}