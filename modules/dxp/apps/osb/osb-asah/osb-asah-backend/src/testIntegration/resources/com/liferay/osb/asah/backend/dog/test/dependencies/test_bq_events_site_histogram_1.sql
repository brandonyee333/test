INSERT INTO Session (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 180000, '1', timestamp '${now-3m}', timestamp '${now}');
INSERT INTO Session (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 1, 3600000, '2', timestamp '${now-3h}', timestamp '${now-2h}');
INSERT INTO Session (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 7200000, '3', timestamp '${now-15h}', timestamp '${now-13h}');
INSERT INTO Session (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 1, 3600000, '4', timestamp '${now-23h}', timestamp '${now-22h}');
INSERT INTO Session (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 3600000, '5', timestamp '${now-26h}', timestamp '${now-25h}');
INSERT INTO Session (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 1, 3600000, '6', timestamp '${now-34h}', timestamp '${now-33h}');
INSERT INTO Session (bounce, channelId, duration, id, sessionStart, sessionEnd) VALUES (0, 1, 7200000, '7', timestamp '${now-38h}', timestamp '${now-36h}');

INSERT INTO Identity_Raw (id, individualId) VALUES ('1', '1');

INSERT INTO Individual (id) VALUES ('1');