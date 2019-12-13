create table OSBCustomer_AuditForm (
	auditFormId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	endUserName VARCHAR(75) null,
	endUserEmailAddress VARCHAR(75) null,
	companyName VARCHAR(75) null,
	agreement BOOLEAN
);