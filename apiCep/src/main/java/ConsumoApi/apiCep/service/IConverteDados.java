package ConsumoApi.apiCep.service;

public interface IConverteDados {
    public <T> T enviaDados(String json, Class<T> tClass);
}
