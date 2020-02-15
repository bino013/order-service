package ph.caleon.sample.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ph.caleon.sample.order.entity.Orders;
import ph.caleon.sample.order.entity.OrdersRepository;
import ph.caleon.sample.order.httpbody.CreateOrderRequest;
import ph.caleon.sample.order.httpbody.OrderResponse;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author arvin on 2/15/20
 **/
@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    private final DistanceService distanceService;

    protected OrderServiceImpl(OrdersRepository ordersRepository, DistanceService distanceService) {
        this.ordersRepository = ordersRepository;
        this.distanceService = distanceService;
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        try {
            final String distance = distanceService.getDistance((String) request.getOrigin().get(0), (String) request.getOrigin().get(1),
                    (String) request.getDestination().get(0), (String) request.getDestination().get(1));
            final Orders orders = ordersRepository.save(new Orders(distance, "UNASSIGNED"));
            return new OrderResponse(orders.getId(), orders.getDistance(), orders.getStatus());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error while creating order.", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String takeOrder(String id) {
        final Orders orders = ordersRepository.findById(id).orElseThrow(() -> new ValidationException(id + " order not found"));
        if("TAKEN".equals(orders.getStatus())) {
            return "ALREADY_TAKEN";
        } else {
            orders.setStatus("TAKEN");
            ordersRepository.save(orders);
            return "TAKEN";
        }
    }

    @Override
    public List<OrderResponse> getOrders(Integer limit, Integer page) {
        final Page<Orders> orders = ordersRepository.findAll(PageRequest.of(page, limit, Sort.Direction.ASC, "id" ));
        return orders.get().map(order ->
                new OrderResponse(order.getId(), order.getDistance(), order.getStatus()))
                .collect(Collectors.toList());
    }
}
