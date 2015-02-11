
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.PopupMenu;
import java.awt.Toolkit;
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
import java.util.Random;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
 
/**
*
* @author bhumitra_nagar
*/
public class ParallelQuery {
 
 
                private JTable table;
                private JTable tableDetails;
                private DefaultTableModel model;
                private JTextField text;
 
                static List sid = new LinkedList();
                static List oldsid = new LinkedList();
                static List dbid= new LinkedList();
                static int runcount=0;
                static int clicked = 0;
                static String prevpassed = "demo";
 
                //static String sql1= "SELECT sh.snap_id, sh.begin_interval_time, s.sql_id, s.executions_delta, s.elapsed_time_delta / DECODE (s.executions_delta, 0, 1, s.executions_delta)/ 1000000 as sec FROM SYS.dba_hist_sqlstat s, SYS.dba_hist_snapshot sh WHERE s.snap_id = sh.snap_id AND sh.begin_interval_time > (SYSDATE - 7) AND s.SQL_ID=? AND ROWNUM IN (1,2,3) order by sec desc";
                static String sql2="select sql_text from v$sqltext_with_newlines where sql_id=? order by piece";
                static String sql1= "SELECT sh.snap_id, sh.begin_interval_time, s.sql_id, s.executions_delta, s.elapsed_time_delta / DECODE (s.executions_delta, 0, 1, s.executions_delta)/ 1000000 as sec FROM SYS.dba_hist_sqlstat s, SYS.dba_hist_snapshot sh WHERE s.sql_id =? AND s.snap_id = sh.snap_id AND sh.begin_interval_time > (SYSDATE - 7) AND ROWNUM<6 order by sec desc";
 
 
                public void getDetails(String text){
 
 
 
 
                }
 
                public synchronized void addMouseListener(MouseListener l)
                {
                                table.addMouseListener(l);
                }
 
                public synchronized void removeMouseListener(MouseListener l)
                {
                                table.removeMouseListener(l);
                }
 
                public Object getSelectedValue()
                {
                                int col = table.getSelectedColumn();
                                int row = table.getSelectedRow();
                                return table.getModel().getValueAt(row, col);
                }
                /**
                * @param args the command line arguments
                */
                public static void main(String[] args){
                                // TODO code application logic here
 
 
 
 
 
                                while(true){
 
                                                final JFrame frame = new JFrame("Parallel Sessions Monitor V3.1");
                                                frame.setSize(300, 200); //setting frame size
                                                frame.setVisible(true);
                                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                               
 
                                               
                                               
 
 
 
                                                JLabel label1 = new JLabel("Loading....");
 
 
 
                                                // frame.setLocation();
 
 
                                                Vector columnNames = new Vector();
                                                Vector data = new Vector();
                                                final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                                final JPanel panelDetail = new JPanel(new FlowLayout(FlowLayout.RIGHT));
 
                                                panel.add(label1);
                                                frame.add(panel);
                                                final JLabel label2 = new JLabel("");
                                                final JPanel pan = new JPanel();
                                                pan.add(label2);
                                                frame.add(pan,BorderLayout.SOUTH);
                                               
                                               
                                                try {
 
 
 
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
                                                                Connection conrot = DriverManager.getConnection(
                                                                                                urlrot , "", "");
                                                               
                                                                System.out.println("conneted to ROT");
                                                                Connection conwln = DriverManager.getConnection(
                                                                                                urlwln , "", "");
 
 
 
                                                                Connection conmob = DriverManager.getConnection(
                                                                                                urlmob , "", "");
                                                                Connection conorc = DriverManager.getConnection(
                                                                                                urlorc , "", "");
                                                                Connection conbpd = DriverManager.getConnection(
                                                                                                urlbpd , "", "");
 
                                                                Connection conbulk = DriverManager.getConnection(
                                                                                                urlbulk , "", "");
                                                                Connection conptv = DriverManager.getConnection(
                                                                                                urlptv , "", "");
                                                                Connection conusm = DriverManager.getConnection(
                                                                                                urlusm , "", "");
 
 
                                                                //String sql = "Select * from MYSHOPEE_LOGINDETAILS";
 
                                                                String sql = "select d.name,v.sql_id, count (v.sql_id) as count,pms.value as PX_MAX from v$parameter pms, v$database d, v$px_process x, v$lock l, v$session v, v$session_wait w1, v$session_wait w2 where x.sid <> l.sid(+) and   to_number (substr(x.server_name,3)) = l.id2(+) and   x.sid = w1.sid(+) and   l.sid = w2.sid(+) and   x.sid = v.sid(+) and   nvl(l.type,'PS') = 'PS' and pms.name like '%parallel_max_servers%' group by v.sql_id,d.name,pms.value";
 
 
                                                                // String sql2= "SELECT sh.snap_id, sh.begin_interval_time, s.sql_id, s.executions_delta, s.elapsed_time_delta / DECODE (s.executions_delta, 0, 1, s.executions_delta)/ 1000000 as sec FROM SYS.dba_hist_sqlstat s, SYS.dba_hist_snapshot sh WHERE s.snap_id = sh.snap_id AND sh.begin_interval_time > (SYSDATE - 7) AND s.SQL_ID='8w17rdnu4sm9j' AND ROWNUM IN (1,2,3) order by sec desc";
 
 
                                                                PreparedStatement sROT = conrot.prepareStatement(sql);
                                                                Statement sMOB = conmob.createStatement();
                                                                Statement sBPD = conbpd.createStatement();
                                                                Statement sBULK = conbulk.createStatement();
                                                                Statement sORC = conorc.createStatement();
                                                                Statement sPTV = conptv.createStatement();
                                                                PreparedStatement sWLN = conwln.prepareStatement(sql);
                                                                Statement sUSM = conusm.createStatement();
 
 
 
                                                                ResultSet resultSetROT = sROT.executeQuery();
 
                                                                ResultSet resultSetMOB = sMOB.executeQuery(sql);
                                                                ResultSet resultSetORC = sORC.executeQuery(sql);
                                                                ResultSet resultSetBPD = sBPD.executeQuery(sql);
                                                                ResultSet resultSetBULK = sBULK.executeQuery(sql);
                                                                ResultSet resultSetUSM = sUSM.executeQuery(sql);
                                                                ResultSet resultSetPTV = sPTV.executeQuery(sql);
 
 
                                                                ResultSet resultSetWLN = sWLN.executeQuery();
 
 
 
                                                                ResultSetMetaData mdROT = resultSetROT.getMetaData();
                                                                ResultSetMetaData mdBPD = resultSetBPD.getMetaData();
                                                                ResultSetMetaData mdBULK = resultSetBULK.getMetaData();
                                                                ResultSetMetaData mdMOB = resultSetMOB.getMetaData();
                                                                ResultSetMetaData mdORC = resultSetORC.getMetaData();
                                                                ResultSetMetaData mdPTV = resultSetPTV.getMetaData();
                                                                ResultSetMetaData mdUSM = resultSetUSM.getMetaData();
                                                                ResultSetMetaData mdWLN = resultSetWLN.getMetaData();
 
 
                                                                //ROT
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
                                                                resultSetROT.close();
                                                                sROT.close();
                                                                conrot.close();
 
 
                                                                //WLN
                                                                int columnsWLN = mdWLN.getColumnCount();
 
                                                                while (resultSetWLN.next()) {
                                                                                Vector row = new Vector(columnsWLN);
                                                                                for (int i = 1; i <= columnsWLN; i++) {
                                                                                                row.addElement(resultSetWLN.getObject(i));
                                                                                }
                                                                                data.addElement(row);
                                                                }
                                                                resultSetWLN.close();
                                                                sWLN.close();
                                                                conwln.close();
                                                                //ORC
 
                                                                int columnsORC = mdORC.getColumnCount();
 
                                                                while (resultSetORC.next()) {
                                                                                Vector row = new Vector(columnsORC);
                                                                                for (int i = 1; i <= columnsORC; i++) {
                                                                                                row.addElement(resultSetORC.getObject(i));
                                                                                }
                                                                                data.addElement(row);
                                                                }
                                                                resultSetORC.close();
                                                                sORC.close();   
                                                                conorc.close();
                                                                //MOB
 
                                                                int columnsMOB = mdMOB.getColumnCount();
 
                                                                while (resultSetMOB.next()) {
                                                                                Vector row = new Vector(columnsMOB);
                                                                                for (int i = 1; i <= columnsMOB; i++) {
                                                                                                row.addElement(resultSetMOB.getObject(i));
                                                                                }
                                                                                data.addElement(row);
                                                                }
                                                                resultSetMOB.close();
                                                                sMOB.close();   
                                                                conmob.close();
 
                                                                //BPD
 
                                                                int columnsBPD = mdBPD.getColumnCount();
 
                                                                while (resultSetBPD.next()) {
                                                                                Vector row = new Vector(columnsBPD);
                                                                                for (int i = 1; i <= columnsBPD; i++) {
                                                                                                row.addElement(resultSetBPD.getObject(i));
                                                                                }
                                                                                data.addElement(row);
                                                                }
                                                                resultSetBPD.close();
                                                                sBPD.close();  
                                                                conbpd.close();
                                                                //PTV
                                                                int columnsPTV = mdPTV.getColumnCount();
 
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
                                                                //USM
                                                                int columnsUSM = mdUSM.getColumnCount();
 
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
                                                                //BULK
                                                                int columnsBULK = mdBULK.getColumnCount();
 
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
 
                                                } catch (Exception e) {
                                                                System.out.println(e);
                                                }
 
 
                                                System.out.println("here");
 
 
                                                final ParallelQuery pq= new ParallelQuery();
 
                                                pq.table = new JTable(data, columnNames);
 
                                                Font font = new Font("Calibri", Font.BOLD, 16);
                                                pq.table.setFont(font);
                                                pq.table.setRowHeight(20);
 
                                                CharSequence s = "64, 64" ;
                                                final JButton button;
                                                button = new JButton();
                                                button.setOpaque(true);
                                                button.addActionListener(new ActionListener() {
 
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
 
                                                                }
                                                });
 
                                                int clr = 0;
 
                                                sid.clear();
                                                dbid.clear();
                                                for (int i=0;i<data.size();i++){
                                                                if(data.get(i).toString().contains(s)){
 
 
                                                                                sid.add(pq.table.getValueAt(i, 1).toString());
                                                                                dbid.add(pq.table.getValueAt(i, 0).toString());
                                                                                clr=1;
                                                                }
 
                                                                else
                                                                {
 
                                                                }
 
                                                }
                                               
                                                if (clr==0)
                                                {
                                                                pq.table.setBackground(Color.green);
                                                }else{
                                                                pq.table.setBackground(Color.ORANGE);
                                                }
 
                                                //   JOptionPane.showMessageDialog(button, "Alert!! in  "+table.getValueAt(i, 0) + ", " +table.getValueAt(i, 1)+ " running..");
 
 
 
 
                                                // System.out.println();
 
                                                //System.out.println(data.get(1));
                                                //System.out.println(data.get(2));
 
                                                TableColumn column;
                                                for (int i = 0; i < pq.table.getColumnCount(); i++) {
                                                                column = pq.table.getColumnModel().getColumn(i);
                                                                column.setMaxWidth(250);
 
                                                }
 
                                                final Dimension p = new Dimension();
                                                p.height=250;
                                                p.width=400;
 
                                                panel.remove(label1);
                                                frame.remove(panel);
                                                //frame.remove(label1);
                                                JScrollPane scrollPane = new JScrollPane(pq.table);  
                                                scrollPane.setPreferredSize(p);
                                                panel.add(scrollPane);              
                                                //frame location
                                                frame.add(panel,BorderLayout.WEST);
                                                frame.pack();
 
 
                                                //adding panel to the frame
                                                System.out.println("reached here");
 
                                                //setting visibility true
 
                                                pq.addMouseListener(new MouseAdapter()
                                                {
                                                                public void mouseClicked(MouseEvent evt)
                                                                {
 
                                                                                //pq.text.setText(pq.getSelectedValue().toString());
 
                                                                                int rowid= pq.table.getSelectedRow();
                                                                                int colid = 0;
                                                                                int colno = pq.table.getSelectedColumn();
                                                                               
                                                                                clicked++;
                                                                               
 
                                                                                try{
                                                                                               
                                                                                               
                                                                                               
                                                                                                String passed = pq.getSelectedValue().toString();
                                                                                               
 
                                                                                                System.out.println("selected value ==  "+pq.getSelectedValue().toString());
                                                                                                System.out.println("calue from table==  "+pq.table.getValueAt(rowid, colid).toString());
 
                                                                                                if(colno ==1){
                                                                                                                label2.setText("Fetching Details... Please wait...");
                                                                                                                JOptionPane.showMessageDialog(button, "Fetching Details... Please wait.. ");
                                                                                                               
                                                                                                                pan.repaint();
                                                                                                                frame.repaint();
                                                                                               
                                                                                                }
 
 
 
                                                                                                if (colno == 1 && clicked == 1 && !prevpassed.equalsIgnoreCase(passed)){
 
                                                                                                               
                                                                                               
                                                                                                               
                                                                                                               
                                                                                                                panelDetail.removeAll();
                                                                                                               
                                                                                                               
                                                                                                               
                                                                                                               
                                                                                                               
                                                                                                                ParallelQueryDetails pqd = new ParallelQueryDetails(passed,pq.table.getValueAt(rowid, colid).toString(),sql1);
                                                                                                                pq.tableDetails = new JTable(pqd.dataDetails, pqd.columnNamesDetails);
 
                                                                                                                System.out.println(pqd.dataDetails+"   "+pqd.columnNamesDetails);
                                                                                                               
                                                                                                                prevpassed = passed;
                                                                                                               
                                                                                                                JScrollPane scrollPane = new JScrollPane(pq.tableDetails);  
                                                                                                                scrollPane.setPreferredSize(p);
                                                                                                                panelDetail.add(scrollPane);            
                                                                                                                frame.getContentPane().add(panelDetail,BorderLayout.EAST);               
                                                                                                                frame.pack();
                                                                                                                //frame.setLocationRelativeTo(null);
 
                                                                                                                ParallelQueryDetails pqd1 = new ParallelQueryDetails(passed,pq.table.getValueAt(rowid, colid).toString(),sql2);
                                                                                                                pq.tableDetails = new JTable(pqd1.dataDetails, pqd1.columnNamesDetails);
 
                                                                                                                System.out.println(pqd.dataDetails+"   "+pqd.columnNamesDetails);
 
                                                                                                                JScrollPane scrollPane1 = new JScrollPane(pq.tableDetails);  
                                                                                                                scrollPane1.setPreferredSize(p);
                                                                                                                panelDetail.add(scrollPane1);            
                                                                                                                frame.getContentPane().add(panelDetail,BorderLayout.EAST);               
                                                                                                                frame.pack();
                                                                                                               
                                                                                                                label2.setText("");
                                                                                                                frame.repaint();
                                                                                                                clicked =0;
                                                                                                                //frame.setLocationRelativeTo(null);
                                                                                                               
 
                                                                                                }else if(colno==1 && prevpassed.equalsIgnoreCase(passed))
                                                                                               
                                                                                                {
                                                                                                               
                                                                                                               
                                                                                                                label2.setText("");
                                                                                                               
                                                                                                                frame.repaint();
                                                                                                                //panelDetail.removeAll();
                                                                                                                //frame.remove(panelDetail);
                                                                                                                //frame.remove(panel);
                                                                                                                //frame.add(panel,BorderLayout.WEST);
                                                                                                                //frame.pack();
                                                                                                                System.out.println("inside if else block");
                                                                                                                clicked=0;
                                                                                                }
                                                                                                else {
                                                                                                                prevpassed="demo";
                                                                                                                label2.setText("Please click on sql_id to get details");
                                                                                                                panelDetail.removeAll();
                                                                                                                frame.remove(panelDetail);
                                                                                                                frame.remove(panel);
                                                                                                                frame.add(panel,BorderLayout.WEST);
                                                                                                                frame.pack();
                                                                                                                System.out.println("inside else block");
                                                                                                                clicked=0;
                                                                                                               
                                                                                                               
                                                                                                }
 
                                                                                }
 
                                                                                catch (Exception e) {
                                                                                                // TODO Auto-generated catch block
                                                                                               
                                                                                                prevpassed="demo";
                                                                                                label2.setText("Please click on sql_id to get details");
                                                                                                panelDetail.removeAll();
                                                                                                frame.remove(panelDetail);
                                                                                                frame.remove(panel);
                                                                                                frame.add(panel,BorderLayout.WEST);
                                                                                                frame.pack();
                                                                                                System.out.println("inside exception");
                                                                                                e.printStackTrace();
 
                                                                                }
                                                                }
 
 
                                                });
 
                                                // frame.getContentPane().add(tsPanel, BorderLayout.CENTER);
 
                                                // pq.text = new JTextField();
                                                // pq.table = new JTable();
 
                                                // frame.getContentPane().add(pq.text, BorderLayout.SOUTH);
 
                                                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                // frame.pack();
                                                // frame.setLocationRelativeTo(null);
 
 
 
 
 
 
 
                                                runcount++;
                                               
 
                                                System.out.println("resume");
                                                System.out.println(sid);
                                                System.out.println(oldsid);
 
                                                if(runcount==2 && clr==1){
 
                                                                for(int i=0;i<sid.size();i++){
                                                                                for(int j=0;j<oldsid.size();j++){
 
                                                                                                System.out.println(sid.get(i).toString());
                                                                                                System.out.println(oldsid.get(j).toString());
                                                                                                if(sid.get(i).toString().equalsIgnoreCase(oldsid.get(j).toString())){
                                                                                                               
                                                                                                                pq.table.setBackground(Color.red);
                                                                                                                Toolkit.getDefaultToolkit().beep();
                                                                                                //            JOptionPane.showMessageDialog(button, "Alert!!  " +sid.get(i).toString()+ " in"+ dbid.get(i).toString() +" Please check... ");
                                                                                                                frame.setAlwaysOnTop(true);
 
                                                                                                }
                                                                                }
 
                                                                }
                                                                runcount=0;
                                                }else{
 
                                                                if (runcount==2){
                                                                                runcount=0;
                                                                }
                                                                oldsid=sid;
 
                                                }
                                                System.out.println(runcount);
                                                try {
                                                                Thread.sleep(60000);
                                                } catch (InterruptedException e1) {
                                                                // TODO Auto-generated catch block
                                                                label2.setText(e1.getMessage());
                                                                e1.printStackTrace();
                                                }
                                                clicked=0;
                                                prevpassed="demo";
                                               
                                                frame.dispose();
 
                                }
 
                }
}
 
