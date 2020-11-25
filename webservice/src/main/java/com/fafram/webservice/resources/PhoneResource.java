package com.fafram.webservice.resources;

import com.fafram.webservice.entities.Customer;
import com.fafram.webservice.entities.Phone;
import com.fafram.webservice.services.PhoneService;
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
@RequestMapping(value = "/phone")
public class PhoneResource {

    @Autowired
    private PhoneService service;

    //documentação para o swagger
    @ApiOperation(value = "Retorna uma lista de telefones")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mostra a lista de Clientes"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public ResponseEntity<List<Phone>> findAll() {
        List<Phone> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


    @ApiOperation(value = "Busca uma Telefone na lista de Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Buscando o Telefone"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Phone> findById(@PathVariable Long id) {
        Phone num = service.findById(id);
        return ResponseEntity.ok().body(num);
    }


    @ApiOperation(value = "Insere Telefone na lista de Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inserindo telefone na lista"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping // indica que o método responde a uma requisição POST HTTP
    /* A requisição envia um json, que deve ser desserializado para um obj User
    usamos a annotation @RequestBody
     */
    public ResponseEntity<Phone> insert(@RequestBody Phone num) {
        num = service.insert(num);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(num.getId()).toUri();
        return ResponseEntity.created(uri).body(num); // Retorna status code 201
    }

    @ApiOperation(value = "Apaga Telefones da lista de Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Apagando Telefone"),
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


    @ApiOperation(value = "Atualiza Telefones dos Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inserindo um Telefone"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 403, message = "Acesso Negado"),
            @ApiResponse(code = 404, message = "Sua requisição nao foi encontrada"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping(value = "/{id}") // inidica que o método responde a uma requisição PUT HTTP
    public ResponseEntity<Phone> update(@PathVariable Long id, @RequestBody Phone num) {
        num = service.update(id, num);
        return ResponseEntity.ok().body(num);
    }



}