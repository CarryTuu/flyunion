package org.flyunion.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * plan_list
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PlanList{
    private String planId;

    private String flightCode;

    private String departure;

    private String arrival;

    private String pilot;

    private LocalDate planTime;

    private String status;
}