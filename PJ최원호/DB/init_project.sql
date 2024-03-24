
DROP TABLE book_rental_list;
DROP TABLE members;
DROP SEQUENCE book_id_seq;
DROP SEQUENCE rental_list_seq;
DROP TABLE books;

-- BOOK TABLE
CREATE TABLE books
( -- 컬럼이름, 데이터 타입, 제약조건
    b_no NUMBER(4,0) PRIMARY KEY,
    b_title VARCHAR2(1000 byte) NOT NULL,
    b_writer VARCHAR2(50 char) DEFAULT 'ANY' NOT NULL,
    b_info VARCHAR2(1000 byte),
    b_inventory NUMBER(2, 0) DEFAULT 0 CHECK(b_inventory > -1),
    b_created_date DATE DEFAULT sysdate,
    UNIQUE (b_title, b_writer)
);
CREATE SEQUENCE book_id_seq
        START WITH 100
        NOCYCLE;
INSERT INTO books
        (
            b_no
            , b_title
            , b_writer
            , b_info
            , b_inventory
        )
        VALUES
        (
            book_id_seq.NEXTVAL
            , '곰돌이 푸'
            , 'A.A. 밀른'
            , '곰돌이 푸의 이야기'
            , 9
        );
INSERT INTO books
        (
            b_no
            , b_title
            , b_writer
            , b_info
            , b_inventory
        )
        VALUES
        (
            book_id_seq.NEXTVAL
            , '해리포터와 죽음의 성물'
            , 'J.K. 롤링'
            , '해리포터 시리즈의 일곱 번째 책이다'
            , 2
        );
INSERT INTO books
        (
            b_no
            , b_title
            , b_writer
            , b_info
            , b_inventory
        )
        VALUES
        (
            book_id_seq.NEXTVAL
            , '인간실격'
            , '다자이 오사무'
            , '오직 순수함만을 갈망하던 여린 심성의 한 젊은이가 인간들의 위선과 잔인함에 의해 파멸되어 가는 과정을 그린 책'
            , 3
        );

-- MEMBER TABLE
CREATE TABLE members
(
    mem_id VARCHAR2(30 byte) PRIMARY KEY
    , mem_pw VARCHAR2(50 byte) NOT NULL
    , mem_level NUMBER(1, 0) DEFAULT 0
    , mem_join_date DATE DEFAULT sysdate
);

INSERT INTO members
        (
            mem_id
            , mem_pw
            , mem_level
        )
        VALUES
        (
            'admin'
            , 'admin'
            , 9
        );
INSERT INTO members
        (
            mem_id
            , mem_pw
        )
        VALUES
        (
            'user1'
            , 'user1'
        );
        
-- BOOK RENTAL LIST TABLE
CREATE TABLE book_rental_list
(
    rent_key NUMBER(3, 0) PRIMARY KEY
    , b_no NUMBER(4,0) NOT NULL
    , renter VARCHAR2(20 byte) NOT NULL
    , start_rent DATE DEFAULT sysdate
    , end_rent DATE DEFAULT TRUNC(sysdate+14)
    , isReturn NUMBER(1, 0) DEFAULT 0
    , FOREIGN KEY(b_no) REFERENCES books(b_no)
    , FOREIGN KEY(renter) REFERENCES members(mem_id)
);
CREATE SEQUENCE rental_list_seq
        NOCYCLE;
INSERT INTO book_rental_list
        (
            rent_key
            , b_no
            , renter
        )
        VALUES
        (
            rental_list_seq.NEXTVAL
            , 101
            , 'admin'
        );
