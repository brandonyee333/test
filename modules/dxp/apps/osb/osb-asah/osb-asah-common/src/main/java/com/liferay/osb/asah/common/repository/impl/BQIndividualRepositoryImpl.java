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

import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.CustomBQIndividualRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
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
		@Nullable Long channelId, FilterHelper filterHelper,
		@Nullable Long segmentChannelId, @Nullable Long segmentId) {

		SelectJoinStep<?> selectJoinStep = _getSelectJoinStep(
			channelId, segmentChannelId, segmentId,
			_dslContext.select(
				DSL.countDistinct(DSL.field("individual.emailAddressHashed"))));

		Condition condition = filterHelper.getCondition();

		if (segmentChannelId != null) {
			condition = condition.and(
				DSL.field(
					"identityActivity.channelId"
				).eq(
					segmentChannelId
				));
		}
		else if (channelId != null) {
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

		return selectJoinStep.where(
			condition
		).groupBy(
			DSL.field("individual.emailAddressHashed")
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
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
	public List<BQIndividual> searchBQIndividuals(
		Long channelId, FilterHelper filterHelper, Long segmentChannelId,
		Long segmentId, Pageable pageable) {

		SelectJoinStep<?> selectJoinStep = _getSelectJoinStep(
			channelId, segmentChannelId, segmentId,
			_dslContext.select(
				DSL.coalesce(
					DSL.cast(
						DSL.sum(
							DSL.field(
								"identityChannel.activitiescount", Long.class)),
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
					"individual.emailaddresshashed"
				).as(
					"emailaddresshashed"
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

		Condition condition = filterHelper.getCondition();

		if (segmentChannelId != null) {
			condition = condition.and(
				DSL.field(
					"identityActivity.channelId"
				).eq(
					segmentChannelId
				));
		}
		else if (channelId != null) {
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

		return _populateBQIndividuals(
			selectJoinStep.where(
				condition
			).groupBy(
				DSL.field("individual.emailAddressHashed")
			).orderBy(
				DSL.field(
					"individual.emailAddressHashed"
				).asc()
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
				record -> new BQIndividual(record.intoMap())
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

	private SelectJoinStep<?> _getSelectJoinStep(
		Long channelId, Long segmentChannelId, Long segmentId,
		SelectSelectStep<?> selectSelectStep) {

		SelectJoinStep<?> selectJoinStep = selectSelectStep.from(
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
				"individual.emailAddressHashed"
			).eq(
				DSL.field("identityChannel.emailAddressHashed")
			)
		);

		if ((channelId != null) || (segmentChannelId != null)) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIdentityActivity"
				).as(
					"identityActivity"
				)
			).on(
				DSL.field(
					"individual.emailAddressHashed"
				).eq(
					DSL.field("identityActivity.emailAddressHashed")
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
					"individual.emailAddressHashed"
				).eq(
					DSL.field("membership.emailAddressHashed")
				)
			);
		}

		return selectJoinStep;
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return Collections.singletonMap("name", "values");
	}

	private List<BQIndividual> _populateBQIndividuals(
		List<BQIndividual> bqIndividuals) {

		if (bqIndividuals.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<BQIndividual> stream = bqIndividuals.stream();

		Map<String, BQIndividual> bqIndividualsById = stream.collect(
			Collectors.toMap(
				BQIndividual::getId, Function.identity(),
				(id, individual) -> id, LinkedHashMap::new));

		return new ArrayList<>(bqIndividualsById.values());
	}

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private QueryExecutor _queryExecutor;

}