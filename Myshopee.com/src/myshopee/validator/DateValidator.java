

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DateValidator implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		if(arg0==null || arg1==null){
			throw new NullPointerException();
		}
		Date date = (Date) arg2;
		Date currentDate = new Date();
		FacesMessage message = new FacesMessage();
		message.setSummary("Date can not be less than current date");
		if(date.before(currentDate)){
			throw new ValidatorException(message);
		}
	}
}

