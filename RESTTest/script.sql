--RESTTest > script.sql

-- 계정 내 테이블 확인
select * from tabs;

select * from tblAddress;

select * from tblAddress order by seq desc;

create user dd identified by pass;
grant connect, resource, dba to dd;