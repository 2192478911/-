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
public class KCdiaobo implements ActionListener{
    JFrame F1;
    JLabel L21,L22,L23,L24;
    JTextField T21,T22,T23,T24;
    JButton B1,B2;
    JPanel P1,P2;
    JTable jt;
    JScrollPane jsp;
    PhoneModel sm;
    Statement stat = null;
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    int row=-1;
    public void OK1() {//Error����
        JOptionPane.showMessageDialog(null, "�����ɹ�");
    }
    public KCdiaobo(){
        Color mycolor=new Color(176,226,255);
        Color mycolor2=new Color(175,238,238);
        F1=new JFrame("������");
        F1.setSize(1000,400);
        F1.setLocation(300,200);
        L21=new JLabel("��Ʒ���");L22=new JLabel("��ǰ��λ");L23=new JLabel("����");L24=new JLabel("������λ");
        T21=new JTextField(15);T22=new JTextField(15);T23=new JTextField(15);T24=new JTextField(15);
        B1=new JButton("ȷ��ѡ��");B2=new JButton("ȷ�ϵ���");
        B1.addActionListener(this);B2.addActionListener(this);
        sm = new PhoneModel();
        //��ʼ��
        jt = new JTable(sm);
        jsp = new JScrollPane(jt);
        P1=new JPanel();P1.add(B1);
        P2=new JPanel();P2.add(L21);P2.add(T21);P2.add(L22);P2.add(T22);P2.add(L23);P2.add(T23);P2.add(L24);P2.add(T24);P2.add(B2);
        P1.setBackground(mycolor);P2.setBackground(mycolor);
        jt.setBackground(mycolor2);
        F1.add(jsp);
        F1.add(P1,"North"); F1.add(P2,"South");
        F1.setDefaultCloseOperation(1);
        F1.setVisible(true);
    }
    public void actionPerformed(ActionEvent e2 )
    {
        if(e2.getSource()==B1 )//ȷ��ѡ��
        {
            row = jt.getSelectedRow();
            if(row==-1) {
                //��ʾ
                JOptionPane.showMessageDialog(null, "��ѡ��һ��");
            }
            T21.setText((String) sm.getValueAt(row, 0));
            T22.setText((String) sm.getValueAt(row, 5));
            T23.setText(sm.getValueAt(row, 4).toString());//����

        }
        if(e2.getSource()==B2 )//ȷ�ϵ���
        {
            String num1 = this.T21.getText().trim();//���
            //String qu=num1.substring(2,4);
            String num2 = this.T22.getText().trim();//��ǰ��λ
            String num3 = this.T23.getText().trim();//����
            String num4 = this.T24.getText().trim();//������λ
            String xin=num1.replace(num2,num4);
            String name=(String) sm.getValueAt(row, 1);
            String color=(String) sm.getValueAt(row, 2);
            String cs=(String) sm.getValueAt(row, 3);
            try{
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String user = "root";
                String passwd = "1234";

                ct = DriverManager.getConnection(url, user, passwd);
                Statement s=ct.createStatement( );

                //ps = ct.prepareStatement("update phone set phone.sl=phone.sl-? where phone.num=?");
                //ps.setInt(1, Integer.parseInt(num3));
                //ps.setString(2, num1);
                //ps.executeUpdate();
                ResultSet rs=s.executeQuery("select * from phone where num = '"+xin+"'");
                if(rs.next( )) {
                    ps = ct.prepareStatement("update phone set phone.sl=phone.sl+? where num=?");
                    ps.setInt(1, Integer.parseInt(num3));
                    ps.setString(2, xin);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "ϵͳ��⵽������λ���ڴ˱�Ų�Ʒ�����Զ�����");
                    ps = ct.prepareStatement("update phone set phone.sl=phone.sl-? where num=?");
                    ps.setInt(1, Integer.parseInt(num3));
                    ps.setString(2, num1);
                    ps.executeUpdate();
                }
                else {
                    ps = ct.prepareStatement("insert into phone values (?,?,?,?,?,?)");
                    ps.setString(1, xin);
                    ps.setString(2, name);
                    ps.setString(3, color);
                    ps.setString(4, cs);
                    ps.setInt(5, Integer.parseInt(num3));
                    ps.setString(6, num4);
                    ps.executeUpdate();
                    ps = ct.prepareStatement("update phone set phone.sl=phone.sl-? where num=?");
                    ps.setInt(1, Integer.parseInt(num3));
                    ps.setString(2, num1);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "���ڴ˿�λ������²�Ʒ��");

                }
            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new PhoneModel();
            jt.setModel(sm);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");
        }
    }
}
