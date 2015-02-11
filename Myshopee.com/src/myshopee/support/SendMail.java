

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import com.sun.mail.smtp.SMTPMessage;

//Sends mail to Fake Mail Server (SMTP Server)at localhost i.e 127.0.0.1
public class SendMail {

	public static void Send(String toAdd, String cc, String from,
			String Subject, String MailBody) throws Exception {


		Properties props = new Properties();
		props.put("mail.smtp.host", "127.0.0.1");
		Session session = Session.getDefaultInstance(props, null);
		SMTPMessage message = new SMTPMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipients(Message.RecipientType.TO, toAdd);
		if (cc != null)
		{
			 message.addRecipients(Message.RecipientType.CC,cc);
		}
		message.setSubject(Subject, "UTF-8");
		message.setContent(MailBody, "text/html; charset=UTF-8 ");
		message.setHeader("Content-Transfer-Encoding", "7bit");
		Transport.send(message);

	}


}
