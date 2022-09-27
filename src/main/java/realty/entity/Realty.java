package realty.entity;

import com.realty.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(schema = "catalogs", name = "trealty")
public class Realty extends BaseEntity {
    private String name;
    private Long price;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "realty_id")
    private Set<Bid> bids;

    // если нужно достать модель
    @ManyToMany
    @JoinTable(
            schema = "catalogs",
            name = "trealty_users",
            joinColumns = @JoinColumn(name = "realty_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    // если нужно достать id
//    @ElementCollection(fetch = FetchType.EAGER)
//    @JoinTable(schema = "catalogs", name = "trealty_users",
//            joinColumns = @JoinColumn(name = "realty_id"))
//    @Column(name = "user_id")
//    private Set<Long> usersId;
}
