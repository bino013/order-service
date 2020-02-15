package ph.caleon.sample.order.entity;

import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

/**
 * @author arvin on 2/15/20
 **/
public interface OrdersRepository extends PagingAndSortingRepository<Orders, String> {
}
