
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class UsernameValidator implements Validator {
	public void validate(FacesContext facesContext, UIComponent component, Object obj)
	throws ValidatorException {
// TODO Auto-generated method stub
if(facesContext==null||component==null){
    throw new NullPointerException();
}
if(!(component instanceof UIInput )){
    return;
}
FacesMessage facesMessage=new FacesMessage();
facesMessage.setSummary("Invalid User Name");
String userName=(String)obj;
int countUser=0;
for(int i=0;i<userName.length();i++){
	if(userName.charAt(i)==' ')
	{	
		countUser = countUser+1;
	}
}
if(countUser!=0){
  throw new ValidatorException(facesMessage);
}




}
}
