
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class SalutationConvertor implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return arg2;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		char gender = (Character) arg2;
		String salutation = null;
		
		switch(gender){
		case 'M':
			salutation = "Mr.";
			break;
		
		case 'F':
			salutation = "Mrs.";
			break;
		case 'f':
			salutation = "Ms.";
			break;
				
		}
		return salutation;
	}

}

