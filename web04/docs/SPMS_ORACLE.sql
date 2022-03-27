-- ���̺����
CREATE TABLE MEMBERS (
	MNO      INTEGER      NOT NULL, -- ȸ���Ϸù�ȣ
	EMAIL    VARCHAR2(40)  NOT NULL, -- �̸���
	PWD      VARCHAR2(100) NOT NULL, -- ��ȣ
	MNAME    VARCHAR2(50)  NOT NULL, -- �̸�
	CRE_DATE DATE     NOT NULL, -- ������
	MOD_DATE DATE     NOT NULL -- ��������ȣ������
)
;

-- �ڸ�Ʈ
COMMENT ON TABLE MEMBERS IS 'ȸ���⺻';
COMMENT ON COLUMN MEMBERS.MNO IS 'ȸ���Ϸù�ȣ';
COMMENT ON COLUMN MEMBERS.EMAIL IS '�̸���';
COMMENT ON COLUMN MEMBERS.PWD IS '��ȣ';
COMMENT ON COLUMN MEMBERS.MNAME IS '�̸�';
COMMENT ON COLUMN MEMBERS.CRE_DATE IS '������';
COMMENT ON COLUMN MEMBERS.MOD_DATE IS '��������ȣ������';

-- PK����
ALTER TABLE MEMBERS ADD CONSTRAINT PK_MEMBERS PRIMARY KEY (MNO);

-- ����ũ �ε���
CREATE UNIQUE INDEX UIX_MEMBERS ON MEMBERS (EMAIL ASC);

-- ������ ����
CREATE SEQUENCE MEMBERS_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 9999 NOCYCLE NOCACHE;

-- ������ �Է�
INSERT INTO MEMBERS(MNO, EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)
VALUES (MEMBERS_SEQ.NEXTVAL, 's1@test.com','1111','ȫ�浿',SYSDATE,SYSDATE);

INSERT INTO MEMBERS(MNO, EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)
VALUES (MEMBERS_SEQ.NEXTVAL, 's2@test.com','1111','�Ӳ���',SYSDATE,SYSDATE);

INSERT INTO MEMBERS(MNO, EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)
VALUES (MEMBERS_SEQ.NEXTVAL, 's3@test.com','1111','������',SYSDATE,SYSDATE);

INSERT INTO MEMBERS(MNO, EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)
VALUES (MEMBERS_SEQ.NEXTVAL, 's4@test.com','1111','�̸���',SYSDATE,SYSDATE);

INSERT INTO MEMBERS(MNO, EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)
VALUES (MEMBERS_SEQ.NEXTVAL, 's5@test.com','1111','������',SYSDATE,SYSDATE);

SELECT * FROM MEMBERS;