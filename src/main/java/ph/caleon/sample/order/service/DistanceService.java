package ph.caleon.sample.order.service;

/**
 * @author arvin on 2/15/20
 **/
public interface DistanceService {

    String getDistance(String startLatitude, String startLongtitude, String endLatitude, String endLongtitude);

}
