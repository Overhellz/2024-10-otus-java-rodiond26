package ru.otus.lesson01.homework;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloOtus {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));
        ImmutableList<String> unmodifiableGuavaList = ImmutableList.copyOf(list);
        int count = unmodifiableGuavaList.stream().mapToInt(String::length).sum();
        System.out.println(count);
    }
}
