

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PasswordValidator implements Validator{

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
      facesMessage.setDetail("Invalid Password");
      String password=(String)obj;
      int count = 0;
  	  Integer length = password.length();
	  if(length<6 || length>15){
		throw new ValidatorException(facesMessage);
	  }
	  else{
		if(password.charAt(0)>=48 && password.charAt(0)<=57){
			throw new ValidatorException(facesMessage);
		}
		else{
			for(int j=0;j<length;j++){
				if(password.charAt(j)>=48 && password.charAt(j)<=57){
					count=count+1;
				}
			}
			if(count==length){
				throw new ValidatorException(facesMessage);
			}
			/*else{
			for(int j=1;j<length;j++){
					if(!((password.charAt(j)>=65 && password.charAt(j)<=90)||(password.charAt(j)>=97 && password.charAt(j)<=122)||(password.charAt(j)>=0 && password.charAt(j)<=9)|| (password.charAt(j)>=97 && password.charAt(j)<=122)||(password.charAt(j)>90 && password.charAt(j)<97))){
						throw new InvalidPasswordException();
						}
					}
				}*/
			//}
			}
		}
	}
}
