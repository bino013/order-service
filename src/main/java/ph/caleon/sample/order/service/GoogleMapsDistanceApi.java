package ph.caleon.sample.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author arvin on 2/15/20
 **/
@Service
public class GoogleMapsDistanceApi implements DistanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleMapsDistanceApi.class);

    private final RestTemplate restTemplate;

    private final String url;

    protected GoogleMapsDistanceApi(RestTemplate restTemplate, @Value("${distance.service.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public String getDistance(String startLatitude, String startLongtitude, String endLatitude, String endLongtitude) {
        final String urlWithParams = url + "&origins=" + startLatitude + "," + startLongtitude + "&destinations=" + endLatitude + "," + endLongtitude;
        try {
            LOGGER.info("Sending distance matrix request. Url: {}", urlWithParams);
            final ResponseEntity<String> entity = restTemplate.getForEntity(urlWithParams, String.class);
            if (!HttpStatus.OK.equals(entity.getStatusCode())) {
                throw new ServiceException("Error while computing distance");
            }
            return getDistance(entity);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error sending compute distance request", e);
        }
    }

    private String getDistance(ResponseEntity<String> entity) {
        final String entityBody = entity.getBody();
        LOGGER.info("Distance matrix response: {}", entityBody );
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readValue(entityBody, JsonNode.class);
            final String status = jsonNode.get("status").asText();
            if (!"OK".equals(status)) {
                throw new ServiceException("Error while computing distance");
            }
            final JsonNode element0 = jsonNode.get("rows").get(0).get("elements").get(0);
            final String elem0status = element0.get("status").asText();
            if (!"OK".equals(elem0status)) {
                throw new ServiceException("Error while computing distance");
            }
            return element0.get("distance").get("text").asText();
        } catch (JsonProcessingException e) {
            throw new ServiceException("Error while processing get distance response", e);
        }
    }
}
