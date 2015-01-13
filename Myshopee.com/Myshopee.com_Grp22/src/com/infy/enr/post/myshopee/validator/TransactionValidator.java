package com.infy.enr.post.myshopee.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class TransactionValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
        if(arg0==null||arg1==null){
            throw new NullPointerException();
      }
      if(!(arg1 instanceof UIInput )){
            return;
      }
      FacesMessage facesMessage=new FacesMessage();
      facesMessage.setDetail("No.of Transaction should be greater than or equal to zero");
      Integer transactionNo=(Integer)arg2;
                                if(transactionNo<0){
                                                
                                                throw new ValidatorException(facesMessage);
                                }
                }


}
