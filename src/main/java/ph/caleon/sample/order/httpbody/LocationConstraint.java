package ph.caleon.sample.order.httpbody;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author arvin on 2/15/20
 **/
@Constraint(validatedBy = LocationValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocationConstraint {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
