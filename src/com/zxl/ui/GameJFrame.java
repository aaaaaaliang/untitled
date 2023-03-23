package com.zxl.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener , ActionListener {
    //创建二维数组
    int[][] data=new int[4][4];
    int x=0;
    int y=0;

    //定义一个变量，记录当时展示图片的路径
    String path="..\\untitled\\image\\animal\\animal3\\";

    //定义一个二维数组，存储正确的数据
    int[][] win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //定义变量用来统计步数
    int step=0;


    //创建选项下面的条目对象
    JMenuItem replayItem=new JMenuItem("重新游戏");
    JMenuItem reLoginItem=new JMenuItem("重新登陆");
    JMenuItem closeItem=new JMenuItem("关闭游戏");

    JMenuItem accountItem=new JMenuItem("公众号");
    public GameJFrame(){
        //初始化界面
        //设置界面的宽高
        //创建一个二维数组
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //显示页面，建议写到最好
        initImage();

        //初始化图片
        this.setVisible(true);
    }

    //初始化数据

    private void initData() {
        int[] tempArr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        //打乱数组中的数据的顺序
        //遍历数组，得到每一个元素，拿着每一个元素跟索引上的数据进行交换
        Random r=new Random();
        for (int i = 0; i < tempArr.length; i++) {

            //获取到随机索引
            int index = r.nextInt(tempArr.length);

            //拿着遍历的数据跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //给二维数组添加数据
        for (int i = 0; i < tempArr.length; i++) {
                if(tempArr[i]==0){
                   x=i/4;
                   y=i%4;
                }
                {
                    data[i/4][i%4]=tempArr[i];
            }
        }
    }

    //初始化图片
    //添加图片
    private void initImage() {

        //删除本来已经出现的图片
        this.getContentPane().removeAll();
        if (  victory()){

            //显示胜利的图标
            JLabel winJLabel=new JLabel(new ImageIcon("untitled\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount=new JLabel("步数"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++) {
                //获取序号
               int num= data[i][j];
                JLabel jLabel=new JLabel(new ImageIcon(path+num+".jpg"));
                jLabel.setBounds(105*j+83,105*i+134,105,105);
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }
        ImageIcon bg=new ImageIcon("..\\untitled\\image\\background.png");
        JLabel background=new JLabel(bg);
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);


        //刷新一下界面
        this.getContentPane().repaint();
    }


    private void initJMenuBar() {

        //初始化菜单
        //创建菜单对象
        JMenuBar jMenuBar=new JMenuBar();

        //创建菜单的两个选项
        JMenu functionJMenu=new JMenu("功能");
        JMenu aboutJMenu=new JMenu("关于我们");


        //将每一个选项条目添加到选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);
        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将菜单两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);

        //设置标题
        this.setTitle("拼图单机版 v1.0");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置居中
        this.setLocationRelativeTo(null);

        //设置游戏的关闭方式
        this.setDefaultCloseOperation(3);

        //取消默认居中
        this.setLayout(null);

        //给整个界面添加键盘事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
 //按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
    int code=e.getKeyCode();
    if(code==65){

        //把界面所有的图片删除
        this.getContentPane().removeAll();

        //加在第一张完整的图片
        JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
        all.setBounds(83,134,420,420);
        this.getContentPane().add(all);

        //加载背景图片
        JLabel background=new JLabel(new ImageIcon("..\\untitled\\image\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //判断游戏是否胜利，如果胜利，此方法直接结束
        if(victory()){
            return;
        }

  //对上下左右进行判断
        int code=e.getKeyCode();
        if(code==37){
            if(y==0){
                return;
            }
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            step++;
            initImage();
        } else if (code==38) {
            if(x==0){
                return;
            }
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            step++;
            initImage();
        }else if (code==39) {
            if(y==3){
                return;
            }
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            step++;
            initImage();
        }else if (code==40) {
            if(x==3){
                return;
            }
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            step++;
            initImage();
        } else if (code==65) {
            initImage();
        } else if (code==87) {
            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,16}
            };
            initImage();
        }
    }
    //判断data数组中的数据是否跟win数组中相同
    //如果全部相同，返回true，否则返回false
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==replayItem){

            //计步器清零
            step=0;

           //再次打乱二维数组中的数据
            initData();

            //重新加在图片
            initImage();

        }else if(obj==reLoginItem){

            //关闭当前界面
            this.setVisible(false);

            //打开登录界面
            new LoginJFrame();
        }else if (obj==closeItem){
            System.out.println("关闭游戏");
            //直接关闭虚拟机

            System.exit(0);
        } else if (obj==accountItem) {
           //创建一个弹框对象
            JDialog jDialog=new JDialog();
            //创建管理的容器对象
            JLabel jLabel=new JLabel(new ImageIcon("..\\untitled\\image\\zxl.jpg"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //单框不关闭无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);
        }
    }
}
