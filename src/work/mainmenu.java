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

/**
 * �б���ͼƬ��Panel��
 */
 class BackgroundPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -6352788025440244338L;
    private Image image = null;
    public BackgroundPanel(Image image) {
        this.image = image;
    }
    // �̶�����ͼƬ���������JPanel������ͼƬ������������
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

public class mainmenu implements ActionListener {
    JFrame F1;
    JPanel P1,P2,P3;
    JButton B1,B2,B3,B4,B5,B6,B7,B8,B9;
    public mainmenu() {
        Image image=new ImageIcon("D:\\javaworkspace\\warehouse\\src\\work\\1.jpg").getImage();
        F1=new JFrame("�ֻ��ֿ����ϵͳ");
        F1.setSize(1100,600);
        F1.setLocation(200,100);
        //P1=new JPanel();
        P1=new BackgroundPanel(image);
        P1.setLayout( new GridLayout(3,3,100,100));
        B1=new JButton("�ֻ����");
        B2=new JButton("�ͻ�����");
        B3=new JButton("������Ϣ");
        B4=new JButton("���ҵ��");
        B5=new JButton("����ҵ��");
        B6=new JButton("�ֿ����");
        B7=new JButton("������");
        B8=new JButton("�û�����");
        B9=new JButton("�˳�ϵͳ");
        Font f=new Font("�����п�",Font.BOLD,25);
        B1.setFont(f);B2.setFont(f);B3.setFont(f);B4.setFont(f);B5.setFont(f);B6.setFont(f);
        B7.setFont(f);B8.setFont(f);B9.setFont(f);
        B1.setContentAreaFilled(false);B2.setContentAreaFilled(false);B3.setContentAreaFilled(false);
        B4.setContentAreaFilled(false);B5.setContentAreaFilled(false);B6.setContentAreaFilled(false);
        B7.setContentAreaFilled(false);B8.setContentAreaFilled(false);B9.setContentAreaFilled(false);
        B1.addActionListener(this);B2.addActionListener(this);B3.addActionListener(this);
        B4.addActionListener(this);B5.addActionListener(this);B6.addActionListener(this);
        B7.addActionListener(this);B8.addActionListener(this);B9.addActionListener(this);
        B1.setPreferredSize(new Dimension(30,30));
        P1.add(B1); P1.add(B2); P1.add(B3); P1.add(B4);
        P1.add(B5); P1.add(B6); P1.add(B7); P1.add(B8);
        P1.add(B9);
        //F1.add(P2);
        F1.add(P1);
        F1.setVisible(true);
        F1.addWindowListener(new Close1());
    }
    public void Error() {//Error����
        JOptionPane.showMessageDialog(null, "���¼��������Ա��");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == B1)//�ֻ���Ϣ
        {
            new phone();
        }
        if(e.getSource() == B2)//�ͻ�����
        {
            new order();
        }
        if(e.getSource() == B3)//������Ϣ
        {
            new changshang();
        }
        if(e.getSource() == B4)//���
        {
            new inku();
        }
        if(e.getSource() == B5)//����
        {
            new outku();
        }
        if(e.getSource() == B6)//�ֿ����
        {
            new CKdiaobo();
        }
        if(e.getSource() == B7)
        {
            new KCdiaobo();
        }

        if(e.getSource() == B8)//�û�����
        {
            String JDriver = "com.mysql.cj.jdbc.Driver";  //����JDBC�����������
            String conURL = "jdbc:mysql://localhost:3306/runoob?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String USER = "root";       //MySql�û���
            String PASS = "1234";   //MySql���룬Ҫ�����������
            try {
                Class.forName(JDriver);  //����JDBC��������
            }
            catch(java.lang.ClassNotFoundException e1) {
                System.out.println("ForName :" + e1.getMessage( ));
            }
            try {
                Connection con= DriverManager.getConnection(conURL,USER,PASS);  //�������ݿ�URL
                Statement s=con.createStatement( );  //����Statement�����
                if(login.sID.equals("root")) {
                    new loginmanage();
                }
                else
                    Error();
                s.close( );  //�ͷ�Statement�����ӵ����ݿ⼰JDBC��Դ
                con.close( );  //�ر������ݿ������
            }
            catch(SQLException e1){ System.out.println("SQLException: " +e1.getMessage( ));  }
        }

        if(e.getSource() == B9)//�˳�ϵͳ
        {
            System.exit(0);
        }
    }
}
