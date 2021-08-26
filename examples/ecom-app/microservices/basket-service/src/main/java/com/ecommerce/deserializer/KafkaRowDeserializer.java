package com.ecommerce.deserializer;

import com.ecommerce.model.KafkaRowDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class KafkaRowDeserializer implements Deserializer<KafkaRowDto> {

    @Override
    public void close() { }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) { }

    @Override
    public KafkaRowDto deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        KafkaRowDto dto = null;
        try {
            dto = mapper.readValue(arg1, KafkaRowDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

}
