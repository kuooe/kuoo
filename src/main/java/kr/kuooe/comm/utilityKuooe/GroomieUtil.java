package kr.kuooe.comm.utilityKuooe;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.kuooe.Groomie.Clothes.service.vo.NaverShopParamVO;
import kr.kuooe.Groomie.Clothes.service.vo.NaverShopResVO;
import kr.kuooe.Groomie.localWeather.service.vo.LocationVO;
import kr.kuooe.Groomie.localWeather.service.vo.WeatherVO;


@Component
public class GroomieUtil {
	public static Logger log = LoggerFactory.getLogger(GroomieUtil.class);

	public static LocationVO kakaoLocation(LocationVO locationVO) throws Exception {
		String apiUrl = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x="+locationVO.getNLongitude()+"&y="+locationVO.getLatitude();
		String restApiKey = "3a8429587290b8a16e916befb328ffbe";
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		String resBody = "";
		try {
		    URL url = new URL(apiUrl);
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Authorization", "KakaoAK " + restApiKey);
		    System.out.println("응답 결과 : " + conn.getResponseCode() + ", " + conn.getResponseMessage());
		    
		    if (conn.getResponseCode() != 200) {
		        is = conn.getErrorStream();
		    } else {
		        is = conn.getInputStream();
		    }
		    
		    baos = new ByteArrayOutputStream();
		    byte[] buf = new byte[1024 * 8];
		    int length = 0;
		    while ((length = is.read(buf)) != -1) {
		        baos.write(buf, 0, length);
		    }
		    
		    resBody = new String(baos.toByteArray(), "UTF-8");
		    System.out.println("응답 데이터 : " + resBody);
            // JSON 데이터를 WeatherVO 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(resBody);

            // 날씨 정보 설정
            locationVO.setLatitude(rootNode.get("documents").get(0).get("y").asText());
            locationVO.setNLongitude(rootNode.get("documents").get(0).get("x").asText());
            locationVO.setAddressName(rootNode.get("documents").get(0).get("address_name").asText());
            locationVO.setAddressName1(rootNode.get("documents").get(0).get("region_1depth_name").asText());

		} catch (IOException e) {
			System.out.println(e);
		    e.printStackTrace();
		} finally {
		    try {
		        if (is != null) is.close();
		        if (baos != null) baos.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return locationVO;
		}
	}
	
	public static WeatherVO openWeather(LocationVO locationVO) throws Exception {

		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lon="+locationVO.getNLongitude()+"&lat="+locationVO.getLatitude()+"&appid=658d847ef1d28e72e047ab0c5a476d54&units=metric";
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
        WeatherVO weatherVO = new WeatherVO();
		String resBody = "";
		try {
		    URL url = new URL(apiUrl);
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    
		    System.out.println("응답 결과 : " + conn.getResponseCode() + ", " + conn.getResponseMessage());
		    
		    if (conn.getResponseCode() != 200) {
		        is = conn.getErrorStream();
		    } else {
		        is = conn.getInputStream();
		    }
		    
		    baos = new ByteArrayOutputStream();
		    byte[] buf = new byte[1024 * 8];
		    int length = 0;
		    while ((length = is.read(buf)) != -1) {
		        baos.write(buf, 0, length);
		    }
		    
		    resBody = new String(baos.toByteArray(), "UTF-8");
		    
            // JSON 데이터를 WeatherVO 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(resBody);

            // 날씨 정보 설정
            weatherVO.setTemperature(rootNode.path("main").path("temp").asText());
            weatherVO.setWeatherMain(rootNode.path("weather").get(0).path("main").asText());
            weatherVO.setWeatherDescription(rootNode.path("weather").get(0).path("description").asText());
            weatherVO.setWeatherCode(rootNode.path("weather").get(0).path("icon").asText());
            weatherVO.setTemperature(rootNode.path("main").path("temp").asText());
		    
		    System.out.println("응답 데이터 : " + resBody);
		   
		} catch (IOException e) {
			System.out.println(e);
		    e.printStackTrace();
		} finally {
		    try {
		        if (is != null) is.close();
		        if (baos != null) baos.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return weatherVO;
		}
	}
	
	
    public static List<NaverShopResVO> naverShop(NaverShopParamVO naverShopParamVO) throws Exception {
        List<NaverShopResVO> naverShopResList = new ArrayList<>();
        String apiUrl = "https://openapi.naver.com/v1/search/shop.json?query="+naverShopParamVO.getQuery()+"&display=10&start=1&sort=sim";

        // 애플리케이션 등록 시 발급받은 클라이언트 아이디와 시크릿
        String clientId = "ocgfp78wPptB7A5McrtO";
        String clientSecret = "7JCmJ9b7Oi";

        // HTTP 클라이언트 생성
        HttpClient httpClient = HttpClients.createDefault();

        // HTTP GET 요청 생성
        HttpGet httpGet = new HttpGet(apiUrl);

        // 요청 헤더 설정
        httpGet.setHeader(HttpHeaders.ACCEPT, "application/xml");
        httpGet.setHeader("X-Naver-Client-Id", clientId);
        httpGet.setHeader("X-Naver-Client-Secret", clientSecret);

        try {
            // HTTP 요청 실행
            HttpResponse response = httpClient.execute(httpGet);

            // 응답 상태코드 확인
            int statusCode = response.getStatusLine().getStatusCode();

            // 응답 내용 읽기
            InputStream is = response.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(is);

            JsonNode itemsNode = rootNode.get("items");

            for (JsonNode item : itemsNode) {
                NaverShopResVO resVO = new NaverShopResVO();

                resVO.setTitle(item.get("title").asText());
                resVO.setLink(item.get("link").asText());
                resVO.setImage(item.get("image").asText());

                // 추출한 정보를 리스트에 추가
                naverShopResList.add(resVO);
            }

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return naverShopResList;
    }
	
    public static String detectSeaso() {
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // 현재 월 가져오기
        Month currentMonth = currentDate.getMonth();

        // 계절 판별
        switch (currentMonth) {
            case DECEMBER:
            case JANUARY:
            case FEBRUARY:
                return "겨울";
            case MARCH:
            case APRIL:
            case MAY:
                return "봄";
            case JUNE:
            case JULY:
            case AUGUST:
                return "여름";
            case SEPTEMBER:
            case OCTOBER:
            case NOVEMBER:
                return "가을";
            default:
                return "알 수 없음";
        }
    }
}