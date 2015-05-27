package processing.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.username")
public class UserNameValidator implements Validator {

	private static final String USERNAME_PATTERN = "^[A-Za-z0-9]+";
	private Pattern pattern;
	private Matcher matcher;
		
	public UserNameValidator(){
		pattern = Pattern.compile(USERNAME_PATTERN);
	}
	
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString()); 
		if(!matcher.matches()){
			FacesMessage msg = new FacesMessage("user name validation failed.",
					"User name validation failed please re-enter a name without special characters."); 
			msg.setSeverity(FacesMessage.SEVERITY_ERROR); 
			throw new ValidatorException(msg);
		}
		
	}

}
