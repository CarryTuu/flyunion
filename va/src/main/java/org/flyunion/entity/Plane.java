package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * plane表实体类
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Plane {

	private String code;
	private Integer owner;
	private String fleet;
	private String model;
	private String status;
	private Integer time;
	private double durability;
	private String company;
}
