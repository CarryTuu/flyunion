package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 已计划航班实体类
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PlannedFlight {
	private String id;
	private String flightCode;
	private String departure;
	private String arrival;
	private String pilot;
	private LocalDateTime plannedTime;
	private String formattedTime;
	private String status;
}
