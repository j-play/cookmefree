package processing.validator;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.mail")
public class UserMailValidator implements Validator, Serializable {

	private static final long serialVersionUID = 1L;
	private static final String USERMAIL_PATTERN = "[a-zA-Z0-9-._]+@[a-zA-Z0-9-._]+.[a-z]+";
	private Pattern pattern;
	private Matcher matcher;
		
	public UserMailValidator(){
		pattern = Pattern.compile(USERMAIL_PATTERN);
	}
	
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString()); 
		if(!matcher.matches()){
			FacesMessage msg = new FacesMessage("user mail validation failed.",
					"E-mail validation failed please re-enter a valid e-mail."); 
			msg.setSeverity(FacesMessage.SEVERITY_ERROR); 
			throw new ValidatorException(msg);
		}
		
	}

}
