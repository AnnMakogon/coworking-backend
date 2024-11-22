package dev.coworking.service;

import dev.coworking.dto.Customer;
import dev.coworking.mapper.CastomerMapper;
import dev.coworking.repository.CastomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
//todo castomer на customer
public class CustomerService {

    private final CastomerRepository castomerRepository;
    private final CastomerMapper castomerMapper;

    @Transactional
    //todo handle exception in controller adivce (404)
    public Customer getPersInfo(Long id){
        return castomerMapper.castomerEntityToCastomer(
                castomerRepository.findById(id).get());
    }

    @Transactional
    public void updateCastomer(Customer newCastomer){
        castomerRepository.save(
                castomerMapper.castomerToCastomerEntity(newCastomer));
    }


}
