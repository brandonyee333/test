INSERT INTO BQIdentity (emailAddressHashed, userId) VALUES ('1', '1');

INSERT INTO BQSession (channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 8.64E7, '1', timestamp '${now-3d}', timestamp '${now-2d}');
INSERT INTO BQSession (channelId, duration, id, sessionStart, sessionEnd) VALUES (1 , 3600000.0, '2', timestamp '${now-3m}', timestamp '${now}');
INSERT INTO BQSession (channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 345600000, '3', timestamp '${now-65d}', timestamp '${now-61d}');