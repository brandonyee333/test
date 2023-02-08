WITH AssetEvent AS (
    SELECT
        event.applicationId,
        event.dataSourceId,
        event.eventId,
        CASE
            WHEN
                assetTitle.value IS NOT NULL
            THEN
                assetTitle.value
            WHEN
                event.eventId = 'pageViewed'
            THEN
                event.title
            END AS assetTitle,
        CASE
            WHEN
                assetId.value IS NOT NULL
            THEN
                assetId.value
            WHEN
                event.eventId = 'pageViewed'
            THEN
                event.canonicalUrl
            END AS assetId
    FROM
        BQEvent event
            LEFT JOIN BQEventProperty assetId ON (
                event.id = assetId.id
            AND
                assetId.name IN (
                    'articleId', 'classPK', 'entryId', 'fileEntryId', 'formId'
                )
            )
            LEFT JOIN BQEventProperty assetTitle ON (
                event.id = assetTitle.id AND assetTitle.name = 'title'
            )
    WHERE
        event.applicationId IN (
            'Blog', 'Custom', 'Document', 'Form', 'WebContent', 'Page'
        ) AND
        event.eventId IN (
            'blogViewed', 'formViewed', 'documentDownloaded',
            'documentPreviewed', 'webContentViewed', 'pageViewed'
        )
)
SELECT
    DISTINCT (
        ENCODE(
            SHA256((dataSourceId || assetId || assetTitle)::BYTEA),
            'hex'
    )) AS id
FROM
    AssetEvent
WHERE
    applicationId = :applicationId
AND
    dataSourceId = :dataSourceId
AND
    eventId = :eventId
