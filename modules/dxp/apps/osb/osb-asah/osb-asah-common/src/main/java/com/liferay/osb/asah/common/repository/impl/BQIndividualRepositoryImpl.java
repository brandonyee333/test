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

import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.CustomBQIndividualRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
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
			channelId, segmentId,
			_dslContext.select(DSL.countDistinct(DSL.field("individual.id"))));

		Condition condition = _getQueryCondition(query);

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					"identityActivity.channelId"
				).eq(
					channelId
				));
		}

		if (segmentId != null) {
			condition = condition.and(
				DSL.field(
					"membership.segmentId"
				).eq(
					segmentId
				));
		}

		return _queryExecutor.queryForLong(selectJoinStep.where(condition));
	}

	@Override
	public Optional<Individual> findByChannelIdAndId(
		@Nullable Long channelId, String id) {

		SelectJoinStep
			<Record12
				<Long, Object, Object, Object, Object, Object, Object, Object,
				 Object, Object, Object, Object>> selectJoinStep =
					_getSelectJoinStep(
						channelId, null,
						_dslContext.select(
							DSL.coalesce(
								DSL.cast(
									DSL.sum(
										DSL.field(
											"identityChannel.activitiescount",
											Long.class)),
									Long.class)
							).as(
								"activitiescount"
							),
							DSL.field(
								"individual.createdate"
							).as(
								"createdate"
							),
							DSL.field(
								"individual.emailaddress"
							).as(
								"emailaddress"
							),
							DSL.field(
								"individual.id"
							).as(
								"id"
							),
							DSL.field("fields"), DSL.field("firstname"),
							DSL.max(
								DSL.field("identityChannel.lastactivitydate")
							).as(
								"lastactivitydate"
							),
							DSL.field("lastname"), DSL.field("jobtitle"),
							DSL.field("middlename"),
							DSL.field(
								"individual.modifieddate"
							).as(
								"modifieddate"
							),
							DSL.field("screenname")));

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"individual.id"
			).eq(
				id
			));

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"identityActivity.channelid"
				).eq(
					channelId
				));
		}

		return _queryExecutor.queryForObject(
			record -> new Individual(
				(Long)record.get("activitiescount"), new BQIndividual(record),
				(Date)record.get("lastactivitydate"), _objectMapper),
			(SelectJoinStep)selectJoinStep.where(
				conditions
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			));
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
					_getSortFieldNameConversionMap(), pageable.getSort(), null)
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
			<Record12
				<Long, Object, Object, Object, Object, Object, Object, Object,
				 Object, Object, Object, Object>> selectJoinStep =
					_getSelectJoinStep(
						channelId, segmentId,
						_dslContext.select(
							DSL.coalesce(
								DSL.cast(
									DSL.sum(
										DSL.field(
											"identityChannel.activitiescount",
											Long.class)),
									Long.class)
							).as(
								"activitiescount"
							),
							DSL.field(
								"individual.createdate"
							).as(
								"createdate"
							),
							DSL.field(
								"individual.emailaddress"
							).as(
								"emailaddress"
							),
							DSL.field(
								"individual.id"
							).as(
								"id"
							),
							DSL.field("fields"), DSL.field("firstname"),
							DSL.max(
								DSL.field("identityChannel.lastactivitydate")
							).as(
								"lastactivitydate"
							),
							DSL.field("lastname"), DSL.field("jobtitle"),
							DSL.field("middlename"),
							DSL.field(
								"individual.modifieddate"
							).as(
								"modifieddate"
							),
							DSL.field("screenname")));

		Condition condition = _getQueryCondition(query);

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					"identityActivity.channelId"
				).eq(
					channelId
				));
		}

		if (dataSourceId != null) {
			condition = condition.and(
				DSL.field(
					"identityActivity.dataSourceId"
				).eq(
					dataSourceId
				));
		}

		if (notSegmentId != null) {
			condition = condition.and(
				DSL.field(
					"membership.segmentId"
				).notEqual(
					notSegmentId
				));
		}

		if (segmentId != null) {
			condition = condition.and(
				DSL.field(
					"membership.segmentId"
				).eq(
					segmentId
				));
		}

		return _queryExecutor.queryForList(
			record -> new Individual(
				(Long)record.get("activitiescount"), new BQIndividual(record),
				(Date)record.get("lastactivitydate"), _objectMapper),
			selectJoinStep.where(
				condition
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
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
		Long channelId, Long segmentId, SelectSelectStep<T> selectSelectStep) {

		SelectJoinStep<T> selectJoinStep = selectSelectStep.from(
			DSL.table(
				"BQIndividual"
			).as(
				"individual"
			)
		).leftJoin(
			DSL.table(
				"BQIdentityChannel"
			).as(
				"identityChannel"
			)
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("identityChannel.individualId")
			)
		);

		if (channelId != null) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIdentityActivity"
				).as(
					"identityActivity"
				)
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("identityActivity.individualId")
				)
			);
		}

		if (segmentId != null) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQMembership"
				).as(
					"membership"
				)
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("membership.individualId")
				)
			);
		}

		return selectJoinStep;
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return Collections.singletonMap("name", "values");
	}

	private static final String[] _SEARCH_COLUMNS = {
		"emailAddress", "firstName", "jobTitle", "lastName", "middleName"
	};

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private QueryExecutor _queryExecutor;

}