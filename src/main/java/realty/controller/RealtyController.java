package realty.controller;

import com.realty.dto.RealtyUserDTO;
import com.realty.entity.Realty;
import com.realty.service.BidService;
import com.realty.service.RealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/realties" )
public class RealtyController {

    private final RealtyService realtyService;
    private final BidService bidService;

    @Autowired
    public RealtyController(RealtyService realtyService, BidService bidService) {
        this.realtyService = realtyService;
        this.bidService = bidService;
    }

    @GetMapping
    public List<Realty> getAll(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) {

        if (pageNum != null && pageNum > -1 && pageSize != null && pageSize > 0) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            return realtyService.getAll(pageable);
        }
        return realtyService.getAll();
    }

    @GetMapping("/{id}" )
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        if (!realtyService.existsById(id)) {
            return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
        } else
            return ResponseEntity.ok().body(realtyService.getById(id));
    }

    @GetMapping("/{id}/bids" )
    public ResponseEntity<?> getAllByRealtyId(@PathVariable final Long id) {
        if (!realtyService.existsById(id)) {
            return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
        } else
            return ResponseEntity.ok().body(bidService.getAllByRealtyId(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Realty realty) {
        try {
            realtyService.save(realty);
            return new ResponseEntity<>("Данный обьект успешно добавлен ", NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Не удалось добавить данный обьект", BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody final Realty realty) {
        if (!realtyService.existsById(realty.getId())) {
            return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
        }
        try {
            realtyService.save(realty);
            return new ResponseEntity<>("Данный обьект успешно обновлен ", NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Не удалось обновить данный обьект", BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}" )
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        try {
            realtyService.deleteById(id);
            return new ResponseEntity<>("Данный обьект успешно удален ", NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Не удалось удалить данный обьект", BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/users" )
    public ResponseEntity<?> addUser(@PathVariable final Long id,
                                     @RequestParam final Long userId) {
        try {
            realtyService.addUser(id, userId);
            return new ResponseEntity<>("Данный обьект успешно добавлен ", NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Не удалось добавить пользователя", BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/users" )
    public ResponseEntity<?> getUsersId(@PathVariable final Long id) {
        if (!realtyService.existsById(id))
            return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
        else
            return ResponseEntity.ok().body(realtyService.getUsers(id));
    }

    @DeleteMapping("/{id}/users" )
    public ResponseEntity<?> deleteUser(@PathVariable final Long id,
                                        @RequestParam final Long userId) {
        if (realtyService.deleteUser(id, userId) > 0)
            return new ResponseEntity<>("Данный обьект успешно удален ", NO_CONTENT);
        else
            return new ResponseEntity<>("Не удалось удалить данный обьект", BAD_REQUEST);
    }

    @GetMapping("/{id}/users/name" )
    public ResponseEntity<?> getUserNamesById(@PathVariable final Long id) {
        if (!realtyService.existsById(id))
            return new ResponseEntity<>("Данный обьект не найден ", NOT_FOUND);
        else
            return ResponseEntity.ok().body(realtyService.getUserNamesById(id));
    }

    @GetMapping("/{id}/users/name/DTO" )
    public RealtyUserDTO getAllRealtyUsersDTO(@PathVariable final Long id,
                                                    @RequestParam final Long userId) {
        return realtyService.getAllRealtyUserDTO(id, userId);
    }
}
