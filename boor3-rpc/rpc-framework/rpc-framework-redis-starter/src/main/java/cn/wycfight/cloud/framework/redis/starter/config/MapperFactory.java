package cn.wycfight.cloud.framework.redis.starter.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.cache.support.NullValue;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author by achun
 * @Classname MapperFactory
 * @Description TODO
 * @Date 2023/8/22 10:55
 */
public class MapperFactory {
    public static ObjectMapper newInstance() {
        return initMapper(new ObjectMapper(), (String) null);
    }

    private static ObjectMapper initMapper(ObjectMapper mapper, String classPropertyTypeName) {
        mapper.registerModule(new SimpleModule().addSerializer(new MapperNullValueSerializer(classPropertyTypeName)));
        if (StringUtils.hasText(classPropertyTypeName)) {
            mapper.activateDefaultTypingAsProperty(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, classPropertyTypeName);
        } else {
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        }
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    private static class MapperNullValueSerializer extends StdSerializer<NullValue> {
        private static final long serialVersionUID = 1L;
        private final String classIdentifier;
        MapperNullValueSerializer(String classIdentifier) {
            super(NullValue.class);
            this.classIdentifier = StringUtils.hasText(classIdentifier) ? classIdentifier : "@class";
        }

        @Override
        public void serialize(NullValue value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();
            gen.writeStringField(classIdentifier, NullValue.class.getName());
            gen.writeEndObject();
        }
    }

}
