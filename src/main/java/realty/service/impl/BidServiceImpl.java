package realty.service.impl;

import com.realty.entity.Bid;
import com.realty.repository.BidRepository;
import com.realty.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bid> getAll(Pageable pageable) {
        return bidRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bid> getAll() {
        return bidRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bid> getAllByRealtyId(Long realtyId, Pageable pageable) { return bidRepository.findAllByRealtyId(realtyId,pageable); }

    @Transactional(readOnly = true)
    @Override
    public Bid getById(Long id) {
        return bidRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public Bid save(Bid bid) { return bidRepository.save(bid); }

    @Transactional
    @Override
    public void deleteById(Long id) { bidRepository.deleteById(id); }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Long id) {
        return bidRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bid> getAllByRealtyId(Long realtyId) {
        return bidRepository.findAllByRealtyId(realtyId);
    }
}
