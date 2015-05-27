package processing.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.password")
public class UserPwdValidator implements Validator {
	
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;

        // Obtain the component and submitted value of the confirm password component.
        UIInput confirmComponent = (UIInput) component.getAttributes().get("confirm");
        String confirm = (String) confirmComponent.getSubmittedValue();

        // On vérifie que les deux password sont renseignés
        if (password == null || password.isEmpty() || confirm == null || confirm.isEmpty()) {
            return; // On laisse required="true" faire son job.
        }

        // On compare les deux password
        if (!password.equals(confirm)) {
            confirmComponent.setValid(false);
            throw new ValidatorException(new FacesMessage("Passwords are not equal."));
        }
		
	}

}
