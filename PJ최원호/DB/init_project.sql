DROP TABLE board_review;
DROP TABLE board_notice;
DROP TABLE book_rental_list;
DROP TABLE members;
DROP SEQUENCE board_notice_seq;
DROP SEQUENCE board_review_seq;
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
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '곰돌이 푸', 'A.A. 밀른', '곰돌이 푸의 이야기', 9);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '해리포터와 죽음의 성물', 'J.K. 롤링', '해리포터 시리즈의 일곱 번째 책이다', 2);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '인간 실격', '다자이 오사무', '오직 순수함만을 갈망하던 여린 심성의 한 젊은이가 인간들의 위선과 잔인함에 의해 파멸되어 가는 과정을 그린 책', 3);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '악의', '히가시노 게이고', '일본추리소설', 1);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '모순', '양귀자', '양귀자 소설의 힘을 보여준 베스트셀러', 4);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '무엇이 나를 행복하게 만드는가', '리처드 J. 라이더', '아무 걱정 없이 행복하게 웃어본 적이 언제인지 기억이 나는가?', 6);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '아이는 무엇으로 자라는가', '버지니아 사티어', '세계에서 가장 많이 읽힌 자녀교육 베스트셀러 ', 10);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '돈의 심리학', '모건하우절', '칼럼니스트이자 콜라보레이티브 펀드 파트너로 활동중인 모건 하우절의 첫 책' , 3);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '비트코인 슈퍼 사이클 ', '신민철', '수십억 원 비트코인을 모으며 깨달은 마법의 비트코인 투자법!', 11);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '보편의 단어', '이기주', '사람은 누구나 마음을 누일 곳이 필요하다.', 21);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '퓨처 셀프', '벤저민 하디', '살 날이 얼마 남지 않은 ‘미래의 내’가 현재로 시간 여행을 왔다고 상상해보자.' , 5);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '구의 증명', '최진영', '사랑 후 남겨진 것들에 관한 숭고할 만큼 아름다운 이야기', 10);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '웡카', '시빌 파운더', '초콜릿 공장 주인의 이야기', 7);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '철학은 날씨를 바꾼다', '서동욱', '염세주의 사상가 쇼펜하우어의 인기가 식을 줄 모른다.', 2);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '기분이 태도가 되지 말자', '김수현', '부정적인 감정으로 부터 스스로를 보호', 1);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '오십에 읽는 주역', '강기진', '운이 좋아지고 싶은가?', 5);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '컨셉 수업', '호소다 다카히로', '''쓸모''를 겨루는 시대는 끝났다.', 3);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '자본주의', 'EBS 자본주의 제작팀', '다큐를 책으로', 9);
INSERT INTO books(b_no, b_title, b_writer, b_info, b_inventory)
VALUES(book_id_seq.NEXTVAL, '역행자', '자청', '95퍼센트의 인간은 타고난 유전자와 본성의 꼭두각시로 살아간다.', 12);

-- MEMBER TABLE
CREATE TABLE members
(
    mem_id VARCHAR2(30 byte) PRIMARY KEY
    , mem_pw VARCHAR2(50 byte) NOT NULL
    , mem_tel VARCHAR2(30 byte) UNIQUE NOT NULL
    , mem_level NUMBER(1, 0) DEFAULT 0
    , mem_join_date DATE DEFAULT sysdate
);

INSERT INTO members(mem_id, mem_pw, mem_tel, mem_level)
VALUES('admin', 'admin', '010-0000-0000', 9);
INSERT INTO members(mem_id, mem_pw, mem_tel, mem_level)
VALUES('manager', 'manager', '010-0000-0001', 9);
INSERT INTO members(mem_id, mem_pw, mem_tel)
VALUES('user1', 'user1', '010-1234-1234');
INSERT INTO members(mem_id, mem_pw, mem_tel)
VALUES('user2', 'user2', '010-4321-4321');     
-- BOOK RENTAL LIST TABLE
CREATE TABLE book_rental_list
(
    rent_key NUMBER(3, 0) PRIMARY KEY
    , b_no NUMBER(4,0) NOT NULL
    , renter VARCHAR2(20 byte) NOT NULL
    , start_rent DATE DEFAULT sysdate
    , end_rent DATE DEFAULT TRUNC(sysdate+14)
    , isReturn NUMBER(2, 0) DEFAULT 0
    , FOREIGN KEY(b_no) REFERENCES books(b_no)
    , FOREIGN KEY(renter) REFERENCES members(mem_id)
);
CREATE SEQUENCE rental_list_seq
        NOCYCLE;
INSERT INTO book_rental_list(rent_key, b_no, renter, start_rent, end_rent)
VALUES(rental_list_seq.NEXTVAL, 114, 'admin', TO_DATE('24-01-15', 'yy-mm-dd'), TO_DATE('24-01-29', 'yy-mm-dd'));
INSERT INTO book_rental_list(rent_key, b_no, renter, start_rent, end_rent)
VALUES(rental_list_seq.NEXTVAL, 108, 'admin', TO_DATE('24-01-18', 'yy-mm-dd'), TO_DATE('24-02-02', 'yy-mm-dd'));
INSERT INTO book_rental_list(rent_key, b_no, renter, isReturn)
VALUES(rental_list_seq.NEXTVAL, 101, 'admin', 1);
INSERT INTO book_rental_list(rent_key, b_no, renter, isReturn)
VALUES(rental_list_seq.NEXTVAL, 102, 'admin', 1);
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 116, 'admin');
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 114, 'admin');

INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 112, 'manager');
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 113, 'manager');
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 115, 'manager');
INSERT INTO book_rental_list(rent_key, b_no, renter, isReturn)
VALUES(rental_list_seq.NEXTVAL, 108, 'manager', 1);
INSERT INTO book_rental_list(rent_key, b_no, renter, isReturn)
VALUES(rental_list_seq.NEXTVAL, 104, 'manager', 1);

INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 116, 'user1');
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 110, 'user1');
INSERT INTO book_rental_list(rent_key, b_no, renter, isReturn)
VALUES(rental_list_seq.NEXTVAL, 101, 'user1', 1);
INSERT INTO book_rental_list(rent_key, b_no, renter, isReturn)
VALUES(rental_list_seq.NEXTVAL, 103, 'user1', 1);
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 100, 'user2');
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 107, 'user2');
INSERT INTO book_rental_list(rent_key, b_no, renter)
VALUES(rental_list_seq.NEXTVAL, 109, 'user2');
INSERT INTO book_rental_list(rent_key, b_no, renter, isReturn)
VALUES(rental_list_seq.NEXTVAL, 105, 'user2', 1);
-- 공지사항
CREATE TABLE board_notice
(
    notice_key NUMBER(3, 0) PRIMARY KEY
    , notice_title VARCHAR2(100 byte) NOT NULL
    , notice_detail VARCHAR2(1000 byte) NOT NULL
    , notice_writer VARCHAR2(30 byte) NOT NULL
    , notice_write_day DATE DEFAULT sysdate
    , FOREIGN KEY(notice_writer) REFERENCES members(mem_id)
);
CREATE SEQUENCE board_notice_seq
        NOCYCLE;
INSERT INTO board_notice(notice_key, notice_title, notice_detail, notice_writer)
VALUES(board_notice_seq.NEXTVAL, '도서관리게시판이 열렸습니다.', '오픈을 축하해주세요!', 'admin');
INSERT INTO board_notice(notice_key, notice_title, notice_detail, notice_writer)
VALUES(board_notice_seq.NEXTVAL, '오픈 기념 이벤트!', '그런건 없습니다.', 'admin');
INSERT INTO board_notice(notice_key, notice_title, notice_detail, notice_writer)
VALUES(board_notice_seq.NEXTVAL, '관리자 채용 공고', '지원 문의 전화주세요', 'manager');
-- 후기게시판
CREATE TABLE board_review
(
    review_key NUMBER(3, 0) PRIMARY KEY
    , b_no NUMBER(4,0) NOT NULL
    , review_detail VARCHAR2(500 byte) NOT NULL
    , review_rate NUMBER(1,0) DEFAULT 0 CHECK (review_rate BETWEEN 0 AND 5)
    , review_writer VARCHAR2(30 byte) NOT NULL
    , review_write_day DATE DEFAULT sysdate
    , FOREIGN KEY(b_no) REFERENCES books(b_no)
    , FOREIGN KEY(review_writer) REFERENCES members(mem_id)
    , UNIQUE (b_no, review_writer)
);
CREATE SEQUENCE board_review_seq
        NOCYCLE;
INSERT INTO board_review(review_key, b_no, review_detail, review_rate, review_writer)
VALUES(board_review_seq.NEXTVAL, 101, '숨쉬듯 읽히는 책.', 5, 'admin');
INSERT INTO board_review(review_key, b_no, review_detail, review_rate, review_writer)
VALUES(board_review_seq.NEXTVAL, 102, '참 재밌어요.', 5, 'admin');

INSERT INTO board_review(review_key, b_no, review_detail, review_rate, review_writer)
VALUES(board_review_seq.NEXTVAL, 108, '쓰레기 책', 0, 'manager');
INSERT INTO board_review(review_key, b_no, review_detail, review_rate, review_writer)
VALUES(board_review_seq.NEXTVAL, 104, '최고의 책', 5, 'manager');

INSERT INTO board_review(review_key, b_no, review_detail, review_rate, review_writer)
VALUES(board_review_seq.NEXTVAL, 102, '그저 그래요.', 3, 'user1');
INSERT INTO board_review(review_key, b_no, review_detail, review_rate, review_writer)
VALUES(board_review_seq.NEXTVAL, 103, '재미 없어요.', 1, 'user1');
INSERT INTO board_review(review_key, b_no, review_detail, review_rate, review_writer)
VALUES(board_review_seq.NEXTVAL, 102, '읽기 힘들어요.', 2, 'user2');

commit;