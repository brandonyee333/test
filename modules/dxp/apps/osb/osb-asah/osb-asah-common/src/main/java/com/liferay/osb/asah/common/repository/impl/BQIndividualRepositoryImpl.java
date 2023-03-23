/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.BQFieldMapping;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.filter.expression.FilterExpression;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQFieldMappingRepository;
import com.liferay.osb.asah.common.repository.CustomBQIndividualRepository;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.BQSQLUtil;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Record4;
import org.jooq.SelectConditionStep;
import org.jooq.SelectFinalStep;
import org.jooq.SelectForStep;
import org.jooq.SelectForUpdateStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnStep;
import org.jooq.SelectSeekStep1;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Robson Pastor
 */
public class BQIndividualRepositoryImpl
	extends BaseRepository implements CustomBQIndividualRepository {

	public BQIndividualRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countBQIndividuals(
		@Nullable Long accountId, @Nullable Long channelId,
		@Nullable Long datasourceId, @Nullable Long notSegmentId, String query,
		@Nullable Long segmentId) {

		SelectJoinStep<Record1<Integer>> selectJoinStep = _getSelectJoinStep(
			segmentId,
			_dslContext.select(DSL.countDistinct(DSL.field("individual.id"))));

		Condition condition = _getQueryCondition(query);

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					"IdentityActivity.channelId"
				).eq(
					channelId
				));
		}

		if (segmentId != null) {
			condition = condition.and(
				DSL.field(
					"Membership.segmentId"
				).eq(
					segmentId
				));
		}

		return _queryExecutor.queryForLong(selectJoinStep.where(condition));
	}

	@Override
	public long countBQIndividuals(
		@Nullable Long channelId, @Nullable String filterString,
		@Nullable Boolean includeAnonymousUsers, @Nullable String query) {

		FilterExpression filterExpression = new FilterExpression(
			filterString, true);

		Set<String> referencedTableNames = new HashSet<>();

		if (!StringUtils.isBlank(filterString)) {
			referencedTableNames = new HashSet<>(
				filterExpression.getReferencedTableNames());
		}

		if (StringUtils.isNotBlank(filterString) ||
			StringUtils.isNotBlank(query)) {

			referencedTableNames.add("Individual");
		}

		Field<String> selectField = null;

		if (BooleanUtils.isTrue(includeAnonymousUsers)) {
			selectField = DSL.field("Identity.id", String.class);
		}
		else {
			selectField = DSL.field("Identity.individualId", String.class);
		}

		SelectJoinStep<Record1<Integer>> selectJoinStep = _dslContext.select(
			DSL.countDistinct(selectField)
		).from(
			DSL.table(
				"BQIdentity"
			).as(
				"Identity"
			)
		);

		selectJoinStep = _getSelectJoinStep(
			channelId, includeAnonymousUsers, referencedTableNames,
			selectJoinStep);

		return _queryExecutor.queryForLong(
			selectJoinStep.where(
				_getConditions(channelId, filterExpression, query)));
	}

	@Override
	public long countBQIndividualsModifiedLast30Days(Long channelId) {
		LocalDateTime localDateTime = LocalDateTime.now(
			_timeZoneDog.getZoneId());

		localDateTime = localDateTime.minusDays(30);

		return _queryExecutor.queryForLong(
			_dslContext.select(
				DSL.countDistinct(DSL.field("Individual.id"))
			).from(
				DSL.table(
					"BQIdentityActivity"
				).as(
					"IdentityActivity"
				)
			).join(
				DSL.table(
					"BQIndividual"
				).as(
					"Individual"
				)
			).on(
				DSL.field(
					"IdentityActivity.individualId"
				).eq(
					DSL.field("Individual.id")
				)
			).where(
				DSL.and(
					DSL.field(
						"IdentityActivity.channelId", Long.class
					).eq(
						channelId
					)),
				DSL.field(
					"Individual.modifiedDate"
				).greaterOrEqual(
					DSL.field(
						"TIMESTAMP '" + DateUtil.toUTCString(localDateTime) +
							"'")
				)
			));
	}

	public long countIndividualFieldValuesCustom(
		@Nullable Long channelId, String fieldName,
		@Nullable String filterString) {

		Optional<BQFieldMapping> bqFieldMappingOptional =
			_bqFieldMappingRepository.findByDisplayName(fieldName);

		if (!bqFieldMappingOptional.isPresent()) {
			return 0;
		}

		BQFieldMapping bqFieldMapping = bqFieldMappingOptional.get();

		if (bqFieldMapping.getRepeatable()) {
			return _countIndividualFieldValuesCustomRepeatable(
				channelId, fieldName, filterString);
		}

		return _countIndividualFieldValuesCustom(
			channelId, fieldName, filterString);
	}

	public long countIndividualFieldValuesDemographics(
		@Nullable Long channelId, String fieldName,
		@Nullable String filterString) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(
				DSL.countDistinct(
					DSL.field(
						_fieldNameConversionMap.getOrDefault(
							fieldName, fieldName))));

		SelectJoinStep<Record1<Integer>> selectJoinStep;

		if (channelId != null) {
			selectJoinStep = _getSelectJoinStep(null, selectSelectStep);
		}
		else {
			selectJoinStep = selectSelectStep.from("BQIndividual");
		}

		Condition condition;

		if (StringUtils.isNotBlank(filterString)) {
			FilterExpression filterExpression = new FilterExpression(
				filterString);

			condition = filterExpression.getCondition();
		}
		else {
			condition = DSL.noCondition();
		}

		return _queryExecutor.queryForLong(selectJoinStep.where(condition));
	}

	@Override
	public void deleteAll() {
		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQIndividual")
			).where(
				DSL.trueCondition()
			));
	}

	@Override
	public Optional<Individual> findByChannelIdAndId(
		@Nullable Long channelId, String id) {

		SelectJoinStep
			<Record11
				<Object, Object, Object, Object, Object, Object, Object, Object,
				 Object, Object, Object>> selectJoinStep1 = _getSelectJoinStep(
					null, _getIndividualSelectJoinStep());

		List<Condition> conditions = new ArrayList<>();

		Field<Object> individualIdField = DSL.field("individual.id");

		conditions.add(individualIdField.eq(id));

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"IdentityActivity.channelId"
				).eq(
					channelId
				));
		}

		SelectSeekStep1
			<Record11
				<Object, Object, Object, Object, Object, Object, Object, Object,
				 Object, Object, Object>,
			 Object> selectSeekStep1 = selectJoinStep1.where(
				conditions
			).groupBy(
				individualIdField, DSL.field("individual.createdate"),
				DSL.field("individual.emailaddress"),
				DSL.field("individual.firstname"),
				DSL.field("individual.lastname"),
				DSL.field("individual.jobtitle"),
				DSL.field("individual.middlename"),
				DSL.field("individual.modifieddate"),
				DSL.field("individual.screenname")
			).orderBy(
				individualIdField.asc()
			);

		return _queryExecutor.queryForObject(
			record -> {
				Map<String, Object> map = new HashedMap<>(record);

				BigDecimal activitiesCount = new BigDecimal(
					String.valueOf(map.get("activitiescount")));

				return new Individual(
					activitiesCount.longValue(), new BQIndividual(map),
					(Date)map.get("lastactivitydate"), _objectMapper);
			},
			(SelectJoinStep)_getIndividualSelectOnConditionStep(
				selectSeekStep1,
				Collections.singletonList(
					DSL.field(
						"individuals.id"
					).asc())));
	}

	@Override
	public Optional<BQIndividual> findByEmailAddress(String emailAddresses) {
		return _queryExecutor.queryForObject(
			BQIndividual::new,
			_dslContext.select(
			).from(
				DSL.table("BQIndividual")
			).where(
				DSL.field(
					"emailAddress"
				).eq(
					emailAddresses
				)
			));
	}

	@Override
	public List<Distribution> getIndividualDistributions(
		@Nullable Long channelId, String fieldName, String fieldType,
		@Nullable Long individualSegmentId, Pageable pageable) {

		Field groupByField = DSL.field("individualFields.value");

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			conditions.add(_getChannelIdCondition(channelId));
		}

		if (individualSegmentId != null) {
			conditions.add(
				_getIndividualSegmentIdCondition(individualSegmentId));
		}

		conditions.add(groupByField.isNotNull());
		conditions.add(groupByField.notEqual(""));
		conditions.add(
			DSL.field(
				"individualFields.name"
			).eq(
				fieldName
			));

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		return _queryExecutor.queryForList(
			record -> {
				BigDecimal count = new BigDecimal(
					String.valueOf(record.get("count")));

				Object groupByFieldValue = record.get("groupByField");

				if (fieldType.equalsIgnoreCase("date")) {
					Date date = new Date(
						Long.parseLong((String)groupByFieldValue));

					groupByFieldValue = DateUtil.toUTCString(date);
				}

				return new Distribution(
					count.intValue(),
					Collections.singletonList(groupByFieldValue));
			},
			modifiedDateSelectSelectStep.select(
				groupByField.as("groupByField"),
				DSL.count(
				).as(
					"count"
				)
			).from(
				"BQIndividual"
			).crossJoin(
				DSL.table("UNNEST(fields) AS individualFields")
			).where(
				conditions
			).groupBy(
				groupByField
			).orderBy(
				getSortFields(
					Collections.singletonMap("name", "individualFields.value"),
					pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public BQIndividual insert(BQIndividual bqIndividual) {
		_queryExecutor.queryExecute(
			BQSQLUtil.createInsertStatement(bqIndividual));

		return bqIndividual;
	}

	@Override
	public List<Individual> searchBQIndividuals(
		@Nullable Long accountId, @Nullable Long channelId,
		@Nullable Long dataSourceId, @Nullable Long notSegmentId,
		Pageable pageable, @Nullable String query, @Nullable Long segmentId) {

		SelectJoinStep
			<Record11
				<Object, Object, Object, Object, Object, Object, Object, Object,
				 Object, Object, Object>> selectJoinStep = _getSelectJoinStep(
					segmentId, _getIndividualSelectJoinStep());

		Condition condition = _getQueryCondition(query);

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					"IdentityActivity.channelId", Long.class
				).eq(
					channelId
				));
		}

		if (dataSourceId != null) {
			condition = condition.and(
				DSL.field(
					"IdentityActivity.dataSourceId", Long.class
				).eq(
					dataSourceId
				));
		}

		condition = condition.and(
			DSL.field(
				"IdentityActivity.eventId"
			).in(
				_eventDefinitionRepository.getEventDefinitionNames(false)
			));

		if (notSegmentId != null) {
			condition = condition.and(
				DSL.field(
					"Individual.id"
				).notIn(
					DSL.select(
						DSL.field("notMembership.individualId")
					).from(
						DSL.table(
							"BQMembership"
						).as(
							"notMembership"
						)
					).where(
						DSL.field(
							"notMembership.segmentId"
						).eq(
							notSegmentId
						)
					)
				));
		}

		if (segmentId != null) {
			condition = condition.and(
				DSL.field(
					"Membership.segmentId"
				).eq(
					segmentId
				));
		}

		Collection<SortField<?>> sortFields = getSortFields(
			_fieldNameConversionMap, pageable.getSort(), null);

		SelectForUpdateStep
			<Record11
				<Object, Object, Object, Object, Object, Object, Object, Object,
				 Object, Object, Object>> selectFinalStep =
					selectJoinStep.where(
						condition
					).groupBy(
						DSL.field("Individual.id"),
						DSL.field("Individual.createdate"),
						DSL.field("Individual.emailaddress"),
						DSL.field("Individual.firstname"),
						DSL.field("Individual.lastname"),
						DSL.field("Individual.jobtitle"),
						DSL.field("Individual.middlename"),
						DSL.field("Individual.modifieddate"),
						DSL.field("Individual.screenname")
					).orderBy(
						sortFields
					).limit(
						pageable.getPageSize()
					).offset(
						pageable.getOffset()
					);

		return _queryExecutor.queryForList(
			record -> {
				BigDecimal activitiesCount = new BigDecimal(
					String.valueOf(record.get("activitiescount")));

				return new Individual(
					activitiesCount.longValue(), new BQIndividual(record),
					(Date)record.get("lastactivitydate"), _objectMapper);
			},
			_getIndividualSelectOnConditionStep(selectFinalStep, sortFields));
	}

	@Override
	public List<Individual> searchBQIndividuals(
		@Nullable Long channelId, String filterString, Pageable pageable,
		@Nullable String query) {

		FilterExpression filterExpression = new FilterExpression(
			filterString, true);

		Set<String> referencedTableNames = new HashSet<>(
			filterExpression.getReferencedTableNames());

		referencedTableNames.add("Individual");

		Field<String> emailAddressField = DSL.field(
			"Individual.emailAddress", String.class);
		Field<String> firstNameField = DSL.field(
			"Individual.firstName", String.class);
		Field<String> idField = DSL.field("Individual.id", String.class);
		Field<String> lastNameField = DSL.field(
			"Individual.lastName", String.class);

		SelectJoinStep<Record4<String, String, String, String>> selectJoinStep =
			_dslContext.select(
				emailAddressField, firstNameField, idField, lastNameField
			).from(
				DSL.table(
					"BQIdentity"
				).as(
					"Identity"
				)
			);

		selectJoinStep = _getSelectJoinStep(
			channelId, null, referencedTableNames, selectJoinStep);

		return _queryExecutor.queryForList(
			record -> {
				BQIndividual bqIndividual = new BQIndividual(record);

				bqIndividual.setFields(
					Arrays.asList(
						new BQIndividual.Field(
							0L, "emailAddress",
							(String)record.get("emailAddress")),
						new BQIndividual.Field(
							0L, "firstName", (String)record.get("firstName")),
						new BQIndividual.Field(
							0L, "lastName", (String)record.get("lastName"))));

				return new Individual(0L, bqIndividual, null, _objectMapper);
			},
			selectJoinStep.where(
				_getConditions(channelId, filterExpression, query)
			).groupBy(
				idField, emailAddressField, firstNameField, lastNameField
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	public List<Long> searchIndividualDataSourceIds(String id) {
		return _queryExecutor.queryForList(
			recordMap -> {
				BigDecimal dataSourceIdBigDecimal = (BigDecimal)recordMap.get(
					"dataSourceId");

				return dataSourceIdBigDecimal.longValue();
			},
			_dslContext.selectDistinct(
				DSL.field("dataSourceId")
			).from(
				"BQIdentityActivity"
			).where(
				DSL.field(
					"individualId"
				).eq(
					id
				)
			));
	}

	public List<String> searchIndividualFieldValuesCustom(
		@Nullable Long channelId, String fieldName,
		@Nullable String filterString, Pageable pageable) {

		Optional<BQFieldMapping> bqFieldMappingOptional =
			_bqFieldMappingRepository.findByDisplayName(fieldName);

		if (!bqFieldMappingOptional.isPresent()) {
			return Collections.emptyList();
		}

		BQFieldMapping bqFieldMapping = bqFieldMappingOptional.get();

		if (bqFieldMapping.getRepeatable()) {
			return _getIndividualFieldValuesCustomRepeatable(
				channelId, fieldName, filterString, pageable);
		}

		return _getIndividualFieldValuesCustom(
			channelId, fieldName, filterString, pageable);
	}

	public List<String> searchIndividualFieldValuesDemographics(
		@Nullable Long channelId, String fieldName,
		@Nullable String filterString, Pageable pageable) {

		SelectSelectStep<Record1<String>> selectSelectStep =
			_dslContext.selectDistinct(
				DSL.field(
					_fieldNameConversionMap.getOrDefault(fieldName, fieldName),
					String.class
				).as(
					"fieldValue"
				));

		SelectJoinStep<Record1<String>> selectJoinStep;

		if (channelId != null) {
			selectJoinStep = _getSelectJoinStep(null, selectSelectStep);
		}
		else {
			selectJoinStep = selectSelectStep.from("BQIndividual");
		}

		Condition condition;

		if (StringUtils.isNotBlank(filterString)) {
			FilterExpression filterExpression = new FilterExpression(
				filterString);

			condition = filterExpression.getCondition();
		}
		else {
			condition = DSL.noCondition();
		}

		return _queryExecutor.queryForList(
			recordMap -> String.valueOf(recordMap.get("fieldValue")),
			selectJoinStep.where(
				condition
			).orderBy(
				DSL.field("fieldValue")
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private long _countIndividualFieldValuesCustom(
		Long channelId, String fieldName, String filterString) {

		return _queryExecutor.queryForLong(
			_getIndividualFieldsSelectConditionStep(
				channelId, fieldName, filterString,
				_dslContext.select(
					DSL.countDistinct(
						DSL.field("individualFields.value")
					).as(
						"totalElements"
					))));
	}

	private long _countIndividualFieldValuesCustomRepeatable(
		Long channelId, String fieldName, String filterString) {

		return _queryExecutor.queryForLong(
			_getIndividualFieldValuesCustomRepeatableSelectJoinStep(
				channelId, fieldName, filterString,
				_dslContext.select(
					DSL.countDistinct(
						DSL.field(
							"JSON_EXTRACT_SCALAR(fieldValueItem, '$')",
							String.class)
					).as(
						"totalElements"
					))));
	}

	private Condition _getChannelIdCondition(Long channelId) {
		return DSL.exists(
			DSL.selectOne(
			).from(
				"BQIdentityActivity"
			).where(
				DSL.and(
					DSL.field(
						"BQIdentityActivity.channelId"
					).eq(
						channelId
					),
					DSL.field(
						"BQIdentityActivity.individualId"
					).eq(
						DSL.field("BQIndividual.id")
					))
			));
	}

	private List<Condition> _getConditions(
		Long channelId, FilterExpression filterExpression, String query) {

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"IdentityActivity.channelId", Long.class
				).eq(
					channelId
				));
		}

		conditions.add(
			_getQueryCondition(
				query, new String[] {"emailAddress", "firstName", "lastName"}));
		conditions.add(filterExpression.getCondition());

		return conditions;
	}

	private <T extends Record> SelectConditionStep<T>
		_getIndividualFieldsSelectConditionStep(
			Long channelId, String fieldName, String filterString,
			SelectSelectStep<T> selectSelectStep) {

		SelectJoinStep<T> selectJoinStep;

		if (channelId != null) {
			selectJoinStep = _getSelectJoinStep(null, selectSelectStep);
		}
		else {
			selectJoinStep = selectSelectStep.from("BQIndividual");
		}

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"BQFieldMapping.context"
			).eq(
				DSL.val("custom")
			));
		conditions.add(
			DSL.field(
				"individualFields.name"
			).eq(
				fieldName
			));
		conditions.add(ConditionUtil.toCondition(filterString));

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"BQIdentityActivity.channelId"
				).eq(
					channelId
				));
		}

		return selectJoinStep.crossJoin(
			DSL.table(
				"UNNEST(fields)"
			).as(
				"individualFields"
			)
		).join(
			"BQFieldMapping"
		).on(
			DSL.field(
				"BQFieldMapping.displayName"
			).eq(
				DSL.field("individualFields.name")
			)
		).where(
			conditions
		);
	}

	private List<String> _getIndividualFieldValuesCustom(
		Long channelId, String fieldName, String filterString,
		Pageable pageable) {

		SelectSelectStep<Record1<String>> selectSelectStep =
			_dslContext.selectDistinct(
				DSL.field(
					"individualFields.value", String.class
				).as(
					"individualFieldValue"
				));

		SelectConditionStep<Record1<String>> selectConditionStep =
			_getIndividualFieldsSelectConditionStep(
				channelId, fieldName, filterString, selectSelectStep);

		return _queryExecutor.queryForList(
			recordMap -> String.valueOf(recordMap.get("individualFieldValue")),
			selectConditionStep.limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private List<String> _getIndividualFieldValuesCustomRepeatable(
		Long channelId, String fieldName, String filterString,
		Pageable pageable) {

		SelectJoinStep selectJoinStep =
			_getIndividualFieldValuesCustomRepeatableSelectJoinStep(
				channelId, fieldName, filterString,
				_dslContext.selectDistinct(
					DSL.field(
						"JSON_EXTRACT_SCALAR(fieldValueItem, '$')"
					).as(
						"individualFieldValue"
					)));

		return _queryExecutor.queryForList(
			recordMap -> String.valueOf(recordMap.get("individualFieldValue")),
			selectJoinStep.limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private SelectJoinStep
		_getIndividualFieldValuesCustomRepeatableSelectJoinStep(
			Long channelId, String fieldName, String filterString,
			SelectSelectStep selectSelectStep) {

		SelectSelectStep jsonExtractSelectSelectStep = _dslContext.select(
			DSL.field(
				"JSON_EXTRACT_ARRAY(individualFields.value)"
			).as(
				"fieldValueArray"
			));

		SelectConditionStep selectConditionStep =
			_getIndividualFieldsSelectConditionStep(
				channelId, fieldName, filterString,
				jsonExtractSelectSelectStep);

		return selectSelectStep.from(
			selectConditionStep.asTable("individualFieldsValues")
		).crossJoin(
			DSL.table(
				"UNNEST(fieldValueArray)"
			).as(
				"fieldValueItem"
			)
		);
	}

	private Condition _getIndividualSegmentIdCondition(
		Long individualSegmentId) {

		return DSL.exists(
			DSL.selectOne(
			).from(
				"BQMembership"
			).where(
				DSL.and(
					DSL.field(
						"BQMembership.segmentId"
					).eq(
						individualSegmentId
					),
					DSL.field(
						"BQMembership.individualId"
					).eq(
						DSL.field("BQIndividual.id")
					))
			));
	}

	private SelectSelectStep
		<Record11
			<Object, Object, Object, Object, Object, Object, Object, Object,
			 Object, Object, Object>> _getIndividualSelectJoinStep() {

		return _dslContext.select(
			DSL.coalesce(
				DSL.field(
					"SAFE_CAST({0} as INT64)",
					DSL.sum(
						DSL.field(
							"IdentityActivity.activitiescount", Long.class))),
				0L
			).as(
				"activitiescount"
			),
			DSL.field(
				"Individual.createdate"
			).as(
				"createdate"
			),
			DSL.field(
				"Individual.emailaddress"
			).as(
				"emailaddress"
			),
			DSL.field(
				"Individual.id"
			).as(
				"id"
			),
			DSL.field("firstname"),
			DSL.max(
				DSL.field("IdentityActivity.lastactivitydate")
			).as(
				"lastactivitydate"
			),
			DSL.field("lastname"), DSL.field("jobtitle"),
			DSL.field("middlename"),
			DSL.field(
				"Individual.modifieddate"
			).as(
				"modifieddate"
			),
			DSL.field("screenname"));
	}

	private SelectFinalStep<?> _getIndividualSelectOnConditionStep(
		SelectForStep<?> selectSeekStep, Collection<SortField<?>> sortFields) {

		return _dslContext.with(
			"individuals"
		).as(
			selectSeekStep
		).select(
			DSL.field("activitiescount"),
			DSL.field(
				"individuals.createdate"
			).as(
				"createdate"
			),
			DSL.field(
				"individuals.emailaddress"
			).as(
				"emailaddress"
			),
			DSL.field(
				"individuals.id"
			).as(
				"id"
			),
			DSL.field(
				"individuals.firstname"
			).as(
				"firstname"
			),
			DSL.field(
				"individuals.lastactivitydate"
			).as(
				"lastactivitydate"
			),
			DSL.field(
				"individuals.lastname"
			).as(
				"lastname"
			),
			DSL.field(
				"individuals.jobtitle"
			).as(
				"jobtitle"
			),
			DSL.field(
				"individuals.middlename"
			).as(
				"middlename"
			),
			DSL.field(
				"individuals.modifieddate"
			).as(
				"modifieddate"
			),
			DSL.field(
				"individuals.screenname"
			).as(
				"screenname"
			),
			DSL.field(
				"BQIndividual.fields"
			).as(
				"fields"
			)
		).from(
			"individuals"
		).innerJoin(
			"BQIndividual"
		).on(
			DSL.field(
				"BQIndividual.id"
			).eq(
				DSL.field("individuals.id")
			)
		).orderBy(
			sortFields
		);
	}

	private Condition _getQueryCondition(String query) {
		return _getQueryCondition(query, _SEARCH_COLUMNS);
	}

	private Condition _getQueryCondition(String query, String[] searchColumns) {
		if (StringUtils.isEmpty(query)) {
			return DSL.noCondition();
		}

		List<Condition> conditions = new ArrayList<>();

		for (String word : StringUtils.split(query)) {
			List<Condition> wordConditions = new ArrayList<>();

			for (String column : searchColumns) {
				wordConditions.add(
					DSL.lower(
						DSL.field(column, String.class)
					).like(
						DSL.lower(StringUtils.wrap(word, "%"))
					));
			}

			conditions.add(DSL.or(wordConditions));
		}

		return DSL.and(conditions);
	}

	private <R extends Record> SelectJoinStep<R> _getSelectJoinStep(
		Long channelId, Boolean includeAnonymousUsers,
		Set<String> referencedTableNames, SelectJoinStep<R> selectJoinStep) {

		if (channelId != null) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIdentityActivity"
				).as(
					"IdentityActivity"
				)
			).on(
				DSL.field(
					"IdentityActivity.identityId"
				).eq(
					DSL.field("Identity.id")
				)
			);
		}

		if (referencedTableNames.contains("Event")) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQEvent"
				).as(
					"Event"
				)
			).on(
				DSL.field(
					"Event.userId"
				).eq(
					DSL.field("Identity.id")
				)
			);
		}

		if (referencedTableNames.contains("Individual")) {
			SelectOnStep selectOnStep = null;

			if (BooleanUtils.isTrue(includeAnonymousUsers)) {
				selectOnStep = selectJoinStep.leftJoin(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					));
			}
			else {
				selectOnStep = selectJoinStep.join(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					));
			}

			selectJoinStep = selectOnStep.on(
				DSL.field(
					"Identity.individualId"
				).eq(
					DSL.field("Individual.id")
				));

			if (referencedTableNames.contains("ExpandoValue")) {
				selectJoinStep = selectJoinStep.crossJoin(
					DSL.unnest(
						DSL.field("Individual.fields")
					).as(
						"Fields"
					));
			}
		}

		if (referencedTableNames.contains("Session")) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQSession"
				).as(
					"Session"
				)
			).on(
				DSL.field(
					"Identity.id"
				).eq(
					DSL.field("Session.userId")
				)
			);
		}

		return selectJoinStep;
	}

	private <R extends Record> SelectJoinStep<R> _getSelectJoinStep(
		Long segmentId, SelectSelectStep<R> selectSelectStep) {

		SelectJoinStep<R> selectJoinStep = selectSelectStep.from(
			DSL.table(
				"BQIndividual"
			).as(
				"Individual"
			)
		).leftJoin(
			DSL.table(
				"BQIdentityActivity"
			).as(
				"IdentityActivity"
			)
		).on(
			DSL.field(
				"Individual.id"
			).eq(
				DSL.field("IdentityActivity.individualId")
			)
		);

		if (segmentId != null) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQMembership"
				).as(
					"Membership"
				)
			).on(
				DSL.field(
					"Individual.id"
				).eq(
					DSL.field("Membership.individualId")
				)
			);
		}

		return selectJoinStep;
	}

	private static final String[] _SEARCH_COLUMNS = {
		"emailAddress", "firstName", "jobTitle", "lastName", "middleName"
	};

	@Autowired
	private BQFieldMappingRepository _bqFieldMappingRepository;

	private final DSLContext _dslContext;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	private final Map<String, String> _fieldNameConversionMap =
		new HashMap<String, String>() {
			{
				put("additionalName", "middlename");
				put("address", "addresses");
				put("birthdate", "birthday");
				put("demographics/familyName/value", "lastname");
				put("demographics/givenName/value", "firstname");
				put("familyName", "lastname");
				put("givenName", "firstname");
			}
		};

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private QueryExecutor _queryExecutor;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}