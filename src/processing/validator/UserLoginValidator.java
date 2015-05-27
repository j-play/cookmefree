package processing.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.login")
public class UserLoginValidator implements Validator {

	private static final String USERLOGIN_PATTERN = "[a-zA-Z0-9-._]+";
	private Pattern pattern;
	private Matcher matcher;
		
	public UserLoginValidator(){
		pattern = Pattern.compile(USERLOGIN_PATTERN);
	}
	
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString()); 
		if(!matcher.matches()){
			FacesMessage msg = new FacesMessage("user mail validation failed.",
					"Login validation failed, you can enter a login with letters, numbers and - . _"); 
			msg.setSeverity(FacesMessage.SEVERITY_ERROR); 
			throw new ValidatorException(msg);
		}
		
	}

}
