package com.sapo.mock.techshop.dto.response;

import com.sapo.mock.techshop.entity.Collection;
import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductResponseDTO {
    private Integer id;
//    private Integer collectionId;
    private String name;
    private Long importingPrice;
    private Long sellingPrice;
    private Double discount;
    private Integer remainingAmount;
    private Integer soldAmount;
    private String detail;
    private Double rating;
    private String imgLink;
    private Integer status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
