package com.project.smartclean.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultDto {
    private Long wasteId;
    private String wasteName;
    private int count;
    private Long price;
    private String wasteDetail;

}
