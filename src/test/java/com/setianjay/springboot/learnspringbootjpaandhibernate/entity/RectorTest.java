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
    public void saveRectorToDatabaseTest() {
        DetailInfo detailInfo = DetailInfo.builder()
                .email("hari11setiaji@gmail.com")
                .address("Kp. Pos Citayam RT.05/11 Desa Pabuaran Kecamatan Bojong Gede Kabupaten Bogor")
                .postalCode("16921")
                .telephone("082298061812")
                .build();

        Rector rector = Rector.builder()
                .name("Hari Setiaji")
                .title("S.Kom, M.Kom")
                .detailInfo(detailInfo)
                .build();

        repository.save(rector);

        List<Rector> rectors = repository.findAll();
        System.out.println(rectors);

        assertTrue(rectors.size() > 0);
    }
}
