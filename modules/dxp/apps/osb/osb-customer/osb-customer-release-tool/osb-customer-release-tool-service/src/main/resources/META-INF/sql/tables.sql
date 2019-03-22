create table OSBCustomer_ArtifactVersion (
	artifactVersionId LONG not null primary key,
	releaseAssetCategoryId LONG,
	owner INTEGER,
	repository INTEGER,
	group_ VARCHAR(75) null,
	name VARCHAR(255) null,
	version VARCHAR(75) null,
	packaging VARCHAR(75) null
);