MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.product` AS replica
USING
	(
		SELECT
			product.*,
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
					`{{ dag.default_args['ac_project_id'] }}.product_raw`
				) AS latest_product
			WHERE
				`rowNumber` = 1
		) AS product
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
				`className` = 'com.liferay.commerce.product.model.CPDefinition'
		) as analyticsDeleteMessage
		ON
			product.projectId = analyticsDeleteMessage.projectId AND
			product.datasourceId = analyticsDeleteMessage.datasourceId AND
			product.id = analyticsDeleteMessage.classPK
		WHERE
			product.uploadDate >=
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
		replica.channelId = staging.channelId,
		replica.dataSourceId = staging.dataSourceId,
		replica.projectId = staging.projectId,
		replica.catalogId = staging.catalogId,
		replica.categoryIds = staging.categoryIds,
		replica.createDate = staging.createDate,
		replica.description = staging.description,
		replica.displayDate = staging.displayDate,
		replica.expirationDate = staging.expirationDate,
		replica.externalReferenceCode = staging.externalReferenceCode,
		replica.id = staging.id,
		replica.metaDescription = staging.metaDescription,
		replica.metaKeyword = staging.metaKeyword,
		replica.metaTitle = staging.metaTitle,
		replica.modifiedDate = staging.modifiedDate,
		replica.name = staging.name,
		replica.productChannelIds = staging.productChannelIds,
		replica.productId = staging.productId,
		replica.productOptions = staging.productOptions,
		replica.productSpecifications = staging.productSpecifications,
		replica.productType = staging.productType,
		replica.skus = staging.skus,
		replica.status = staging.status,
		replica.subscriptionEnabled = staging.subscriptionEnabled,
		replica.tags = staging.tags,
		replica.urls = staging.urls
WHEN MATCHED AND staging.deleted = true THEN
	DELETE
WHEN NOT MATCHED BY TARGET AND staging.deleted IS NULL THEN
	INSERT (
		`channelId`,
		`dataSourceId`,
		`projectId`,
		`catalogId`,
		`categoryIds`,
		`createDate`,
		`description`,
		`displayDate`,
		`expirationDate`,
		`externalReferenceCode`,
		`id`,
		`metaDescription`,
		`metaKeyword`,
		`metaTitle`,
		`modifiedDate`,
		`name`,
		`productChannelIds`,
		`productId`,
		`productOptions`,
		`productSpecifications`,
		`productType`,
		`skus`,
		`status`,
		`subscriptionEnabled`,
		`tags`,
		`urls`
	)
	VALUES (
		staging.channelId,
		staging.dataSourceId,
		staging.projectId,
		staging.catalogId,
		staging.categoryIds,
		staging.createDate,
		staging.description,
		staging.displayDate,
		staging.expirationDate,
		staging.externalReferenceCode,
		staging.id,
		staging.metaDescription,
		staging.metaKeyword,
		staging.metaTitle,
		staging.modifiedDate,
		staging.name,
		staging.productChannelIds,
		staging.productId,
		staging.productOptions,
		staging.productSpecifications,
		staging.productType,
		staging.skus,
		staging.status,
		staging.subscriptionEnabled,
		staging.tags,
		staging.urls
	)