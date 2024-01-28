package duongam.training.service.http;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 
 * @author DuongAM
 *
 * @param <T>  : Request Type
 * @param <K>: Response Type
 */
public class HttpBase<T, K> {

	public K getFromAPI(String url, Class<K> responseType) {
		HttpEntity<T> requestEntity = new HttpEntity<T>(getHttpHeader());

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<K> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);

		HttpStatus statusCode = response.getStatusCode();
		K results = null;
		if (statusCode == HttpStatus.OK) {
			results = response.getBody();
		}
		return results;
	}

	public K deleteFromAPI(String url, Class<K> responseType) {
		HttpEntity<T> requestEntity = new HttpEntity<T>(getHttpHeader());

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<K> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType);

		HttpStatus statusCode = response.getStatusCode();
		K results = null;
		if (statusCode == HttpStatus.OK) {
			results = response.getBody();
		}
		return results;
	}

	public K postToAPI(T requestObject, String url, Class<K> responseType) {
		return postOrPutToAPI(requestObject, url, responseType, HttpMethod.POST);
	}

	public K putToAPI(T requestObject, String url, Class<K> responseType) {
		return postOrPutToAPI(requestObject, url, responseType, HttpMethod.PUT);
	}

	private K postOrPutToAPI(T requestObject, String url, Class<K> responseType, HttpMethod httpMethod) {
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestObject, getHttpHeader());

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<K> response = null;
		try {
			response = restTemplate.exchange(url, httpMethod, requestEntity, responseType);
		} catch (HttpClientErrorException e) {
			System.out.println(e.toString());
			return null;
		}

		HttpStatus statusCode = response.getStatusCode();
		System.out.println(statusCode);
		K results = null;
		if (statusCode == HttpStatus.OK) {
			results = response.getBody();
		}
		return results;
	}
	private HttpHeaders getHttpHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(!Token.API_KEY.equals("None")){
			headers.set(Token.HEADER, Token.API_KEY);
		}
		return headers;
	}
}