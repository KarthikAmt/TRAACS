package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.google.type.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    private volatile String stationCode;

    private String schedDateTime;

}
