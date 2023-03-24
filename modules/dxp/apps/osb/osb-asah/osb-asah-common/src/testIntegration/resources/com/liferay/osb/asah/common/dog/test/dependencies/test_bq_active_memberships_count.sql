INSERT INTO IdentityActivitySummary (channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 'pageViewed', '1', 'A', timestamp '${now-35d}');
INSERT INTO IdentityActivitySummary (channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 'pageViewed', '2', 'A', timestamp '${now-1d}');
INSERT INTO IdentityActivitySummary (channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 'pageViewed', '3', 'B', timestamp '${now-40d}');
INSERT INTO IdentityActivitySummary (channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 'pageViewed', '4', 'C', timestamp '${now-5d}');
INSERT INTO IdentityActivitySummary (channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 'pageViewed', '6', NULL, timestamp '${now-3d}');
INSERT INTO IdentityActivitySummary (channelId, eventId, identityId, individualId, lastActivityDate) VALUES (1, 'pageViewed', '8', NULL, timestamp '${now-2d}');

INSERT INTO Identity_Raw (id, individualId) VALUES ('1', 'A');
INSERT INTO Identity_Raw (id, individualId) VALUES ('2', 'A');
INSERT INTO Identity_Raw (id, individualId) VALUES ('3', 'B');
INSERT INTO Identity_Raw (id, individualId) VALUES ('4', 'C');
INSERT INTO Identity_Raw (id, individualId) VALUES ('5', 'D');
INSERT INTO Identity_Raw (id, individualId) VALUES ('6', NULL);
INSERT INTO Identity_Raw (id, individualId) VALUES ('7', NULL);
INSERT INTO Identity_Raw (id, individualId) VALUES ('8', NULL);

INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('1', 'A', 1);
INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('3', 'B', 1);
INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('4', 'C', 1);
INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('5', 'D', 1);

INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('1', 'A', 2);
INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('3', 'B', 2);
INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('6', NULL, 2);
INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('7', NULL, 2);
INSERT INTO Membership (identityId, individualId, segmentId) VALUES ('8', NULL, 2);