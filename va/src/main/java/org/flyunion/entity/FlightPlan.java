package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * flight-plan实体类
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class FlightPlan {

	private String planId;
	private String flightCode;
	private String departure;
	private String arrival;
	private String route;

}
