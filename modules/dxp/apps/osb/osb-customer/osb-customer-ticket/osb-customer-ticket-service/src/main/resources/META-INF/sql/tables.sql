create table OSBCustomer_TicketAttachment (
	ticketAttachmentId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	accountEntryId LONG,
	zendeskTicketId LONG,
	fileRepositoryId VARCHAR(75) null,
	fileName VARCHAR(255) null,
	fileSize LONG,
	type_ INTEGER
);