package ua.com.foxminded.hotelbooking.model.entity.validator;

import org.passay.LengthRule;
import org.passay.LowercaseCharacterRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import ua.com.foxminded.hotelbooking.model.entity.validator.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private int lengthMin;

    private int lengthMax;

    private int uppercaseCount;

    private int lowercaseCount;

    private int specialCharacter;

    @Override
    public void initialize(ValidPassword validPassword) {
        lengthMin = validPassword.lengthMin();
        lengthMax = validPassword.lengthMax();
        uppercaseCount = validPassword.uppercaseCount();
        lowercaseCount = validPassword.lowercaseCount();
        specialCharacter = validPassword.specialCharacter();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        final PasswordValidator passwordValidator
                = new PasswordValidator(Arrays.asList(
                new LengthRule(lengthMin, lengthMax),
                new UppercaseCharacterRule(uppercaseCount),
                new LowercaseCharacterRule(lowercaseCount),
                new SpecialCharacterRule(specialCharacter)));

        final RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        return false;
    }
}
