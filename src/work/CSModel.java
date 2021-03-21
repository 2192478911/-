package work;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;

public class CSModel extends AbstractTableModel {
    Vector rowData1,columnNames1;
    Statement stat = null;
    Connection ct = null;
    ResultSet rs = null;
    public void init1(String sql) {
        if (sql.equals("")) {
            sql = "select * from changshang";
        }
        columnNames1 = new Vector();
        rowData1 = new Vector();
        columnNames1.add("厂商名称");
        columnNames1.add("地址");
        columnNames1.add("法人");
        columnNames1.add("联系电话");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/runoob?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String passwd = "1234";
            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();//创建stat对象
            rs = stat.executeQuery(sql);//查询结果
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                rowData1.add(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public CSModel(String sql){
        this.init1(sql);
    }
    public CSModel(){
        this.init1("");
    }
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData1.size();
    }
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames1.size();
    }
    public Object getValueAt(int row, int column) {
        // TODO Auto-generated method stub
        return ((Vector)(this.rowData1.get(row))).get(column);
    }
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String)this.columnNames1.get(column);
    }
    public boolean isCellEditable(int nRow, int nCol) {
        return true;
    }
}
