package org.acme.week.first.day.third.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -5233059476964386048L;
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
