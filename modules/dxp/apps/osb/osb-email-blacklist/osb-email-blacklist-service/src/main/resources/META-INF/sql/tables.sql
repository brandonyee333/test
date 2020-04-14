create table OSB_BlacklistEntry (
	blacklistEntryId LONG not null primary key,
	createDate DATE null,
	emailAddress VARCHAR(75) null
);

create table OSB_BounceEntry (
	bounceEntryId LONG not null primary key,
	emailAddress VARCHAR(75) null,
	bounceDate DATE null,
	bounceType VARCHAR(75) null,
	bounceSubtype VARCHAR(75) null
);