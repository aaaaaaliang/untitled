package com.zxl.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {


    public LoginJFrame(){
        //设置标题
        this.setTitle("拼图登录");
        //创建页面的时候，同时给这个界面去设置一些信息
        this.setSize(488,430);
        //设置游戏的关闭方式
        this.setDefaultCloseOperation(3);

        this.setVisible(true);
    }
}
