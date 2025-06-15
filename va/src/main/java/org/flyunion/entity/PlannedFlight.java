package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PlannedFlight {

	private String id;
	private String flightCode;
	private String pilot;
	private Date plannedTime;
	private String status;

}
