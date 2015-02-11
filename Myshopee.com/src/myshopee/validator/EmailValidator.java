/**
 * This class contains methods to validate the email.
 */


import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


import com.infy.enr.post.myshopee.support.StringValidation;

public class EmailValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
	throws ValidatorException {
		// TODO Auto-generated method stub
		String mail = (String)arg2;
		int len = mail.length(),atCount=0,dotCount=0,dotlast=0,atlast=0;
		char spcl[] = new char[3];
		spcl[0]='.';spcl[1]='@';spcl[2]='_';
		final Pattern mailid1 = Pattern.compile("^[A-Za-z0-9_.]*[@]??[A-Za-z]+[.]+[A-Za-z.]*[A-Za-z]+$");
		FacesMessage msg = new FacesMessage();
		if(len<=30){
			if(mail.charAt(len-1)=='.')
				dotlast=1;
			if(mail.charAt(len-1)=='@')
				atlast=1;
			for(int i=0;i<len;i++){
				if(mail.charAt(i)=='@')
					atCount++;
				if(mail.charAt(i)=='.')
					dotCount++;

			}
			if(StringValidation.isNumeric(mail)){
				msg.setSummary("Email id should not contain only numbers!! Please enter valid email id.");
				throw new ValidatorException(msg);	
			}
			else{
				if(atCount==0)
				{ 
					msg.setSummary("Email has to contain an '@' character. Please enter valid email id.");
					throw new ValidatorException(msg);
				}
				else if(atCount>1){
					msg.setSummary("Email id cannot contain more than one '@' character!! Please enter valid email id.");
					throw new ValidatorException(msg);
				}
				else
				{
					if(dotCount==0){
						msg.setSummary("Email has to contain an '.' character. Please enter valid email id.");
						throw new ValidatorException(msg);
					}
					else if(dotlast==1){
						msg.setSummary("Email id cannot end with a '.' character!! Please enter valid email id.");
						throw new ValidatorException(msg);
					}
					else if(atlast==1){
						msg.setSummary("Email id cannot end with a '@' character!! Please enter valid email id.");
						throw new ValidatorException(msg);
					}
					else{
						if(StringValidation.isValidString(mail,spcl)){
							if(mailid1.matcher(mail).matches()==false){
								msg.setSummary("Email id should follow a pattern similar to 'bkinfy@infosys.com'!! Please enter valid email id.");
								throw new ValidatorException(msg);
							}
						}
						else{
							msg.setSummary("Email id can contain only a dot,@ and underscore and no other special character!! Please enter valid email id.");
							throw new ValidatorException(msg);
						}
					}
				}
			}

		}
		else {
			msg.setSummary("Email id can be maximum of 30 chars!!");
			throw new ValidatorException(msg);



		}



	}




}
