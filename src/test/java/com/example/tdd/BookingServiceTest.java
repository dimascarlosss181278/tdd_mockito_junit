package com.example.tdd;

import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.example.tdd.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

//SpringRunner faz a ponte com Junit
@RunWith(SpringRunner.class)
public class BookingServiceTest {

    // define a classe como configuracao de teste, evitando ser visualizada futuramente
    @TestConfiguration
    static class BookingServiceTestConfiguration{

        //Identifica o metodo como Bean do spring para a injecao autowired funcionar
        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    @Autowired
    BookingService bookingService;

    //anotation que prepara o ponto de injecao para o mockito
    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDaysCalculator(){
        String name = "Rei Aragorn";
        int days = bookingService.daysCalculatorWithDatabase(name);

        //passa como parametro days mas o resultado esperado é 10
        Assertions.assertEquals(9, days);
    }

    //prepara o mock com dados para o teste ANTES do mesmo iniciar
    @Before
    public void setup(){
        LocalDate checkIn = LocalDate.parse("2023-02-01");
        LocalDate checkOut = LocalDate.parse("2023-02-10");
        BookingModel bookingModel = new BookingModel("1", "Rei Aragorn", checkIn, checkOut, 2);

        //
        Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
                .thenReturn(java.util.Optional.of(bookingModel));

    }

}
