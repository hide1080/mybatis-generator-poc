package com.example.mybatisgeneratorpoc;

import java.util.List;

import com.example.mybatisgeneratorpoc.entity.mybatisgeneratorpoc.Todo;
import com.example.mybatisgeneratorpoc.entity.mybatisgeneratorpoc.TodoExample;
import com.example.mybatisgeneratorpoc.mapper.mybatisgeneratorpoc.TodoAppMapper;
import com.example.mybatisgeneratorpoc.mapper.mybatisgeneratorpoc.TodoMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
public class MybatisGeneratorPocApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorPocApplication.class, args);
    }

    private final TodoMapper todoMapper;

    public MybatisGeneratorPocApplication(final TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    @Transactional
    @Override
    public void run(final String... args) {

        final var ex = new TodoExample();
        ex.createCriteria().andIdIn(List.of(1L, 2L));
        todoMapper.deleteByExample(ex);

        todoMapper.insert(new Todo()//
            .withId(1L)//
            .withTitle("打合せ")//
            .withDetails("会社 10:00")//
            .withFinished(false));;

        todoMapper.insert(new Todo()//
            .withId(2L)//
            .withTitle("飲み会")//
            .withDetails("銀座 19:00")//
            .withFinished(false));

        todoMapper.selectByExample(ex).forEach(r->{
            System.out.println("ID       : " + r.getId());
            System.out.println("TITLE    : " + r.getTitle());
            System.out.println("DETAILS  : " + r.getDetailsOpt());
            System.out.println("FINISHED : " + r.getFinished());
        });
    }
}
