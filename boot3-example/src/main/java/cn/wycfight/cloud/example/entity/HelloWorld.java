package cn.wycfight.cloud.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HelloWorld {

    private String hello;


    private String world;


    private InnerClass innerClass;

    private List<InnerClass> innerClasses;
}

