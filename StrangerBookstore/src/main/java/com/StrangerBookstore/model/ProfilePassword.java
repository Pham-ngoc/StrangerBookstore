package com.StrangerBookstore.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProfilePassword {
    @NotBlank(message = "New Password must not be blank")
    private String newPassword;

    @NotBlank(message = "Confirm Password must not be blank")
    private String confirmNewPassword;
}
