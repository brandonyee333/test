create index IX_FF5A938F on OSBCommunity_DocProject (groupId);
create index IX_19CB22FA on OSBCommunity_DocProject (name[$COLUMN_LENGTH:75$]);
create index IX_7E117AAB on OSBCommunity_DocProject (unlisted, status);
create index IX_643954AF on OSBCommunity_DocProject (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6027C2AF on OSBCommunity_DocProject (uuid_[$COLUMN_LENGTH:75$], groupId);