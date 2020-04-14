create index IX_20C58AE8 on OSB_BlacklistEntry (emailAddress[$COLUMN_LENGTH:75$]);

create index IX_DA4F9519 on OSB_BounceEntry (bounceDate);
create index IX_21FC1985 on OSB_BounceEntry (emailAddress[$COLUMN_LENGTH:75$], bounceDate);