               import java.awt.AWTException;
import java.awt.Desktop;
                import java.awt.Rectangle;
                import java.awt.Robot;
                import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
                import java.awt.image.BufferedImage;
                import java.io.File;
                import java.io.IOException;
import java.util.Random;
 
import javax.imageio.ImageIO;
               
               
                public class shot {
                /**
                method to capture screen shot
                @param String uploadPath to save screen shot as image
                @returns boolean true if capture successful else false
                */
                               
                               
                private int count;
               
                boolean captureScreenShot(String uploadPath)
                {
                boolean isSuccesful = false;
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage capture;
                try {
                capture = new Robot().createScreenCapture(screenRect);
               
                Random r = new Random();
               
                int num = count;
                               
                String str = Integer.toString(num)+".jpeg";
                               
                // image will be save at given path with name "0.jpeg"
                ImageIO.write(capture, "jpg", new File( uploadPath, str));
                isSuccesful = true;
               
                count++;
                System.out.println(count);
               
                } catch (AWTException awte) {
                awte.printStackTrace();
                isSuccesful = false;
                }
                catch (IOException ioe) {
                ioe.printStackTrace();
                isSuccesful = false;
                }
                return isSuccesful;
                }
 
                public static void main(String args[] ) throws AWTException, IOException, InterruptedException{
                                                               
                shot s = new shot();
                System.out.println("started..");
               
                String filename[] = {
                                               
                                               
                                                //path of the files… use \\ instead of \
                                                                               
                                               
                                               
                };
               
                for (int f=0;f<filename.length;f++){
               
                Desktop.getDesktop().open(new File(filename[f])) ;
               
                Robot rb = new Robot();
               
                Thread.sleep(15000);
               
                rb.mouseMove(30, 150);
                rb.mousePress(InputEvent.BUTTON1_MASK);
                rb.mouseRelease(InputEvent.BUTTON1_MASK);
                String pth="d:\\screenshots\\c++\\day"+(f+1);
                //+(f+2);
                System.out.println(pth);
 
                for (int i=0; i<80; i++){
                               
               
                s.captureScreenShot(pth);
               
               
               
                Robot r = new Robot();
               
               
                r.keyPress(KeyEvent.VK_DOWN);
               
               
               
                try {
                               
                               
                                Thread.sleep(400);
                               
                               
                } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                }
                }
                }
                }
}
 