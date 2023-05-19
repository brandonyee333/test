MERGE INTO
	`${PROJECT_ID}.${asah_project_id}.session` AS replica
USING (
	SELECT
		sessionId, ARRAY_AGG(DISTINCT url) AS urls
	FROM
		`${PROJECT_ID}.${asah_project_id}.event`
	WHERE
		eventDate < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 6 HOUR)
	GROUP
		BY sessionId
) AS staging
ON (
	replica.id = staging.sessionId
)
WHEN MATCHED THEN
	UPDATE SET replica.urls = staging.urls