package me.frank.demo.service;

/**
 * JWT相关服务接口
 *
 * @author 王明哲
 */
public interface JwtService {

    /**
     * 为指定数据生成Token
     * @param subject 指定数据内容
     * @return Token
     */
    String genTokenFor(String subject);

    /**
     * 从Token中解密获取数据
     * @param token Token
     * @return 解密后的数据
     */
    String getSubjectFrom(String token);
}
