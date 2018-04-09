create table WatsonTokenAuthEntry (
	watsonTokenAuthEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	active_ BOOLEAN,
	token VARCHAR(75) null,
	expirationDate DATE null,
	loginDate DATE null
);