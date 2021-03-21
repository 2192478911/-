package work;
import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.awt.event.*;

public class phone implements ActionListener{
    JFrame F1;
    JLabel L1,L2,L3;
    JTextField T1;
    JTextField T21,T22,T23,T24,T25,T26;
    //JTextField T31,T32,T33,T34,T35;
    JPanel P1,P2,P3;
    JButton B11,B12,B13,B14;
    JButton B21,B31;
    JTable jt;
    JScrollPane jsp;
    PhoneModel sm;
    Statement stat = null;
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    public static String id;
    int row=-1;
    //public void Error() {//Error弹窗
     //   JOptionPane.showMessageDialog(null, "商品编号和库位区不匹配，请确认！");
    //}
    public phone()
    {
        F1=new JFrame("手机库存");
        F1.setSize(1000,500);
        F1.setLocation(300,200);
        L1= new JLabel("手机编号");
        L2=new JLabel("修改数据");
        //L3=new JLabel("修改数据");
        P1=new JPanel();P2=new JPanel();P3=new JPanel();
        Color mycolor=new Color(176,226,255);
        Color mycolor2=new Color(175,238,238);
        P1.setBackground(mycolor);
        P2.setBackground(mycolor);
        B11=new JButton("查询");B12=new JButton("删除");B13= new JButton("修改");B14=new JButton("显示全部");
        B21=new JButton("保存添加");
        B31=new JButton("保存修改");
        B11.addActionListener(this);B12.addActionListener(this);B13.addActionListener(this);B14.addActionListener(this);
        B21.addActionListener(this);B31.addActionListener(this);
        T1=new JTextField(15);
        T21=new JTextField(10);T22=new JTextField(10);T23=new JTextField(10);
        T24=new JTextField(10);T25=new JTextField(10);T26=new JTextField(10);
        //T31=new JTextField(10);T32=new JTextField(10);T33=new JTextField(10);T34=new JTextField(10);T35=new JTextField(10);
        P1.add(L1);P1.add(T1);P1.add(B11);P1.add(B12);P1.add(B13);P1.add(B14);
        P2.add(L2); P2.add(T21); P2.add(T22);P2.add(T23);P2.add(T24);P2.add(T25);P2.add(T26);//P2.add(B21);
        P2.add(B31);
        //P3.add(L3);P3.add(T31);P3.add(T32);P3.add(T33);P3.add(T34);P3.add(T35);P3.add(B31);
        sm = new PhoneModel();
        //初始化
        jt = new JTable(sm);
        jt.setBackground(mycolor2);
        jsp = new JScrollPane(jt);
        F1.add(jsp);
        F1.add(P1,"North");
        F1.add(P2,"South");
       // F1.add(P3);
        F1.setDefaultCloseOperation(1);
        F1.setVisible(true);
    }
    public void actionPerformed(ActionEvent e2 ){
        if(e2.getSource() == B11) {//查询
            String num = this.T1.getText().trim();
            String sql = "select * from phone where num like '%"+num+"%' ";
            sm = new PhoneModel(sql);
            //更新jtable
            jt.setModel(sm);
        }
        if(e2.getSource() == B12) {//删除
            row = this.jt.getSelectedRow();
            if(row==-1 ) {
                //提示
                JOptionPane.showMessageDialog(null, "请选择一行");
            }
            String num1 = (String)sm.getValueAt(row, 0);
            try{
                //1.加载驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.连接数据库
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                //System.out.println("连接成功");
                ps = ct.prepareStatement("delete from phone where num = ? ");
                ps.setString(1,num1);
                ps.executeUpdate();

            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new PhoneModel();
            //更新jtable
            jt.setModel(sm);
        }
        if(e2.getSource() == B13) {//修改
            row = jt.getSelectedRow();
            if(row==-1 ) {
                //提示
                JOptionPane.showMessageDialog(null, "请选择一行");
            }
            T21.setText((String)sm.getValueAt(row, 0));
            T22.setText((String)sm.getValueAt(row, 1));
            T23.setText((String)sm.getValueAt(row, 2));
            T24.setText((String)sm.getValueAt(row, 3));
            T25.setText(sm.getValueAt(row, 4).toString());
            T26.setText((String)sm.getValueAt(row, 5));
        }
        if(e2.getSource() == B14) {//显示全部
            String sql = "select * from phone ";
            sm = new PhoneModel(sql);
            //更新jtable
            jt.setModel(sm);
        }
        if(e2.getSource() == B31) {//保存修改
            String num1 = this.T21.getText().trim();
            String qu=num1.substring(2,4);
            String num2 = this.T22.getText().trim();
            String num3 = this.T23.getText().trim();
            String num4 = this.T24.getText().trim();
            String num5 = this.T25.getText().trim();
            String num6 = this.T26.getText().trim();
            boolean a=qu.equals(num6);
            try{
                //1.加载驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.连接数据库
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                if(!a)
                    JOptionPane.showMessageDialog(null, "商品编号和库位区不匹配，请确认！");
                else {
                    ct = DriverManager.getConnection(url, user, passwd);
                    ps = ct.prepareStatement("Update phone set name=?,color=?,cs=?,sl=?,kw=? where num=?");
                    ps.setString(1, num2);
                    ps.setString(2, num3);
                    ps.setString(3, num4);
                    ps.setInt(4, Integer.parseInt(num5));
                    ps.setString(5, num6);
                    ps.setString(6, num1);
                    ps.executeUpdate();
                }
            }catch(Exception e) { e.printStackTrace(); }
            sm = new PhoneModel();
            //更新jtable
            jt.setModel(sm);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");T25.setText(" ");T26.setText(" ");
        }
    }
}
