package processing.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.age")
public class UserAgeValidator implements Validator {

	private static final String AGE_PATTERN = "[0-9]+";
	private Pattern pattern;
	private Matcher matcher;
		
	public UserAgeValidator(){
		pattern = Pattern.compile(AGE_PATTERN);
	}
	
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString()); 
		if(!matcher.matches()){
			FacesMessage msg = new FacesMessage("user age validation failed.",
					"Age validation failed please re-enter an age < 100."); 
			msg.setSeverity(FacesMessage.SEVERITY_ERROR); 
			throw new ValidatorException(msg);
		}
		
	}

}
