package com.fafram.webservice.config;

import com.fafram.webservice.entities.Customer;
import com.fafram.webservice.entities.Phone;
import com.fafram.webservice.repositories.CustomerRepository;
import com.fafram.webservice.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration //classe de configuração
@Profile("test")
public class TestConfig implements CommandLineRunner {

    //injeção de depedência
    @Autowired
    private CustomerRepository customerRepository;

    //injecao de depedencia
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public void run(String... args) throws Exception {

        Phone num1 = new Phone(null,"Fixo",15555555);
        Phone num2 = new Phone(null,"celular",12342121);
        Phone num3 = new Phone(null,"Fixo",13231313);
        Phone num4 = new Phone(null,"celular",554545);



        phoneRepository.saveAll(Arrays.asList(num1,num2,num3,num4));

        Customer user1 = new Customer(null,"maria","maria@gmail","121355");
        Customer user2 = new Customer(null,"joao","joao@gmail","121342255");
        Customer user3 = new Customer(null,"Nilton","nilton@123","23424244");
        Customer user4 = new Customer(null,"Nilton","nilton@123","23424244");

        user1.getPhones().add(num1);
        user2.getPhones().add(num2);
        user3.getPhones().add(num3);
        user4.getPhones().add(num4);

        customerRepository.saveAll(Arrays.asList(user1, user2,user3,user4));

    }
}
