create table DLFileEntryPreview (
	fileEntryPreviewId LONG not null primary key,
	groupId LONG,
	fileEntryId LONG,
	fileVersionId LONG,
	previewType INTEGER
);

create table FileVersionPreview (
	fileVersionPreviewId LONG not null primary key,
	groupId LONG,
	fileEntryId LONG,
	fileVersionId LONG,
	previewStatus INTEGER
);