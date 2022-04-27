package com.project_serralheria.services;

import com.project_serralheria.models.Cliente;
import com.project_serralheria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository cr;

    @Transactional
    public void save(Cliente cliente){
        cr.save(cliente);
    }

    @Transactional
    public List<Cliente> findAll(){
        return cr.findAll();
    }

    @Transactional
    public Cliente findById(Long id){
        Optional<Cliente> op = cr.findById(id);
        ResponseEntity<Cliente> re = ResponseEntity.of(op);
        Cliente cliente = re.getBody();
        return cliente;
    }

    @Transactional
    public void deleteById(Long id){
        cr.deleteById(id);
    }
}
