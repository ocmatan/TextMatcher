package org.example;

import java.util.Map;

public class ResultsPrinter {

    public void printAll(Map results){
        results.forEach((key, value) -> System.out.println(key + ": " + value));
    }



}
