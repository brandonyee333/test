INSERT INTO BQSession (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 180000, '1', timestamp '${now-3m}', timestamp '${now}');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 1, 3600000.0, '2', timestamp '${now-3h}', timestamp '${now-2h}');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 7200000.0, '3', timestamp '${now-15h}', timestamp '${now-13h}');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 1, 3600000.0, '4', timestamp '${now-23h}', timestamp '${now-22h}');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 3600000.0, '5', timestamp '${now-26h}', timestamp '${now-25h}');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 1, 3600000.0, '6', timestamp '${now-34h}', timestamp '${now-33h}');
INSERT INTO BQSession (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 7200000.0, '7', timestamp '${now-38h}', timestamp '${now-36h}');

INSERT INTO BQIdentity (individualId, userId) VALUES ('1', '1');

INSERT INTO BQIndividual (id) VALUES ('1');