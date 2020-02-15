package ph.caleon.sample.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ph.caleon.sample.order.httpbody.CreateOrderRequest;
import ph.caleon.sample.order.httpbody.OrderResponse;
import ph.caleon.sample.order.httpbody.Response;
import ph.caleon.sample.order.httpbody.TakeOrderRequest;
import ph.caleon.sample.order.service.OrderService;
import ph.caleon.sample.order.service.ValidationException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author arvin on 2/15/20
 **/
@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    protected OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        LOGGER.info("Create order request received. Request: {}", request);
        final OrderResponse response = orderService.createOrder(request);
        LOGGER.info("Create order response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(path = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> takeOrder(@Valid @RequestBody TakeOrderRequest request,
                                              @Valid @PathVariable(required = true) @NotBlank(message = "Id should not be empty") String id) {
        if (!"TAKEN".equals(request.getStatus())) {
            throw new ValidationException("Value for status should be 'TAKEN'");
        }
        LOGGER.info("Take order request received. Request: Id {}", id);
        final String orderStatus = orderService.takeOrder(id);
        LOGGER.info("Take order request response. {}", orderStatus);
        return new ResponseEntity<Response>(new Response(orderStatus, null), HttpStatus.OK);
    }

    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponse>> getOrders(@Valid @RequestParam(name = "limit", required = true) int limit,
                                                         @Valid @RequestParam(name = "page", required = true) int page) {
        LOGGER.info("Get order request received. Limit: {}, Page: {}", limit, page);
        if (page < 1) {
            throw new ValidationException("Page number must starts with 1");
        }
        final List<OrderResponse> orders = orderService.getOrders(limit, page);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
