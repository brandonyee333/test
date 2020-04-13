create index IX_49386606 on OSBCommunity_GitHubContributor (gitHubRepositoryId);

create index IX_8C398B5B on OSBCommunity_GitHubRepository (owner[$COLUMN_LENGTH:75$], name[$COLUMN_LENGTH:75$]);