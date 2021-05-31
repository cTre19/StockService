package com.cognizant.estock.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StatisticsDTO {

    private double min;
    private double max;
    private double avg;
}
