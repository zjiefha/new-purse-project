package com.springapp.mvc.Utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

/**
 * Created by zhangjiefeng on 16/4/3.
 */
public class JwtUtil {

    private static String key = "51vc";

    public static String getJwt(String payLoad) {
        byte[] bytes = Base64Utils.decodeFromString(key);
        SecretKey secretKey = new SecretKeySpec(bytes, "HS256");
        return Jwts.builder().setHeaderParam("type", "JSON Web Tokens").setPayload(payLoad)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public static Map<String, Object> parseJwt(String jwt) {
        byte[] bytes = Base64Utils.decodeFromString(key);
        SecretKey secretKey = new SecretKeySpec(bytes, "HS256");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        System.out.println(jsonObject);
        String jwt = getJwt(jsonObject.toString());
        System.out.println(jwt);
//        Map<String, Object> stringObjectMap = parseJwt(jwt);


//        String[] split = jwt.split("\\.");
//        System.out.println(split[1]);
//        String string = new String(Base64Utils.decodeFromString(split[2]));
//        System.out.println("11" + string);
//        System.out.println(jwt);
    }

}
