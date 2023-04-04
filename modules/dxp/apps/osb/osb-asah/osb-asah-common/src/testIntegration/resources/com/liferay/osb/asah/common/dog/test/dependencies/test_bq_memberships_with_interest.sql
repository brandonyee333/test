INSERT INTO IdentityInterestScore (identityId, interested, keyword, recordedDate) VALUES ('abc-123', true, 'analytics', CURRENT_DATE());
INSERT INTO IdentityInterestScore (identityId, interested, keyword, recordedDate) VALUES ('abc-123', false, 'cloud', CURRENT_DATE());
INSERT INTO IdentityInterestScore (identityId, interested, keyword, recordedDate) VALUES ('bcd-456', true, 'cloud', CURRENT_DATE());
INSERT INTO IdentityInterestScore (identityId, interested, keyword, recordedDate) VALUES ('bcd-456', false, 'analytics', CURRENT_DATE());
INSERT INTO IdentityInterestScore (identityId, interested, keyword, recordedDate) VALUES ('efg-789', false, 'dev', CURRENT_DATE());
INSERT INTO IdentityInterestScore (identityId, interested, keyword, recordedDate) VALUES ('efg-789', true, 'metrics', CURRENT_DATE());
INSERT INTO IdentityInterestScore (identityId, interested, keyword, recordedDate) VALUES ('efg-789', true, 'quality', CURRENT_DATE());

INSERT INTO Identity_Raw (id, individualId) VALUES ('abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO Identity_Raw (id, individualId) VALUES ('bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO Identity_Raw (id, individualId) VALUES ('efg-789', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');
INSERT INTO Identity_Raw (id, individualId) VALUES ('ghi-101', 'def73c7b1d2934d8bcdc8080a221c39df40e7ccfa499ad49d862138f5bc055f9');

INSERT INTO Individual (emailAddress, id) VALUES ('joe@alpha.com', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO Individual (emailAddress, id) VALUES ('marcus@beta.com', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO Individual (emailAddress, id) VALUES ('nina@delta.com', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');
INSERT INTO Individual (emailAddress, id) VALUES ('chris@gamma.com', 'def73c7b1d2934d8bcdc8080a221c39df40e7ccfa499ad49d862138f5bc055f9');