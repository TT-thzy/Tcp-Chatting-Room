package com.qq.test;

import com.qq.util.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-12 17:01
 **/
public class DateTest {

    @Test
    public void test(){
        String s="2025年6月4日";
        Date date = DateUtil.stringToDate(s, "yyyy年MM月dd日");
        System.out.println(DateUtil.dateToString(date));
    }
}
