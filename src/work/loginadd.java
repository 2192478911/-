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

public class loginadd implements ActionListener {
    JFrame F1;
    JLabel jl1,jl2,jl3;
    JTextField jf1,jf2,jf3;
    JButton jb1,jb2;
    JPanel jp1,jp2;
    public loginadd() {
        F1 = new JFrame("注册");
        F1.setLayout(new FlowLayout());
        jl1=new JLabel("用户名");
        jl2=new JLabel("密     码");
        jl3=new JLabel("确认密码");
        jf1=new JTextField(20);
        jf2=new JTextField(20);
        jf3=new JTextField(20);
        jb1=new JButton("注册");
        jb2=new JButton("取消");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        F1.add(jl1);F1.add(jf1);
        F1.add(jl2);F1.add(jf2);
        F1.add(jl3);F1.add(jf3);
        F1.add(jb1);
        F1.add(jb2);
        F1.setSize(300,200);
        F1.setLocation(630,330);
        F1.setVisible(true);
        F1.addWindowListener(new Close1());
    }
    public void OK() {//Error弹窗
        JOptionPane.showMessageDialog(null, "注册成功！");
        F1.dispose();
    }
    public void NO()
    {
        JOptionPane.showMessageDialog(null, "两次密码不一致，请重新确认");
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == jb1){
            Connection ct = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/runoob?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";
                ct = DriverManager.getConnection(url,user,passwd);

                String strsql = "insert into WHlogin values(?,?)";
                pstmt = ct.prepareStatement(strsql);
                pstmt.setString(1,jf1.getText());
                pstmt.setString(2,jf2.getText());
                boolean bz=jf2.getText().equals(jf3.getText());//判断两次密码是否一致
                if(bz==true) {
                    OK();
                    pstmt.executeUpdate();
                }
                else
                    NO();
                //this.dispose();
            }catch(Exception arg1){
                arg1.printStackTrace();
            }
        }
        else if(e.getSource() == jb2)
        {
            F1.dispose();
        }

    }
    }

