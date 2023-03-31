package com.setianjay.springboot.learnspringbootjpaandhibernate.entity;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.embedded.DetailInfo;
import com.setianjay.springboot.learnspringbootjpaandhibernate.repository.RectorRepository;
import com.setianjay.springboot.learnspringbootjpaandhibernate.repository.UniversityRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UniversityTest {

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private RectorRepository rectorRepository;

    private List<DetailInfo> rectorsInfo;

    private List<Rector> rectors;

    private List<DetailInfo> universityInfo;
    private List<University> universities;

    private static int counter = 0;

    /*----------------------------------------------------------------------------------------------------------------
     *                               Data Source for testing the transaction
     * ---------------------------------------------------------------------------------------------------------------*/

    @BeforeEach
    public void setup() {
        rectorsInfo = List.of(
                DetailInfo.builder()
                        .email("hari11setiaji@gmail.com")
                        .address("Kp. Pos Citayam RT.05/11 Desa Pabuaran Kecamatan Bojong Gede Kabupaten Bogor")
                        .postalCode("16921")
                        .telephone("082298061812")
                        .build(),
                DetailInfo.builder()
                        .email("hari.setiaji2000@gmail.com")
                        .address("Kp. Pos Citayam RT.05/11 Desa Pabuaran Kecamatan Bojong Gede Kabupaten Bogor")
                        .postalCode("16921")
                        .telephone("082298061812")
                        .build()
        );

        rectors = List.of(
                Rector.builder()
                        .name("Hari Setiaji")
                        .title("S.Kom, M.Kom")
                        .detailInfo(rectorsInfo.get(0))
                        .build(),
                Rector.builder()
                        .name("Gurindo Sekti")
                        .title("S.Kom, M.Kom")
                        .detailInfo(rectorsInfo.get(1))
                        .build()
        );

        universityInfo = List.of(
                DetailInfo.builder()
                        .telephone("02168605578")
                        .email("ui@edu")
                        .address("Pondok Cina, Beji, Kota Depok Jawa Barat")
                        .postalCode("16424")
                        .build(),
                DetailInfo.builder()
                        .telephone("02168605579")
                        .email("its@edu")
                        .address("Surabaya, Jawa Timur")
                        .postalCode("16425")
                        .build()
        );

        universities = List.of(
                University.builder()
                        .universityId(setUniqueIdForUniversity())
                        .name("Universitas Indonesia")
                        .accreditation('A')
                        .detailInfo(universityInfo.get(0))
                        .rector(rectors.get(0))
                        .build(),
                University.builder()
                        .universityId(setUniqueIdForUniversity())
                        .name("Institut Teknologi Sepuluh November")
                        .accreditation('A')
                        .detailInfo(universityInfo.get(1))
                        /*
                        * Error karena hubungan relasi adalah one to one, maka tidak boleh ada data
                        * reference (rector_id) yang sama di table university.
                         */
//                        .rector(rectors.get(0))
                        .rector(rectors.get(1))
                        .build()
        );
    }


    @Test
    @Order(value = 1)
    public void saveUniversityToDatabaseTest() {
        rectorRepository.saveAll(rectors);
        universityRepository.saveAll(universities);
    }

    @Test
    @Order(value = 2)
    public void getUniversityFromDatabaseTest() {
        //get university
        int counter = 1;

        for (University university : universities) {
            String univId = "UN-"+counter;
            Optional<University> result = universityRepository.findById(univId);

            /*
            * Karena data Rector di table University di deklarasikan dengan FetchType.LAZY, dimana data rector tidak
            * akan otomatis dimuat apabila kita menjalankan query apa saja di table university, jika ingin memuat
            * datanya maka bisa lakukan cara seperti dibawah.
            * */
//            Rector rector = result.get().getRector();
//            if(!Hibernate.isInitialized(rector)){
//                Optional<Rector> resultRector = rectorRepository.findById(rector.getRectorId());
//                System.out.println(resultRector.get());
//            }

            assertEquals(univId, result.get().getUniversityId());
            assertEquals(university.getName(), result.get().getName());
            counter++;
        }
    }

    @Test
    @Order(value = 3)
    public void incrementingUniqueIdForUniversityTest() {
        assertEquals(setUniqueIdForUniversity(), "UN-3");
    }

    private String setUniqueIdForUniversity() {
        List<University> universities = universityRepository.findAll();
        if (universities.size() == 0 && counter == 0) {
            counter = 1;
        } else {
            if (universities.size() > 0) {
                counter = universities.size() + 1;
            } else {
                counter++;
            }
        }
        return "UN-" + counter;
    }
}
