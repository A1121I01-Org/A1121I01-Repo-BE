package module6.backend.service.Impl;

import module6.backend.repository.ICartRepository;
import module6.backend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Integer displayHuy() {
        return cartRepository.huy();
    }

    @Override
    public Integer displayTra() {
        return cartRepository.tra();
    }

    @Override
    public Integer displayBan() {
        return cartRepository.ban();
    }
}
