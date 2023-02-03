MERGE INTO
	`${PROJECT_ID}.${ASAH_PROJECT_ID}.session` AS replica
USING (
	SELECT
		sessionId, ARRAY_AGG(DISTINCT url) AS urls
	FROM
		`${PROJECT_ID}.${ASAH_PROJECT_ID}.event`
	GROUP
		BY sessionId
) AS staging
ON (
	replica.id = staging.sessionId
)
WHEN MATCHED THEN
	UPDATE SET replica.urls = staging.urls