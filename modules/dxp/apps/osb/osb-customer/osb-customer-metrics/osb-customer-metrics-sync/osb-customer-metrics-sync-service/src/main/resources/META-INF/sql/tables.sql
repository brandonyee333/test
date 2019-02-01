create table OSBCustomer_SyncState (
	syncStateId LONG not null primary key,
	modelName VARCHAR(75) null,
	lastRunTime LONG
);