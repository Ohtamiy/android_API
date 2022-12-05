package ua.cn.stu.countries_mvvm.model.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipNetworkEntity {

    @JsonProperty("ship_id")
    private String ship_id;

    @JsonProperty("ship_name")
    private String ship_name;

    @JsonProperty("ship_type")
    private String ship_type;

    @JsonProperty("home_port")
    private String home_port;

    public String getShip_id() {
        return ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public String getShip_type() {
        return ship_type;
    }

    public String getHome_port() {
        return home_port;
    }

}
