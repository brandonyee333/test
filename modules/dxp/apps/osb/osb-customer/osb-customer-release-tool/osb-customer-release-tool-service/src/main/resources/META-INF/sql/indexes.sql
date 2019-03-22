create index IX_1BB18E92 on OSBCustomer_ArtifactVersion (releaseAssetCategoryId, group_[$COLUMN_LENGTH:75$], name[$COLUMN_LENGTH:255$]);

create index IX_C6B593C7 on OSBCustomer_JIRAComponent (remoteId);
create index IX_948873D0 on OSBCustomer_JIRAComponent (visible);