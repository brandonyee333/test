create table TokenAuthEntry (
	tokenAuthEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	device VARCHAR(75) null,
	token VARCHAR(1000) null,
	loginDate DATE null,
	loginIP VARCHAR(75) null
);