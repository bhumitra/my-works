import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
 
 
 
@SuppressWarnings("unused")
public class ParallelQueryDetails {
 
                @SuppressWarnings("rawtypes")
                public static Vector columnNamesDetails;
                @SuppressWarnings("rawtypes")
                public static Vector dataDetails;
               
               
 
 
 
 
                @SuppressWarnings({ "unchecked", "rawtypes" })
                public ParallelQueryDetails(String text,String dbid,String sql) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
 
                                System.out.print("constructor called");
                                System.out.print("text" + text);
                                System.out.print("dbid " + dbid);
                               
                               
                               
                                Vector columnNames = new Vector();
                                Vector data = new Vector();
                               
                                String urlrot = "jdbc:oracle:thin:@:1521:ROTBP1AP";
                                String urlmob = "jdbc:oracle:thin:@:1521:MOBBP1AP";
                                String urlorc = "jdbc:oracle:thin:@:1521:ORCBP1AP";
                                String urlwln = "jdbc:oracle:thin:@:1521:WLNBP1AP";
                                String urlbpd = "jdbc:oracle:thin:@:1521:BPDBP1AP";
                                String urlbulk = "jdbc:oracle:thin:@:1521:BULBP1AP";
                                String urlptv = "jdbc:oracle:thin:@:1521:PTVBP1AP";
                                String urlusm = "jdbc:oracle:thin:@:1521:USMBP1AP";
 
                                // String url="jdbc:oracle:thin:@localhost:1521:name";
 
                                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                               
                               
                                //String sql = "Select * from MYSHOPEE_LOGINDETAILS";
 
                                // String sql2 = "select d.name,v.sql_id, count (v.sql_id) as count,pms.value as PX_MAX from v$parameter pms, v$database d, v$px_process x, v$lock l, v$session v, v$session_wait w1, v$session_wait w2 where x.sid <> l.sid(+) and   to_number (substr(x.server_name,3)) = l.id2(+) and   x.sid = w1.sid(+) and   l.sid = w2.sid(+) and   x.sid = v.sid(+) and   nvl(l.type,'PS') = 'PS' and pms.name like '%parallel_max_servers%' group by v.sql_id,d.name,pms.value";
                                //String sql1= "SELECT sh.snap_id, sh.begin_interval_time, s.sql_id, s.executions_delta, s.elapsed_time_delta / DECODE (s.executions_delta, 0, 1, s.executions_delta)/ 1000000 as sec FROM SYS.dba_hist_sqlstat s, SYS.dba_hist_snapshot sh WHERE s.snap_id = sh.snap_id AND sh.begin_interval_time > (SYSDATE - 7) AND s.SQL_ID=? AND ROWNUM IN (1,2,3) order by sec desc";
                                //String sql2="select sql_fulltext from v$sql where sql_id='?'";
                               
                                //String sql = "select sid,username,status from v$session";
                                               
                                if (dbid.equalsIgnoreCase("ROTBP1AP")){
                                                Connection conrot = DriverManager.getConnection(
                                                                                urlrot , "", "");
                                                PreparedStatement sROT = conrot.prepareStatement(sql);
                                                sROT.setString(1, text);
                                                ResultSet resultSetROT = sROT.executeQuery();
                                                ResultSetMetaData mdROT = resultSetROT.getMetaData();
                                               
                                               
                                                int columns = mdROT.getColumnCount();
                                                for (int i = 1; i <= columns; i++) {
                                                                columnNames.addElement(mdROT.getColumnName(i));
                                                }
                                                while (resultSetROT.next()) {
                                                                Vector row = new Vector(columns);
                                                                for (int i = 1; i <= columns; i++) {
                                                                                row.addElement(resultSetROT.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                               
                                                 System.out.println(data);
                                                System.out.println(columnNames);
                                               
                                                 columnNamesDetails = columnNames;
                                                dataDetails = data;
                                               
                                                resultSetROT.close();
                                                sROT.close();
                                                conrot.close();
                                               
                                               
                                               
                                }
                                if (dbid.equalsIgnoreCase("MOBBP1AP")){
                                               
                                                System.out.println("in MOB");
                                                Connection conmob = DriverManager.getConnection(
                                                                                urlmob , "", "");
                                               
                                                PreparedStatement sMOB = conmob.prepareStatement(sql);
                                                sMOB.setString(1, text);
                                                ResultSet resultSetMOB = sMOB.executeQuery();
                                               
                                                ResultSetMetaData mdMOB = resultSetMOB.getMetaData();
                                               
                                                System.out.println("query executed");
                                                //
                                                int columns = mdMOB.getColumnCount();
                                                for (int i = 1; i <= columns; i++) {
                                                                columnNames.addElement(mdMOB.getColumnName(i));
                                                }
                                                while (resultSetMOB.next()) {
                                                                Vector row = new Vector(columns);
                                                                for (int i = 1; i <= columns; i++) {
                                                                                row.addElement(resultSetMOB.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                               
                                                 System.out.println(data);
                                                System.out.println(columnNames);
                                               
                                                 columnNamesDetails = columnNames;
                                                dataDetails = data;
                                               
                                                resultSetMOB.close();
                                                sMOB.close();
                                                conmob.close();
                                               
                                               
                                               
                                }
                                if (dbid.equalsIgnoreCase("ORCBP1AP")){
                                               
                                                System.out.println("in orc");
                                               
                                                Connection conorc = DriverManager.getConnection(
                                                                                urlorc , "", "");
                                               
                                                PreparedStatement sORC = conorc.prepareStatement(sql);
                                                sORC.setString(1, text);
                                                ResultSet resultSetORC = sORC.executeQuery();
                                                ResultSetMetaData mdORC = resultSetORC.getMetaData();
                                                //
                                               
                                               
                                                System.out.println("query executed");
                                                int columnsORC = mdORC.getColumnCount();
                                               
                                                 for (int i = 1; i <= columnsORC; i++) {
                                                                columnNames.addElement(mdORC.getColumnName(i));
                                                }
 
                                                while (resultSetORC.next()) {
                                                                Vector row = new Vector(columnsORC);
                                                                for (int i = 1; i <= columnsORC; i++) {
                                                                                row.addElement(resultSetORC.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                               
                                                 
                                                 System.out.println(data);
                                                System.out.println(columnNames);
                                                columnNamesDetails = columnNames;
                                                dataDetails = data;
                                                resultSetORC.close();
                                                sORC.close();   
                                                 conorc.close();
                                }
                                if (dbid.equalsIgnoreCase("WLNBP1AP")){
                                               
                                                System.out.println("in WLN");
                                                Connection conwln = DriverManager.getConnection(
                                                                                urlwln , "", "");
                                                PreparedStatement sWLN = conwln.prepareStatement(sql);
                                                sWLN.setString(1, text);
                                                ResultSet resultSetWLN = sWLN.executeQuery();
                                                ResultSetMetaData mdWLN = resultSetWLN.getMetaData();
                                                //
                                               
                                                int columnsWLN = mdWLN.getColumnCount();
                                                for (int i = 1; i <= columnsWLN; i++) {
                                                                columnNames.addElement(mdWLN.getColumnName(i));
                                                }
                                                while (resultSetWLN.next()) {
                                                                Vector row = new Vector(columnsWLN);
                                                                for (int i = 1; i <= columnsWLN; i++) {
                                                                                row.addElement(resultSetWLN.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                               
                                                 
                                                 System.out.println(data);
                                                System.out.println(columnNames);
                                                columnNamesDetails = columnNames;
                                                dataDetails = data;
                                               
                                                 resultSetWLN.close();
                                                sWLN.close();
                                                conwln.close();
                                }
                                if (dbid.equalsIgnoreCase("BPDBP1AP")){
                                                System.out.print("inside bpd");
                                                Connection conbpd = DriverManager.getConnection(
                                                                                urlbpd , "", "");
                                                PreparedStatement sBPD = conbpd.prepareStatement(sql);
                                                sBPD.setString(1, text);
                                                ResultSet resultSetBPD = sBPD.executeQuery();
                                                ResultSetMetaData mdBPD = resultSetBPD.getMetaData();
                                                //
                                               
                                                int columnsBPD = mdBPD.getColumnCount();
                                                for (int i = 1; i <= columnsBPD; i++) {
                                                                columnNames.addElement(mdBPD.getColumnName(i));
                                                }
                                                while (resultSetBPD.next()) {
                                                                Vector row = new Vector(columnsBPD);
                                                                for (int i = 1; i <= columnsBPD; i++) {
                                                                                row.addElement(resultSetBPD.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                               
                                                 
                                                 System.out.println("in bpd   "+data);
                                                System.out.println(columnNames);
                                                columnNamesDetails = columnNames;
                                                dataDetails = data;
                                               
                                                 
                                                 
                                                 resultSetBPD.close();
                                                sBPD.close();  
                                                 conbpd.close();
                                               
                                               
                                }
                                if (dbid.equalsIgnoreCase("BULBP1AP")){
 
                                                Connection conbulk = DriverManager.getConnection(
                                                                                urlbulk , "", "");
                                                PreparedStatement sBULK = conbulk.prepareStatement(sql);
                                                sBULK.setString(1, text);
                                                ResultSet resultSetBULK = sBULK.executeQuery();
                                                ResultSetMetaData mdBULK = resultSetBULK.getMetaData();
                                               
                                                int columnsBULK = mdBULK.getColumnCount();
                                                for (int i = 1; i <= columnsBULK; i++) {
                                                                columnNames.addElement(mdBULK.getColumnName(i));
                                                }
                                                while (resultSetBULK.next()) {
                                                                Vector row = new Vector(columnsBULK);
                                                                for (int i = 1; i <= columnsBULK; i++) {
                                                                                row.addElement(resultSetBULK.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                                resultSetBULK.close();
                                                sBULK.close();  
                                                 conbulk.close();
                                               
                                               
                                               
                                }
                                if (dbid.equalsIgnoreCase("PTVBP1AP")){
 
                                                Connection conptv = DriverManager.getConnection(
                                                                                urlptv , "", "");
                                                PreparedStatement sPTV = conptv.prepareStatement(sql);
                                                sPTV.setString(1, text);
                                                ResultSet resultSetPTV = sPTV.executeQuery();
                                                ResultSetMetaData mdPTV = resultSetPTV.getMetaData();
                                               
                                                int columnsPTV = mdPTV.getColumnCount();
                                                for (int i = 1; i <= columnsPTV; i++) {
                                                                columnNames.addElement(mdPTV.getColumnName(i));
                                                }
                                               
                                                while (resultSetPTV.next()) {
                                                                Vector row = new Vector(columnsPTV);
                                                                for (int i = 1; i <= columnsPTV; i++) {
                                                                                row.addElement(resultSetPTV.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                                resultSetPTV.close();
                                                sPTV.close();  
                                                 conptv.close();
                                }
                                if (dbid== "USMBP1AP"){
 
                                                Connection conusm = DriverManager.getConnection(
                                                                                urlusm , "", "");
                                                PreparedStatement sUSM = conusm.prepareStatement(sql);
                                                sUSM.setString(1, text);
                                                ResultSet resultSetUSM = sUSM.executeQuery();
                                                ResultSetMetaData mdUSM = resultSetUSM.getMetaData();
                                               
                                                int columnsUSM = mdUSM.getColumnCount();
                                                for (int i = 1; i <= columnsUSM; i++) {
                                                                columnNames.addElement(mdUSM.getColumnName(i));
                                                }
                                               
                                                while (resultSetUSM.next()) {
                                                                Vector row = new Vector(columnsUSM);
                                                                for (int i = 1; i <= columnsUSM; i++) {
                                                                                row.addElement(resultSetUSM.getObject(i));
                                                                }
                                                                data.addElement(row);
                                                }
                                                resultSetUSM.close();
                                                sUSM.close();  
                                                 conusm.close();
                                }
 
 
                }
}
 
 
