package com.evgeniy.riakhin;

import com.evgeniy.riakhin.domain.Status;
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
