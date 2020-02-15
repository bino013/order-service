package ph.caleon.sample.order.httpbody;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

/**
 * @author arvin on 2/15/20
 **/
@Value
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {

    private String status;

    private String error;
}
