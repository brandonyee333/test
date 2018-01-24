create table PULPO_ConnectorTransaction (
	connectorTransactionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	connectorTransactionUuid VARCHAR(75) null,
	operation VARCHAR(75) null,
	status VARCHAR(75) null
);