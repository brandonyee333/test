INSERT INTO IdentityInterestPage (canonicalUrl, channelId, identityId, keyword, title, views) VALUES ('http://www.liferay.com/sales', 1, '374790572703144534', 'sales', 'A page about sales', 1);
INSERT INTO IdentityInterestPage (canonicalUrl, channelId, identityId, keyword, title, views) VALUES ('http://www.liferay.com/sales', 1, '374790572703144535', 'sales', 'A page about sales', 2);

INSERT INTO IdentityInterestScore (channelId, identityId, interested, interestScore, keyword, recordedDate) VALUES (1, '374790569167317525', true, 1.7676619, 'clicks-and-mortar e-tailers', DATE '2021-09-12');
INSERT INTO IdentityInterestScore (channelId, identityId, interested, interestScore, keyword, recordedDate) VALUES (1, '374790575409131096', true, 2.6149597, 'javascript', DATE '2021-09-13');
INSERT INTO IdentityInterestScore (channelId, identityId, interested, interestScore, keyword, recordedDate) VALUES (1, '374790572703144534', false, 0.77022254, 'compelling metrics', DATE '2021-09-14');
INSERT INTO IdentityInterestScore (channelId, identityId, interested, interestScore, keyword, recordedDate) VALUES (1, '374790572703144534', true, 1.454685, 'sales', DATE '2021-09-14');
INSERT INTO IdentityInterestScore (channelId, identityId, interested, interestScore, keyword, recordedDate) VALUES (1, '374790572703144535', true, 1.454685, 'sales', DATE '2021-09-14');
INSERT INTO IdentityInterestScore (channelId, identityId, interested, interestScore, keyword, recordedDate) VALUES (1, '374790575409131096', true, 1.454685, 'rick\'s garage', DATE '2021-09-14');

INSERT INTO Identity_Raw (id, individualId) VALUES ('374790569167317525', '374790569167317525');
INSERT INTO Identity_Raw (id, individualId) VALUES ('374790575409131096', '374790575409131096');
INSERT INTO Identity_Raw (id, individualId) VALUES ('374790572703144534', '374790572703144534');
INSERT INTO Identity_Raw (id, individualId) VALUES ('374790572703144535', '374790572703144535');

INSERT INTO Membership (createDate, individualId, modifiedDate, segmentId, status) VALUES (timestamp '${now}', '374790569167317525', timestamp '${now}', 123, 'ACTIVE');