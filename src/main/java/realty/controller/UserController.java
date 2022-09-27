package realty.controller;

import com.realty.entity.User;
import com.realty.service.RealtyService;
import com.realty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RealtyService realtyService;

    @Autowired
    public UserController(UserService userService, RealtyService realtyService) {
        this.userService = userService;
        this.realtyService = realtyService;
    }

    @GetMapping
    public List<User> getAll(@RequestParam(required = false) Integer pageNum,
                             @RequestParam(required = false) Integer pageSize) {

        if (pageNum != null && pageNum > -1 && pageSize != null && pageSize > 0) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            return userService.getAll(pageable);
        }
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {

        if (!userService.existsById(id))
            return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
        else
            return ResponseEntity.ok().body(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final User user) {

        try {
            userService.save(user);
            return new ResponseEntity<>("Данный обьект успешно добавлен ", NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Не удалось добавить данный обьект ", BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody final User user) {

        if (!userService.existsById(user.getId()))
            return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
        try {
            userService.save(user);
            return new ResponseEntity<>("Данный обьект успешно добавлен ", NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Не удалось обновить данный обьект ", BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {

        try {
            userService.deleteById(id);
            return new ResponseEntity<>("Данный обьект успешно удален ", NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Не удалось удалить данный обьект ", BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> addRealtyUser(@PathVariable final Long id,
                                           @RequestParam final Long realtyId) {
        if (!userService.existsById(id)) return new ResponseEntity<>("Данный пользователь не найден ", NOT_FOUND);
        if (!realtyService.existsById(realtyId)) return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
    }

}
