create table OSBCommunity_DocProject (
	uuid_ VARCHAR(75) null,
	docProjectId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	iconFileName VARCHAR(75) null,
	unlisted BOOLEAN,
	type_ VARCHAR(75) null,
	typeSettings TEXT null,
	status INTEGER
);