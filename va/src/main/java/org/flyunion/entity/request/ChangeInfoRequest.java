package org.flyunion.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ChangeInfoRequest {

	private String cid;
	private String userName;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	private String email;

}
