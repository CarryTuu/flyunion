package org.flyunion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 通知实体类，暂未实装，敬请期待后续版本
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
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
