create table OSBCustomer_Collaborator (
	collaboratorId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	accountEntryId LONG,
	emailAddress VARCHAR(75) null,
	fullName VARCHAR(75) null,
	gitHubUserName VARCHAR(75) null,
	status INTEGER
);