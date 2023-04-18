INSERT INTO Event (applicationId, channelId, createDate, eventDate, eventId, id, userId) VALUES ('Blog', 1, timestamp '${now-2d}', timestamp '${now-2d}', 'blogClicked', '1', '1');
INSERT INTO Event (applicationId, channelId, createDate, eventDate, eventId, id, userId) VALUES ('Blog', 1, timestamp '${now-4d}', timestamp '${now-4d}', 'blogClicked', '2', '2');
INSERT INTO Event (applicationId, channelId, createDate, eventDate, eventId, id, userId) VALUES ('Blog', 1, timestamp '${now-11d}', timestamp '${now-11d}', 'blogClicked', '3', '3');

INSERT INTO IdentityActivitySummary (channelId, identityId, individualId, lastActivityDate) VALUES (1, 'abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb', timestamp '${now-3d}');
INSERT INTO IdentityActivitySummary (channelId, identityId, individualId, lastActivityDate) VALUES (1, 'bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a', timestamp '${now-5d}');
INSERT INTO IdentityActivitySummary (channelId, identityId, individualId, lastActivityDate) VALUES (1, 'efg-789', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34', timestamp '${now-11d}');

INSERT INTO Identity_Raw (id, individualId) VALUES ('abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO Identity_Raw (id, individualId) VALUES ('bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO Identity_Raw (id, individualId) VALUES ('efg-789', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');

INSERT INTO Individual (emailAddress, fields, id, memberships) VALUES ('joe@alpha.com', ARRAY[STRUCT(123, 'classPK', '41847'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Developer')], '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb', ARRAY[STRUCT(['23k92323l923lf0as'], 'organizationIds')]);
INSERT INTO Individual (emailAddress, fields, id, memberships) VALUES ('marcus@beta.com', ARRAY[STRUCT(123, 'classPK', '41848'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Engineer')], '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a', ARRAY[STRUCT(['9823423jh23908234'], 'groupIds')]);
INSERT INTO Individual (emailAddress, fields, id, memberships) VALUES ('nina@delta.com', ARRAY[STRUCT(123, 'classPK', '41849'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Developer')], '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34', ARRAY[STRUCT(['newr87232kjhdsf89'], 'userGroupIds')]);

INSERT INTO Membership (identityId, segmentId) VALUES ('yyy-999', 1);
INSERT INTO Membership (identityId, segmentId) VALUES ('zzz-999', 1);