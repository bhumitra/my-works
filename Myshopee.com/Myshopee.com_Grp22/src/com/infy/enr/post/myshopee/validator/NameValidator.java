package com.infy.enr.post.myshopee.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NameValidator implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		if(arg0==null || arg1==null){
			throw new NullPointerException();
		}
		String str = (String) arg2;
		FacesMessage message = new FacesMessage();
		if(str.length()>30 || str.length()<1){
			message.setSummary("Invalid number of characters in name. Please re-enter");
			throw new ValidatorException(message);
		}
		for(int count=0; count<str.length(); count++){
			char ch = str.charAt(count);
			if(Character.isDigit(ch)){
				message.setSummary("Only Characters are allowed. Please re-enter");
				throw new ValidatorException(message);
			}
			if(!((str.charAt(count)>=65 && str.charAt(count)<=91) || (str.charAt(count)>=97 && str.charAt(count)<=122))){			
				message.setSummary("Special Characters or Spaces are not allowed. Please re-enter");
				throw new ValidatorException(message);
			}
		}	
		
	}

}
