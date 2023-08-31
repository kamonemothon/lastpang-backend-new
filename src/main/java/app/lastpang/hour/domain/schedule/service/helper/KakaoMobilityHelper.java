package app.lastpang.hour.domain.schedule.service.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoMobilityHelper {

    @Value("${KAKAO_API_KEY}")
    private String API_KEY;

    public Long getDepartureTime(String origin, String destination, String arrivalTime) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", API_KEY);
            httpHeaders.set("Content-Type", "application/json");

            String url = "https://apis-navi.kakaomobility.com/v1/future/directions";
            String queryParams = String.format(
                    "?origin=%s&destination=%s&departure_time=%s",
                    origin, destination, arrivalTime);

            HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url + queryParams,
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String response = responseEntity.getBody();
                int startIndex = response.indexOf("\"duration\"");

                if (startIndex != -1) {
                    int colonIndex = response.indexOf(":", startIndex);
                    int commaIndex = response.indexOf(",", colonIndex);

                    if (colonIndex != -1 && commaIndex != -1) {
                        String durationValue = response.substring(colonIndex + 1, commaIndex).trim();
                        durationValue = durationValue.replace("{", "").replace("}", "").trim();
                        Long duration = Long.valueOf(durationValue);
                        return duration / 60;
                    }
                } else {
                    System.err.println("duration을 찾을 수 없습니다.");
                }
            } else {
                System.err.println("Request failed with status code: " + responseEntity.getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
