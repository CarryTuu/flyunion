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
public class Notification {

	private String title;
	private LocalDateTime dateTime;
	private String formattedDateTime;
	private String content;
	private String type;
	private String author;

}
