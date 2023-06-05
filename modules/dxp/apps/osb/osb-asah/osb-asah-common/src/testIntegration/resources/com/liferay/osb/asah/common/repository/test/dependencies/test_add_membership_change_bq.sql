INSERT INTO Individual (id) VALUES ('123456789');
INSERT INTO Individual (id) VALUES ('234567890');
INSERT INTO Individual (id) VALUES ('345678902');

INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', 'abc-123', '123456789', timestamp '${now}', 1029384756, 'ACTIVE');
INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', 'bcd-234', '234567890', timestamp '${now}', 1029384756, 'ACTIVE');
INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', 'cde-345', null, timestamp '${now}', 1029384756, 'ACTIVE');
INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', 'def-456', '345678902', timestamp '${now}', 1029384756, 'ACTIVE');
INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', 'efg-567', null, timestamp '${now}', 1029384756, 'ACTIVE');
INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', 'fgh-678', '345678902', timestamp '${now}', 1029384756, 'ACTIVE');