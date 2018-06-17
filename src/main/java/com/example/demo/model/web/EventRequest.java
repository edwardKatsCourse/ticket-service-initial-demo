package com.example.demo.model.web;

import com.example.demo.validators.DayDifferenceTimestamp;
import com.example.demo.validators.FutureTimestamp;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EventRequest {

    @Length(max = 255, message = "Event title length cannot exceed 255 characters")
    private String eventTitle;

    //2018-06-17
    //2018-06-07
    //2018-07-06
    //06-07-2018T
    //06-07-2018 23 00 00
    //06-07-2018T23 00 00
    //06-07-2018'T'23:00:00
    //06/07/2018
    //06 / 07 / 2018
    //07-06-2018 - (Amer) 6th of July, (Worldwide) 7th of June
    //Timestamp - 1530835200

    //Long -> LocalDateTime -> future
    @NotNull
    @FutureTimestamp(message = "Start date should in future")
    @DayDifferenceTimestamp(days = 2)
    private Long eventStart;

    @Min(1)
    private Integer durationInHours;

    @Min(1)
    private Integer hallId;
}
