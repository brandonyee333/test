INSERT INTO BQIdentity (id, individualId) VALUES ('1', 'A');
INSERT INTO BQIdentity (id, individualId) VALUES ('2', 'B');
INSERT INTO BQIdentity (id, individualId) VALUES ('3', 'C');
INSERT INTO BQIdentity (id, individualId) VALUES ('4', null);
INSERT INTO BQIdentity (id, individualId) VALUES ('5', null);
INSERT INTO BQIdentity (id, individualId) VALUES ('6', null);
INSERT INTO BQIdentity (id, individualId) VALUES ('7', null);

INSERT INTO BQIndividual (id) VALUES ('A');
INSERT INTO BQIndividual (id) VALUES ('B');
INSERT INTO BQIndividual (id) VALUES ('C');
INSERT INTO BQIndividual (id) VALUES ('D');

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