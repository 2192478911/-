package work;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class loginmanage implements ActionListener {
    JFrame F1;
    JPanel P1;
    JPanel P2;
    JLabel L1;
    JButton B1,B2,B3,B4;
    JTextField T1,T2,T3;
    JTable jt;
    JScrollPane jsp;
    houseModel sm;
    Statement stat = null;
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    public loginmanage()
    {
        Color mycolor=new Color(176,226,255);
        Color mycolor2=new Color(175,238,238);
        F1=new JFrame("����Ա����");
        F1.setSize(500,300);
        F1.setLocation(400,300);
        L1=new JLabel("�������˺�");
        B1=new JButton("ɾ��");
        B1.addActionListener(this);
        B2=new JButton("��ѯ");
        B2.addActionListener(this);
        B3=new JButton("�޸�");
        B3.addActionListener(this);
        B4=new JButton("�����޸�");
        B4.addActionListener(this);
        T1=new JTextField(10);
        T2=new JTextField(15);
        T3=new JTextField(15);
        P1=new JPanel();
        P2=new JPanel();
        P1.add(L1);
        P1.add(T1);
        P1.add(B1);
        P1.add(B2);
        P1.add(B3);
        P2.add(T2);
        P2.add(T3);
        P2.add(B4);
        P1.setBackground(mycolor);
        P2.setBackground(mycolor);
        sm = new houseModel();
        //��ʼ��
        jt = new JTable(sm);
        jsp = new JScrollPane(jt);
        jt.setBackground(mycolor2);
        F1.add(jsp);
        F1.add(P1,"North");
        F1.add(P2,"South");
        F1.setDefaultCloseOperation(1);
        F1.setVisible(true);
    }
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == B1){//ɾ��
           // int rowNum = this.jt.getSelectedRow();
            String num1 = this.T1.getText().trim();
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                //System.out.println("���ӳɹ�");
                ps = ct.prepareStatement("delete from WHlogin where idd = ? ");
                ps.setString(1,num1);
                ps.executeUpdate();

            }catch(Exception e) { e.printStackTrace(); }
            sm = new houseModel();
            //����jtable
            jt.setModel(sm);
        }
        if(arg0.getSource() == B2)//��ѯ
        {
            String num = this.T1.getText().trim();
            String sql = "select * from WHlogin where idd = '"+num+"' ";
            sm = new houseModel(sql);
            //����jtable
            jt.setModel(sm);
        }
        if(arg0.getSource() == B3)
        {
            int row = jt.getSelectedRow();
            if(row==-1) {
                //��ʾ
                JOptionPane.showMessageDialog(null, "��ѡ��һ��");
            }
            T2.setText((String)sm.getValueAt(row, 0));
            T3.setText(sm.getValueAt(row, 1).toString());
        }
        if(arg0.getSource() == B4)//�����޸�
        {
            String num2 = this.T2.getText().trim();
            String pss = this.T3.getText().trim();
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                //System.out.println("���ӳɹ�");
                ps = ct.prepareStatement("update WHlogin set keyy = ? where idd = ?");
                ps.setString(2,num2);
                ps.setString(1,pss);
                ps.executeUpdate();

            }catch(Exception e) { e.printStackTrace(); }
            sm = new houseModel();
            //����jtable
            jt.setModel(sm);
            JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
            T2.setText(" ");T3.setText(" ");
        }
    }
}
