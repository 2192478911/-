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
public class changshang implements ActionListener {
    JFrame F1;
    JLabel L1,L2;
    JTextField T1;
    JTextField T21,T22,T23,T24;
    JPanel P1,P2,P3;
    JButton B11,B12,B13,B14;
    JButton B21,B31;
    JTable jt;
    JScrollPane jsp;
    CSModel sm;
    Statement stat = null;
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    public static String id;
    int row=-1;
    public changshang()
    {
        Color mycolor=new Color(176,226,255);
        Color mycolor2=new Color(175,238,238);
        F1=new JFrame("������Ϣ");
        F1.setSize(900,500);
        F1.setLocation(300,200);
        L1= new JLabel("��������");
        L2=new JLabel("���/�޸���Ϣ");
        //L3=new JLabel("�޸�����");
        P1=new JPanel();P2=new JPanel();P3=new JPanel();
        P1.setBackground(mycolor);
        P2.setBackground(mycolor);
        B11=new JButton("��ѯ");B12=new JButton("ɾ��");B13= new JButton("�޸�");B14=new JButton("��ʾȫ��");
        B21=new JButton("�������");
        B31=new JButton("�����޸�");
        B11.addActionListener(this);B12.addActionListener(this);B13.addActionListener(this);B14.addActionListener(this);
        B21.addActionListener(this);B31.addActionListener(this);
        T1=new JTextField(15);
        T21=new JTextField(10);T22=new JTextField(10);T23=new JTextField(10);T24=new JTextField(10);
        P1.add(L1);P1.add(T1);P1.add(B11);P1.add(B12);P1.add(B13);P1.add(B14);
        P2.add(L2); P2.add(T21); P2.add(T22);P2.add(T23);P2.add(T24);P2.add(B21);P2.add(B31);
        sm = new CSModel();
        //��ʼ��
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
        if(e2.getSource() == B11) {//��ѯ
            String num = this.T1.getText().trim();
            String sql = "select * from changshang where name = '"+num+"' ";
            sm = new CSModel(sql);
            //����jtable
            jt.setModel(sm);
        }
        if(e2.getSource() == B12) {//ɾ��
            row = this.jt.getSelectedRow();
            if(row==-1 ) {
                //��ʾ
                JOptionPane.showMessageDialog(null, "��ѡ��һ��");
            }
            String num1 = (String)sm.getValueAt(row, 0);
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                //System.out.println("���ӳɹ�");
                ps = ct.prepareStatement("delete from changshang where name = ? ");
                ps.setString(1,num1);
                ps.executeUpdate();

            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new CSModel();
            //����jtable
            jt.setModel(sm);
        }
        if(e2.getSource() == B13) {//�޸�
            row = this.jt.getSelectedRow();
            if(row==-1 ) {
                //��ʾ
                JOptionPane.showMessageDialog(null, "��ѡ��һ��");
            }
            T21.setText((String)sm.getValueAt(row, 0));
            T22.setText((String)sm.getValueAt(row, 1));
            T23.setText((String)sm.getValueAt(row, 2));
            T24.setText((String)sm.getValueAt(row, 3));
        }
        if(e2.getSource() == B14) {//��ʾȫ��
            String sql = "select * from changshang ";
            sm = new CSModel(sql);
            //����jtable
            jt.setModel(sm);
        }
        if(e2.getSource() == B21) {//���
            String num1 = this.T21.getText().trim();
            String num2 = this.T22.getText().trim();
            String num3 = this.T23.getText().trim();
            String num4 = this.T24.getText().trim();
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                //System.out.println("���ӳɹ�");
                ps = ct.prepareStatement("insert into changshang values (?,?,?,?)");
                ps.setString(1,num1);
                ps.setString(2,num2);
                ps.setString(3,num3);
                ps.setString(4,num4);
                ps.executeUpdate();

            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new CSModel();
            //����jtable
            jt.setModel(sm);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");
        }
        if(e2.getSource() == B31) {//�����޸�
            String num1 = this.T21.getText().trim();
            String num2 = this.T22.getText().trim();
            String num3 = this.T23.getText().trim();
            String num4 = this.T24.getText().trim();
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                //System.out.println("���ӳɹ�");
                ps = ct.prepareStatement("Update changshang set address=?,host=?,tel=? where name=?");
                ps.setString(1, num2);
                ps.setString(2, num3);
                ps.setString(3, num4);
                ps.setString(4, num1);
                ps.executeUpdate();
            }catch(Exception e) { e.printStackTrace(); }
            sm = new CSModel();
            //����jtable
            jt.setModel(sm);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");
        }
    }
}
