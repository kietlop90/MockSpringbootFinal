package duongam.training.service.http;

import duongam.training.customexception.ForbiddenException;
import lombok.SneakyThrows;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @param <T>  : Request Type
 * @param <K>: Response Type
 * @author DuongAM
 */
public class HttpBase<T, K> {

    @SneakyThrows
    public K getFromAPI(String url, Class<K> responseType) {
        try {
            HttpEntity<T> requestEntity = new HttpEntity<T>(getHttpHeader());

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<K> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);

            HttpStatus statusCode = response.getStatusCode();
            K results = null;
            if (statusCode == HttpStatus.OK) {
                results = response.getBody();
            }
            return results;
        } catch (HttpClientErrorException.Forbidden exception) {
            throw new ForbiddenException(exception.getMessage());
        }
    }

    @SneakyThrows
    public K putStringToAPI(String url, Class<K> responseType) {
        try {
            HttpEntity<T> requestEntity = new HttpEntity<T>(getHttpHeader());

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<K> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);

            HttpStatus statusCode = response.getStatusCode();
            K results = null;
            if (statusCode == HttpStatus.OK) {
                results = response.getBody();
            }
            return results;
        } catch (HttpClientErrorException.Forbidden exception) {
            throw new ForbiddenException(exception.getMessage());
        }
    }

    @SneakyThrows
    public K deleteFromAPI(String url, Class<K> responseType) {
        try {
            HttpEntity<T> requestEntity = new HttpEntity<T>(getHttpHeader());

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<K> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType);

            HttpStatus statusCode = response.getStatusCode();
            K results = null;
            if (statusCode == HttpStatus.OK) {
                results = response.getBody();
            }
            return results;
        } catch (HttpClientErrorException.Forbidden exception) {
            throw new ForbiddenException(exception.getMessage());
        }
    }


    public K postToAPI(T requestObject, String url, Class<K> responseType) {
        return postOrPutToAPI(requestObject, url, responseType, HttpMethod.POST);
    }

    public K putToAPI(T requestObject, String url, Class<K> responseType) {
        return postOrPutToAPI(requestObject, url, responseType, HttpMethod.PUT);
    }

    @SneakyThrows
    private K postOrPutToAPI(T requestObject, String url, Class<K> responseType, HttpMethod httpMethod) {
        try {
            HttpEntity<T> requestEntity = new HttpEntity<T>(requestObject, getHttpHeader());

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<K> response = null;
            try {
                response = restTemplate.exchange(url, httpMethod, requestEntity, responseType);
            } catch (HttpClientErrorException e) {
                System.out.println(e.toString());
                throw e;
            }

            HttpStatus statusCode = response.getStatusCode();
            System.out.println(statusCode);
            K results = null;
            if (statusCode == HttpStatus.OK) {
                results = response.getBody();
            }
            return results;
        } catch (HttpClientErrorException.Forbidden exception) {
            throw new ForbiddenException(exception.getMessage());
        }
    }

    private HttpHeaders getHttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!Token.API_KEY.equals("None")) {
            headers.set(Token.HEADER, Token.API_KEY);
        }
        return headers;
    }
}