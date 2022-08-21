package com.qq.test;

import com.qq.util.HexStringWithByteTransUtil;
import com.qq.util.MD5Util;
import org.junit.Test;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 09:41
 **/
public class MD5test {
    @Test
    public void test(){
        String encode = MD5Util.encode("123456");
        System.out.println(encode);
    }
}
