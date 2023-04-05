package com.setianjay.springboot.learnspringbootjpaandhibernate.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class DetailInfo {

    @Column(
            nullable = false,
            columnDefinition = "CHAR(13)"
    )
    private String telephone;

    @Column(
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String email;

    private String address;

    @Column(
            columnDefinition = "CHAR(5)"
    )
    private String postalCode;
}
