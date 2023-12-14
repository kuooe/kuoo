package kr.kuooe.comm.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WeatherEnum {
    CODE_01D("01d", "맑음 (낮)"),
    CODE_01N("01n", "맑음 (밤)"),
    CODE_02D("02d", "구름이 조금 (낮)"),
    CODE_02N("02n", "구름이 조금 (밤)"),
    CODE_03D("03d", "구름이 많음"),
    CODE_03N("03n", "구름이 많음"),
    CODE_04D("04d", "흐림"),
    CODE_04N("04n", "흐림"),
    CODE_09D("09d", "소나기"),
    CODE_09N("09n", "소나기"),
    CODE_10D("10d", "비 (낮)"),
    CODE_10N("10n", "비 (밤)"),
    CODE_11D("11d", "천둥 번개"),
    CODE_11N("11n", "천둥 번개"),
    CODE_13D("13d", "눈"),
    CODE_13N("13n", "눈"),
    CODE_50D("50d", "안개"),
    CODE_50N("50n", "안개"),
    UNKNOWN("unknown", "알 수 없음");

    private final String code;
    private final String description;

    public static String getWeatherDescription(String code) {
        for (WeatherEnum weatherCode : values()) {
            if (weatherCode.getCode().equals(code)) {
                return weatherCode.getDescription();
            }
        }
        return UNKNOWN.getDescription();
    }
}