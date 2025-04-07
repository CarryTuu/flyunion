package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
/**
 * company表实体类
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	private String id;
	private String name;
	private int pilotCount;
	private String balance;
	private int planeCount;
	private String base;

}
