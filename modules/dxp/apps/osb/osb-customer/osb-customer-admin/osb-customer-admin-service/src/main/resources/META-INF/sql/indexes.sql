create index IX_1B961F96 on OSB_AccountAttachment (accountEntryId, accountProjectId, fileName[$COLUMN_LENGTH:75$], type_);
create index IX_D44EEA51 on OSB_AccountAttachment (accountEntryId, accountProjectId, type_);

create index IX_66F378D7 on OSB_AccountEntries_SupportRegions (companyId);
create index IX_B9459F3D on OSB_AccountEntries_SupportRegions (supportRegionId);

create index IX_4304C568 on OSB_AccountEntry (dossieraAccountKey[$COLUMN_LENGTH:75$]);
create index IX_32D8BC5 on OSB_AccountEntry (koroneikiAccountKey[$COLUMN_LENGTH:75$]);

create index IX_8B166398 on OSB_AccountEntryLanguage (accountEntryId);

create index IX_4A401F32 on OSB_AccountEnvironment (accountEntryId, productEntryId, name[$COLUMN_LENGTH:75$]);

create index IX_8CAAC4C0 on OSB_AccountEnvironmentAttachment (accountEnvironmentId, fileName[$COLUMN_LENGTH:75$]);
create index IX_23139A90 on OSB_AccountEnvironmentAttachment (accountEnvironmentId, type_);

create index IX_AC72BE4D on OSB_AuditEntry (classNameId, classPK, action, field);
create index IX_56D6EFE1 on OSB_AuditEntry (classNameId, classPK, field, action);
create index IX_6DC23F99 on OSB_AuditEntry (classNameId, classPK, visibility);
create index IX_558935D2 on OSB_AuditEntry (createDate, classNameId);
create index IX_A70C3087 on OSB_AuditEntry (fieldClassNameId, fieldClassPK, field);

create index IX_86A7752 on OSB_ExternalIdMapper (classNameId, classPK, type_);
create index IX_8FBE42CF on OSB_ExternalIdMapper (classNameId, type_, externalId[$COLUMN_LENGTH:75$]);

create index IX_8B5D7FE4 on OSB_LicenseEntry (productEntryId, portalVersionMin);
create unique index IX_7576438B on OSB_LicenseEntry (productEntryId, type_[$COLUMN_LENGTH:75$]);

create index IX_884EBE59 on OSB_ProductEntry (environment);
create unique index IX_9EC71E05 on OSB_ProductEntry (koroneikiProductKey[$COLUMN_LENGTH:75$]);
create unique index IX_190AFA9 on OSB_ProductEntry (name[$COLUMN_LENGTH:75$]);

create unique index IX_DC67F8E7 on OSB_SupportRegion (name[$COLUMN_LENGTH:75$]);