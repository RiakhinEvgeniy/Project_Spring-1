package com.evgeniy.riakhin.dto;

import com.evgeniy.riakhin.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TaskInfoDTO {
    private String description;
    private Status status;
}
