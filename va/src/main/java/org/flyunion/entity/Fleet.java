package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * fleet表实体类
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Fleet {
	private String name;
	private String model;
	private double economy;
	private double business;
	private double cargo;
	private int number;
}
