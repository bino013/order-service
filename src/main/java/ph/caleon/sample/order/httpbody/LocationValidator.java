package ph.caleon.sample.order.httpbody;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * @author arvin on 2/15/20
 **/
public class LocationValidator implements ConstraintValidator<LocationConstraint, List> {

    @Override
    public boolean isValid(List values, ConstraintValidatorContext constraintValidatorContext) {
        final boolean isExactlyTwo = Objects.nonNull(values) && values.size() == 2;
        boolean isString = true;
        for (Object obj : values) {
            if (!(obj instanceof String)) {
                isString = false;
                break;
            }
        }
        return isExactlyTwo && isString;
    }
}
