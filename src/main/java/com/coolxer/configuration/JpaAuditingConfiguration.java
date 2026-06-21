package com.coolxer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;
import java.util.Optional;

/**
 * jpa 审计功能提供记录着
 */
@Slf4j
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration implements AuditorAware<Integer> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Optional<Integer> getCurrentAuditor() {

        try {
            HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                String sessionId = requestAttributes.getSessionId();
                if (StringUtils.isNotEmpty(sessionId)) {
                    Map<String, String> mapSession = hashOperations.entries(sessionId);
                    return Optional.of(Integer.valueOf(mapSession.get("strUid")));
                }
            }
            return Optional.ofNullable(1);

        } catch (Exception e) {
            log.error("", e);
            return Optional.ofNullable(1);
        }
    }
}
