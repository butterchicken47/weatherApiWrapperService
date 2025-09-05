package au.com.vanguard.demo.weatherapi.client;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    private String id;
    private String main;
    private String description;
    private String icon;
}
