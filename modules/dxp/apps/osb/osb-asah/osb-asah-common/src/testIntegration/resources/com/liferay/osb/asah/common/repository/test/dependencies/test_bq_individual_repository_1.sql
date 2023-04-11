INSERT INTO Event (applicationId, channelId, createDate, dataSourceId, eventDate, eventId, id, sessionId, userId) VALUES ('WebContent', 11, timestamp '${now}', 1, timestamp '2022-12-14T23:59:59.999Z', 'webContentViewed', '123', 'abc', 'abc-123');
INSERT INTO Event (applicationId, channelId, createDate, dataSourceId, eventDate, eventId, id, sessionId, userId) VALUES ('WebContent', 11, timestamp '${now}', 1, timestamp '2022-12-15T23:59:59.999Z', 'webContentViewed', '234', 'bcd', 'abc-123');
INSERT INTO Event (applicationId, channelId, createDate, dataSourceId, eventDate, eventId, id, sessionId, userId) VALUES ('WebContent', 11, timestamp '${now}', 1, timestamp '2022-12-16T23:59:59.999Z', 'webContentViewed', '345', 'cde', 'bcd-456');
INSERT INTO Event (applicationId, channelId, createDate, dataSourceId, eventDate, eventId, id, sessionId, userId) VALUES ('Page', 11, timestamp '${now}', 1, timestamp '2022-12-17T23:59:59.999Z', 'pageViewed', '456', 'def', 'bcd-456');
INSERT INTO Event (applicationId, channelId, createDate, dataSourceId, eventDate, eventId, id, sessionId, userId) VALUES ('Page', 11, timestamp '${now}', 1, timestamp '2022-12-18T23:59:59.999Z', 'pageLoaded', '567', 'efg', 'efg-789');

INSERT INTO ExpandoColumn (columnId, dataSourceId, dataType, id, name) VALUES ('190', 123, 'STRING', '1', 'Organization Type-Text');

INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2345', 'com.liferay.portal.kernel.model.Organization', '190', 123, 'Organization_Type', '19879', 'test');

INSERT INTO IdentityActivitySummary (activitiesCount, channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 11, 'webContentViewed', 'abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb', timestamp '2022-12-14T23:59:59.999Z');
INSERT INTO IdentityActivitySummary (activitiesCount, channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 11, 'webContentViewed', 'abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb', timestamp '2022-12-15T23:59:59.999Z');
INSERT INTO IdentityActivitySummary (activitiesCount, channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 11, 'webContentViewed', 'bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a', timestamp '2022-12-16T23:59:59.999Z');
INSERT INTO IdentityActivitySummary (activitiesCount, channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 11, 'pageViewed', 'bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a', timestamp '2022-12-17T23:59:59.999Z');
INSERT INTO IdentityActivitySummary (activitiesCount, channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 11, 'pageLoaded', 'efg-789', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34', timestamp '2022-12-18T23:59:59.999Z');

INSERT INTO Identity_Raw (id, individualId) VALUES ('abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO Identity_Raw (id, individualId) VALUES ('bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO Identity_Raw (id, individualId) VALUES ('efg-789', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');

INSERT INTO Individual (emailAddress, fields, firstName, id, jobTitle, memberships) VALUES ('joe@alpha.com', ARRAY[STRUCT(123, 'classPK', '41847'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Developer'), STRUCT(123, 'Salary', '120000.30')], 'Joe','761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb', '', ARRAY[STRUCT(['23k92323l923lf0as'], 'organizationIds')]);
INSERT INTO Individual (emailAddress, fields, firstName, id, jobTitle, memberships) VALUES ('marcus@beta.com', ARRAY[STRUCT(123, 'classPK', '41848'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Engineer'), STRUCT(123, 'Salary', '100000.50')], 'Marcus', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a', 'Engineer', ARRAY[STRUCT(['9823423jh23908234'], 'groupIds')]);
INSERT INTO Individual (emailAddress, fields, firstName, id, jobTitle, memberships) VALUES ('nina@delta.com', ARRAY[STRUCT(123, 'classPK', '41849'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Developer'), STRUCT(123, 'Birth_Country', '["England"]'), STRUCT(123, 'Zip_Code', '[91765]')], 'Nina', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34', NULL, ARRAY[STRUCT(['newr87232kjhdsf89'], 'userGroupIds')]);

INSERT INTO Organization (dataSourceId, id, name, organizationId) VALUES (123, '23k92323l923lf0as', 'Organization 1', 2345);