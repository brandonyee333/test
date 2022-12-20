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

import com.google.cloud.bigquery.FieldValueList;

import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.CustomBQIndividualRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.util.FieldValueListUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.SelectConditionStep;
import org.jooq.SelectFinalStep;
import org.jooq.SelectForStep;
import org.jooq.SelectForUpdateStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSeekStep1;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	public List<String>
		findBQIndividualIdsByChannelIdAndLastActivityDateAndSegmentId(
			Long channelId, Date lastActivityDate, Long segmentId) {

		SelectSelectStep<Record1<String>> selectSelectStep = _dslContext.select(
			DSL.field(
				"BQIndividual.id", String.class
			).as(
				"id"
			));

		SelectJoinStep<Record1<String>> selectJoinStep = selectSelectStep.from(
			DSL.table("BQIndividual"));

		selectJoinStep = selectJoinStep.join(
			DSL.table(
				"BQIdentityActivity"
			).as(
				"IdentityActivity"
			)
		).on(
			DSL.field(
				"BQIndividual.id"
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
					"BQIndividual.id"
				).eq(
					DSL.field("Membership.individualId")
				)
			);
		}

		SelectConditionStep<Record1<String>> selectConditionStep =
			selectJoinStep.where(
				DSL.field(
					"IdentityActivity.channelId", Long.class
				).eq(
					channelId
				));

		if (lastActivityDate != null) {
			selectConditionStep = selectConditionStep.and(
				DSL.field(
					"IdentityActivity.lastActivityDate"
				).gt(
					_dslHelper.getDateValue(lastActivityDate)
				));
		}

		if (segmentId != null) {
			selectConditionStep = selectConditionStep.and(
				DSL.field(
					"Membership.segmentId"
				).eq(
					segmentId
				));
		}

		return _queryExecutor.queryForList(
			record -> (String)record.get("id"), selectConditionStep);
	}

	@Override
	public Optional<Individual> findByChannelIdAndId(
		@Nullable Long channelId, String id) {

		SelectJoinStep
			<Record11
				<Long, Object, Object, Object, Object, Object, Object, Object,
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
				<Long, Object, Object, Object, Object, Object, Object, Object,
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

				if (_dslHelper.isBigQueryDialect()) {
					map.put(
						"fields",
						FieldValueListUtil.toJSONArray(
							(List<FieldValueList>)map.get("fields")));
				}

				BigDecimal activitiescount = new BigDecimal(
					String.valueOf(map.get("activitiescount")));

				return new Individual(
					activitiescount.longValue(), new BQIndividual(map),
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
	public List<Distribution> getIndividualDistributions(
		@Nullable Long channelId, String fieldName,
		@Nullable Long individualSegmentId, Pageable pageable) {

		Field<String> nestedField = _dslHelper.getNestedField(
			"name", fieldName, "value", "fields");

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			conditions.add(_getChannelIdCondition(channelId));
		}

		if (individualSegmentId != null) {
			conditions.add(
				_getIndividualSegmentIdCondition(individualSegmentId));
		}

		conditions.add(nestedField.isNotNull());

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		return _queryExecutor.queryForList(
			record -> new Distribution(
				(Integer)record.get("count"),
				Collections.singletonList(record.get("nestedField"))),
			modifiedDateSelectSelectStep.select(
				nestedField.as("nestedField"),
				DSL.count(
				).as(
					"count"
				)
			).from(
				"BQIndividual"
			).where(
				conditions
			).groupBy(
				nestedField.as("nestedField")
			).orderBy(
				getSortFields(
					Collections.singletonMap("name", "values"),
					pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public List<Individual> searchBQIndividuals(
		@Nullable Long accountId, @Nullable Long channelId,
		@Nullable Long dataSourceId, @Nullable Long notSegmentId,
		Pageable pageable, @Nullable String query, @Nullable Long segmentId) {

		SelectJoinStep
			<Record11
				<Long, Object, Object, Object, Object, Object, Object, Object,
				 Object, Object, Object>> selectJoinStep = _getSelectJoinStep(
					segmentId, _getIndividualSelectJoinStep());

		Condition condition = _getQueryCondition(query);

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					"IdentityActivity.channelId"
				).eq(
					channelId
				));
		}

		if (dataSourceId != null) {
			condition = condition.and(
				DSL.field(
					"IdentityActivity.dataSourceId"
				).eq(
					dataSourceId
				));
		}

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
			_sortFieldNameConversionMap, pageable.getSort(), null);

		SelectForUpdateStep
			<Record11
				<Long, Object, Object, Object, Object, Object, Object, Object,
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
				Map<String, Object> map = new HashedMap<>(record);

				if (_dslHelper.isBigQueryDialect()) {
					map.put(
						"fields",
						FieldValueListUtil.toJSONArray(
							(List<FieldValueList>)map.get("fields")));
				}

				BigDecimal activitiesCount = new BigDecimal(
					String.valueOf(map.get("activitiescount")));

				return new Individual(
					activitiesCount.longValue(), new BQIndividual(map),
					(Date)map.get("lastactivitydate"), _objectMapper);
			},
			_getIndividualSelectOnConditionStep(selectFinalStep, sortFields));
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

	private Condition _getIndividualSegmentIdCondition(
		Long individualSegmentId) {

		return DSL.exists(
			DSL.selectOne(
			).from(
				"BQMembership"
			).where(
				DSL.and(
					DSL.field(
						"BQMembership.individualSegmentId"
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
			<Long, Object, Object, Object, Object, Object, Object, Object,
			 Object, Object, Object>> _getIndividualSelectJoinStep() {

		return _dslContext.select(
			DSL.coalesce(
				DSL.cast(
					DSL.sum(
						DSL.field(
							"IdentityActivity.activitiescount", Long.class)),
					Long.class),
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
		if (StringUtils.isEmpty(query)) {
			return DSL.noCondition();
		}

		List<Condition> conditions = new ArrayList<>();

		for (String word : StringUtils.split(query)) {
			List<Condition> wordConditions = new ArrayList<>();

			for (String column : _SEARCH_COLUMNS) {
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

	private <T extends Record> SelectJoinStep<T> _getSelectJoinStep(
		Long segmentId, SelectSelectStep<T> selectSelectStep) {

		SelectJoinStep<T> selectJoinStep = selectSelectStep.from(
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

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private Environment _environment;

	private final List<Field<Object>> _identiyChannelFields = Arrays.asList(
		DSL.field("activitiesCount"), DSL.field("channelId"),
		DSL.field("createDate"), DSL.field("id"), DSL.field("identityId"),
		DSL.field("individualId"), DSL.field("lastActivityDate"),
		DSL.field("modifiedDate"));

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private QueryExecutor _queryExecutor;

	private final Map<String, String> _sortFieldNameConversionMap =
		new HashMap<String, String>() {
			{
				put("demographics/familyName/value", "lastname");
				put("demographics/givenName/value", "firstname");
			}
		};

}