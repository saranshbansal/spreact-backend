package com.sbansal.spreact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter
{
    private String clientid = "tutorialspoint";
    private String clientSecret = "my-secret-key";
    private String privateKey =
        "-----BEGIN RSA PRIVATE KEY-----\n"
            +
            "MIIEpAIBAAKCAQEAu17Aih24u908J0YnOegE3IGd/K30xf5PW76H9WDVs6FNdPHq\n" +
            "/cYyz/1CXqucA3jIxWUgfVsFhBInxxcT6vevyGFy/ZRsBeShU1yp1fKdNwHqDV4u\n" +
            "CdFHS4o6pv3ZbVaTkHanPo4cI4GC4ewoaKroAOyQcjcH8OVPrW7dXt1xECMh0m5T\n" +
            "4DeRy4ECS4FhLa5DG6VA+ib20Vl0cjf4TmVrqI9uN4Ky0a0bWJsnEcWR2eavXZDN\n" +
            "tsV4fPKJH8kpiCEXeM/NQcuxbiS+JTCumegWKLSNuALMiMxs6tzIATN+S5UqT5GJ\n" +
            "7DC1c/oBWFq1MFi8UoM0XZNSlCY+IKZIk0zkzwIDAQABAoIBAHwzWMm03emZwu/9\n" +
            "rq7Rv7czLDh8Tw6Xfp8/8vuSjtWdjKyCV9SUdsYsCLycYLRkciF24Ux283cSWc+8\n" +
            "2nUcbicTgZfZtKlLXizV9bZeNhxqZm0qCrZzRSErpalgB6pUg0h1fnMW3Ewk409C\n" +
            "rZ6D+3nxVeZ/2AG031YFcTQa8YFRxhBF8pw5SJWd3tktyK7kQ0OxYFqoew8WxOnI\n" +
            "zFdn43PkUWiLS2u051MKxeynPaoKY8KCxT2gT9uOtVgeaihPCk+z21sHCtBqhdGe\n" +
            "EwbsWlkqB1NMinhCXIB2dULdt0n/o+elqD4xtJUlVTlbl8DABcmdyRzKZZ6cTHkv\n" +
            "nWcXyPECgYEA3WeT2mscVCJnQBhgjnph+a3QpyBreZO83QsgVuc6G5VRPFOInoO+\n" +
            "y+MZivbO1PWKLSlGYp4XReA6y/29NsVX7Sjr3BhNIMSbV5D6bCXRsA2Pd68fvvKj\n" +
            "9EUTJVDJKy+syl9Hwg4wPFOvEVPyWBuaUcIyJ9bJDkCL9tSNTlLIJK0CgYEA2KXC\n" +
            "SOMUotMmV9aVCDpGiNBAClKOR8tiaqMJZ3x6A3Tv4dYKuJrR2nMZ9aJFIqSxqZNO\n" +
            "D8cH5xbf4EL9TwQej86oZM+sGDzjFufqYOz9fOcVqtWzrOcWI8vrwdHJe4Y44mNt\n" +
            "MqLKRv75hNUzWy52OZJ51dxGjs6coRi1+R3NYusCgYABmfAt0jyAN0d75TE9lpEC\n" +
            "10j9DHT6iiEBkEUJm96FuIy/3PLOArHsk5XptWNFkwkOzo+dwvr0HG0QcKNftAmw\n" +
            "w20BSIO2SvW8qmXFRni/xzlUakXWgbbB24YJ7Q2nbE7Zn9rLAoNCzHAlte+4x2pU\n" +
            "UQ0/0IPfwIPCo0LqYBrmeQKBgQC8AbUFnZKl7okixwPT/QaC6f35N0VjxnskwVGC\n" +
            "7gHQiCU4HZB2PVyJr0NC58wJUVUgLMYcaQNRpocnzdqXPKLs91EpExNsgUIs+Ztq\n" +
            "+57LSpqCAywGiw+qhueT7mNj/8nTpKb5v028qL6OaJpFsZ9FPPjf/2V3vpMsNg6N\n" +
            "HjDDewKBgQCvVHNBHimt3aTzK8Uu41rXIW2o1cWIjeq+lbdJ7MHv7p1YoobDDlyl\n" +
            "C9N+p45p5Qe4rD/EmpexcNymVdI/jSpZnspnNcHbgdMo8KbMegVBqGG3EGgHYo90\n" +
            "Qa0OD1F5Xm1ZLuwBhV9mCXhDuL4Q6Q3W6uRDiHmuwqxAOBtSr/Y5iQ==\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey =
        "-----BEGIN PUBLIC KEY-----\n"
            +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu17Aih24u908J0YnOegE\n" +
            "3IGd/K30xf5PW76H9WDVs6FNdPHq/cYyz/1CXqucA3jIxWUgfVsFhBInxxcT6vev\n" +
            "yGFy/ZRsBeShU1yp1fKdNwHqDV4uCdFHS4o6pv3ZbVaTkHanPo4cI4GC4ewoaKro\n" +
            "AOyQcjcH8OVPrW7dXt1xECMh0m5T4DeRy4ECS4FhLa5DG6VA+ib20Vl0cjf4TmVr\n" +
            "qI9uN4Ky0a0bWJsnEcWR2eavXZDNtsV4fPKJH8kpiCEXeM/NQcuxbiS+JTCumegW\n" +
            "KLSNuALMiMxs6tzIATN+S5UqT5GJ7DC1c/oBWFq1MFi8UoM0XZNSlCY+IKZIk0zk\n" +
            "zwIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Bean
    public JwtAccessTokenConverter tokenEnhancer()
    {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }


    @Bean
    public JwtTokenStore tokenStore()
    {
        return new JwtTokenStore(tokenEnhancer());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints
            .authenticationManager(authenticationManager).tokenStore(tokenStore())
            .accessTokenConverter(tokenEnhancer());
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
    {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients
            .inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
            .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
            .refreshTokenValiditySeconds(20000);

    }
}
