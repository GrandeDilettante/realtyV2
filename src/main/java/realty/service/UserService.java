package realty.service;

import com.realty.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> getAll(Pageable pageable);
    List<User> getAll();

    User getById(Long id);

    User save(User user);

    void deleteById(Long id);

    boolean existsById(Long id);

    void addRealtyUser(Long realtyId, Long userId);
}
