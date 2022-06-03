MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.expandocolumn` AS replica
USING (
	SELECT
		*
	FROM (
		SELECT
			analyticsDeleteMessage.deleted,
			expandoColumn.classPK,
			expandoColumn.dataSourceId,
			expandoColumn.modifiedDate,
			expandoColumn.projectId,
			expandoColumn.type,
			expandoColumn.uploadDate,
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(expandoColumn.fields)
				WHERE
					name = 'className'
			) AS className,
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(expandoColumn.fields)
				WHERE
					name = 'columnId'
			) AS columnId,
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(expandoColumn.fields)
				WHERE
					name = 'dataType'
			) AS dataType,
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(expandoColumn.fields)
				WHERE
					name = 'name'
			) AS name,
			ROW_NUMBER() OVER (
				PARTITION BY
					expandoColumn.projectId, expandoColumn.dataSourceId, expandoColumn.classPK
				ORDER BY
					expandoColumn.modifiedDate DESC
			) AS rowNumber,
			TO_HEX(
				SHA256(
					CONCAT(expandoColumn.projectId, '#', expandoColumn.dataSourceId, '#', expandoColumn.classPK)
				)
			) AS sha256HexId
		FROM
			`{{ dag.default_args['ac_project_id'] }}.dxpentity` AS expandoColumn
		LEFT JOIN (
			SELECT
				*,
				TRUE AS deleted,
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
				type = 'com.liferay.analytics.message.storage.model.AnalyticsDeleteMessage'
		) AS analyticsDeleteMessage
		ON
			analyticsDeleteMessage.className = expandoColumn.type AND
			analyticsDeleteMessage.columnId = expandoColumn.classPK AND
			analyticsDeleteMessage.dataSourceId = expandoColumn.dataSourceId AND
			analyticsDeleteMessage.projectId = expandoColumn.projectId
		WHERE
			(
				analyticsDeleteMessage.columnId IS NOT NULL OR
				expandoColumn.uploadDate >=
					{% if prev_start_date_success is not none %}
						'{{ prev_start_date_success }}'
					{% else %}
						'1970-01-01T00:00:00'
					{% endif %}
			) AND
			expandoColumn.type = 'com.liferay.expando.kernel.model.ExpandoColumn'
	)
	WHERE
		rowNumber = 1
) AS staging
ON
	staging.columnId = replica.columnId AND
	staging.dataSourceId = replica.dataSourceId AND
	staging.projectId = replica.projectId
WHEN MATCHED AND staging.deleted IS NULL THEN
	UPDATE SET
		replica.className = staging.className,
		replica.dataType = staging.dataType,
		replica.modifiedDate = staging.modifiedDate,
		replica.name = staging.name
WHEN MATCHED AND staging.deleted = true THEN
	DELETE
WHEN NOT MATCHED BY TARGET AND staging.deleted IS NULL THEN
	INSERT (
		`className`,
		`columnId`,
		`dataSourceId`,
		`dataType`,
		`id`,
		`modifiedDate`,
		`name`,
		`projectId`
	)
	VALUES (
		staging.className,
		staging.columnId,
		staging.dataSourceId,
		staging.dataType,
		staging.sha256HexId,
		staging.modifiedDate,
		staging.name,
		staging.projectId
	)