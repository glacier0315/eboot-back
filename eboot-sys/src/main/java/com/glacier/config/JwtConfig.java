package com.glacier.config;


import com.glacier.auth.common.utils.jwt.JwtUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-21 21:22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config.jwt")
public class JwtConfig {
    private String header;
    private String secret;
    private long expireTime;
    private List<String> ignoredPath;

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(secret, expireTime);
    }

    /**
     * 验证是否是忽略验证
     *
     * @param path
     * @return
     */
    public boolean ignoreMath(String path) {
        boolean ignore = false;
        List<String> ignoredPath = this.getIgnoredPath();
        if (ignoredPath != null && !ignoredPath.isEmpty()) {
            for (String prifx : ignoredPath) {
                // 开头
                if (path.startsWith(prifx)) {
                    ignore = true;
                    break;
                }
                // 正则表达式判断
                Pattern pattern = Pattern.compile("^" + prifx.replaceAll("\\*\\*", "\\*"));
                Matcher matcher = pattern.matcher(path);
                if (matcher.find()) {
                    ignore = true;
                    break;
                }
            }
        }
        return ignore;
    }
}