package com.setianjay.springboot.learnspringbootjpaandhibernate.entity;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.embedded.DetailInfo;
import jakarta.persistence.*;
import lombok.*;

/**
 * CREATE TABLE tbl_rector (
 * rector_id INT AUTO_INCREMENT,
 * name VARCHAR(50) NOT NULL,
 * title VARCHAR(50) NOT NULL,
 * telephone CHAR(13) NOT NULL,
 * email VARCHAR(50) NOT NULL,
 * address VARCHAR(255),
 * city VARCHAR(30),
 * district VARCHAR (30),
 * postal_code CHAR(4)
 * */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Rector {

    @Id
    @SequenceGenerator(
            name = "rector_sequence",
            sequenceName = "rector_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rector_sequence"
    )
    private Long rectorId;

    @Column(
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String name;

    @Column(
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String title;

    /**
     * [@Embedded] adalah anotasi yang digunakan jika kita ingin memisahkan beberapa column atau field ke class
     * yang berbeda.
     * */
    @Embedded
    private DetailInfo detailInfo;
}
