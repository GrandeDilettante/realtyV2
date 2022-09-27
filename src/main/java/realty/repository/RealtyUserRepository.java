package realty.repository;

import com.realty.dto.RealtyUserDTO;
import com.realty.entity.RealtyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtyUserRepository extends JpaRepository<RealtyUser, Long> {

    Integer deleteByRealtyIdAndUserId(Long realtyId, Long userId);

    @Query("SELECT new com.realty.dto.RealtyUserDTO(r.name, u.name) FROM RealtyUser ru " +
            "JOIN User u ON ru.userId = u.id " +
            "JOIN Realty r ON ru.realtyId = r.id " +
            "WHERE r.id = ?1 and u.id= ?2")
    RealtyUserDTO getAllDTO(Long realtyId, Long userId);
}
