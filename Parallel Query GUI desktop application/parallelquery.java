import java.awt.*;
import java.awt.event.*;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
 
import javax.naming.spi.DirStateFactory.Result;
// Create a frame window.
 
 
public class ParallelQuery extends Frame {
                String count ="0";
                String sqlid = "";
                String px ="";
                String pxtotal= "";
                String dbname ="";
                //String k1 ="200";
                int countref=50;
                int posx=0;
                int posy=0;
               
               
                static List list = new List();
               
               
                public ParallelQuery() {
                                addWindowListener(new MyWindowAdapter());
                }
               
 
 
                public void paint(Graphics g) {
 
                               
                               
                                Font font = new Font("Calibri", Font.BOLD, 18);
                                g.setFont(font);
                                setBackground(Color.green);
                               
                                int ucount = Integer.parseInt(count);
                                if (ucount<countref){
                                               
                                }else{
                                                setBackground(Color.red);
                                                Toolkit.getDefaultToolkit().beep();
                                }
                               
                               
                                g.drawString("Count ", 180, 70);
                                g.drawString("Sql Id ", 10, 70);
                                g.drawString("PX", 250, 70);
                                g.drawString("PXTotal", 290, 70);
                                g.drawString(count, 195, 100);
                                g.drawString(sqlid, 10, 100);
                                g.drawString(px, 250, 100);
                               
                               
               
 
 
                }
                // Create the window.
                public static void main(String args[]) throws ClassNotFoundException, SQLException, InterruptedException {
                                ParallelQuery appwin = new ParallelQuery();
                                appwin.setSize(new Dimension(350, 350));
                                appwin.setTitle("Parallel Query ROT");
                                appwin.setVisible(true);
                                appwin.setAlwaysOnTop(true);
 
 
 
                                Class.forName("oracle.jdbc.driver.OracleDriver");
 
 
                                //String url = "jdbc:oracle:thin:@offmprd1dbs10:1521:ROTBP1AP";
 
                                String url = "jdbc:oracle:thin:@localhost:1521:name";
 
 
 
                //            Connection conn = DriverManager.getConnection(url,"sac", "welcome1");
                                Connection conn = DriverManager.getConnection(url,"bhumitra", "bhumitra");
                                conn.setAutoCommit(false);
 
                                while (true) {
 
                                                Statement stmt = conn.createStatement();
 
                                                System.out.println("connection opened");
                                               
                                //            stmt.executeQuery("select v.sql_id, count (v.sql_id) as count from  v$px_process x, v$lock l, v$session v, v$session_wait w1, v$session_wait w2 where x.sid <> l.sid(+) and   to_number (substr(x.server_name,3)) = l.id2(+) and   x.sid = w1.sid(+) and   l.sid = w2.sid(+) and   x.sid = v.sid(+) and   nvl(l.type,'PS') = 'PS' group by v.sql_id");
                                                ResultSet rset =stmt.executeQuery("select t,count(*) from t group by t");
                                               
                               
                                               
                                                System.out.println("query executed");
                                               
                                               
                                               
                                               
                                                while (rset.next()) {
                                                               
                                                                list.add(rset.getString(1));
                                                                list.add(rset.getString(2));
                                                               
                                                               
                                                                appwin.repaint();
                                                               
                                                               
                                                }
                                               
                       
                        
                                                ResultSet rset2= stmt.executeQuery("select value from v$parameter where name like '%parallel_max_servers%'");
                                               
                                                ResultSet rset3= stmt.executeQuery("select name from v$database");
                                               
                                               
                                                while (rset.next()) {
                                                                System.out.println (rset.getString(1));
                                                                appwin.sqlid = rset.getString(1);
                                                                appwin.count = rset.getString(2);
                                                               
                                                }
                                               
                                                while(rset3.next()){
                                                                System.out.println (rset3.getString(1));
                                                                appwin.dbname = rset3.getString(1);
                                                                appwin.repaint();
                                                               
                                                }
                                               
                                                while(rset2.next()){
                                                                System.out.println (rset2.getString(1));
                                                                appwin.px = rset2.getString(1);
                                                                appwin.repaint();
                                                               
                                                }
 
                                                stmt.close();
 
                                                System.out.println("closed");
                                                Thread.sleep(600000);
 
                                }
                }
 
 
                class MyWindowAdapter extends WindowAdapter {
                                public void windowClosing(WindowEvent we) {
                                                System.exit(0);
                                }
                }
}
 