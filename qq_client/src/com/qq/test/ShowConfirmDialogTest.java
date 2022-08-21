package com.qq.test;

import org.junit.Test;

import javax.swing.*;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-12 10:04
 **/
public class ShowConfirmDialogTest {

    @Test
    public void test(){
        int i = JOptionPane.showConfirmDialog(null, "");
        if (i==JOptionPane.YES_OPTION){
            System.out.println(1);
        }
    }
}
