package com.fafram.webservice.services;

import com.fafram.webservice.entities.Customer;
import com.fafram.webservice.entities.Phone;
import com.fafram.webservice.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository repository;

    public List<Phone> findAll() {
        return repository.findAll();
    }

    public Phone findById(Long id) {
        Optional<Phone> obj = repository.findById(id);
        return obj.get();
    }

    public Phone insert(Phone user) {
        return repository.save(user); // o método save retorna o usuário salvo
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Phone update(Long id, Phone num) {
        Phone entity = repository.getOne(id);
        updateData(entity, num);
        return repository.save(entity);
    }

    private void updateData(Phone entity, Phone num) {
        entity.setType(num.getType());
        entity.setNumber(num.getNumber());
        //entity.setPhones(user.getPhones());
    }

}
