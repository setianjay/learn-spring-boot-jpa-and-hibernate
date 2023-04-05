package com.setianjay.springboot.learnspringbootjpaandhibernate.entity;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.embedded.DetailInfo;
import com.setianjay.springboot.learnspringbootjpaandhibernate.repository.RectorRepository;
import com.setianjay.springboot.learnspringbootjpaandhibernate.repository.UniversityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UniversityTest {

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private RectorRepository rectorRepository;

    @Test
    public void saveUniversityTest() {
        /*
         * karena nilai rector yang kita masukan belum ada di table referensi maka harusnya proses insert error.
         * Namun karena kita menset nilai cascade dengan cascade all, walaupun nilai rector tidak ada hibernate akan
         * secara otomatis memasukan terlebih dahulu nilai rector yang belum ada tersebut ke dalam table referensi
         * kemudian setelah nilai tersebut sudah ada maka proses insert baru dilakukan.
         * */
        DetailInfo rectorInfo = DetailInfo.builder()
                .email("gurindo05sekti@gmail.com")
                .address("Kp. Pos Citayam RT.05/11 Desa Pabuaran Kecamatan Bojong Gede Kabupaten Bogor")
                .telephone("081315142380")
                .postalCode("16421")
                .build();

        Rector rector = Rector.builder()
                .name("Gurindo Sekti")
                .title("S.Kom, M.Kom")
                .detailInfo(rectorInfo)
                .build();


        DetailInfo universityInfo = DetailInfo.builder()
                .email("lp3i@sch.edu")
                .address("Kramat, Jakarta Timur")
                .telephone("02168974512")
                .postalCode("27489")
                .build();

        University university = University.builder()
                .universityId(setUniqueIdForUniversity())
                .name("Politeknik LP3I")
                .accreditation('B')
                .detailInfo(universityInfo)
                .rector(rector)
                .build();

        universityRepository.save(university);

        List<Rector> rectors = rectorRepository.findAll();
        List<University> universities = universityRepository.findAll();
        System.out.println("Rector: " + rectors + " size: " + rectors.size());
        System.out.println("Universities: " + universities + " size: " + universities.size());

        int resultRector = universities.get(universities.size() - 1).getRector().hashCode();
        System.out.println("rector: " + rector.hashCode() + " result rector: " + resultRector);

        assertTrue(rectors.contains(rector));
        assertTrue(universities.contains(university));
    }

    @Test
    public void updateUniversityTest() {
        // disini kita akan mengupdate nilai univeristy dengan nilai university yang mempunyai university id nya UN-2
        universityRepository.findById("UN-2").ifPresent(university -> {
            System.out.println("Before update: " + university);

            DetailInfo editedUniversityInfo = DetailInfo.builder()
                    .email("its@sch.edu")
                    .address("Surabaya, Jawa Timur")
                    .telephone("02168974513")
                    .postalCode("27490")
                    .build();

            University editedUniversity = University.builder()
                    .universityId(university.getUniversityId())
                    .name("Institut Teknologi Surabaya")
                    .accreditation('A')
                    .detailInfo(editedUniversityInfo)
                    .rector(university.getRector())
                    .build();

            // proses save akan menjadi proses update jika kita mengirimkan data dengan primary key yang sama. Karena diatas
            // kita tidak menset univerityId ke nilai yang baru maka proses ini akan menjadi proses update.
            universityRepository.save(editedUniversity);

            // mendapatkan nilai university yang baru setelah diupdate untuk di printout dan dilihat hasilnya
            universityRepository.findById(university.getUniversityId()).ifPresent(newUniversity -> {
                System.out.println(newUniversity);

                // cek apakah nilai university yang kita update itu sama dengan nilai rector yang sudah diupdate dan tersimpan di
                // database.
                assertEquals(editedUniversity, newUniversity);
            });
        });

    }

    @Test
    public void deleteUniversityTest() {
        // mengambil data university yang ingin dihapus, disini nilai university yang ingin kita hapus mempunyai
        // university id UN-1
        universityRepository.findById("UN-1").ifPresent(university -> {
            // hapus data university berdasarkan nilai university id nya
            universityRepository.deleteById(university.getUniversityId());

            // disini kita akan men cek nilai university apakah sudah benar-benar di hapus atau belum.
            // nilai kembalian false berarti bahwa nilai tersebut sudah tidak ada di dalam database.
            assertFalse(universityRepository.findById(university.getUniversityId()).isPresent());
        });
    }

    private String setUniqueIdForUniversity() {
        int counter;
        List<University> universities = universityRepository.findAll();

        if (universities.size() == 0) {
            counter = 1;
        } else {
            counter = universities.size() + 1;
        }

        return "UN-" + counter;
    }



    /*----------------------------------------------------------------------------------------------------------------
     *                               Data Source for testing the transaction
     * ---------------------------------------------------------------------------------------------------------------*/

//    @BeforeEach
//    public void setup() {
//        rectorsInfo = List.of(
//                DetailInfo.builder()
//                        .email("hari11setiaji@gmail.com")
//                        .address("Kp. Pos Citayam RT.05/11 Desa Pabuaran Kecamatan Bojong Gede Kabupaten Bogor")
//                        .postalCode("16921")
//                        .telephone("082298061812")
//                        .build(),
//                DetailInfo.builder()
//                        .email("hari.setiaji2000@gmail.com")
//                        .address("Kp. Pos Citayam RT.05/11 Desa Pabuaran Kecamatan Bojong Gede Kabupaten Bogor")
//                        .postalCode("16921")
//                        .telephone("082298061812")
//                        .build()
//        );
//
//        rectors = List.of(
//                Rector.builder()
//                        .name("Hari Setiaji")
//                        .title("S.Kom, M.Kom")
//                        .detailInfo(rectorsInfo.get(0))
//                        .build(),
//                Rector.builder()
//                        .name("Gurindo Sekti")
//                        .title("S.Kom, M.Kom")
//                        .detailInfo(rectorsInfo.get(1))
//                        .build()
//        );
//
//        universityInfo = List.of(
//                DetailInfo.builder()
//                        .telephone("02168605578")
//                        .email("ui@edu")
//                        .address("Pondok Cina, Beji, Kota Depok Jawa Barat")
//                        .postalCode("16424")
//                        .build(),
//                DetailInfo.builder()
//                        .telephone("02168605579")
//                        .email("its@edu")
//                        .address("Surabaya, Jawa Timur")
//                        .postalCode("16425")
//                        .build()
//        );
//
//        universities = List.of(
//                University.builder()
//                        .universityId(setUniqueIdForUniversity())
//                        .name("Universitas Indonesia")
//                        .accreditation('A')
//                        .detailInfo(universityInfo.get(0))
//                        .rector(rectors.get(0))
//                        .build(),
//                University.builder()
//                        .universityId(setUniqueIdForUniversity())
//                        .name("Institut Teknologi Sepuluh November")
//                        .accreditation('A')
//                        .detailInfo(universityInfo.get(1))
    /*
     * Error karena hubungan relasi adalah one to one, maka tidak boleh ada data
     * reference (rector_id) yang sama di table university.
     */
//                        .rector(rectors.get(0))
//                        .rector(rectors.get(1))
//                        .build()
//        );
//    }

//    @Test
//    @Order(value = 2)
//    public void getUniversityFromDatabaseTest() {
//        //get university
//        int counter = 1;
//
//        for (University university : universities) {
//            String univId = "UN-"+counter;
//            Optional<University> result = universityRepository.findById(univId);

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
//
//            assertEquals(univId, result.get().getUniversityId());
//            assertEquals(university.getName(), result.get().getName());
//            counter++;
//        }
//    }

//    @Test
//    @Order(value = 3)
//    public void incrementingUniqueIdForUniversityTest() {
//        assertEquals(setUniqueIdForUniversity(), "UN-3");
//    }

}
