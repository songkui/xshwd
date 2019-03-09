

package com.xshwd.user.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Configuration
@ConfigurationProperties(prefix = "tenxunCos", ignoreUnknownFields = true)

public class TenXunCosProperties {
    @Value("${tenxunCos.AppId}")
    public String AppId;

    @Value("${tenxunCos.SecretId}")
    public String SecretId;

    @Value("${tenxunCos.SecretKey}")
    public String SecretKey;

    @Value("${tenxunCos.Bucket}")
    public String Bucket;

    @Value("${tenxunCos.Region}")
    public String Region;

    @Value("${tenxunCos.URL}")
    public String URL;

    @Value("${tenxunCos.CDN}")
    public String CDN;


    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getSecretId() {
        return SecretId;
    }

    public void setSecretId(String secretId) {
        SecretId = secretId;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }

    public String getBucket() {
        return Bucket;
    }

    public void setBucket(String bucket) {
        Bucket = bucket;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCDN() {
        return CDN;
    }

    public void setCDN(String CDN) {
        this.CDN = CDN;
    }
}
