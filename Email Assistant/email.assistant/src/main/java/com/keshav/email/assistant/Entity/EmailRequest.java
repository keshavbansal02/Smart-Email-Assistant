package com.keshav.email.assistant.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    private String emailContent;

    private String emailTone;

}
