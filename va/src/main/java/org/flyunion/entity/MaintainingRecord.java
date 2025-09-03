package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class MaintainingRecord {

	private String id;
	private String code;
	private String type;
	private LocalDateTime date;
	private String formattedTime;

}
