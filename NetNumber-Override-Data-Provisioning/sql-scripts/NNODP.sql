CREATE SEQUENCE "NN_CARRIER_SMSURI_MAPPING_SEQ" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 43947 NOCACHE ORDER NOCYCLE ;
CREATE SEQUENCE "NN_LAST_TXN_SEQ" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 43947 NOCACHE ORDER NOCYCLE ;
CREATE SEQUENCE "NN_PROCESS_AUDIT_SEQ" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 43947 NOCACHE ORDER NOCYCLE ;
CREATE SEQUENCE "NN_PROCESS_AUDIT_DESC_SEQ" MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 43947 NOCACHE ORDER NOCYCLE ;


 CREATE TABLE "NN_CARRIER_SMSURI_MAPPING"
  (
    "ID"          NUMBER (38,0)  NOT NULL ENABLE,
    "CARRIER_ID"   VARCHAR2 (4 byte) NOT NULL ENABLE,
    "CUSTOMER_NAME"  VARCHAR2 (255 byte) NOT NULL ENABLE,
    "SMSURI"  VARCHAR2 (255 byte) NOT NULL ENABLE,
    "IS_ACTIVE" VARCHAR2 (2 byte) default 'Y' NOT NULL ENABLE,
    "CREATED_DATE" TIMESTAMP NOT NULL  ENABLE                                                                                                                                                                             ,
    "MODIFIED_DATE" TIMESTAMP,
    CONSTRAINT "NN_CARRIER_SMSURI_MAPPING_PK" PRIMARY KEY ("CUSTOMER_NAME") 
  )
  CREATE TABLE "NN_LAST_TXN"
  (
    "ID"          NUMBER (38,0)  NOT NULL ENABLE,
    "LAST_PROCESSES_TXN_ID"   NUMBER (38,0)  NOT NULL ENABLE,
    "CUSTOMER_NAME"  VARCHAR2 (255 byte) NOT NULL ENABLE,
    "CREATED_DATE" TIMESTAMP NOT NULL  ENABLE                                                                                                                                                                             ,
    "MODIFIED_DATE" TIMESTAMP,
    CONSTRAINT "NN_LAST_TXN_PK" PRIMARY KEY ("CUSTOMER_NAME") 
  )
  CREATE TABLE "NN_PROCESS_AUDIT"
  (
    "ID"          NUMBER (38,0)  NOT NULL ENABLE,
    "START_DATE" TIMESTAMP NOT NULL  ENABLE                                                                                                                                                                             ,
    "END_DATE" TIMESTAMP,
    "PROCESS_TYPE"  VARCHAR2 (16 byte) DEFAULT 'INCREMENTAL' NOT NULL ENABLE,
    "CUSTOMER_NAME"  VARCHAR2 (255 byte) NOT NULL ENABLE,
    "EXEC_STATUS" VARCHAR2 (16 byte) NOT NULL ENABLE,
    "NO_OF_FILES_GENERATED" NUMBER(8,0) NOT NULL ENABLE,
    "END_TXN_ID" NUMBER (38,0)  NOT NULL ENABLE,
    "START_TXN_ID" NUMBER (38,0)  NOT NULL ENABLE,
    "CREATED_DATE" TIMESTAMP NOT NULL  ENABLE                                                                                                                                                                             ,
    "MODIFIED_DATE" TIMESTAMP,
    CONSTRAINT "NN_PROCESS_AUDIT_PK" PRIMARY KEY ("CUSTOMER_NAME") 
  )
  
  CREATE TABLE "NN_PROCESS_AUDIT_DESC"
  (
    "ID"          NUMBER (38,0)  NOT NULL ENABLE,
    "AUDIT_ID"      NUMBER (38,0)  NOT NULL ENABLE,
    "FILENAME"  VARCHAR2 (255 byte) NOT NULL ENABLE,
    "IS_UPLOADED" VARCHAR2 (16 byte) NOT NULL ENABLE,
     "CREATED_DATE" TIMESTAMP NOT NULL  ENABLE                                                                                                                                                                             ,
     CONSTRAINT "NN_PROCESS_AUDIT_DESC_PK" PRIMARY KEY ("ID") 
  )

