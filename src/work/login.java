package work;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import javax.swing.JPasswordField;

public class login implements ActionListener {

    public static String sID;
    public static String sKEY;
    JFrame f;
    JPanel P1,P2;
    JLabel user, pass;
    JButton log, register,text;
    JTextField username;
    JTextField password;

    public void show() {
        Image image1=new ImageIcon("D:\\javaworkspace\\warehouse\\src\\work\\2.png").getImage();
        P2=new BackgroundPanel(image1);
        //P2.setLayout(null);
        //P2=new JPanel();
        f = new JFrame("����Ա��¼");
        f.setSize(680, 470);//���崰������
        f.setLocation(450, 210);
        f.setLayout(null);
        user = new JLabel("�û���"); //����������ǩ�������ı���
        username = new JTextField(20);
        pass = new JLabel("��    ��");
        password = new JPasswordField(20);//������û����ַ���*��
        log = new JButton("��¼");
        register=new JButton("ע��");
        text=new JButton("ʹ��˵��");
        P2.setBounds(0,0,680,470);
        username.setBounds(130,70,150,30);
        password.setBounds(130,105,150,30);
        text.setBounds(400,190,90,20);
        text.setContentAreaFilled(false);
        log.setBounds(130,150,60,20);
        register.setBounds(220,150,60,20);
        user.setBounds(80,70,50,40);
        pass.setBounds(80,100,50,40);
        username.addActionListener(this); //ע���¼���������
        password.addActionListener(this);
        text.addActionListener(this);
        username.setText("root");
        password.setText("6666");
        log.addActionListener(this);
        register.addActionListener(this);
        f.add(username);
        f.add(password);
        f.add(log);
        f.add(register);
        f.add(text);
        f.add(user);
        f.add(pass);
        f.add(P2);
        f.addWindowListener(new Close1());
        f.setVisible(true);//������ӻ�
    }
    public static void main(String[ ] args) {
        String JDriver = "com.mysql.cj.jdbc.Driver";  //����JDBC�����������
        String conURL = "jdbc:mysql://localhost:3306/runoob?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String USER = "root";       //MySql�û���
        String PASS = "1234";   //MySql���룬Ҫ�����������
        try {
            Class.forName(JDriver);  //����JDBC��������
        }
        catch(java.lang.ClassNotFoundException e) {
            System.out.println("ForName :" + e.getMessage( ));
        }
        try {
            Connection con= DriverManager.getConnection(conURL,USER,PASS);  //�������ݿ�URL
            Statement s=con.createStatement( );  //����Statement�����
            String query = "create table WHlogin ( "
                    + "idd char(255),"
                    + "keyy char(255)"
                    + ")";
            String query2 = "create table phone ( "
                    + "num char(255),"
                    + "name char(255),"
                    + "color char(255),"
                    + "cs char(255),"
                    + "sl int,"
                    + "kw char(255)"
                    + ")";
            String query3 = "create table orders ( "
                    + "id char(255),"
                    + "name char(255),"
                    + "tel char(255),"
                    + "num char(255),"
                    + "sl int,"
                    + "money char(255),"
                    + "time char(255)"
                    + ")";
            String query4 = "create table changshang ( "
                    + "name char(255),"
                    + "address char(255),"
                    + "host char(255),"
                    + "tel char(255)"
                    + ")";
            String query5 = "create table BJphone ( "
                    + "num char(255),"
                    + "name char(255),"
                    + "color char(255),"
                    + "cs char(255),"
                    + "sl int,"
                    + "kw char(255)"
                    + ")";
            s.executeUpdate(query5);
            s.executeUpdate(query4);
            s.executeUpdate(query3);
            s.executeUpdate(query2);
            s.executeUpdate(query);
            s.close( );  //�ͷ�Statement�����ӵ����ݿ⼰JDBC��Դ
            con.close( );  //�ر������ݿ������
        }
        catch(SQLException e){ System.out.println("SQLException: " +e.getMessage( ));  }
        login Loginer=new login();
        Loginer.show();
    }

    public void Error() {//Error����
        JOptionPane.showMessageDialog(null, "�û������������");
    }
    public void OK() {//OK����
        JOptionPane.showMessageDialog(null, "��¼�ɹ���");
    }

    public void actionPerformed(ActionEvent e)//��ȡ�¼�
    {
        if (e.getSource() == text)
        {
            new text();
        }
         if (e.getSource() == register)//ע��
        {
            new loginadd();
        }
        else if(e.getSource() == log){//��¼
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
                String ID = username.getText();
                //String KEY = this.password.getText().trim();
                String KEY = password.getText();
                ResultSet rs=s.executeQuery("select * from WHlogin where idd = '"+ID+"'");
                if(rs.next( )) {
                    ResultSet count2 = s.executeQuery   ("select keyy from  WHlogin  where idd='"+ID+"'" );
                    if(count2.next())
                    {
                        //������û���Ӧ�������������������ȣ�˵����֤ͨ��
                        if (count2.getString("keyy").equals(KEY))
                        {
                            sID=username.getText();
                            mainmenu menu=new mainmenu();
                            f.dispose();
                        }
                        else
                            Error();
                    }
                }
                else
                    Error();
                s.close( );  //�ͷ�Statement�����ӵ����ݿ⼰JDBC��Դ
                con.close( );  //�ر������ݿ������
            }
            catch(SQLException e1){
                //System.out.println("SQLException: " +e1.getMessage( ));
                Error();
            }
        }
    }
}
class Close1 implements WindowListener
{ public void windowClosing(WindowEvent e)
{
    System.exit(0);
}
    public void windowOpened(WindowEvent e){ } //���ڴ�ʱ����
    public void windowIconified(WindowEvent e){ } //����ͼ�껯ʱ����
    public void windowDeiconified(WindowEvent e){ } //���ڷ�ͼ�껯ʱ����
    public void windowClosed(WindowEvent e){ } //���ڹر�ʱ����
    public void windowActivated(WindowEvent e){ } //���ڼ���ʱ����
    public void windowDeactivated(WindowEvent e){ } //���ڷǼ���ʱ����
}
