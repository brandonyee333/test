INSERT INTO Identity_Raw (id, individualId) VALUES ('1', '1');

INSERT INTO Individual (id) VALUES ('1');

INSERT INTO Session (channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 86400000, '1', timestamp '${now-3d}', timestamp '${now-2d}');
INSERT INTO Session (channelId, duration, id, sessionStart, sessionEnd) VALUES (1 , 3600000, '2', timestamp '${now-3m}', timestamp '${now}');
INSERT INTO Session (channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 345600000, '3', timestamp '${now-65d}', timestamp '${now-61d}');