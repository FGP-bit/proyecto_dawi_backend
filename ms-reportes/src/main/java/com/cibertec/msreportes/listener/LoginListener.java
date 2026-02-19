package com.cibertec.msreportes.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LoginListener {

    @RabbitListener(queues = "cola-login")
    public void recibirMensaje(String mensaje) {
        System.out.println("=========================================");
        System.out.println("RABBITMQ RECIBIÓ UN MENSAJE EN MS-REPORTES:");
        System.out.println(mensaje);
        System.out.println("=========================================");
    }
}