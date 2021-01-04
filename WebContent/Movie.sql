DROP TABLE movie;
DROP TABLE member;
DROP TABLE schedule;

-- 01 액션 02 로맨스 03 코미디 04 스릴러 05 애니메이션
CREATE table movie(
	movieNo number primary key, -- 영화 번호
	movieName VARCHAR2(20), -- 영화 제목
	category number, -- 장르xxx
	runtime number, -- 120분
	img VARCHAR2(20), -- 이미지 파일 불러오기위한 제목
	info VARCHAR2(200) --영화 정보
);
COMMIT

create table member(
	id VARCHAR2(20) primary key, -- 아이디
	pw VARCHAR2(20), -- 비밀번호
	email VARCHAR2(50), -- 이메일
	tel VARCHAR2(20), -- 전화번호
	birth date -- 생년월일
);

create table schedule( -- 상영관(room) -- 관람시간 영화관 이랑 연결해주는 테이블\
	schNo number primary key, -- 스케줄 번호
	movieNo number, -- 영화관 번호
	runDay date, -- 상영 날짜
	runtime number, -- 상영 시간 : 몇분짜리 영화냐
	roomNo number -- 상영관 번호
);

DROP TABLE room;
DROP TABLE ticket;

create table room( -- 상영관
	roomNo number, -- 상영관 번호
	schNo number, -- 스케줄 번호
	seatCnt number -- 그 상여관에 얼마나 좌석이 예매가 되어있는지 카운트
);

create table ticket(
	ticketNo number primary key, -- 티켓 번호
	bookDate date, -- 결제한 날짜
	schNo number, -- 스케줄 번호
	seatNo number, -- 내가 선택한 좌석 번호
	id VARCHAR2(20) -- 회원아이디
);

-- 01 액선 02 로멘스 03 코미디 04 스릴러 05 애니메이션
insert into movie values(10000,'어벤저스',01,120,'1.jpg','재밌다 ');
insert into movie values(10001,'노팅힐',02, 120 , '2.jpg','감동적이다 ');
insert into movie values(10002,'아이언맨',01, 120 , '3.jpg','멋있따');
insert into movie values(10003,'겨울왕국2',05, 130 , '4.jpg','재밌다 ');
insert into movie values(10004,'엑시트',03, 140 , '5.jpg','킬링타임 ');
insert into movie values(10005,'반도',04, 155 , '6.jpg','잘생겼다 ');
insert into movie values(10006,'23아이덴티디',04, 150 , '7.jpg','꿀잼');

insert into member values('test1','1','test1@test.com','010-1234-1234','2002-05-12');
insert into member values('test2','12','test2@test.com','010-1234-1234','2002-05-12');
insert into member values('test3','123','test3@test.com','010-1234-1234','2002-05-12');
insert into member values('test4','1234','test4@test.com','010-1234-1234','2002-05-12');

                                           --sysdate varchar2 -> date 
insert into schedule values(1,10000,TO_DATE('2020/11/11 11:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(2,10000,TO_DATE('2020/11/11 1:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(3,10000,TO_DATE('2020/11/11 3:10','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(4,10000,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,1);

insert into schedule values(5,10001,TO_DATE('2020/11/11 11:51','yyyy/mm/dd hh24:mi'),120,2);
insert into schedule values(6,10001,TO_DATE('2020/11/11 1:51','yyyy/mm/dd hh24:mi'),120,2);
insert into schedule values(7,10001,TO_DATE('2020/11/11 3:11','yyyy/mm/dd hh24:mi'),120,2);
insert into schedule values(8,10001,TO_DATE('2020/11/11 8:51','yyyy/mm/dd hh24:mi'),120,2);

insert into schedule values(9,10002,TO_DATE('2020/11/11 11:52','yyyy/mm/dd hh24:mi'),120,3);
insert into schedule values(10,10002,TO_DATE('2020/11/11 1:52','yyyy/mm/dd hh24:mi'),120,3);
insert into schedule values(11,10002,TO_DATE('2020/11/11 3:12','yyyy/mm/dd hh24:mi'),120,3);
insert into schedule values(12,10002,TO_DATE('2020/11/11 8:52','yyyy/mm/dd hh24:mi'),120,3);


insert into schedule values(13,10003,TO_DATE('2020/11/11 11:53','yyyy/mm/dd hh24:mi'),120,4);
insert into schedule values(14,10003,TO_DATE('2020/11/11 1:53','yyyy/mm/dd hh24:mi'),120,4);
insert into schedule values(15,10003,TO_DATE('2020/11/11 3:13','yyyy/mm/dd hh24:mi'),120,4);
insert into schedule values(16,10003,TO_DATE('2020/11/11 8:53','yyyy/mm/dd hh24:mi'),120,4);


insert into schedule values(17,10004,TO_DATE('2020/11/11 11:54','yyyy/mm/dd hh24:mi'),120,5);
insert into schedule values(18,10004,TO_DATE('2020/11/11 1:54','yyyy/mm/dd hh24:mi'),120,5);
insert into schedule values(19,10004,TO_DATE('2020/11/11 3:14','yyyy/mm/dd hh24:mi'),120,5);
insert into schedule values(20,10004,TO_DATE('2020/11/11 8:54','yyyy/mm/dd hh24:mi'),120,5);


insert into schedule values(21,10005,TO_DATE('2020/11/11 11:55','yyyy/mm/dd hh24:mi'),120,6);
insert into schedule values(22,10005,TO_DATE('2020/11/11 1:55','yyyy/mm/dd hh24:mi'),120,6);
insert into schedule values(23,10005,TO_DATE('2020/11/11 3:15','yyyy/mm/dd hh24:mi'),120,6);
insert into schedule values(24,10005,TO_DATE('2020/11/11 8:55','yyyy/mm/dd hh24:mi'),120,6);


insert into schedule values(25,10006,TO_DATE('2020/11/11 11:56','yyyy/mm/dd hh24:mi'),120,7);
insert into schedule values(26,10006,TO_DATE('2020/11/11 1:56','yyyy/mm/dd hh24:mi'),120,7);
insert into schedule values(27,10006,TO_DATE('2020/11/11 3:16','yyyy/mm/dd hh24:mi'),120,7);
insert into schedule values(28,10006,TO_DATE('2020/11/11 8:56','yyyy/mm/dd hh24:mi'),120,7);


--상영관 --스케줄번호 --예매좌석 카운트 
insert into room values(1,1,0);
insert into room values(1,2,0);
insert into room values(1,3,0);
insert into room values(1,4,0);	

insert into room values(2,5,0);
insert into room values(2,6,0);
insert into room values(2,7,0);
insert into room values(2,8,0);

insert into room values(3,9,0);
insert into room values(3,10,0);
insert into room values(3,11,0);
insert into room values(3,12,0);

insert into room values(4,13,0);
insert into room values(4,14,0);
insert into room values(4,15,0);
insert into room values(4,16,0);

insert into room values(5,17,0);
insert into room values(5,18,0);
insert into room values(5,19,0);
insert into room values(5,20,0);

insert into room values(6,21,0);
insert into room values(6,22,0);
insert into room values(6,23,0);
insert into room values(6,24,0);

insert into room values(7,25,0);
insert into room values(7,26,0);
insert into room values(7,27,0);
insert into room values(7,28,0);
