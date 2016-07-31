package com.log.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class JSR310LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>
{

    //TODO verify the user of zone
    private static final DateTimeFormatter ISOFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.systemDefault()/*Constants.zoneId*/);

    public static final JSR310LocalDateTimeDeserializer INSTANCE = new JSR310LocalDateTimeDeserializer();

    private JSR310LocalDateTimeDeserializer()
    {
    }

    @Override
    public LocalDateTime deserialize(JsonParser value, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        return LocalDateTime.parse(value.getText(), ISOFormatter).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
