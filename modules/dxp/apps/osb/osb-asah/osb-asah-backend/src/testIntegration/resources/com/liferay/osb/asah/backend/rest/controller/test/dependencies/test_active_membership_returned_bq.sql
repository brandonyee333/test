INSERT INTO Identity_Raw (createDate, id, individualId) VALUES (timestamp '${now}', '123', '123');
INSERT INTO Identity_Raw (createDate, id, individualId) VALUES (timestamp '${now}', '456', '123');

INSERT INTO IdentityActivitySummary (activitiesCount, channelId, dataSourceId, eventId, firstActivityDate, identityId, individualId, lastActivityDate) VALUES (1, 100, 567, 'pageViewed', timestamp '2019-03-11T17:10:00.666Z', '123', '123', timestamp '2019-03-11T17:10:00.666Z');
INSERT INTO IdentityActivitySummary (activitiesCount, channelId, dataSourceId, eventId, firstActivityDate, identityId, individualId, lastActivityDate) VALUES (1, 100, 567, 'pageViewed', timestamp '2019-03-11T17:10:00.666Z', '456', '123', timestamp '2019-03-11T17:10:00.666Z');

INSERT INTO Individual (createDate, emailAddress, id, modifiedDate) VALUES (timestamp '${today-1dT00:00:00.000Z}', 'test@liferay.com', '123', timestamp '${today-1dT00:00:00.000Z}');

INSERT INTO Membership (createDate, individualId, identityId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '123', '123', timestamp '${now}', 910, 'ACTIVE');
INSERT INTO Membership (createDate, individualId, identityId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '123', '123', timestamp '${now}', 101, 'ACTIVE');
INSERT INTO Membership (createDate, individualId, identityId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '123', '456', timestamp '${now}', 910, 'INACTIVE');

INSERT INTO MembershipChange (createDate, identitiesCount, individualsCount, segmentId) VALUES (timestamp '${now}', 1, 1, 101);
INSERT INTO MembershipChange (createDate, identitiesCount, individualsCount, segmentId) VALUES (timestamp '${now}', 1, 1, 910);