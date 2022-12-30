package com.edu.miu.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryResponse {
    private Long Id;
    private String skuCode;
    private Integer quantity;
}
