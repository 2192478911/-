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

public class outku implements ActionListener {
    JFrame F1;
    JLabel L1,L21,L22,L23,L24,L25,L26,L27;
    JTextField T1;
    JTextField T21,T22,T23,T24,T25,T26,T27;
    //JTextField T31,T32,T33,T34,T35;
    JPanel P1,P2,P3;
    JButton B11,B12;
    JButton B21;
    JTable jt,jt1;
    JScrollPane jsp1;
    orderModel sm;
    PhoneModel sm1;
    Statement stat = null;
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    //public static String id;
    int row=-1;
    public void Error() {//Error����
        JOptionPane.showMessageDialog(null, "�ֿ���û�д���Ʒ��������ȷ����Ʒ���");
    }
    public outku(){
        Color mycolor=new Color(176,226,255);
        Color mycolor2=new Color(175,238,238);
        F1=new JFrame("����ҵ��");
        F1.setSize(1200,550);
        F1.setLocation(300,200);
        L1= new JLabel("�ֻ����");
        L21=new JLabel("������");L22=new JLabel("�ͻ���");L23=new JLabel("�绰");L24=new JLabel("��Ʒ���");
        L25=new JLabel("����");L26=new JLabel("���");L27=new JLabel("����");
        P1=new JPanel();P2=new JPanel();P3=new JPanel();
        P1.setBackground(mycolor);
        P2.setBackground(mycolor);
        B11=new JButton("��ѯ");B12=new JButton("ȷ��ѡ��");
        B21=new JButton("ȷ�ϳ���");
        B11.addActionListener(this);B12.addActionListener(this);
        B21.addActionListener(this);
        T1=new JTextField(15);
        T21=new JTextField(10);T22=new JTextField(10);T23=new JTextField(10);T24=new JTextField(10);
        T25=new JTextField(10);T26=new JTextField(10);T27=new JTextField(10);
        P1.add(L1);P1.add(T1);P1.add(B11);P1.add(B12);
        P2.add(L21); P2.add(T21);P2.add(L22);P2.add(T22);P2.add(L23);P2.add(T23);P2.add(L24);P2.add(T24);
        P2.add(L25);P2.add(T25);P2.add(L26);P2.add(T26);P2.add(L27);P2.add(T27);P2.add(B21);
        sm1 = new PhoneModel();
        sm = new orderModel();
        //��ʼ��
        jt1 = new JTable(sm1);
        jt = new JTable(sm);
        jt1.setBackground(mycolor2);
        jsp1 = new JScrollPane(jt1);
        F1.add(jsp1);
        F1.add(P1,"North");
        F1.add(P2,"South");
        // F1.add(P3);
        F1.setDefaultCloseOperation(1);
        F1.setVisible(true);
    }
    public void actionPerformed(ActionEvent e2 ){
        if(e2.getSource() == B11)//��ѯ
        {
            String num = this.T1.getText().trim();
            String sql = "select * from phone where num like '%"+num+"%' ";
            sm1 = new PhoneModel(sql);
            //����jtable
            jt.setModel(sm1);
        }
        if(e2.getSource() == B12)//ȷ��ѡ��
        {
            row = jt1.getSelectedRow();
            if(row==-1) {
                //��ʾ
                JOptionPane.showMessageDialog(null, "��ѡ��һ��");
            }
            T24.setText((String)sm1.getValueAt(row, 0));
            T25.setText(sm1.getValueAt(row, 4).toString());
        }
            if(e2.getSource() == B21) {//ȷ�ϳ���
            String num1 = this.T21.getText().trim();
            String num2 = this.T22.getText().trim();
            String num3 = this.T23.getText().trim();
            String num4 = this.T24.getText().trim();
            String num5 = this.T25.getText().trim();
            String num6 = this.T26.getText().trim();
            String num7 = this.T27.getText().trim();
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                Statement s=ct.createStatement( );
                //System.out.println("���ӳɹ�");
                if(num1==null)
                    JOptionPane.showMessageDialog(null, "������Ϣ����������ȷ��");
                else {
                    ps = ct.prepareStatement("insert into orders values (?,?,?,?,?,?,?)");
                    ps.setString(1, num1);
                    ps.setString(2, num2);
                    ps.setString(3, num3);
                    ps.setString(4, num4);
                    ps.setInt(5, Integer.parseInt(num5));
                    ps.setString(6, num6);
                    ps.setString(7, num7);
                    ps.executeUpdate();
                    //ps = ct.prepareStatement("delete from phone where num = ? ");
                    ps = ct.prepareStatement("update phone set sl=phone.sl-? where num=?");
                    ps.setInt(1, Integer.parseInt(num5));
                    ps.setString(2, num4);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "�ɹ�����");
                }
            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new orderModel();
                //����jtable
            jt.setModel(sm);
            sm1 = new PhoneModel();
            //����jtable
            jt1.setModel(sm1);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");T25.setText(" ");T26.setText(" ");T27.setText(" ");
        }
    }
}
