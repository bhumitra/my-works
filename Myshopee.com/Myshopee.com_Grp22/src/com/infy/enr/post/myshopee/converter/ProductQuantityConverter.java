package com.infy.enr.post.myshopee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ProductQuantityConverter implements Converter {

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) 
	{
		Integer quantity=(Integer)arg2;
		if(quantity>0)
		{
			return "Y";
		}
		else
		{
			return "N";
		}
	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}
