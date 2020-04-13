create table OSBCommunity_GitHubContributor (
	gitHubContributorId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	gitHubRepositoryId LONG,
	name VARCHAR(75) null,
	avatarURL VARCHAR(75) null,
	contributions INTEGER,
	profileURL VARCHAR(75) null
);

create table OSBCommunity_GitHubRepository (
	gitHubRepositoryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	owner VARCHAR(75) null,
	name VARCHAR(75) null,
	commits INTEGER,
	openIssues INTEGER,
	stars INTEGER,
	url VARCHAR(75) null,
	repositoryCreateDate DATE null
);