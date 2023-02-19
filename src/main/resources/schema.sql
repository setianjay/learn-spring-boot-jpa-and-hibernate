-- This file will execute automatically on the first launch application

-- create course table
CREATE TABLE course(
id BIGINT NOT NULL,
title VARCHAR(255) NOT NULL,
author VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
);