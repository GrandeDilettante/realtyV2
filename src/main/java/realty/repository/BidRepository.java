package realty.repository;

import com.realty.entity.Bid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findAllByRealtyId(Long realtyId);

    List<Bid> findAllByRealtyId(Long realtyId, Pageable pageable);
}
