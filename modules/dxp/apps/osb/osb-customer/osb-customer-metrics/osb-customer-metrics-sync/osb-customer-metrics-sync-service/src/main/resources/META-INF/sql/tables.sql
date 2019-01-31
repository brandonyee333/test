create table OSBCustomer_SyncState (
	syncStateId LONG not null primary key,
	model VARCHAR(75) null,
	lastRunTime LONG
);