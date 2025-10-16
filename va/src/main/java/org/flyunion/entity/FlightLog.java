package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * flight-log实体类
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class FlightLog {

	private String id;
	private String code;
	private String plane;
	private String pilot;
	private String departure;
	private String arrival;
	private String route;
	private LocalDateTime date;
	private String formattedTime;
	private int rate;
	private double oil;
	private String status;

}
