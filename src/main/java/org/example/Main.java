package org.example;

import lombok.SneakyThrows;
import org.example.task7.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var paymentService = context.getBean(PaymentService.class);
        var product1 = paymentService.findProductById(1);
        System.out.println(product1);

        //тут получим ошибку, тк в бд нет такого продукта
        var product10 = paymentService.findProductById(10);
        //2025-10-03T20:53:04.744+03:00  INFO 3540 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
        //ProductDto[id=1, accountNumber=40817810099910004321, balance=150000.50, productType=ACCOUNT, user=null, userId=11]
        //Ошибка при обращении по адресу: http://localhost:8080/v1/products/10 {"message":"Product not found"}
    }
}
