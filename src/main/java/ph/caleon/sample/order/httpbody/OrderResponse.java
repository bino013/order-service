package ph.caleon.sample.order.httpbody;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author arvin on 2/15/20
 **/
@Value
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponse {

    private String id;

    private String distance;

    private String status;

}
