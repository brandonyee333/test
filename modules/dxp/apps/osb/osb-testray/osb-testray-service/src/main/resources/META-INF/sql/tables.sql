create table OSB_TestrayArchive (
	testrayArchiveId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	compressedData BLOB
);

create table OSB_TestrayAssignment (
	testrayAssignmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	assignedUserId LONG,
	classNameId LONG,
	classPK LONG
);

create table OSB_TestrayBuild (
	testrayBuildId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	templateTestrayBuildId LONG,
	testrayRoutineId LONG,
	testrayProductVersionId LONG,
	testrayProjectId LONG,
	name VARCHAR(150) null,
	description STRING null,
	descriptionType VARCHAR(75) null,
	template BOOLEAN,
	dueDate DATE null,
	gitHash VARCHAR(75) null,
	githubCompareURLs VARCHAR(500) null,
	promoted BOOLEAN,
	status INTEGER
);

create table OSB_TestrayBuildMetric (
	testrayBuildMetricId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayBuildId LONG,
	testrayCaseTypeId LONG,
	status INTEGER,
	count LONG
);

create table OSB_TestrayBuilds_TestrayCases (
	companyId LONG not null,
	testrayBuildId LONG not null,
	testrayCaseId LONG not null,
	primary key (testrayBuildId, testrayCaseId)
);

create table OSB_TestrayCase (
	testrayCaseId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayCaseTypeId LONG,
	testrayComponentId LONG,
	testrayProjectId LONG,
	name VARCHAR(150) null,
	description STRING null,
	descriptionType VARCHAR(75) null,
	originationKey LONG,
	priority INTEGER,
	estimatedDuration INTEGER,
	steps TEXT null,
	stepsType VARCHAR(75) null,
	caseNumber LONG
);

create table OSB_TestrayCaseResult (
	testrayCaseResultId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commentMBMessageId LONG,
	testrayBuildId LONG,
	testrayCaseId LONG,
	testrayComponentId LONG,
	testrayRunId LONG,
	assignedUserId LONG,
	startDate DATE null,
	closedDate DATE null,
	attachments STRING null,
	errors STRING null,
	status INTEGER
);

create table OSB_TestrayCaseResultWarning (
	testrayCaseResultWarningId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayCaseResultId LONG,
	content STRING null
);

create table OSB_TestrayCaseResults_TestrayIssues (
	companyId LONG not null,
	testrayCaseResultId LONG not null,
	testrayIssueId LONG not null,
	primary key (testrayCaseResultId, testrayIssueId)
);

create table OSB_TestrayCaseType (
	testrayCaseTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null
);

create table OSB_TestrayCases_TestrayComponents (
	companyId LONG not null,
	testrayCaseId LONG not null,
	testrayComponentId LONG not null,
	primary key (testrayCaseId, testrayComponentId)
);

create table OSB_TestrayComponent (
	testrayComponentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayProjectId LONG,
	testrayTeamId LONG,
	name VARCHAR(75) null,
	originationKey LONG
);

create table OSB_TestrayFactor (
	testrayFactorId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	testrayFactorCategoryId LONG,
	testrayFactorCategoryName VARCHAR(75) null,
	testrayFactorOptionId LONG,
	testrayFactorOptionName VARCHAR(75) null
);

create table OSB_TestrayFactorCategory (
	testrayFactorCategoryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null
);

create table OSB_TestrayFactorOption (
	testrayFactorOptionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayFactorCategoryId LONG,
	name VARCHAR(75) null
);

create table OSB_TestrayIssue (
	testrayIssueId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(150) null
);

create table OSB_TestrayProductVersion (
	testrayProductVersionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayProjectId LONG,
	name VARCHAR(75) null
);

create table OSB_TestrayProject (
	testrayProjectId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(150) null,
	description STRING null
);

create table OSB_TestrayRequirement (
	testrayRequirementId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayComponentId LONG,
	testrayProjectId LONG,
	key_ VARCHAR(75) null,
	summary VARCHAR(255) null,
	components VARCHAR(255) null,
	linkTitle VARCHAR(75) null,
	linkURL VARCHAR(255) null,
	description STRING null,
	descriptionType VARCHAR(75) null,
	goals STRING null,
	goalsType VARCHAR(75) null,
	variations STRING null,
	variationsType VARCHAR(75) null
);

create table OSB_TestrayRequirements_TestrayCases (
	companyId LONG not null,
	testrayCaseId LONG not null,
	testrayRequirementId LONG not null,
	primary key (testrayCaseId, testrayRequirementId)
);

create table OSB_TestrayRoutine (
	testrayRoutineId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayProjectId LONG,
	name VARCHAR(75) null,
	autoanalyze BOOLEAN
);

create table OSB_TestrayRun (
	testrayRunId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayBuildId LONG,
	name VARCHAR(150) null,
	description STRING null,
	externalReferencePK VARCHAR(255) null,
	externalReferenceType INTEGER,
	jenkinsJobKey LONG,
	number_ LONG,
	environmentHash VARCHAR(75) null
);

create table OSB_TestraySubtask (
	testraySubtaskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commentMBMessageId LONG,
	mergedToTestraySubtaskId LONG,
	splitFromTestraySubtaskId LONG,
	testrayTaskId LONG,
	name VARCHAR(75) null,
	score INTEGER,
	statusUpdateDate DATE null,
	status INTEGER
);

create table OSB_TestraySubtasks_TestrayCaseResults (
	companyId LONG not null,
	testrayCaseResultId LONG not null,
	testraySubtaskId LONG not null,
	primary key (testrayCaseResultId, testraySubtaskId)
);

create table OSB_TestraySubtasks_TestrayIssues (
	companyId LONG not null,
	testrayIssueId LONG not null,
	testraySubtaskId LONG not null,
	primary key (testrayIssueId, testraySubtaskId)
);

create table OSB_TestraySuite (
	testraySuiteId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayProjectId LONG,
	name VARCHAR(75) null,
	description STRING null,
	caseParameters STRING null
);

create table OSB_TestraySuites_TestrayCases (
	companyId LONG not null,
	testrayCaseId LONG not null,
	testraySuiteId LONG not null,
	primary key (testrayCaseId, testraySuiteId)
);

create table OSB_TestrayTask (
	testrayTaskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayBuildId LONG,
	name VARCHAR(150) null,
	statusUpdateDate DATE null,
	status INTEGER
);

create table OSB_TestrayTasks_TestrayCaseTypes (
	companyId LONG not null,
	testrayCaseTypeId LONG not null,
	testrayTaskId LONG not null,
	primary key (testrayCaseTypeId, testrayTaskId)
);

create table OSB_TestrayTeam (
	testrayTeamId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	testrayProjectId LONG,
	name VARCHAR(75) null
);