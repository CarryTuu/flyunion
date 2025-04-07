package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

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
	private int pilot;
	private Date date;
	private int rate;
	private double oil;

}
