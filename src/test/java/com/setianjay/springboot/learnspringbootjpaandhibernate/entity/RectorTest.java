package com.setianjay.springboot.learnspringbootjpaandhibernate.entity;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.embedded.DetailInfo;
import com.setianjay.springboot.learnspringbootjpaandhibernate.repository.RectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RectorTest {

    @Autowired
    private RectorRepository repository;

    @Test
    public void saveRectorTest() {
        DetailInfo detailInfo = DetailInfo.builder()
                .email("gurindo05sekti@gmail.com")
                .address("Kp. Pos Citayam RT.05/11 Desa Pabuaran Kecamatan Bojong Gede Kabupaten Bogor")
                .postalCode("16921")
                .telephone("081315142380")
                .build();

        Rector rector = Rector.builder()
                .name("Gurindo Sekti")
                .title("S.Kom, M.Kom")
                .detailInfo(detailInfo)
                .build();

        // save rector ke dalam database
        repository.save(rector);

        // mengambil semua data rector yang ada pada database lalu printout datanya
        List<Rector> rectors = repository.findAll();
        System.out.println("save rector: " + rectors + " size: " + rectors.size());

        // cek apakah data rector yang kita save sebelumnya ada di dalam list data rector yang kita dapat dari database,
        // jika ada maka testing berhasil
        assertTrue(rectors.contains(rector));
    }

    @Test
    public void updateRectorTest() {
        // disini kita akan mengupdate nilai rector dengan nilai rector yang mempunyai rector id nya 2
        repository.findById(2L).ifPresent(rector -> {

            System.out.println("Rector before update: " + rector);

            Rector updateRector = Rector.builder()
                    .rectorId(rector.getRectorId())
                    .name("Sukiyah")
                    .title("S.Pd, M.Sos")
                    .detailInfo(DetailInfo
                            .builder()
                            .email("sukiyah@gmail.com")
                            .address("Purworejo Jawa Tengah")
                            .telephone("08147896512")
                            .postalCode("20458")
                            .build())
                    .build();

            // proses save akan menjadi proses update jika kita mengirimkan data dengan primary key yang sama. Karena diatas
            // kita tidak menset rectorId ke nilai yang baru maka proses ini akan menjadi proses update.
            repository.save(updateRector);

            // mendapatkan nilai rector yang baru setelah diupdate untuk di printout dan dilihat hasilnya
           repository.findById(updateRector.getRectorId()).ifPresent(newRector -> {
               System.out.println("Rector after update: " + newRector);

               // cek apakah nilai rector yang kita update itu sama dengan nilai rector yang sudah diupdate dan tersimpan di
               // database.
               assertEquals(updateRector, newRector);
           });
        });
    }

    @Test
    public void deleteRectorTest() {
        // mengambil data rector yang ingin dihapus, disini nilai rector yang ingin kita hapus mempunyai rector id 1
        repository.findById(1L).ifPresent(rector -> {
            // hapus data rector berdasarkan nilai rector id nya
            repository.deleteById(rector.getRectorId());

            // disini kita akan men cek nilai rector apakah sudah benar-benar di hapus atau belum.
            // nilai kembalian false berarti bahwa nilai tersebut sudah tidak ada di dalam database.
            assertFalse(repository.findById(rector.getRectorId()).isPresent());
        });
    }
}
