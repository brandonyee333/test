UPDATE `${PROJECT_ID}.${asah_project_id}.event`
SET
	experimentId = CAST(JSON_VALUE(context, '$.experimentId') AS INT64)
WHERE
    experimentId IS NULL AND
	JSON_VALUE(context, '$.experimentId') != ''