package realty.repository;

import com.realty.entity.Realty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealtyRepository extends JpaRepository<Realty, Long> {
    @Query("SELECT DISTINCT ru.userId FROM RealtyUser ru WHERE ru.realtyId = :id")
    List<Long> getUsersIdById(Long id);

    @Query("SELECT u.name FROM RealtyUser ru " +
            "JOIN User u ON ru.userId = u.id " +
            "WHERE ru.realtyId = ?1")
    List<String> getUsersNamesById(Long id);
}