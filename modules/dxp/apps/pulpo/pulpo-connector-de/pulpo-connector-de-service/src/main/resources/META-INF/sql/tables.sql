create table PULPO_ConnectorTransaction (
	uuid_ VARCHAR(75) null,
	connectorTransactionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	operation VARCHAR(75) null,
	status VARCHAR(75) null
);