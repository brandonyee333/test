create table OSBCustomer_ZendeskArticle (
	zendeskArticleId LONG not null primary key,
	modifiedDate DATE null,
	zendeskSectionId LONG,
	documentationKey VARCHAR(150) null,
	remoteId LONG,
	remoteHtmlURL STRING null
);

create table OSBCustomer_ZendeskArticleAttachment (
	zendeskArticleAttachmentId LONG not null primary key,
	zendeskArticleId LONG,
	filePath VARCHAR(150) null,
	checksum VARCHAR(75) null,
	remoteId LONG,
	remoteContentURL STRING null
);

create table OSBCustomer_ZendeskCategory (
	zendeskCategoryId LONG not null primary key,
	documentationKey VARCHAR(75) null,
	articleLabels STRING null,
	remoteId LONG
);

create table OSBCustomer_ZendeskSection (
	zendeskSectionId LONG not null primary key,
	modifiedDate DATE null,
	zendeskCategoryId LONG,
	documentationKey VARCHAR(150) null,
	remoteId LONG,
	remoteHtmlURL VARCHAR(75) null
);