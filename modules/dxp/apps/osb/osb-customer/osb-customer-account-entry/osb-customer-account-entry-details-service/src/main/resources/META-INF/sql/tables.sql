create table OSBCustomer_Event (
	eventId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	occurDate DATE null,
	accountEntryId LONG,
	classNameId LONG,
	classPK LONG,
	type_ INTEGER,
	typeClassNameId LONG,
	typeClassPK LONG,
	title VARCHAR(255) null,
	summary STRING null,
	additionalInfo STRING null
);