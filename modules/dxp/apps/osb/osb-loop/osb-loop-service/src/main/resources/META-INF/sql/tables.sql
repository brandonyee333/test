create table LoopAuditEntry (
	loopAuditEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	name VARCHAR(75) null
);

create table LoopDivision (
	loopDivisionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	organizationId LONG,
	parentLoopDivisionId LONG,
	type_ INTEGER,
	subtype INTEGER,
	extraData STRING null,
	imagePayload STRING null
);

create table LoopDivisionRel (
	loopDivisionRelId LONG not null primary key,
	childLoopDivisionId LONG,
	loopPersonId LONG,
	parentLoopDivisionId LONG
);

create table LoopExternalReferenceRel (
	loopExternalReferenceRelId LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	externalReferenceName VARCHAR(75) null,
	externalReferencePK VARCHAR(75) null
);

create table LoopJobTitle (
	loopJobTitleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	status INTEGER
);

create table LoopParticipantAssignment (
	loopParticipantAssignmentId LONG not null primary key,
	loopDivisionId LONG,
	loopPersonId LONG,
	description STRING null,
	type_ INTEGER
);

create table LoopPerson (
	loopPersonId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	loopJobTitleId LONG,
	managerLoopPersonId LONG,
	personUserId LONG,
	extraData STRING null,
	groupedUserNotificationEventsCount INTEGER,
	imagePayload STRING null
);

create table LoopPersonRel (
	loopPersonRelId LONG not null primary key,
	childLoopPersonId LONG,
	parentLoopPersonId LONG,
	type_ INTEGER
);

create table LoopStatsEntry (
	loopStatsEntryId LONG not null primary key,
	modifiedTime LONG,
	classNameId LONG,
	classPK LONG,
	score DOUBLE
);

create table LoopStream (
	loopStreamId LONG not null primary key,
	loopPersonId LONG,
	name VARCHAR(75) null,
	description STRING null
);

create table LoopStreamEntry (
	loopStreamEntryId LONG not null primary key,
	loopPersonId LONG,
	loopStreamId LONG,
	classNameId LONG,
	classPK LONG,
	following BOOLEAN,
	followingType INTEGER
);

create table LoopTopic (
	loopTopicId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentLoopTopicId LONG,
	name VARCHAR(75) null,
	description STRING null,
	imagePayload STRING null,
	mergeTime LONG
);

create table LoopTopicAssignment (
	loopTopicAssignmentId LONG not null primary key,
	loopPersonId LONG,
	loopTopicId LONG,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusByDate DATE null
);

create table LoopUserNotificationEvent (
	loopUserNotificationEventId LONG not null primary key,
	createTime LONG,
	recipientUserId LONG,
	classNameId LONG,
	classPK LONG,
	groupClassNameId LONG,
	groupClassPK LONG,
	groupKey LONG,
	type_ INTEGER,
	received BOOLEAN,
	opened BOOLEAN
);

create table LoopUserNotificationRecord (
	loopUserNotificationRecordId LONG not null primary key,
	userId LONG,
	createTime LONG,
	classNameId LONG,
	classPK LONG,
	deliveryType INTEGER
);

create table LoopUserNotificationSubscription (
	loopUserNotificationSubscriptionId LONG not null primary key,
	loopPersonId LONG,
	classNameId LONG,
	classPK LONG,
	deliveryType INTEGER
);