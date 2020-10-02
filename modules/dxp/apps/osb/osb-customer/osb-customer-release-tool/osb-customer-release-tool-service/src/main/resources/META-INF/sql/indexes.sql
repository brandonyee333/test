create index IX_1BB18E92 on OSBCustomer_ArtifactVersion (releaseAssetCategoryId, group_[$COLUMN_LENGTH:75$], name[$COLUMN_LENGTH:255$]);

create index IX_B9CDBD3C on OSBCustomer_JIRAComponent (remoteId, remoteProject[$COLUMN_LENGTH:75$]);
create index IX_9939E831 on OSBCustomer_JIRAComponent (remoteProject[$COLUMN_LENGTH:75$], visible);
create index IX_948873D0 on OSBCustomer_JIRAComponent (visible);