--SecurityTest > script.sql

-- 회원 테이블
create table tblMember (
    userid varchar2(50) not null primary key,
    userpw varchar2(100) not null,              --최소 100byte 이상
    username varchar2(100) not null,
    regdate date default sysdate not null,
    enabled char(1) default '1'                 --활동: 1, 탈퇴: 0
);

-- 권한(자격) 테이블 > ROLE_MEMBER, ROLE_ADMIN.. 을 넣기 위해 만든 테이블
create table tblAuth (
    userid varchar2(50) not null,               --tblMember의 FK
    auth varchar2(50) not null,
    constraint fk_member_auth foreign key(userid) references tblMember(userid)
);

commit;

select * from tblmember;
select * from tblAuth;

delete from tblauth;
delete from tblmember;



select 
    m.userid,
    m.userpw,
    m.username,
    m.enabled,
    m.regdate,
    a.auth
from tblmember m
left outer join tblauth a
on m.userid = a.userid
where m.userid = 'panda';