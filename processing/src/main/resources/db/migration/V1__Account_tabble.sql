CREATE table ACCOUNT
(
    ID bigint not null primary key,
    USER_ID bigint not null,
    CURRENCY_CODE varchar(6) not null,
    BALANCE numeric not null
);

CREATE SEQUENCE ACCOUNT_SEQ;