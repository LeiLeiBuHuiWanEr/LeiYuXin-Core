package com.iloveleiyuxin.websitmanager;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;

public class LogReaderTest {
    public static void main(String[] args) throws IOException {
//        File file = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\logs\\tomcat9-stdout.2022-06-13.log");
        File file = new File("D:\\leiyuxin3.txt");
        int n_lines = 200;
        int counter = 0;
        ReversedLinesFileReader object = new ReversedLinesFileReader(file,100,"UTF-8");
        StringBuilder sb = new StringBuilder();
        while(counter < n_lines) {
            if(object.readLine() == null){
                break;
            }else {
                sb = new StringBuilder(object.readLine()+'\n').append(sb);
                counter++;
            }
        }
        System.out.println(sb);
    }
}
