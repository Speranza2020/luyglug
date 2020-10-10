package requester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import org.springframework.web.client.RestTemplate;

public class WeatherRequester {
    private final String PREFIX = "https://samples.openweathermap.org/data/2.5/weather?q=";
    private final String POSTFIX = "&appid=439d4b804bc8187953eb36d2a8c26a02";


    // нам надо взять наш Prefix + city+ "," + country+POSTFIX
    //PREFIX i POSTFIX у нас есть

    public Response requestWeather(String city, String country) throws JsonProcessingException {
        String url = PREFIX + city + "," + country + POSTFIX;  // подготовили ссылку(сделали 1 ый пункт из тетради за 27.09.)

        RestTemplate restTemplate = new RestTemplate(); // создали копию
        String responseToParse = restTemplate.getForEntity(url, String.class).getBody(); // url - 4to mi emu dajem, i vtoroje - na etot objekt natjani JSON
        // это и есть сам mapping
        // responseToParse - в строку переделываем



        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseToParse,Response.class);
    }

}
