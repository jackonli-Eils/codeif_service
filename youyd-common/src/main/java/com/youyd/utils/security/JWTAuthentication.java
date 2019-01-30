package com.youyd.utils.security;

import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * @description: S
 * @author: LGG
 * @create: 2018-10-24 19:54
 **/
@ConfigurationProperties("jwt.config")
public class JWTAuthentication {

	private String encodedSecretKey ;//签名秘钥
	private long expiration ;// 过期时间


	/**
	 * 生成JWT
	 * @param id
	 * @param subject
	 * @param roles
	 * @return
	 */
	public String createJWT(String id, String subject, String roles) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder();
		builder.setId(id);
		builder.setSubject(subject);
		builder.setIssuedAt(now);// 设置签发时间
		builder.signWith(SignatureAlgorithm.HS256, encodedSecretKey); // 设置签名秘钥
		builder.setExpiration(new Date(System.currentTimeMillis() + 1000*60 ));//过期时间为1分钟
		builder.claim("roles",roles);
		if (expiration > 0) {
			builder.setExpiration( new Date( nowMillis + expiration));
		}
		return builder.compact();
	}

	/**
	 * 解析JWT
	 * @param jwtStr:待解密的jwt
	 * @return
	 */
	public Claims parseJWT(String jwtStr){
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(encodedSecretKey);
		Claims body = parser.parseClaimsJws(jwtStr).getBody();
		return body;
	}




	public String getEncodedSecretKey() {
		return encodedSecretKey;
	}
	public void setEncodedSecretKey(String encodedSecretKey) {
		this.encodedSecretKey = encodedSecretKey;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
}