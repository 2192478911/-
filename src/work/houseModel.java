package work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;

public class houseModel extends AbstractTableModel {
    Vector rowData,columnNames;
    Statement stat = null;
    Connection ct = null;
    ResultSet rs = null;
    public void init(String sql){
        if(sql.equals("")){
            sql = "select * from WHlogin";
        }
        columnNames = new Vector();
        rowData = new Vector();
        columnNames.add("账号");
        columnNames.add("密码");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/runoob?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String passwd = "1234";
            ct = DriverManager.getConnection(url,user,passwd);
            stat = ct.createStatement();//创建stat对象
            rs = stat.executeQuery(sql);//查询结果
            while(rs.next()){
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                rowData.add(hang);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public houseModel(String sql){
        this.init(sql);
    }
    public houseModel(){
        this.init("");
    }
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }
    public Object getValueAt(int row, int column) {
        // TODO Auto-generated method stub
        return ((Vector)(this.rowData.get(row))).get(column);
    }
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String)this.columnNames.get(column);
    }
    public boolean isCellEditable(int nRow, int nCol) {
        return true;
    }

}
