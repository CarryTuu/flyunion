package org.flyunion.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * constants表实体类
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Constants {

	private String key;
	private double value;
	private String description;

}
