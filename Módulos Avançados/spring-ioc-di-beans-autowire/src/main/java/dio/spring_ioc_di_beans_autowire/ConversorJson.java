package dio.spring_ioc_di_beans_autowire;

import com.google.gson.Gson;

public class ConversorJson {
    private Gson gson = new Gson();
    public ViaCepResponse converter(String json){
        ViaCepResponse response = gson.fromJson(json, ViaCepResponse.class);
        return response;
    }
}
