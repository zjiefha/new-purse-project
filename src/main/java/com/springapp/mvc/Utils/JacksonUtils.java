package com.springapp.mvc.Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by changjingzou on 2015/11/4.
 * Desc: bean对象和json之间转换
 */
public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 设置允许使用非双引号属性名
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 设置允许使用单引号包住属性名称和字符串值
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 设置当遇到未知属性（没有映射到属性，没有任何setter或者任何可以处理它的handler），不抛出一个JsonMappingException异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将bean对象转换成json字符串
     * @param object
     * @return
     * @throws IOException
     */
    public static String toJson(Object object) throws IOException {
        ObjectWriter writer = null;
        try {
            writer = objectMapper.writer().withDefaultPrettyPrinter();
            return writer.writeValueAsString(object);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


    /**
     * 将json字符串转换成 Java Bean
     * @param valueJson
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T toBean(String valueJson, Class<T> valueType) throws IOException {
        return objectMapper.readValue(valueJson, valueType);
    }

    public static void main(String[] args){
        String test = "test";
        try {
            String tmp = JacksonUtils.toJson(test);
            System.out.println(tmp);
            String s = JacksonUtils.toBean(tmp, String.class);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将JsonArray字符串转换成List对象
     * @param listJson
     * @param valueType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> toBeanList(String listJson, Class<T> valueType) throws IOException {
        return objectMapper.readValue(listJson, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }

    /**
     * 将List对象转换成JsonArray字符串
     * @param objectList
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> String toJsonArray(List<T> objectList) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            objectMapper.writeValue(writer, objectList);
            return writer.toString();
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            writer.close();
        }
    }

}
