INSERT INTO Membership (identityId, individualId, modifiedDate, segmentId) VALUES ('1', 'A', timestamp '${now}', 1);
INSERT INTO Membership (identityId, individualId, modifiedDate, segmentId) VALUES ('3', 'C', timestamp '${now-1h}', 1);
INSERT INTO Membership (identityId, individualId, modifiedDate, segmentId) VALUES ('4', NULL, timestamp '${now-2h}', 1);

INSERT INTO Membership (identityId, individualId, modifiedDate, segmentId) VALUES ('1', 'A', timestamp '${now}', 2);
INSERT INTO Membership (identityId, individualId, modifiedDate, segmentId) VALUES ('2', 'B', timestamp '${now-1h}', 2);
INSERT INTO Membership (identityId, individualId, modifiedDate, segmentId) VALUES ('4', NULL, timestamp '${now-2h}', 2);

INSERT INTO User (dataSourceId, individualId, uuid) VALUES (1, 'A', 'uuid-1');
INSERT INTO User (dataSourceId, individualId, uuid) VALUES (2, 'A', 'uuid-2');
INSERT INTO User (dataSourceId, individualId, uuid) VALUES (1, 'B', '');
INSERT INTO User (dataSourceId, individualId, uuid) VALUES (1, 'C', 'uuid-3');