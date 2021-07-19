USE hive.default;

DELETE FROM Form;

INSERT INTO Form (assetId, channelId, eventDate, projectId, canonicalUrl) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-28h}'), 'test', 'https://www.beryl.com/products/commercial/irrigation');
INSERT INTO Form (assetId, channelId, eventDate, projectId, canonicalUrl) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-30h}'), 'test', 'https://www.beryl.com/delivery');
INSERT INTO Form (assetId, channelId, eventDate, projectId, canonicalUrl) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-5d}'), 'test', 'https://www.beryl.com/products/commercial/irrigation/FF-2100');
INSERT INTO Form (assetId, channelId, eventDate, projectId, canonicalUrl) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'test', 'https://should.not.return.com');