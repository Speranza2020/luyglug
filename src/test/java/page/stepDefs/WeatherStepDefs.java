package page.stepDefs;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Response;
import org.junit.jupiter.api.Assertions;
import requester.WeatherRequester;


public class WeatherStepDefs {
    private String city;
    private String county;
    private Response response;

    @Given("city is: {string}")
    public void set_city(String city) {
        this.city = city; // this.city = zna4it obra6ajemsja k peremen. na urovne klasse

    }

    @Given("country is: {string}")
    public void set_country(String country) {
        this.county = country.toLowerCase();// mi pisali bolj6imi, a nam nado malenjkimi

    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException { //mi ni4ego ne peredajem< poetomu funkcija ni4ego ne polu4aet
        WeatherRequester requester = new WeatherRequester();
        System.out.println(requester.requestWeather(city,county).getCoord().getLat()); // он нам вернет response
        response = requester.requestWeather(city, county);
    }

    @Then("lon is: {double}")
    public void check_lon(Double lon) {
        Assertions.assertEquals(lon,response.getCoord().getLon(), "Wrong lon value");


    }

    @Then("lat is: {double}")
    public void  check_lat(Double lat) {
        Assertions.assertEquals(lat,response.getCoord().getLat(),"Wrong lat value");


    }

    @Then("id is: {int}")
    public void check_id(int id) {
        Assertions.assertEquals(id,response.getWeathers().getId(), "Wrong Id value");

    }

    @Then("main is: {string}")
    public void check_main(String main) {
        Assertions.assertEquals(main,response.getWeathers().getMain(),"Wrong main value");

    }

    @Then("description is: {string}")
    public void check_description(String description) {
        Assertions.assertEquals(description,response.getWeathers().getDescription(), "Wrong Description value");

    }

    @Then("icon is: {string}")
    public void check_icon(String icon) {
        Assertions.assertEquals(icon,response.getWeathers().getIcon(), "Wrong Icon value");

    }

    @Then("base is: {string}")
    public void check_base(String base) {


    }
    @Then("temp is: {double}")
    public void check_temp(Double temp) {
        Assertions.assertEquals(temp,response.getMains().getTemp(),"Wrong Temp value");

    }

    @Then("pressure is: {int}")
    public void check_temp(int pressure) {
        Assertions.assertEquals(pressure,response.getMains().getPressure(),"Wrong Pressure value");

    }

    @Then("humidity is: {int}")
    public void check_humidity(int humidity) {
        Assertions.assertEquals(humidity,response.getMains().getHumidity(),"Wrong Humidity value");
    }

    @Then("temp_min is: {double}")
    public void check_temp_min(Double temp_min) {
        Assertions.assertEquals(temp_min,response.getMains().getTemp_min(), "Wrong Temp_min value");

    }

    @Then("temp_max is: {double}")
    public void check_temp_max(Double temp_max) {
        Assertions.assertEquals(temp_max,response.getMains().getTemp_max(),"Wrong Temp_max value");

    }

    @Then("visibility is: {int}")
    public void check_visibility(int visibility) {


    }

    @Then("speed is: {double}")
    public void check_speed(Double speed) {
        Assertions.assertEquals(speed,response.getWinds().getSpeed(), "Wrong Speed value");

    }

    @Then("deg is: {int}")
    public void check_deg(int deg) {
        Assertions.assertEquals(deg,response.getWinds().getDeg(), "Wrong Speed value");

    }

    @Then("all is: {int}")
    public void check_all(int all) {
        Assertions.assertEquals(all,response.getClouds().getAll(), "Wrong Speed value");
    }

    @Then("dt is: {long}")
    public void check_dt(Long dt) {


    }

    @Then("type is: {int}")
    public void check_type(int type) {
        Assertions.assertEquals(type,response.getSys().getType(),"Wrong Type  value");
    }

    @Then("message is: {double}")
    public void check_message(Double message) {
        Assertions.assertEquals(message,response.getSys().getMessage(),"Wrong message");
    }

    @Then("country is: {string}")
    public void check_country(String country) {
        Assertions.assertEquals(country,response.getSys().getCountry(),"Wrong country");

    }

    @Then("sunrise is: {long}")
    public void check_sunrise(Long sunrise) {
        Assertions.assertEquals(sunrise,response.getSys().getSunrise(),"Wrong sunrise value");

    }

    @Then("sunset is: {long}")
    public void check_sunset(Long sunset) {
        Assertions.assertEquals(sunset,response.getSys().getSunset(),"Wrong sunset value");

    }

    @Then("name is: {string}")
    public void check_name(String name) {


    }

    @Then("cod is: {int}")
    public void check_cod(int cod) {

    }
}
