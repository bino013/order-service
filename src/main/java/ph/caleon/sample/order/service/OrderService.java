package ph.caleon.sample.order.service;

import ph.caleon.sample.order.httpbody.CreateOrderRequest;
import ph.caleon.sample.order.httpbody.OrderResponse;

import java.util.List;

/**
 * @author arvin on 2/15/20
 **/
public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    String takeOrder(String id);

    List<OrderResponse> getOrders(Integer limit, Integer page);

}
