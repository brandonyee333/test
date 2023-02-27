WITH CustomFieldMapping AS (
	SELECT
		'custom' AS context,
		dataSourceIds,
		name AS displayName,
		displayType,
		CASE
			WHEN
				rowNumber = 1
			THEN
				REGEXP_REPLACE(REPLACE(name, SUBSTR(name, STRPOS(name, CONCAT('-', dataType))), ''), r'\s', '_')
			ELSE
				REGEXP_REPLACE(REPLACE(name, SUBSTR(name, STRPOS(name, CONCAT('-', dataType))), ''), r'\s', '_') || '_' || (rowNumber - 1)
		END fieldName,
		dataType AS fieldType,
		modifiedDate,
		ownerType,
		CASE
			WHEN
				displayType IN ('checkbox', 'radio', 'selection-list')
			THEN
				TRUE
			ELSE
				FALSE
		END repeatable_
	FROM (
		SELECT
			*,
			ROW_NUMBER() OVER (
				PARTITION BY
					name,
					ownerType
				ORDER BY
					modifiedDate ASC
			) AS rowNumber
		FROM (
			SELECT
				ARRAY_AGG(dataSourceId) AS dataSourceIds,
				displayType,
				dataType,
				MAX(modifieDdate) AS modifiedDate,
				name,
				CASE
					WHEN
						className = 'com.liferay.portal.kernel.model.User'
					THEN
						'individual'
					WHEN
						className = 'com.liferay.portal.kernel.model.Organization'
					THEN
						'organization'
				END ownerType
			FROM
				`$[AC_PROJECT_ID].expandocolumn` AS ExpandoColumn
			GROUP BY
				name, dataType, displayType, ownerType
		) AS TMP1
	) AS TMP2
),
DemographicsFieldMapping AS (
	SELECT
		'demographics' context,
		ARRAY<INT64>[] dataSourceIds,
		displayName,
		'input-field' displayType,
		fieldName,
		fieldType,
		CURRENT_TIMESTAMP() modifiedDate,
		'individual' ownerType,
		false repeatable_
	FROM UNNEST(ARRAY<STRUCT<displayName STRING, fieldName STRING, fieldType STRING>>
		[
			('additionalName', 'middleName', 'text'),
			('birthDate', 'birthday', 'date'),
			('email', 'emailAddress', 'text'),
			('familyName', 'lastName', 'text'),
			('givenName', 'firstName', 'text'),
			('jobTitle', 'jobTitle', 'text'),
			('languageId', 'languageId', 'text'),
			('modifiedDate', 'modifiedDate', 'date'),
			('screenName', 'screenName', 'text')
		])
)

SELECT
	*
FROM
	CustomFieldMapping
UNION ALL
SELECT
	*
FROM
	DemographicsFieldMapping