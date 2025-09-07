package com.example.springboot.dto.bol;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BillOfLadingDetailDTOResponse {
    Long serial;
    String hscodeName;
    String bolNbr;
    BigDecimal weight;
    Long count;
    BigDecimal volume;
}
