MERGE INTO
	`${PROJECT_ID}.${asah_project_id}.membershipchange` AS target
USING (
	SELECT
		*
	FROM EXTERNAL_QUERY("${PROJECT_ID}.${REGION_ID}.postgresql", "SELECT channelId, id as segmentId FROM ${asah_project_id}.Segment GROUP BY channelId, id;")
) AS source
ON (
	target.segmentId = source.segmentId
)
WHEN MATCHED THEN
	UPDATE SET
		target.channelId = source.channelId