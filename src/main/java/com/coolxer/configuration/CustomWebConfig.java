package com.coolxer.configuration;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.Duration;

/**
 * 加载配置参数
 *
 * @author hunter
 */
@Repository
@Getter
@ToString
public class CustomWebConfig {

    /**
     * 系统插件目录
     */
    @Value("${dih.session.path}")
    private String dihSessionPath;

    /**
     * 系统插件目录
     */
    @Value("${system.plugin.path}")
    private String sysPluginPath;

    /**
     * 系统信息文件目录
     */
    @Value("${system.info.path}")
    private String sysInfoPath;

    /**
     * 配置树路径
     */
    @Value("${policy.config.path}")
    private String configPath;

    /**
     * 需要校验的路径
     */
    @Value("${authority.interceptor.check.path}")
    private String needCheckPath;

    @Value("${retrieval.meta.path}")
    private String retrievalMetaFilePath;

    @Value("${service.data.url}")
    private String dataServiceUrl;

    @Value("${trend.data.size:12}")
    private int trendDateSize;

    @Value("${server.servlet.session.timeout}")
    private Duration sessionTimeout;

}
