INSERT INTO Channel (id, name) VALUES (1357, 'Channel');
INSERT INTO Channel (id, name) VALUES (2468, 'Channel');

INSERT INTO Segment (channelId, createDate, filter, id, name, referencedDataSourceIds, state, status, type) VALUES (1357, '${now}', '(channelId eq ''1'')', 34, 'Segment 1', ARRAY[5, 6], 'READY', 'STARTED', 'DYNAMIC');
INSERT INTO Segment (channelId, createDate, filter, id, name, referencedDataSourceIds, state, status, type) VALUES (2468, '${now}', '(channelId eq ''1'')', 56, 'Segment 1', ARRAY[5, 6], 'READY', 'STARTED', 'DYNAMIC');