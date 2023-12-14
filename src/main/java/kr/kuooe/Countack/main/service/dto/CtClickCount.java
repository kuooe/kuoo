package kr.kuooe.Countack.main.service.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CtClickCount {

    @Id
    private String countryCode;
    private int countryClicks;
}