INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES ('A', '1');
INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES ('B', '2');
INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES ('C', '3');
INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES (null, '4');
INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES (null, '5');
INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES (null, '6');
INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES (null, '7');

INSERT INTO BQIndividual (emailAddressHashed) VALUES ('A');
INSERT INTO BQIndividual (emailAddressHashed) VALUES ('B');
INSERT INTO BQIndividual (emailAddressHashed) VALUES ('C');
INSERT INTO BQIndividual (emailAddressHashed) VALUES ('D');

INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (0, 1, 150000, '1', timestamp '${today-1d}', timestamp '${today-1d}', '1');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (1, 1, 300000, '2', timestamp '${today-5d}', timestamp '${today-5d}', '1');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (1, 1, 120000, '3', timestamp '${today-5d}', timestamp '${today-5d}', '2');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (0, 1, 60000, '4', timestamp '${today-8d}', timestamp '${today-8d}', '2');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (0, 1, 60000, '5', timestamp '${today-9d}', timestamp '${today-9d}', '3');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (1, 1, 120000, '6', timestamp '${today-13d}', timestamp '${today-13d}', '4');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (1, 1, 180000, '7', timestamp '${today-18d}', timestamp '${today-18d}', '4');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (0, 1, 120000, '8', timestamp '${today-22d}', timestamp '${today-22d}', '4');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (1, 1, 60000, '9', timestamp '${today-34d}', timestamp '${today-34d}', '6');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionEnd, sessionStart, userId) VALUES (0, 1, 120000, '10', timestamp '${today-41d}', timestamp '${today-41d}', '7');