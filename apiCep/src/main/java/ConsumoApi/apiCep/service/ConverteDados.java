package ConsumoApi.apiCep.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    @Override
    public <T> T enviaDados(String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e){
                throw new RuntimeException(e);
        }
    }
}

