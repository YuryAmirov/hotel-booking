package ua.com.foxminded.hotelbooking.model.entity.validator.annotation;

import ua.com.foxminded.hotelbooking.model.entity.validator.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Documented
public @interface ValidPassword {

    String message() default "Entered invalid password";

    public int lengthMin() default 2;

    public int lengthMax() default 100;

    public int uppercaseCount() default 2;

    public int lowercaseCount() default 2;

    public int specialCharacter() default 1;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
