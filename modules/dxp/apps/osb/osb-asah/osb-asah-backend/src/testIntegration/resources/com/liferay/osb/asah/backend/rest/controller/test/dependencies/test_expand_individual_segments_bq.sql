INSERT INTO Identity_Raw (createDate, id, individualId) VALUES (timestamp '${now}', '123', '123');
INSERT INTO Identity_Raw (createDate, id, individualId) VALUES (timestamp '${now}', '456', '123');

INSERT INTO Individual (createDate, emailAddress, fields, id, modifiedDate) VALUES (timestamp '${now}', 'test@liferay.com', ARRAY<STRUCT<INT64, STRING, STRING>>[(null, null, null)], '123', timestamp '${now}');

INSERT INTO Membership (createDate, individualId, identityId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '123', '123', timestamp '${now}', 101, 'ACTIVE');
INSERT INTO Membership (createDate, individualId, identityId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '123', '123', timestamp '${now}', 910, 'ACTIVE');
INSERT INTO Membership (createDate, individualId, identityId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '123', '456', timestamp '${now}', 910, 'INACTIVE');