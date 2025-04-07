package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * user表实体类
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String cid;
	private String userName;
	private Integer callsign;
	private String password;
	private String qq;
	private Integer permission;
	private String email;
	private String status;
	private Integer balance;
	private String company;
	private String position;
	private Integer time;
	private Integer flightCount;
	private Integer job;
	private Integer planeCount;
}
