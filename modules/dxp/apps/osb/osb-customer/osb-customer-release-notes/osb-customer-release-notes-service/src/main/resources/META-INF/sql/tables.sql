create table RN_ReleaseNotes (
	uuid_ VARCHAR(75) null,
	releaseNotesId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	jiraIssueKeys TEXT null
);