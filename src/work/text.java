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
public class text {
    JFrame F1;
    JPanel P1;
    public text()
    {
        Image image=new ImageIcon("D:\\javaworkspace\\warehouse\\src\\work\\3.PNG").getImage();
        F1=new JFrame("使用说明");
        F1.setSize(800,450);
        F1.setLocation(500,300);
        //P1=new JPanel();
        P1=new BackgroundPanel(image);
        F1.add(P1);
        F1.setVisible(true);
        F1.setDefaultCloseOperation(1);
    }
}
