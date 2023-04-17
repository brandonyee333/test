INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '12', '12', timestamp '${now}', 34, 'ACTIVE');
INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '12', '12', timestamp '${now}', 56, 'INACTIVE');
INSERT INTO Membership (createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '78', '78', timestamp '${now}', 34, 'ACTIVE');

INSERT INTO MembershipChange (createDate, identitiesCount, individualsCount, segmentId) VALUES (timestamp '${now}', 37, 37, 34);
INSERT INTO MembershipChange (createDate, identitiesCount, individualsCount, segmentId) VALUES (timestamp '${now}', 27, 19, 56);