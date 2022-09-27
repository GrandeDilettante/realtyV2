package realty.service;

import com.realty.dto.RealtyUserDTO;
import com.realty.entity.Bid;
import com.realty.entity.Realty;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RealtyService {

    List<Realty> getAll(Pageable pageable);

    List<Realty> getAll();

    List<Bid> getAllByRealtyId(Long realtyId);

    Realty save(Realty realty);

    Realty getById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);

    void addUser(Long realtyId, Long userId);

    List<Long> getUsers(Long id);

    Integer deleteUser(Long realtyId, Long userId);

    List<String> getUserNamesById(Long id);

    RealtyUserDTO getAllRealtyUserDTO(Long realtyId, Long userId);
}
