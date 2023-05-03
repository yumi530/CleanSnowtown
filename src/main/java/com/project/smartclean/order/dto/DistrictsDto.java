package com.project.smartclean.order.dto;

import com.project.smartclean.order.entity.Districts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictsDto {
    private Long districtCode;

    private String districtName;

    public static DistrictsDto of(Districts districts) {
        return DistrictsDto.builder()
                .districtCode(districts.getDistrictCode())
                .districtName(districts.getDistrictName())
                .build();
    }

    public static List<DistrictsDto> of(List<Districts> districts) {

        if (districts == null) {
            return null;
        }

        List<DistrictsDto> districtsDtoList = new ArrayList<>();
        for (Districts x : districts) {
            districtsDtoList.add(DistrictsDto.of(x));
        }
        return districtsDtoList;
    }
}
