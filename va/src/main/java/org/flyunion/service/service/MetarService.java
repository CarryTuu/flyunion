package org.flyunion.service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flyunion.entity.weather.MetarData;
import org.flyunion.utils.MetarParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 气象报文服务
 * 获取指定机场的气象报文
 * 使用xflysim的api
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Service
public class MetarService {

	private static final String REQUEST_URL = "https://api.xflysim.com/pilot/api/realTimeMap/weather/";

	public MetarData getMetarData(String icao) throws IOException, InterruptedException {

		ObjectMapper objectMapper = new ObjectMapper();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(URI.create(REQUEST_URL + icao))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode node = objectMapper.readTree(response.body());
		String text = node.get("data").get("rawText").asText();

		return MetarParser.parseMetar(text);
	}

}
