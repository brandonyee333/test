INSERT INTO Identity_Raw (createDate, id, individualId) values (CURRENT_TIMESTAMP, 'identity1', 'user1');
INSERT INTO Identity_Raw (createDate, id, individualId) values (CURRENT_TIMESTAMP, 'identity2', 'user2');
INSERT INTO Identity_Raw (createDate, id, individualId) values (CURRENT_TIMESTAMP, 'identity3', 'user3');
INSERT INTO Identity_Raw (createDate, id, individualId) values (CURRENT_TIMESTAMP, 'identity4', 'user4');
INSERT INTO Identity_Raw (createDate, id, individualId) values (CURRENT_TIMESTAMP, 'identity5', null);
INSERT INTO Identity_Raw (createDate, id, individualId) values (CURRENT_TIMESTAMP, 'identity6', 'fake_individual_id1');
INSERT INTO Identity_Raw (createDate, id, individualId) values (CURRENT_TIMESTAMP, 'identity7', 'fake_individual_id2');

INSERT INTO Individual (createDate, emailAddress, id, modifiedDate) values (CURRENT_TIMESTAMP, 'user1@gmail.com', 'user1', CURRENT_TIMESTAMP);
INSERT INTO Individual (createDate, emailAddress, id, modifiedDate) values (CURRENT_TIMESTAMP, 'user2@gmail.com', 'user2', CURRENT_TIMESTAMP);
INSERT INTO Individual (createDate, emailAddress, id, modifiedDate) values (CURRENT_TIMESTAMP, 'user3@gmail.com', 'user3', CURRENT_TIMESTAMP);
INSERT INTO Individual (createDate, emailAddress, id, modifiedDate) values (CURRENT_TIMESTAMP, 'user4@gmail.com', 'user4', CURRENT_TIMESTAMP);

INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session1', TIMESTAMP '${now-5M}', TIMESTAMP '${now-5M}', 'identity1');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session2', TIMESTAMP '${now-5M}', TIMESTAMP '${now-5M}', 'identity2');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session3', TIMESTAMP '${now-5M}', TIMESTAMP '${now-5M}', 'identity5');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session4', TIMESTAMP '${now-5M}', TIMESTAMP '${now-5M}', 'identity6');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session5', TIMESTAMP '${now-4M}', TIMESTAMP '${now-4M}', 'identity2');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session6', TIMESTAMP '${now-4M}', TIMESTAMP '${now-4M}', 'identity6');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session7', TIMESTAMP '${now-4M}', TIMESTAMP '${now-4M}', 'identity1');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session8', TIMESTAMP '${now-1M}', TIMESTAMP '${now-1M}', 'identity7');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session9', TIMESTAMP '${now}', TIMESTAMP '${now}', 'identity4');
INSERT INTO Session (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) values (1, 1, 60000, 'session10', TIMESTAMP '${now}', TIMESTAMP '${now}', 'identity3');