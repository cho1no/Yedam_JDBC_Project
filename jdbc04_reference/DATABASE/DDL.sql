DROP TABLE members;
CREATE TABLE members
(
	member_id VARCHAR2(1000) PRIMARY KEY
	, member_password VARCHAR2(1000) NOT NULL
	, member_role NUMBER(1,0) CHECK (member_role IN (0,1))
);

INSERT INTO members
		(
			member_id
			, member_password
			, member_role
		)
		VALUES
		(
			'admin'
			,'admin'
			, 0
		);
        
INSERT INTO members
		(
			member_id
			, member_password
			, member_role
		)
		VALUES
		(
			'user1'
			,'1234'
			, 1
		);
COMMIT;