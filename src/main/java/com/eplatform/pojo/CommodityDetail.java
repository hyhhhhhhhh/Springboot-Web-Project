package com.eplatform.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableAutoConfiguration
public class CommodityDetail {
    private int id;
    private String brief;
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public CommodityDetail(Map<String, Object> maps){
        id = (int)maps.get("id");
        brief = String.valueOf(maps.get("brief"));
        detail = String.valueOf(maps.get("detail"));
    }
}
