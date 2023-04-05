package com.setianjay.springboot.learnspringbootjpaandhibernate.entity;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.embedded.DetailInfo;
import jakarta.persistence.*;
import lombok.*;

/**
 * CREATE TABLE university (
 * university_id CHAR(5), #U0001
 * name VARCHAR(50) NOT NULL,
 * accreditation CHAR(1) NOT NULL,
 * telephone CHAR(13) NOT NULL,
 * email VARCHAR(50) NOT NULL,
 * address VARCHAR(255),
 * postal_code CHAR(4),
 * rector_id BIGINT,
 * PRIMARY KEY(university_id),
 * CONSTRAINT FK_UNIVERSITY_RECTOR FOREIGN KEY(rector_id) REFERENCES RECTOR (rector_id) ON UPDATE CASCADE ON DELETE CASCADE
 * )
 */
@Entity
@Table(name = "university",
        /*
        * membuat field atau column yang ingin dijadikan sebagai unique key dan memberikan nama constraint
        * kepada unique key tersebut.
        * */
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_university_email",
                        columnNames = "email")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class University {

    @Id
    @Column(updatable = false,
            columnDefinition = "VARCHAR(10)")
    private String universityId;

    @Column(
            nullable = false,
            columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(nullable = false)
    private Character accreditation;

    @Embedded
    private DetailInfo detailInfo;

    /**
     * fetchType.Lazy   = membuat data rector tidak dimuat secara otomatis pada saat pengambilan semua data yang
     * ada di table university, kecuali kita menyatakan bahwa kita ingin membawanya, caranya ada di class testing.
     * optional         = bila false maka kita wajib memasukan data, jika true maka kita tidak wajib memasukkan
     * data (null)
     */
    @OneToOne(
            targetEntity = Rector.class,
            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
            optional= false
    )
    @JoinColumn(name = "rector_id", referencedColumnName = "rectorId", foreignKey = @ForeignKey(name = "FK_university_rector"))
    private Rector rector;


}
