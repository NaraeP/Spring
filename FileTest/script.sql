--File Test > script.sql

--부모 테이블
create table tblPlace (
    seq number primary key,
    subject varchar2(500) not null,
    content varchar2(1000) not null,
    regdate date default sysdate not null
);

--파일을 관리할 자식 테이블
create table tblPic (
    seq number primary key,
    filename varchar2(300) not null,
    pseq number references tblPlace(seq) not null
);

create sequence seqPlace;
create sequence seqPic;

commit;

select * from tblPlace;
select * from tblPic;

select a.*, (select count(*) from tblPic where pseq = a.seq) as picCount from tblPlace a order by seq desc;

select * from tblPlace where seq = 1;
select * from tblPic where pseq = 1;
--이 경우 Join은 쌤 추천 X!!!
-- 각자 가져와서 결합하는게 낫다!
