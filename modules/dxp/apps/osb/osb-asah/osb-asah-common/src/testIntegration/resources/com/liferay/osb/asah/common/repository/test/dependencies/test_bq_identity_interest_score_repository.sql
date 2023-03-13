INSERT INTO Identity_Raw (id, individualId) VALUES ('374790569167317525', '374790569167317525');
INSERT INTO Identity_Raw (id, individualId) VALUES ('374790575409131096', '374790575409131096');
INSERT INTO Identity_Raw (id, individualId) VALUES ('374790572703144534', '374790572703144534');
INSERT INTO Identity_Raw (id, individualId) VALUES ('374790572703144535', '374790572703144535');

INSERT INTO IdentityInterestScore (identityId, interested, interestScore, keyword, recordedDate) VALUES ('374790569167317525', true, 1.7676619, 'clicks-and-mortar e-tailers', timestamp '2021-09-12T00:00:00.000Z');
INSERT INTO IdentityInterestScore (identityId, interested, interestScore, keyword, recordedDate) VALUES ('374790575409131096', true, 2.6149597, 'javascript', timestamp '2021-09-13T00:00:00.000Z');
INSERT INTO IdentityInterestScore (identityId, interested, interestScore, keyword, recordedDate) VALUES ('374790572703144534', false, 0.77022254, 'compelling metrics', timestamp '2021-09-14T00:00:00.000Z');
INSERT INTO IdentityInterestScore (identityId, interested, interestScore, keyword, recordedDate) VALUES ('374790572703144534', true, 1.454685, 'sales', timestamp '2021-09-14T00:00:00.000Z');
INSERT INTO IdentityInterestScore (identityId, interested, interestScore, keyword, recordedDate) VALUES ('374790572703144535', true, 1.454685, 'sales', timestamp '2021-09-14T00:00:00.000Z');

INSERT INTO Membership (createDate, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '374790569167317525', timestamp '${now}', 123, 'ACTIVE');