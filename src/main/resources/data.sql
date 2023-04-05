-- course
INSERT INTO course (title, author) VALUES ('Learn Kotlin', 'Hari Setiaji');
INSERT INTO course (title, author) VALUES ('Learn Java', 'Hari Setiaji');
INSERT INTO course (title, author) VALUES ('Learn Android', 'Hari Setiaji');
INSERT INTO course (title, author) VALUES ('Learn SpringBoot', 'Hari Setiaji');

-- rector
INSERT INTO rector (name, title, telephone, email, address, postal_code)
VALUES ('Setyarto', 'S.Kom, M.Kom', '089578454565' ,'setyarto@gmail.com', 'Kp. Pos Citayam RT.05/11 Desa Paburan Kecamatan Bojong Gede Kabupaten Bogor', '16921');
INSERT INTO rector (name, title, telephone, email, address, postal_code)
VALUES ('Sudaryati', 'S.Pd, M.M', '08115146589' ,'sudaryati@gmail.com', 'Kp. Pos Citayam RT.05/11 Desa Paburan Kecamatan Bojong Gede Kabupaten Bogor', '16921');
INSERT INTO rector (name, title, telephone, email, address, postal_code)
VALUES ('Hari Setiaji', 'S.Kom, M.Kom', '082298061812' ,'hari.setiaji2000@gmail.com', 'Kp. Pos Citayam RT.05/11 Desa Paburan Kecamatan Bojong Gede Kabupaten Bogor', '16921');

-- university
INSERT INTO university (university_id, name, accreditation, telephone, email, address, postal_code, rector_id)
VALUES ('UN-1', 'Universitas Indonesia', 'A', '02154507741', 'ui@sch.edu', 'Beji, Depok', '16924', 2);
INSERT INTO university (university_id, name, accreditation, telephone, email, address, postal_code, rector_id)
VALUES ('UN-2', 'Institut Teknologi Sepuluh November', 'A', '02154507742', 'its@sch.edu', 'Surabaya, Jawa Timur', '20924', 3);

