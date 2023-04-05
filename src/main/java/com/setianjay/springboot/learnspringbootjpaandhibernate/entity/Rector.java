package com.setianjay.springboot.learnspringbootjpaandhibernate.entity;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.embedded.DetailInfo;
import jakarta.persistence.*;
import lombok.*;

/**
 * CREATE TABLE rector (
 * rector_id INT AUTO_INCREMENT,
 * name VARCHAR(50) NOT NULL,
 * title VARCHAR(50) NOT NULL,
 * telephone CHAR(13) NOT NULL,
 * email VARCHAR(50) NOT NULL,
 * address VARCHAR(255),
 * postal_code CHAR(4)
 * */

@Entity
@Table(name = "rector",
        /*
        * membuat field atau column yang ingin dijadikan sebagai unique key dan memberikan nama constraint
        * kepada unique key tersebut.
        * */
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_rector_email",
                        columnNames = "email")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "rectorId")
@ToString
@Builder
public class Rector {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
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
