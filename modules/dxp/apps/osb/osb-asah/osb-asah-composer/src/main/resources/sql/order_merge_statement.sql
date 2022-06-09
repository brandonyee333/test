MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.order` AS replica
USING
	(
		SELECT
			`order`.*,
			analyticsDeleteMessage.deleted
		FROM (
			SELECT
				*
			FROM (
				SELECT
					*,
					ROW_NUMBER() OVER (
						PARTITION BY
							`channelId`, `id`
						ORDER BY
							`uploadDate` DESC
					) AS `rowNumber`
				FROM
					`{{ dag.default_args['ac_project_id'] }}.order_raw`
				) AS latest_order
			WHERE
				`rowNumber` = 1
		) AS `order`
		LEFT JOIN (
			SELECT
			  *
			FROM (
				SELECT
					projectId,
					dataSourceId,
					TRUE AS deleted,
					(
						SELECT
							SAFE_CAST(value AS INT64)
						FROM
							UNNEST(fields)
						WHERE
							name = 'classPK'
					) AS classPK,
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
			  ) AS analyticsDeleteMessage_inner
			WHERE
			  `className` = 'com.liferay.commerce.model.CommerceOrder'
		) as analyticsDeleteMessage
		ON
			`order`.projectId = analyticsDeleteMessage.projectId AND
			`order`.datasourceId = analyticsDeleteMessage.datasourceId AND
			`order`.id = analyticsDeleteMessage.classPK
		WHERE
			`order`.uploadDate >=
				{% if prev_start_date_success is not none %}
					'{{ prev_start_date_success }}'
				{% else %}
					'1970-01-01T00:00:00'
				{% endif %}
		) AS staging
ON
	staging.id = replica.id AND
	staging.dataSourceId = replica.dataSourceId AND
	staging.projectId = replica.projectId
WHEN MATCHED AND staging.deleted IS NULL AND staging.modifiedDate > replica.modifiedDate THEN
	UPDATE SET
		replica.dataSourceId = staging.dataSourceId,
		replica.projectId = staging.projectId,
		replica.accountId = staging.accountId,
		replica.channelId = staging.channelId,
		replica.createDate = staging.createDate,
		replica.currencyCode = staging.currencyCode,
		replica.externalReferenceCode = staging.externalReferenceCode,
		replica.id = staging.id,
		replica.modifiedDate = staging.modifiedDate,
		replica.orderDate = staging.orderDate,
		replica.orderItems = staging.orderItems,
		replica.orderStatus = staging.orderStatus,
		replica.orderTypeId = staging.orderTypeId,
		replica.paymentMethod = staging.paymentMethod,
		replica.paymentStatus = staging.paymentStatus,
		replica.status = staging.status,
		replica.total = staging.total,
		replica.userId = staging.userId
WHEN MATCHED AND staging.deleted = true THEN
	DELETE
WHEN NOT MATCHED BY TARGET AND staging.deleted IS NULL THEN
	INSERT (
		`dataSourceId`,
		`projectId`,
		`accountId`,
		`channelId`,
		`createDate`,
		`currencyCode`,
		`externalReferenceCode`,
		`id`,
		`modifiedDate`,
		`orderDate`,
		`orderItems`,
		`orderStatus`,
		`orderTypeId`,
		`paymentMethod`,
		`paymentStatus`,
		`status`,
		`total`,
		`userId`
	)
	VALUES (
		staging.dataSourceId,
		staging.projectId,
		staging.accountId,
		staging.channelId,
		staging.createDate,
		staging.currencyCode,
		staging.externalReferenceCode,
		staging.id,
		staging.modifiedDate,
		staging.orderDate,
		staging.orderItems,
		staging.orderStatus,
		staging.orderTypeId,
		staging.paymentMethod,
		staging.paymentStatus,
		staging.status,
		staging.total,
		staging.userId
	)