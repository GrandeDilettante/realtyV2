package realty.entity;

import com.realty.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
@Table(schema = "catalogs", name = "tusers")
public class User extends BaseEntity {
    private String name;
    private Double budget;
    private String whatsapp;
    private Long phone;
}
