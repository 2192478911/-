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
public class inku implements ActionListener {
    JFrame F1;
    JLabel L1,L2,L3,L4,L5,L6;
    JTextField T21,T22,T23,T24,T25,T26;
    JPanel P1,P2,P3;
    JButton B21;
    JTable jt;
    JScrollPane jsp;
    PhoneModel sm;
    Statement stat = null;
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    public static String id;
    int row=-1;
    public void Error() {//Error����
        JOptionPane.showMessageDialog(null, "�����Ʒ��źͿ�λ����ƥ�䣬��ȷ�ϣ�");
    }
    public inku()
    {
        Color mycolor=new Color(176,226,255);
        F1=new JFrame("�ֻ����");
        F1.setSize(800,250);
        F1.setLocation(300,200);
        L1= new JLabel("���");L2=new JLabel("����");
        L3=new JLabel("��ɫ");L4=new JLabel("����");L5=new JLabel("����");L6=new JLabel("��λ��");
        //L3=new JLabel("�޸�����");
        P1=new JPanel();P2=new JPanel();P3=new JPanel();
        P3.setBackground(mycolor);
        B21=new JButton("ȷ�����");

        B21.addActionListener(this);

        T21=new JTextField(20);T22=new JTextField(20);T23=new JTextField(20);
        T24=new JTextField(20);T25=new JTextField(20);T26=new JTextField(20);

        P1.add(L1);P1.add(T21);P1.add(L2);P1.add(T22);P1.add(L3);P1.add(T23);
        P2.add(L4);P2.add(T24);P2.add(L5);P2.add(T25);P2.add(L6);P2.add(T26);
        P3.add(B21);
        sm = new PhoneModel();
        //��ʼ��
        jt = new JTable(sm);
        jsp = new JScrollPane(jt);
        //F1.add(jsp);
        F1.add(P1,"North");
        F1.add(P2,"Center");
        F1.add(P3,"South");
        F1.setDefaultCloseOperation(1);
        F1.setVisible(true);
    }
    public void actionPerformed(ActionEvent e2 ) {
        if(e2.getSource() == B21) {//���
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
                ResultSet rs=s.executeQuery("select * from phone where num = '"+num1+"'");
                if(!a)
                    JOptionPane.showMessageDialog(null, "�����Ʒ��źͿ�λ����ƥ�䣬��ȷ�ϣ�");
                else if(rs.next( )) {
                    ps = ct.prepareStatement("update phone set sl=phone.sl+? where num=?");
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
                    JOptionPane.showMessageDialog(null, "�������һ���µĲ�Ʒ���ͣ�");
                }
            }catch(Exception e1) { e1.printStackTrace(); }
            sm = new PhoneModel();
            //����jtable
            jt.setModel(sm);
            T21.setText(" "); T22.setText(" ");T23.setText(" ");T24.setText(" ");T25.setText(" ");T26.setText(" ");
        }
    }
}
