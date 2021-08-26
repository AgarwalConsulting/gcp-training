package com.ecommerce.resources;

import com.ecommerce.service.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final Producer producer;

    @GetMapping("/publish")
    public void messageToTopic(){
        this.producer.sendMessage("hadi bi daha");
    }

}
