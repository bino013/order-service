package ph.caleon.sample.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author arvin on 2/15/20
 **/
@Entity
@Getter @Setter
@NoArgsConstructor
public class Orders {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String distance;

    private String status;

    public Orders(String distance, String status) {

        this.distance = distance;
        this.status = status;
    }
}
