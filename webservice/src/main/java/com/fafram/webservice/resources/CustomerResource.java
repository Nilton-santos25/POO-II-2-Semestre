package com.fafram.webservice.resources;
import com.fafram.webservice.entities.Customer;
import com.fafram.webservice.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/customer") //caminho do recurso

public class CustomerResource {

    @Autowired
    private CustomerService service;

    //documentação para o swagger
    @ApiOperation(value = "Retorna uma lista de usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inserindo um Usuário"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    //endpoint
    @GetMapping // método que responde a requisiçãop Get HTTP
    public ResponseEntity<List<Customer>> findAAll() {
        List<Customer> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


    @ApiOperation(value = "Retorna uma Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontra um Cliente"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }


    @ApiOperation(value = "Insere os Cliente na lista")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inserindo um Cliente"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping // indica que o método responde a uma requisição POST HTTP
    /* A requisição envia um json, que deve ser desserializado para um obj User
    usamos a annotation @RequestBody
     */
    public ResponseEntity<Customer> insert(@RequestBody Customer user) {
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user); // Retorna status code 201
    }

    @ApiOperation(value = "Deleta os Clientes da lista")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Apaga um Cliente"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping(value = "/{id}") // indica que o método responde a uma requisição DELETE HTTP
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "Atualiza a lista de Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza um Cliente"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping(value = "/{id}") // inidica que o método responde a uma requisição PUT HTTP
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer user) {
        user = service.update(id, user);
        return ResponseEntity.ok().body(user);
    }


    }


