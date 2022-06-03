MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.user` AS replica
USING (
	SELECT
		user.classPK,
		user.dataSourceId,
		user.modifiedDate,
		user.projectId,
		user.type,
		user.uploadDate,
		user.userId,
		ARRAY(
			SELECT
				unnestExpandoColumnIds
			FROM
				UNNEST(user.expandoColumnIds) AS unnestExpandoColumnIds
			WHERE
				unnestExpandoColumnIds <> analyticsDeleteMessage.columnId
		) AS updatedExpandoColumnIds
	FROM (
		SELECT
			*,
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(fields)
				WHERE
					name = 'classPK'
			) AS columnId,
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(fields)
				WHERE
					name = 'className'
			) AS className
		FROM
			`{{ dag.default_args['ac_project_id'] }}.dxpentity`
		WHERE
			type = 'com.liferay.analytics.message.storage.model.AnalyticsDeleteMessage' AND
			uploadDate >=
				{% if prev_start_date_success is not none %}
					'{{ prev_start_date_success }}'
				{% else %}
					'1970-01-01T00:00:00'
				{% endif %}
	) AS analyticsDeleteMessage
	INNER JOIN (
		SELECT
			*,
			(
			SELECT ARRAY(
				SELECT
					DISTINCT SAFE_CAST(columnId AS STRING)
				FROM
					UNNEST(expandoFields)
			)
		) AS expandoColumnIds,
		(
			SELECT
				SAFE_CAST(value AS INT64)
			FROM
				UNNEST(fields)
			WHERE
				name = 'userId'
		) AS userId
		FROM
			`{{ dag.default_args['ac_project_id'] }}.dxpentity`
		WHERE
			type = 'com.liferay.portal.kernel.model.User'
	) AS user
	ON
		analyticsDeleteMessage.columnId in UNNEST(user.expandoColumnIds) AND
		analyticsDeleteMessage.dataSourceId = user.dataSourceId AND
		analyticsDeleteMessage.projectId = user.projectId
	WHERE
		analyticsDeleteMessage.className = 'com.liferay.expando.kernel.model.ExpandoColumn'
) AS staging

ON
	staging.dataSourceId = replica.dataSourceId AND
	staging.modifiedDate = replica.modifiedDate AND
	staging.projectId = replica.projectId AND
	staging.userId = replica.dxpUserId
WHEN MATCHED THEN
	UPDATE SET
		replica.expandoColumnIds = staging.updatedExpandoColumnIds