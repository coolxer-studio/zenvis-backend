package com.coolxer.component;


import com.coolxer.commons.enums.PluginStatusType;
import com.coolxer.configuration.CustomWebConfig;
import com.coolxer.configuration.extend.ExtendJarManager;
import com.coolxer.model.retrieval.meta.MetaData;
import com.coolxer.service.core.ClickhouseSchemeService;
import com.coolxer.service.retrieval.MetaDataService;
import com.coolxer.service.system.PluginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 启动加载类
 */
@Component
@Slf4j
@Order(value = 1)
@ImportResource(locations = {"classpath:kaptcha.xml"})
public class InitComponent implements CommandLineRunner {

    @Autowired
    private ClickhouseSchemeService clickhouseSchemeService;
    @Autowired
    private DataInitiator dataInitiator;

    @Autowired
    private CustomWebConfig customWebConfig;

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private PluginService pluginService;

    @Autowired
    private ExtendJarManager extendJarManager;

    @Override
    public void run(String... args) throws Exception {
        // 输出关键信息
        log.info("InitComponent is run");
        log.info("totalMemory:{}M", Runtime.getRuntime().totalMemory() / 1024 / 1024);
        log.info("userDir is {}", System.getProperty("user.dir"));
        log.info("config:{}", customWebConfig.toString());

        clickhouseSchemeService.initScheme();

        dataInitiator.initData();

        // 初始化加载meta
        MetaData metaData = metaDataService.loadMetaData();
        // 初始化Clickhouse表
        clickhouseSchemeService.loadSchemeFromMetaData(metaData);

        // TODO 扫描已经安装的插件，执行加载操作
        pluginService.findAll().stream().filter(plugin -> plugin.getStatus() == PluginStatusType.INSTALLED).forEach(pluginVo -> {
            Path apiPath = Paths.get(customWebConfig.getPluginPath(), pluginVo.getPackageName(), "03_api");
            try (Stream<Path> paths = Files.walk(apiPath)) {
                paths.filter(Files::isRegularFile) // 过滤出文件
                        .filter(path -> path.toString().endsWith(".jar")) // 过滤
                        .forEach(jarPath -> {
                            try {
                                if (extendJarManager.load(pluginVo.getPackageName(), jarPath.toFile())) {
                                    log.info("loaded");
                                } else {
                                    log.info("loaded(already)");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
