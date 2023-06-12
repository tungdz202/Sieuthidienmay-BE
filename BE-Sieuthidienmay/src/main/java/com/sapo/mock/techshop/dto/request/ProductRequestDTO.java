package com.sapo.mock.techshop.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRequestDTO {
    @NotNull(message = "collection_id must not be empty!")
    private Integer collectionId;
    @NotBlank(message = "name must not be empty!")
    private String name;
    private Long importingPrice;
    private Long sellingPrice;
    private Double discount;
    private Integer remainingAmount;
    private Integer soldAmount;
    private String detail;
    private Double rating;
    private String imgLink;
}
