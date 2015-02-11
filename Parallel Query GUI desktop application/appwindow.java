
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.AttributedString;
 
import javax.swing.*;
 
 
public class AppWindow extends Frame {
                String countROT ="0";
                String countBPD ="0";
                String countWLN ="0";
               
                String message= "Executing Query... ";
                //String k1 ="200";
                int count=50;
                boolean executed = false;
               
 
                public AppWindow() {
                                addWindowListener(new MyWindowAdapter());
                }
 
 
                public void paint(Graphics g) {
 
                                Font font = new Font("Calibri", Font.BOLD, 30);
                               
                               
                                g.setFont(font);
                               
                               
                               
                               
                                if (executed){
                               
                                                               
                                g.drawRect(0, 5, 160, 66);
                                g.drawRect(0, 71, 160, 40);
                                g.drawRect(0, 111, 160, 40);
                               
                               
                               
                                g.drawRect(160, 0, 50, 71);
                                g.drawRect(160, 71, 50, 40);
                                g.drawRect(160, 111, 50, 40);
                                                               
                                int numROTCount = Integer.parseInt(countROT);
                                int numWLNCount = Integer.parseInt(countWLN);
                                int numBPDCount = Integer.parseInt(countBPD);
                               
                                if (numROTCount<count & numWLNCount<count & numBPDCount<count) {
                                                setBackground(Color.green);
                                               
                                }else{
                                                setBackground(Color.red);
                                                Toolkit.getDefaultToolkit().beep();
                               
                                }
                               
                                if (numROTCount>count) {
                                                g.drawString("***", 80, 60);
                                }
                               
                                if(numWLNCount>count){
                                                g.drawString("***", 80, 100);
                                }
                               
                                if(numBPDCount>count){
                                                g.drawString("***", 80, 140);
                                }
                               
               
                                g.drawString("ROT                "        +countROT, 10, 60);
                                g.drawString("WLN               "        +countWLN, 10, 100);
                                g.drawString("BPD                "        +countBPD, 10, 140);
                                }
                               
                                else{
                                                g.drawString(message,14, 100);
                                }
                }
               
 
 
                // Create the window.
                public static void main(String args[]) throws ClassNotFoundException, SQLException, InterruptedException {
                                AppWindow appwin = new AppWindow();
                                appwin.setSize(new Dimension(250, 170));
                                appwin.setTitle("Union All count V2.0");
                                appwin.setVisible(true);
                                appwin.setAlwaysOnTop(true);
 
 
 
                                Class.forName("oracle.jdbc.driver.OracleDriver");
 
 
                                String url = "jdbc:oracle:thin:@";
                                String urlwln = "jdbc:oracle:thin:@<>:1521:<>";
                                String urlbpd = "jdbc:oracle:thin:@<>:1521:<>";
 
                                // String url = "jdbc:oracle:thin:@localhost:1521:name";
 
//query bpd SELECT count(T4.objid) FROM table_oss_eh_event T1 INNER JOIN table_hgbst_elm T2 ON T1.oss_state2hgbst_elm = T2.objid INNER JOIN table_oss_eh_error_code T3 ON T1.oss_eh_event2eh_error_code = T3.objid INNER JOIN table_oss_router_msg T4 ON T1.oss_eh_event2router_msg = T4.objid WHERE (T2.s_ref_id = 'OSS_EH_MANUAL') AND (T3.s_oss_error_code ='TLS_SF_TECHNICAL_ERROR') AND (T4.s_oss_message_id IS NULL)
//query wln SELECT count(t4.objid) FROM table_oss_eh_event T1 INNER JOIN table_hgbst_elm T2 ON T1.oss_state2hgbst_elm = T2.objid INNER JOIN table_oss_eh_error_code T3 ON T1.oss_eh_event2eh_error_code = T3.objid INNER JOIN table_oss_router_msg T4 ON T1.oss_eh_event2router_msg = T4.objid WHERE (T2.s_ref_id = 'OSS_EH_MANUAL') AND (T3.s_oss_error_code = 'TLS_SF_TECHNICAL_ERROR') AND (T4.s_oss_message_id IS NULL)
                               
                               
                                Connection conn = DriverManager.getConnection(url,"sac", "*****");
                                Connection connwln = DriverManager.getConnection(urlwln,"sac", "*****");
                                Connection connbpd = DriverManager.getConnection(urlbpd,"sac", "*****");
                               
                               
                                conn.setAutoCommit(false);
                                connwln.setAutoCommit(false);
                                connbpd.setAutoCommit(false);
                               
                                while (true) {
 
                                                Statement stmt = conn.createStatement();
                                                System.out.println("connection ROT opened");
                                                Statement stmtwln = connwln.createStatement();
                                                System.out.println("connection WLN opened");
                                                Statement stmtbpd = connbpd.createStatement();
                                                System.out.println("connection BPD opened");
                                               
                                                                                               
                                                ResultSet rset = stmt.executeQuery("SELECT count(T4.objid) FROM table_oss_eh_event T1 INNER JOIN table_hgbst_elm T2 ON T1.oss_state2hgbst_elm = T2.objid INNER JOIN table_oss_eh_error_code T3 ON T1.oss_eh_event2eh_error_code = T3.objid INNER JOIN table_oss_router_msg T4 ON T1.oss_eh_event2router_msg = T4.objid WHERE (T2.s_ref_id = 'OSS_EH_HOLD') AND (T3.s_oss_error_code = 'BOSS_EXCEPTION') AND (T4.s_oss_message_id IS NULL)");
                                                System.out.println("query ROT executed");
                                               
                                                ResultSet rsetwln = stmtwln.executeQuery("SELECT count(t4.objid) FROM table_oss_eh_event T1 INNER JOIN table_hgbst_elm T2 ON T1.oss_state2hgbst_elm = T2.objid INNER JOIN table_oss_eh_error_code T3 ON T1.oss_eh_event2eh_error_code = T3.objid INNER JOIN table_oss_router_msg T4 ON T1.oss_eh_event2router_msg = T4.objid WHERE (T2.s_ref_id = 'OSS_EH_MANUAL') AND (T3.s_oss_error_code = 'TLS_SF_TECHNICAL_ERROR') AND (T4.s_oss_message_id IS NULL)");
                                                System.out.println("query WLN executed");
                                               
                                                ResultSet rsetbpd = stmtbpd.executeQuery("SELECT count(T4.objid) FROM table_oss_eh_event T1 INNER JOIN table_hgbst_elm T2 ON T1.oss_state2hgbst_elm = T2.objid INNER JOIN table_oss_eh_error_code T3 ON T1.oss_eh_event2eh_error_code = T3.objid INNER JOIN table_oss_router_msg T4 ON T1.oss_eh_event2router_msg = T4.objid WHERE (T2.s_ref_id = 'OSS_EH_MANUAL') AND (T3.s_oss_error_code ='TLS_SF_TECHNICAL_ERROR') AND (T4.s_oss_message_id IS NULL)");
                                                System.out.println("query BPD executed");
                                               
                                                appwin.executed=true;
                                               
                                                while (rset.next()) {
                                                                System.out.println (rset.getString(1));
                                                                appwin.countROT = rset.getString(1);                                                                   
                                                                appwin.repaint();
                                                }
                                               
                                               
                                                while (rsetwln.next()) {
                                                                System.out.println (rsetwln.getString(1));
                                                                appwin.countWLN = rsetwln.getString(1);
                                                                appwin.repaint();
                                                }
                                               
                                                while (rsetbpd.next()) {
                                                                System.out.println (rsetbpd.getString(1));
                                                                appwin.countBPD = rsetbpd.getString(1);
                                                                appwin.repaint();
                                                }
/*
                                                appwin.countROT = "5";                                                              
                                                appwin.repaint();
                                               
                                               
                                                appwin.countWLN = "5";                                                             
                                                appwin.repaint();
                                               
                                                appwin.countBPD = "533";                                                          
                                                appwin.repaint();
                                                */                                                          
                                                stmt.close();
                                                stmtwln.close();
                                                stmtbpd.close();
 
                                                System.out.println("closed");
                                                Thread.sleep(900000);
 
                                }
                }
 
 
                class MyWindowAdapter extends WindowAdapter {
                                public void windowClosing(WindowEvent we) {
                                                System.exit(0);
                                }
                }
}
