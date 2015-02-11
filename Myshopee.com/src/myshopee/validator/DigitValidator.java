

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


//not used
public class DigitValidator implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		if(arg0==null || arg1==null){
			throw new NullPointerException();
		}
		String str = (String) arg2;
		FacesMessage message = new FacesMessage();
		
		message.setSummary("Only Digits are allowed. Please re-enter");
		for(int count=0; count<str.length(); count++){
			char ch = str.charAt(count);
			if(!(Character.isDigit(ch))){
				throw new ValidatorException(message);
			}
		}
		
	}

}
