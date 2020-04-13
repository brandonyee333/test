create table OSBCommunity_MeetupGroup (
	meetupGroupId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	city VARCHAR(75) null,
	memberCount INTEGER,
	description VARCHAR(2000) null,
	url VARCHAR(75) null
);