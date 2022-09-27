package realty.entity;

import com.realty.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(schema = "catalogs", name = "tbid")
public class Bid extends BaseEntity {

    @CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;
    private Double value;
    @Column(name = "realty_id")
    private Long realtyId;

//    @EqualsAndHashCode.Exclude
//    @ManyToOne
//    @JoinColumn(name = "")
//    private Realty realty;
}
