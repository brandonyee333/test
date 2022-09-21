INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Form', 'https://www.beryl.com/products/commercial/irrigation/FF-2100', 1, date_trunc('HOUR', timestamp '${now-5d}' ), 'formViewed', '1');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Form', 'https://should.not.return.com', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'formViewed', '2' );
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Form', 'https://www.beryl.com/products/commercial/irrigation', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'formViewed', '3');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Form', 'https://www.beryl.com/delivery', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'formViewed', '4');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'formId', 'e131fabc');