create table OSBCustomer_ArtifactVersion (
	artifactVersionId LONG not null primary key,
	releaseAssetCategoryId LONG,
	group_ VARCHAR(75) null,
	name VARCHAR(75) null,
	version VARCHAR(75) null
);