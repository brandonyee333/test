INSERT INTO Channel (createDate, id, name, state) VALUES (timestamp '2020-04-14T08:30:00.000Z', 1, 'Site 1', 'READY');

INSERT INTO ChannelDataSource (channelId, dataSourceId, groupIds) VALUES (1, 123456789, '{123456}');

INSERT INTO DataSource (id) VALUES (123456789);