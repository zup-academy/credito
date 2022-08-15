package com.zupedu.credito.oferta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NovaContaListener {

    Logger logger = LoggerFactory.getLogger(NovaContaListener.class);

    @Autowired
    private OfertaService ofertaService;

    @KafkaListener(topics = "NOVA_CONTA")
    public void receber(NovaContaEvent event){

        logger.info("Evento NOVA_CONTA consumido com sucesso {} ", event.toString());

        ofertaService.incluirNovaOferta(
                event.getAgencia(), event.getNumero(), event.getSaldo());
    }
}
