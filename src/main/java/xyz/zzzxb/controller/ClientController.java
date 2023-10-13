package xyz.zzzxb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zzzxb.pojo.StartDTO;

/**
 * zzzxb
 * 2023/10/13
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    @PostMapping("/start")
    public String start(@RequestBody StartDTO dto) {
        System.out.println(dto);
        return "ok";
    }
}
