create index IX_699314F0 on OSBCustomer_Collaborator (accountEntryId, gitHubUserName[$COLUMN_LENGTH:75$]);
create index IX_6DEC5E22 on OSBCustomer_Collaborator (gitHubUserName[$COLUMN_LENGTH:75$], status);
create index IX_6BF43815 on OSBCustomer_Collaborator (status);