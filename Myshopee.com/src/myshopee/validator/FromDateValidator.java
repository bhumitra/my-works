

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class FromDateValidator implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		Date date=new Date();
		Date fromdate=(Date)arg2;
		if(fromdate.after(date)){
			FacesMessage message=new FacesMessage();
			message.setDetail("From date cannot be greater than current date");
			throw new ValidatorException(message);
		}
		
	}

}
