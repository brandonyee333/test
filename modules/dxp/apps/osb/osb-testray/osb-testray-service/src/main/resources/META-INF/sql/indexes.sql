create unique index IX_8A80C617 on OSB_TestrayBuild (testrayRoutineId, name[$COLUMN_LENGTH:150$]);

create unique index IX_57029B81 on OSB_TestrayBuildMetric (testrayBuildId, testrayCaseTypeId, status);

create index IX_B8573C94 on OSB_TestrayBuilds_TestrayCases (companyId);
create index IX_D374433F on OSB_TestrayBuilds_TestrayCases (testrayCaseId);

create unique index IX_E3EAF8FC on OSB_TestrayCase (testrayProjectId, name[$COLUMN_LENGTH:150$]);

create index IX_A700B728 on OSB_TestrayCaseResult (createDate);
create index IX_A08EDB4F on OSB_TestrayCaseResult (testrayBuildId);
create unique index IX_26BEC6B on OSB_TestrayCaseResult (testrayCaseId, testrayRunId);
create index IX_E79A1BE0 on OSB_TestrayCaseResult (testrayComponentId, testrayRunId);
create index IX_7631F5EC on OSB_TestrayCaseResult (testrayRunId);

create index IX_F6B7D0B2 on OSB_TestrayCaseResultWarning (testrayCaseResultId);

create index IX_49918CC0 on OSB_TestrayCaseResults_TestrayIssues (companyId);
create index IX_711D8338 on OSB_TestrayCaseResults_TestrayIssues (testrayIssueId);

create unique index IX_8925D02 on OSB_TestrayCaseType (groupId, name[$COLUMN_LENGTH:75$]);

create index IX_185D7EE7 on OSB_TestrayCases_TestrayComponents (companyId);
create index IX_A0EBE615 on OSB_TestrayCases_TestrayComponents (testrayComponentId);

create unique index IX_30D6FF7B on OSB_TestrayComponent (testrayProjectId, name[$COLUMN_LENGTH:75$]);

create unique index IX_B5A8AD86 on OSB_TestrayFactor (classNameId, classPK, testrayFactorOptionId);

create unique index IX_2A0497BF on OSB_TestrayFactorCategory (groupId, name[$COLUMN_LENGTH:75$]);

create unique index IX_4D2F0F8E on OSB_TestrayFactorOption (testrayFactorCategoryId, name[$COLUMN_LENGTH:75$]);

create unique index IX_EF624647 on OSB_TestrayIssue (name[$COLUMN_LENGTH:150$]);

create unique index IX_2902ACB5 on OSB_TestrayProductVersion (testrayProjectId, name[$COLUMN_LENGTH:75$]);

create unique index IX_4F5C5361 on OSB_TestrayProject (groupId, name[$COLUMN_LENGTH:150$]);

create unique index IX_7F95BF16 on OSB_TestrayRequirement (testrayProjectId, key_[$COLUMN_LENGTH:75$]);

create index IX_B3ED945F on OSB_TestrayRequirements_TestrayCases (companyId);
create index IX_32059323 on OSB_TestrayRequirements_TestrayCases (testrayRequirementId);

create unique index IX_2ED0971E on OSB_TestrayRun (testrayBuildId, externalReferencePK[$COLUMN_LENGTH:255$], externalReferenceType);

create index IX_3F42321A on OSB_TestraySubtasks_TestrayCaseResults (companyId);
create index IX_78C8D50A on OSB_TestraySubtasks_TestrayCaseResults (testraySubtaskId);

create index IX_7DD2C30A on OSB_TestraySubtasks_TestrayIssues (companyId);
create index IX_C490561A on OSB_TestraySubtasks_TestrayIssues (testraySubtaskId);

create index IX_FEDBA5CA on OSB_TestraySuites_TestrayCases (companyId);
create index IX_BC07070D on OSB_TestraySuites_TestrayCases (testraySuiteId);

create index IX_6CA79E29 on OSB_TestrayTasks_TestrayCaseTypes (companyId);
create index IX_25285A29 on OSB_TestrayTasks_TestrayCaseTypes (testrayTaskId);

create unique index IX_7077F1E9 on OSB_TestrayTeam (testrayProjectId, name[$COLUMN_LENGTH:75$]);