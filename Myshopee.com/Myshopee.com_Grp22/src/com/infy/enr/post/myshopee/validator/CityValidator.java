package com.infy.enr.post.myshopee.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


//not used
public class CityValidator implements Validator {

	
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
	throws ValidatorException {
if(arg0==null || arg1==null){
	throw new NullPointerException();
}
String str = (String) arg2;
FacesMessage message = new FacesMessage();
if(str.length()>30 || str.length()<1){
	message.setSummary("Invalid City name. Please re-enter.");
	throw new ValidatorException(message);
}
}
}
