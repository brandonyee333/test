INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Document', 'https://www.beryl.com/products/commercial/irrigation/FF-2100', 1, date_trunc('HOUR', timestamp '${now-5d}' ), 'documentDownloaded', '1');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Document', 'https://should.not.return.com', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'documentDownloaded', '2' );
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Document', 'https://www.beryl.com/products/commercial/irrigation', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'documentPreviewed', '3');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Document', 'https://www.beryl.com/delivery', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'documentPreviewed', '4');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'fileEntryId', 'e131fabc');