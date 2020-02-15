package ph.caleon.sample.order.httpbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

/**
 * @author arvin on 2/15/20
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TakeOrderRequest {

    @NotBlank(message = "Status should not be empty")
    private String status;

}
