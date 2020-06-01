create index IX_C924302C on RN_ReleaseNotes (jiraIssueKeys[$COLUMN_LENGTH:20000$]);
create index IX_ED03B242 on RN_ReleaseNotes (name[$COLUMN_LENGTH:75$]);
create index IX_47A71791 on RN_ReleaseNotes (uuid_[$COLUMN_LENGTH:75$]);