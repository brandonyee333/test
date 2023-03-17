INSERT INTO Identity_Raw (createDate, id, individualId) VALUES (timestamp '${now}', 'abc-123', '123');

INSERT INTO IdentityActivitySummary (activitiesCount, channelId, dataSourceId, eventId, firstActivityDate, identityId, individualId, lastActivityDate) VALUES (1, 100, 567, 'pageViewed', timestamp '2019-03-11T17:10:00.666Z', 'abc-123', '123', timestamp '2019-03-11T17:10:00.666Z');
INSERT INTO IdentityActivitySummary (activitiesCount, channelId, dataSourceId, eventId, firstActivityDate, identityId, individualId, lastActivityDate) VALUES (1, 100, 678, 'pageViewed', timestamp '2019-03-11T17:10:00.666Z', 'abc-123', '123', timestamp '2019-03-11T17:10:00.666Z');

INSERT INTO Individual (createDate, emailAddress, fields, id, modifiedDate) VALUES (timestamp '${today-1dT00:00:00.000Z}', 'test@liferay.com', ARRAY<STRUCT<INT64, STRING, STRING>>[(null, null, null)], '123', timestamp '${today-1dT00:00:00.000Z}');