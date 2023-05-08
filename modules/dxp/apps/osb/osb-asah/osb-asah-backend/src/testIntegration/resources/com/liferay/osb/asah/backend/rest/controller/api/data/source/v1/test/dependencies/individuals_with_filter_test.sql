INSERT INTO Identity_Raw (createDate, id, individualId) values (timestamp '2019-02-08T22:40:44.412Z', '953be104-5540-abf8-59b8-55f895200acc', '337984659206412898');
INSERT INTO Identity_Raw (createDate, id, individualId) values (timestamp '2019-02-08T22:40:44.412Z', '953be104-5540-abf8-59b8-5wer32200acc', '337984612356412845');

INSERT INTO IdentityActivitySummary (dataSourceId, identityId) VALUES (337984445922213329, '953be104-5540-abf8-59b8-55f895200acc');
INSERT INTO IdentityActivitySummary (dataSourceId, identityId) VALUES (337984445922213329, '953be104-5540-abf8-59b8-5wer32200acc');

INSERT INTO Individual (createDate, emailAddress, fields, firstName, id, modifiedDate) VALUES (timestamp '2019-02-08T22:40:44.412Z', 'test.test@liferay.com', ARRAY<STRUCT<dataSourceId INT64, name STRING, value String>> [(337984445922213329, 'middleName', '')], 'Test', '337984659206412898', timestamp '2019-02-08T22:40:44.412Z');
INSERT INTO Individual (createDate, emailAddress, fields, firstName, id, modifiedDate) VALUES (timestamp '2019-02-08T22:40:44.412Z', 'dummy@liferay.com', ARRAY<STRUCT<dataSourceId INT64, name STRING, value String>> [(337984445922213329, 'middleName', '')], null, '337984612356412845', timestamp '2019-02-08T22:40:44.412Z');