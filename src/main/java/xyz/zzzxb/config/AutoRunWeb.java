package xyz.zzzxb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.Properties;

/**
 * zzzxb
 * 2023/10/7
 */
@Slf4j
@Configuration
public class AutoRunWeb {

    @Value("${server.port}")
    private String port;
    @Value("${open.web.command:open}")
    private String command;

    @EventListener(ApplicationReadyEvent.class)
    void applicationReadEven() {
        String url = "http://localhost:" + port;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command(url));
            log.info("start server: {}", url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String command(String url) {
        Properties properties = System.getProperties();
        String osName = properties.getProperty("os.name");
        if (osName.toLowerCase().startsWith("mac")) {
            command = "open " + url;
        } else if (osName.toLowerCase().startsWith("linux")) {
            command = "open " + url;
        } else if (osName.toLowerCase().startsWith("windows")) {
            command = "start " + url;
        }
        return command;
    }
}
