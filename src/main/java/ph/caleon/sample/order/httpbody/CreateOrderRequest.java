package ph.caleon.sample.order.httpbody;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arvin on 2/15/20
 **/
@Value
@AllArgsConstructor
public class CreateOrderRequest {

    @LocationConstraint(message = "Origin coordinate should be exactly two and be string")
    private List origin = new ArrayList<>();

    @LocationConstraint(message = "Destination coordinate should be exactly two and be string")
    private List destination = new ArrayList<>();

}
