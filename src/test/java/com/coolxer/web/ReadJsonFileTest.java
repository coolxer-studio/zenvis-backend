package com.coolxer.web;

import com.coolxer.configuration.CustomWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

/**
 * desc
 */
@SpringBootTest
@Slf4j
public class ReadJsonFileTest {

    @Autowired
    private CustomWebConfig customWebConfig;


    private String readJsonFile(String filePath) {

        InputStream configInputStream = getClass().getResourceAsStream(filePath);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(configInputStream).toString();

        } catch (IOException e) {
            log.error("读取json文件失败", e);
        }
        return null;
    }
}
