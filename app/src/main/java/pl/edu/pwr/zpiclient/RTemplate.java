package pl.edu.pwr.zpiclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;


public class RTemplate {
    private static final RTemplate ourInstance = new RTemplate();

    RestTemplate restTemplate;

    HttpHeaders sessionHeaders;

    public static RTemplate getInstance() {
        return ourInstance;
    }

    private RTemplate() {
        this.restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new ResourceHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        //HttpsURLConnection
        //HttpClient httpClient = HttpClientBuilder.create().build();
        //HttpClient httpClient = HttpClientBuilder.create().build();
        //ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory((org.apache.http.client.HttpClient) httpClient);
        //restTemplate.setRequestFactory(factory);
    }
}
