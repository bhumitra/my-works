
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
public class ToDateValidator implements Validator {
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)throws ValidatorException {

		// TODO Auto-generated method stub

		Date toDate=(Date)arg2;
		Date currentDate=new Date();
		if(toDate.after(currentDate)){
			FacesMessage message=new FacesMessage();
			message.setDetail("To Date should not be greater than current date");

			throw new ValidatorException(message);
		}
	}
}
