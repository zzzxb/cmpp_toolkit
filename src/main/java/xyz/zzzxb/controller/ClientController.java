package xyz.zzzxb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zzzxb.client.CmppClient;
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
        try {
            CmppClient cmppClient = new CmppClient();
            cmppClient.bootstrap(dto.getHost(), dto.getPort());
        }catch (Exception e) {
            return e.getMessage();
        }
        return "ok";
    }
}
