package org.acme.week.first.day.third.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2999477183795932964L;
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
