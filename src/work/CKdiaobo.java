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
public class CKdiaobo implements ActionListener{
    JFrame F1;
    JLabel L1,L2,L3,L4,L5,L6;
    JTextField T21,T22,T23,T24,T25,T26;
    JPanel P1,P2,P3,P4;
    JButton B1,B2,B3,B4,B5;
    JTable jt,jt1;
    JScrollPane jsp,jsp1;
    PhoneModel sm;
    BJPhoneModel sm1;
    Statement stat = null;
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    public static String id;
    int row=-1;
    int row1=-1;
    boolean flag;
    public void OK1() {//Error����
        JOptionPane.showMessageDialog(null, "�����һ���µĲ�Ʒ���࣡");
    }
    public void OK2() {//Error����
        JOptionPane.showMessageDialog(null, "�����ɹ�");
    }
    public void Error() {//Error����
        JOptionPane.showMessageDialog(null, "�����Ʒ��źͿ�λ����ƥ�䣬��ȷ�ϣ�");
    }
    public CKdiaobo()
    {
        Color mycolor=new Color(176,226,255);
        Color mycolor2=new Color(175,238,238);
        F1=new JFrame("�ֿ����");
        F1.setSize(1100,500);
        F1.setLocation(300,200);
        L1= new JLabel("���");L2=new JLabel("����");
        L3=new JLabel("��ɫ");L4=new JLabel("����");L5=new JLabel("����");L6=new JLabel("��λ��");
        P1=new JPanel();P2=new JPanel();P4=new JPanel();
        B1=new JButton("�鿴����");B2=new JButton("�鿴������");B3=new JButton("ȷ��ѡ��");
        B4=new JButton("��������");B5=new JButton("����������");
        B1.addActionListener(this);B2.addActionListener(this);B3.addActionListener(this);
        B4.addActionListener(this);B5.addActionListener(this);
        T21=new JTextField(10);T22=new JTextField(10);T23=new JTextField(10);
        T24=new JTextField(10);T25=new JTextField(10);T26=new JTextField(10);
        sm = new PhoneModel();
        //��ʼ��
        jt = new JTable(sm);
        jsp = new JScrollPane(jt);
        sm1 = new BJPhoneModel();
        //��ʼ��
        jt1 = new JTable(sm1);
        jsp1 = new JScrollPane(jt1);
        P1.add(B1);P1.add(B2);P1.add(B3);
        P4.add(L1);P4.add(T21);P4.add(L2);P4.add(T22);P4.add(L3);P4.add(T23);
        P4.add(L4);P4.add(T24);P4.add(L5);P4.add(T25);P4.add(L6);P4.add(T26);
        P4.add(B4);P4.add(B5);
        P1.setBackground(mycolor);
        P4.setBackground(mycolor);
        jt.setBackground(mycolor2);
        jt1.setBackground(mycolor2);
        F1.add(P1,"North");
        //F1.add(jsp);
        //jsp1.setVisible(false);
        F1.add(P4,"South");
        F1.setDefaultCloseOperation(1);
        F1.setVisible(true);
    }
    public void actionPerformed(ActionEvent e2 )
    {
        if(e2.getSource()==B1)//�鿴����
        {
            F1.remove(jsp1);
            F1.add(jsp);
            B4.setVisible(false);
            B5.setVisible(true);
            F1.setVisible(true);
            flag=true;
        }
        if(e2.getSource()==B2)//�鿴������
        {
            F1.remove(jsp);
            F1.add(jsp1);
            B4.setVisible(true);
            B5.setVisible(false);
            F1.setVisible(true);
            flag=false;
        }
        if(e2.getSource() == B3) {//ȷ��ѡ��
            row = jt.getSelectedRow();
            row1 = jt1.getSelectedRow();
            if(row==-1&&flag) {
                //��ʾ
                JOptionPane.showMessageDialog(null, "��ѡ��һ��");
            }
            if(row1==-1&&!flag) {
                //��ʾ
                JOptionPane.showMessageDialog(null, "��ѡ��һ��");
            }
            if(flag)
            {
                T21.setText((String) sm.getValueAt(row, 0));
                T22.setText((String) sm.getValueAt(row, 1));
                T23.setText((String) sm.getValueAt(row, 2));
                T24.setText((String) sm.getValueAt(row, 3));
                T25.setText(sm.getValueAt(row, 4).toString());
                T26.setText((String) sm.getValueAt(row, 5));
            }
            if(!flag)
            {
                T21.setText((String) sm1.getValueAt(row1, 0));
                T22.setText((String) sm1.getValueAt(row1, 1));
                T23.setText((String) sm1.getValueAt(row1, 2));
                T24.setText((String) sm1.getValueAt(row1, 3));
                T25.setText(sm1.getValueAt(row1, 4).toString());
                T26.setText((String) sm1.getValueAt(row1, 5));
            }
        }
        if(e2.getSource() == B5)//����������
        {
            String num1 = this.T21.getText().trim();
            String qu=num1.substring(2,4);
            String num2 = this.T22.getText().trim();
            String num3 = this.T23.getText().trim();
            String num4 = this.T24.getText().trim();
            String num5 = this.T25.getText().trim();
            String num6 = this.T26.getText().trim();
            boolean a=qu.equals(num6);
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                Statement s=ct.createStatement( );

                    ps = ct.prepareStatement("update phone set phone.sl=phone.sl-? where phone.num=?");
                    ps.setInt(1, Integer.parseInt(num5));
                    ps.setString(2, num1);
                    ps.executeUpdate();
                ResultSet rs=s.executeQuery("select * from BJphone where num = '"+num1+"'");
                if(rs.next( )) {
                    ps = ct.prepareStatement("update BJphone set BJphone.sl=BJphone.sl+? where num=?");
                    ps.setInt(1, Integer.parseInt(num5));
                    ps.setString(2, num1);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "ϵͳ��⵽�ֿ��д��ڴ˱�Ų�Ʒ�����Զ�����");
                }
                else {
                    ps = ct.prepareStatement("insert into BJphone values (?,?,?,?,?,?)");
                    ps.setString(1, num1);
                    ps.setString(2, num2);
                    ps.setString(3, num3);
                    ps.setString(4, num4);
                    ps.setInt(5, Integer.parseInt(num5));
                    ps.setString(6, num6);
                    ps.executeUpdate();
                    //JOptionPane.showMessageDialog(null, "�������һ���µĲ�Ʒ���ͣ�");
                    OK2();
                }
            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new PhoneModel();
            sm1 = new BJPhoneModel();
            //����jtable
            jt1.setModel(sm1);
            jt.setModel(sm);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");T25.setText(" ");T26.setText(" ");
        }
        if(e2.getSource() == B4)//��������
        {
            String num1 = this.T21.getText().trim();
            String qu=num1.substring(2,4);
            String num2 = this.T22.getText().trim();
            String num3 = this.T23.getText().trim();
            String num4 = this.T24.getText().trim();
            String num5 = this.T25.getText().trim();
            String num6 = this.T26.getText().trim();
            boolean a=qu.equals(num6);
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                Statement s=ct.createStatement( );

                ps = ct.prepareStatement("update BJphone set BJphone.sl=BJphone.sl-? where BJphone.num=?");
                ps.setInt(1, Integer.parseInt(num5));
                ps.setString(2, num1);
                ps.executeUpdate();
                ResultSet rs=s.executeQuery("select * from phone where num = '"+num1+"'");
                if(rs.next( )) {
                    ps = ct.prepareStatement("update phone set phone.sl=phone.sl+? where num=?");
                    ps.setInt(1, Integer.parseInt(num5));
                    ps.setString(2, num1);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "ϵͳ��⵽�ֿ��д��ڴ˱�Ų�Ʒ�����Զ�����");
                }
                else {
                    ps = ct.prepareStatement("insert into phone values (?,?,?,?,?,?)");
                    ps.setString(1, num1);
                    ps.setString(2, num2);
                    ps.setString(3, num3);
                    ps.setString(4, num4);
                    ps.setInt(5, Integer.parseInt(num5));
                    ps.setString(6, num6);
                    ps.executeUpdate();
                    //JOptionPane.showMessageDialog(null, "�������һ���µĲ�Ʒ���ͣ�");
                    OK2();
                }
            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new PhoneModel();
            sm1 = new BJPhoneModel();
            //����jtable
            jt1.setModel(sm1);
            jt.setModel(sm);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");T25.setText(" ");T26.setText(" ");
        }
    }
}
