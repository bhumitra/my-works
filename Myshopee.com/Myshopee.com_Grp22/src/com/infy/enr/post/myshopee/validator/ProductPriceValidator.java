package com.infy.enr.post.myshopee.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ProductPriceValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		if(arg0==null || arg1==null){
			throw new NullPointerException();
		}
		if(!(arg1 instanceof UIInput)){
			return;
		}
		
		Integer price = (Integer) arg2;
		
		if (price%100 != 0){
			FacesMessage message=new FacesMessage();
			message.setSummary("Invalid Product Price Entered. Enter price in multipes of Hundred");
			throw new ValidatorException(message);
		}
	}

}
