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

import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.CustomIndividualRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectOrderByStep;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.UpdateSetFirstStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class IndividualRepositoryImpl
	extends BaseRepository implements CustomIndividualRepository {

	public IndividualRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long
		countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
			Long channelId, @Nullable Date endLastActivityDate,
			@Nullable Date endPreviousActivityDate, List<Long> segmentIds,
			@Nullable Date startLastActivityDate,
			@Nullable Date startPreviousActivityDate) {

		SelectConditionStep<Record1<Integer>> query = _dslContext.select(
			DSL.count()
		).from(
			"individual"
		).innerJoin(
			"individualchannel"
		).on(
			DSL.field(
				"individualchannel.individualid"
			).eq(
				DSL.field("individual.id")
			)
		).where(
			DSL.field(
				DSL.cast(
					DSL.array(DSL.field("individual.channelids")), Long[].class)
			).contains(
				DSL.cast(DSL.array(channelId), Long[].class)
			)
		).and(
			DSL.field(
				DSL.cast(
					DSL.array(DSL.field("individual.segmentids")), Long[].class)
			).contains(
				DSL.cast(DSL.array(segmentIds.toArray()), Long[].class)
			)
		).and(
			DSL.field(
				"individualchannel.channelId"
			).eq(
				channelId
			)
		);

		Condition lastActivityDateCondition = null;

		if (!Objects.isNull(endLastActivityDate) &&
			!Objects.isNull(startLastActivityDate)) {

			lastActivityDateCondition = DSL.field(
				"individualchannel.lastactivitydate"
			).between(
				startLastActivityDate, endLastActivityDate
			);
		}

		Condition previousActivityDateCondition = null;

		if (!Objects.isNull(endPreviousActivityDate) &&
			!Objects.isNull(startPreviousActivityDate)) {

			previousActivityDateCondition = DSL.field(
				"individualchannel.previousactivitydate"
			).between(
				startPreviousActivityDate, endPreviousActivityDate
			);
		}

		if (!Objects.isNull(lastActivityDateCondition) &&
			!Objects.isNull(previousActivityDateCondition)) {

			query.and(
				DSL.or(
					lastActivityDateCondition, previousActivityDateCondition));
		}
		else if (!Objects.isNull(lastActivityDateCondition)) {
			query.and(lastActivityDateCondition);
		}
		else if (!Objects.isNull(previousActivityDateCondition)) {
			query.and(previousActivityDateCondition);
		}

		return query.fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(DSL.countDistinct(DSL.field("individual.id")));

		List<Condition> conditions = new ArrayList<>();

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				));
		}

		if (StringUtils.isEmpty(query)) {
			if (conditions.isEmpty()) {
				conditions.add(DSL.noCondition());
			}

			return selectSelectStep.from(
				"Individual"
			).where(
				conditions
			).groupBy(
				DSL.field("individual.id")
			).fetchOptional(
				0, Long.class
			).orElse(
				0L
			);
		}

		Condition condition = DSL.noCondition();

		for (String fieldName : fieldNames) {
			condition = condition.or(
				DSL.and(
					DSL.field(
						"field.value"
					).likeIgnoreCase(
						"%" + query + "%"
					),
					DSL.field(
						"field.name"
					).eq(
						fieldName
					)));
		}

		return selectSelectStep.from(
			"Individual"
		).join(
			"Field"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("field.ownerid")
			)
		).where(
			conditions
		).groupBy(
			DSL.field("individual.id")
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countByIdInAndKeywords(
		List<Long> ids, @Nullable String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(DSL.countDistinct(DSL.field("individual.id")));

		return selectSelectStep.from(
			"Individual"
		).leftOuterJoin(
			"Field"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("field.ownerid")
			)
		).where(
			_getConditions(ids, keywords)
		).groupBy(
			DSL.field("individual.id")
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(
				filterHelper,
				_dslContext.select(
					DSL.countDistinct(DSL.field("individual.id"))));

		Condition condition = filterHelper.getCondition();

		if (segmentChannelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentChannelId), Long[].class)
				));
		}
		else if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individual.emailAddressHashed"
				).isNotNull());
		}

		if (segmentId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				));
		}

		return selectOnConditionStep.where(
			condition
		).groupBy(
			DSL.field("individual.id")
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, FilterHelper filterHelper,
		@Nullable Long id) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(filterHelper, DSL.selectOne());

		Condition condition = filterHelper.getCondition();

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (id != null) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).eq(
					id
				));
		}

		return _dslContext.fetchExists(selectOnConditionStep.where(condition));
	}

	@Override
	public boolean existsByFilterStringAndId(
		FilterHelper filterHelper, @Nullable Long id) {

		return existsByChannelIdAndFilterStringAndId(null, filterHelper, id);
	}

	@Override
	public List<String> findAccountPKsByChannelIdAndSegmentId(
		@Nullable Long channelId, @Nullable Long segmentId) {

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			conditions.add(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				));
		}

		Field<Object> accountPKField = DSL.field("accountPK");

		SelectSelectStep<Record1<Object>> selectSelectStep =
			_dslContext.selectDistinct(accountPKField);

		return selectSelectStep.from(
			_dslContext.select(
				DSL.function(
					"unnest", String.class,
					DSL.field("DataSourceIndividual.accountPKs")
				).as(
					accountPKField
				)
			).from(
				"Individual"
			).join(
				"DataSourceIndividual"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("datasourceindividual.individualid")
				)
			).where(
				conditions
			)
		).groupBy(
			accountPKField
		).orderBy(
			accountPKField
		).fetch(
			record -> (String)record.get(accountPKField)
		);
	}

	@Override
	public List<Individual.ActivitiesCount> findActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Condition condition = DSL.field(
			DSL.cast(
				DSL.array(DSL.field("individual.segmentids")), Long[].class)
		).contains(
			DSL.cast(DSL.array(segmentId), Long[].class)
		);

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individual.emailAddressHashed"
				).isNotNull());
		}

		return selectSelectStep.select(
			DSL.sum(
				DSL.field(
					"activitiesCount"
				).cast(
					Long.class
				)
			).as(
				"activitiesCount"
			),
			DSL.field("channelId")
		).from(
			"IndividualChannel"
		).join(
			"Individual"
		).on(
			DSL.field(
				"individualchannel.individualid"
			).eq(
				DSL.field("individual.id")
			)
		).where(
			condition
		).groupBy(
			DSL.field("channelId")
		).fetch(
			record -> {
				BigDecimal activitiesCount = (BigDecimal)record.get(
					"activitiesCount");
				Long channelId = (Long)record.get("channelId");

				return new Individual.ActivitiesCount(
					activitiesCount.longValue(), channelId);
			}
		);
	}

	@Override
	public List<Individual> findAnonymousByCreateDateAndLastActivityDateAndId(
		Date date, @Nullable Long id, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Individual"
			).asterisk());

		Condition condition = DSL.and(
			DSL.field(
				"individual.createdate"
			).lt(
				date
			),
			DSL.field(
				"individual.emailaddresshashed"
			).isNull(),
			DSL.not(
				DSL.field(
					"individualchannel.lastactivitydate"
				).gt(
					date
				)));

		if (id != null) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).gt(
					id
				));
		}

		return _populateIndividuals(
			selectSelectStep.from(
				"Individual"
			).join(
				"IndividualChannel"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("individualchannel.individualid")
				)
			).where(
				condition
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).limit(
				size
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public Individual findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
		Long associatedId, Long dataSourceId, String fieldName,
		String individualPK) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Individual"
			).asterisk());

		return selectSelectStep.from(
			"Individual"
		).join(
			"DataSourceIndividual"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("datasourceindividual.individualid")
			)
		).where(
			DSL.and(
				DSL.field(
					"datasourceindividual.datasourceid"
				).eq(
					dataSourceId
				),
				DSL.field(
					DSL.cast(
						DSL.array(
							DSL.field("datasourceindividual.individualpks")),
						String[].class)
				).contains(
					DSL.cast(DSL.array(individualPK), String[].class)
				),
				DSL.field(
					DSL.cast(DSL.array(DSL.field(fieldName)), Long[].class)
				).notContains(
					DSL.cast(DSL.array(associatedId), Long[].class)
				))
		).limit(
			1
		).fetchOne(
			record -> _populateIndividual(new Individual(record.intoMap()))
		);
	}

	@Override
	public List<Individual> findByChannelIdAndFilterStringAndIdIn(
		@Nullable Long channelId, FilterHelper filterHelper, List<Long> ids) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(
				filterHelper,
				_dslContext.select(
					DSL.table(
						"Individual"
					).asterisk()));

		Condition condition = filterHelper.getCondition();

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (CollectionUtils.isNotEmpty(ids)) {
			condition.and(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
			condition.and(
				DSL.field(
					"individual.id"
				).in(
					ids
				));
		}

		return _populateIndividuals(
			selectOnConditionStep.where(
				condition
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public Individual findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Individual"
			).asterisk());

		return selectSelectStep.from(
			"DataSourceIndividual"
		).join(
			"Individual"
		).on(
			DSL.field(
				"datasourceindividual.individualid"
			).eq(
				DSL.field("individual.id")
			)
		).where(
			DSL.and(
				DSL.field(
					"dataSourceId"
				).eq(
					dataSourceId
				),
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individualPKs")), String[].class)
				).contains(
					DSL.cast(DSL.array(individualPK), String[].class)
				))
		).limit(
			1
		).fetchOne(
			record -> _populateIndividual(new Individual(record.intoMap()))
		);
	}

	@Override
	public Individual findByEmailAddress(String emailAddress) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Individual"
			).asterisk());

		return selectSelectStep.from(
			"Individual"
		).join(
			"Field"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("field.ownerId")
			)
		).where(
			DSL.and(
				DSL.field(
					"field.name"
				).eq(
					"email"
				),
				DSL.field(
					"field.value"
				).eq(
					emailAddress
				))
		).fetchOne(
			record -> _populateIndividual(new Individual(record.intoMap()))
		);
	}

	@Override
	public List<Individual> findByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId, Pageable pageable) {

		List<Condition> conditions = new ArrayList<>();

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				));
		}

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			DSL.table(
				"Individual"
			).asterisk());

		if (StringUtils.isEmpty(query)) {
			if (conditions.isEmpty()) {
				conditions.add(DSL.noCondition());
			}

			return _populateIndividuals(
				selectSelectStep.from(
					"Individual"
				).where(
					conditions
				).limit(
					pageable.getPageSize()
				).offset(
					pageable.getOffset()
				).fetch(
					record -> new Individual(record.intoMap())
				));
		}

		Condition condition = DSL.noCondition();

		for (String fieldName : fieldNames) {
			condition = condition.or(
				DSL.and(
					DSL.field(
						"field.value"
					).likeIgnoreCase(
						"%" + query + "%"
					),
					DSL.field(
						"field.name"
					).eq(
						fieldName
					)));
		}

		conditions.add(condition);

		return _populateIndividuals(
			selectSelectStep.from(
				"Individual"
			).join(
				"Field"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("field.ownerid")
				)
			).where(
				conditions
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
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Individual> findByIdInAndKeywords(
		List<Long> ids, @Nullable String keywords, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			DSL.table(
				"Individual"
			).asterisk());

		return _populateIndividuals(
			selectSelectStep.from(
				"Individual"
			).join(
				"Field"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("field.ownerid")
				)
			).where(
				_getConditions(ids, keywords)
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getPageNumber()
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Individual> findBySegmentIds(Long segmentId) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _populateIndividuals(
			selectSelectStep.select(
				DSL.table(
					"Individual"
				).asterisk()
			).from(
				"Individual"
			).where(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				)
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Long>
		findIdsByAnyChannelIdsAndLastActivityDateAfterAndAnySegmentIds(
			@Nullable Long channelId, @Nullable Date lastActivityDate,
			@Nullable Long segmentId) {

		Condition condition = DSL.noCondition();

		if (!Objects.isNull(channelId)) {
			condition = DSL.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		SelectSelectStep<Record1<Object>> selectSelectStep =
			_dslContext.selectDistinct(DSL.field("individual.id"));

		SelectJoinStep<Record1<Object>> selectJoinStep = selectSelectStep.from(
			"Individual");

		if (!Objects.isNull(lastActivityDate)) {
			selectJoinStep.join(
				"IndividualChannel"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("individualchannel.individualid")
				),
				DSL.field(
					"individualchannel.lastactivitydate"
				).gt(
					lastActivityDate
				)
			);
		}

		if (!Objects.isNull(segmentId)) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				));
		}

		return selectJoinStep.where(
			condition
		).orderBy(
			DSL.field(
				"individual.id"
			).asc()
		).fetch(
			record -> (Long)record.get("individual.id")
		);
	}

	@Override
	public Map<Long, Long> findIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		Condition condition = DSL.field(
			DSL.cast(
				DSL.array(DSL.field("individual.segmentids")), Long[].class)
		).contains(
			DSL.cast(DSL.array(segmentId), Long[].class)
		);

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
		}

		Map<Long, Long> individualCounts = new HashMap<>();

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		selectSelectStep.select(
			DSL.field("channelid"),
			DSL.countDistinct(
				DSL.field("individualid")
			).as(
				"individualcounts"
			)
		).from(
			"IndividualChannel"
		).join(
			"Individual"
		).on(
			DSL.field(
				"individualchannel.individualid"
			).eq(
				DSL.field("individual.id")
			)
		).where(
			condition
		).groupBy(
			DSL.field("individualchannel.channelId")
		).fetch(
			record -> {
				Integer count = (Integer)record.get("individualcounts");

				individualCounts.put(
					(Long)record.get("channelid"), count.longValue());

				return null;
			}
		);

		return individualCounts;
	}

	@Override
	public List<Long> findKnownIndividualIds(
		FilterHelper filterHelper, Long segmentId) {

		Condition condition = filterHelper.getCondition();

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> modifiedDateField = DSL.field("field.modifiedDate");
		Field<Object> nameField = DSL.field("field.name");
		Field<Object> ownerIdField = DSL.field("field.ownerId");

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			modifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), nameField.as("name"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				condition
			).groupBy(
				ownerIdField, nameField
			).asTable(
				"maxModifiedDateTable"
			);

		SelectSelectStep<Record1<Object>> selectSelectStep =
			_dslContext.selectDistinct(DSL.field("individual.id"));

		return selectSelectStep.from(
			"Individual"
		).join(
			"Field"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("field.ownerid")
			)
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				modifiedDateField.eq(
					maxModifiedDateTable.field("modifiedDate")),
				DSL.field(
					"field.name"
				).eq(
					maxModifiedDateTable.field("name")
				),
				ownerIdField.eq(maxModifiedDateTable.field("ownerId")))
		).where(
			DSL.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				),
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull())
		).orderBy(
			DSL.field(
				"individual.id"
			).asc()
		).fetch(
			record -> (Long)record.get("individual.id")
		);
	}

	@Override
	public List<Distribution> getIndividualDistributions(
		String fieldName, String fieldType, FilterHelper filterHelper,
		Pageable pageable) {

		if (fieldType.equals("Number")) {
			return _getIndividualNumbersDistributions(
				fieldName, filterHelper.getCondition(), pageable);
		}

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> modifiedDateField = DSL.field("field.modifiedDate");
		Field<Object> nameField = DSL.field("field.name");
		Field<Object> ownerIdField = DSL.field("field.ownerId");
		Field<Object> valueField = DSL.field("field.value");

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			modifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), nameField.as("name"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				filterHelper.getCondition()
			).groupBy(
				ownerIdField, nameField
			).asTable(
				"maxModifiedDateTable"
			);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.count(
				ownerIdField
			).as(
				"count"
			),
			valueField.as("values")
		).from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				modifiedDateField.eq(
					maxModifiedDateTable.field("modifiedDate")),
				DSL.field(
					"field.name"
				).eq(
					maxModifiedDateTable.field("name")
				),
				ownerIdField.eq(maxModifiedDateTable.field("ownerId")))
		).where(
			DSL.field(
				"field.name"
			).eq(
				fieldName
			)
		).groupBy(
			valueField
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Distribution(
				(Integer)record.get("count"),
				Collections.singletonList(record.get("values")))
		);
	}

	@Override
	public List<Transformation> getIndividualTransformations(
		String apply, @Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable) {

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					MatcherUtil.getGroupByPattern());
		}

		String containsField = matcher.group("containsField");

		String groupByField = matcher.group("groupByField");

		String fieldName = groupByField.replace("demographics/", "");

		fieldName = fieldName.replace("/value", "");

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> modifiedDateField = DSL.field("modifiedDate");
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Object> valueField = DSL.field("value");

		Condition condition = filterHelper.getCondition();

		condition = condition.and(_getIncludeCondition(containsField));

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			modifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), nameField.as("name"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				condition.and(nameField.eq(fieldName))
			).groupBy(
				ownerIdField, nameField
			).asTable(
				"maxModifiedDateTable"
			);

		List<Condition> conditions = new ArrayList<>();

		if (segmentChannelId != null) {
			conditions.add(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentChannelId), Long[].class)
				));
		}
		else if (channelId != null) {
			conditions.add(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (!includeAnonymousUsers) {
			conditions.add(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
		}

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				));
		}

		if (conditions.isEmpty()) {
			conditions.add(DSL.noCondition());
		}

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			valueField.as("terms"),
			DSL.count(
				ownerIdField
			).as(
				"totalelements"
			)
		).from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				modifiedDateField.eq(maxModifiedDateTable.field("modifiedDate"))
			).and(
				nameField.eq(maxModifiedDateTable.field("name"))
			).and(
				ownerIdField.eq(maxModifiedDateTable.field("ownerId"))
			)
		).where(
			conditions
		).groupBy(
			valueField
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField, record.get("terms"))),
				(Integer)record.get("totalelements"))
		);
	}

	@Override
	public List<Individual> searchIndividualIds(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long id, int size) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(
				filterHelper,
				_dslContext.select(
					DSL.table(
						"Individual"
					).asterisk()));

		Condition condition = filterHelper.getCondition();

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
		}

		if (id != null) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).gt(
					id
				));
		}

		return _populateIndividuals(
			selectOnConditionStep.where(
				condition
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).limit(
				size
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Individual> searchIndividuals(
		FilterHelper filterHelper, Pageable pageable) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(
				filterHelper,
				_dslContext.select(
					DSL.table(
						"Individual"
					).asterisk()));

		return _populateIndividuals(
			selectOnConditionStep.where(
				filterHelper.getCondition()
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
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(
				filterHelper,
				_dslContext.select(
					DSL.table(
						"Individual"
					).asterisk()));

		Condition condition = filterHelper.getCondition();

		if (segmentChannelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentChannelId), Long[].class)
				));
		}
		else if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
		}

		if (segmentId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.segmentids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(segmentId), Long[].class)
				));
		}

		return _populateIndividuals(
			selectOnConditionStep.where(
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
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper, List<Long> ids,
		Boolean includeAnonymousUsers) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(
				filterHelper,
				_dslContext.select(
					DSL.table(
						"Individual"
					).asterisk()));

		Condition condition = filterHelper.getCondition();

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (CollectionUtils.isNotEmpty(ids)) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).in(
					ids
				));
		}

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individual.emailAddressHashed"
				).isNotNull());
		}

		return _populateIndividuals(
			selectOnConditionStep.where(
				condition
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper, @Nullable Long id,
		Boolean includeAnonymousUsers, int size) {

		SelectOnConditionStep<?> selectOnConditionStep =
			_getSelectOnConditionStep(
				filterHelper,
				_dslContext.select(
					DSL.table(
						"Individual"
					).asterisk()));

		Condition condition = filterHelper.getCondition();

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(channelId), Long[].class)
				));
		}

		if (id != null) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).gt(
					id
				));
		}

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individual.emailAddressHashed"
				).isNotNull());
		}

		return _populateIndividuals(
			selectOnConditionStep.where(
				condition
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).limit(
				size
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public List<Individual> searchIndividuals(
		Long dataSourceId, @Nullable Long id, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			DSL.table(
				"Individual"
			).asterisk());

		Condition condition = DSL.field(
			"datasourceindividual.datasourceid"
		).eq(
			dataSourceId
		);

		if (id != null) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).gt(
					id
				));
		}

		return _populateIndividuals(
			selectSelectStep.from(
				"Individual"
			).join(
				"DataSourceIndividual"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("datasourceindividual.individualid")
				)
			).where(
				condition
			).groupBy(
				DSL.field("individual.id")
			).orderBy(
				DSL.field(
					"individual.id"
				).asc()
			).limit(
				size
			).fetch(
				record -> new Individual(record.intoMap())
			));
	}

	@Override
	public void updateAssociatedIds(String fieldName, Set<Long> ids, Long id) {
		UpdateSetFirstStep<Record> update = _dslContext.update(
			DSL.table("Individual"));

		update.set(
			DSL.field(fieldName), DSL.array(ids.toArray(new Long[0]))
		).where(
			DSL.field(
				"id"
			).eq(
				id
			)
		).execute();
	}

	@Override
	public void updateDataSourceNameByDataSourceId(
		Long dataSourceId, String dataSourceName) {

		throw new UnsupportedOperationException();
	}

	private List<Condition> _getConditions(List<Long> ids, String keywords) {
		List<Condition> conditions = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(ids)) {
			conditions.add(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
			conditions.add(
				DSL.field(
					"individual.id"
				).in(
					ids
				));
		}

		if (StringUtils.isNotEmpty(keywords)) {
			conditions.add(
				DSL.and(
					DSL.field(
						"field.value"
					).containsIgnoreCase(
						keywords
					),
					DSL.or(
						DSL.field(
							"field.name"
						).eq(
							"email"
						),
						DSL.field(
							"field.name"
						).eq(
							"familyName"
						),
						DSL.field(
							"field.value"
						).eq(
							"givenName"
						))));
		}

		if (conditions.isEmpty()) {
			conditions.add(DSL.noCondition());
		}

		return conditions;
	}

	private Condition _getIncludeCondition(String contains) {
		if (contains == null) {
			return DSL.noCondition();
		}

		return DSL.field(
			"value"
		).containsIgnoreCase(
			contains
		);
	}

	private List<Distribution> _getIndividualNumbersDistributions(
		String fieldName, Condition condition, Pageable pageable) {

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Integer> valueField = DSL.field(
			"value"
		).cast(
			Integer.class
		);

		SelectSelectStep<Record> maxModifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			maxModifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				condition
			).groupBy(
				ownerIdField
			).asTable(
				"maxModifiedDateTable"
			);

		SelectSelectStep<Record> fieldsSelectSelectStep = _dslContext.select();

		Table<Record> fieldTable = fieldsSelectSelectStep.from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				DSL.field(
					"field.modifiedDate"
				).eq(
					maxModifiedDateTable.field("modifiedDate")
				),
				DSL.field(
					"field.ownerId"
				).eq(
					maxModifiedDateTable.field("ownerId")
				))
		).where(
			nameField.eq(fieldName)
		).asTable(
			"fieldTable"
		);

		Field<Integer> maxValueField = DSL.max(valueField);
		Field<Integer> minValueField = DSL.min(valueField);

		final Integer[] values = new Integer[2];

		SelectSelectStep<Record> maxAndMinValuesSelectSelectStep =
			_dslContext.select();

		maxAndMinValuesSelectSelectStep.select(
			maxValueField.as("max"), minValueField.as("min")
		).from(
			fieldTable
		).fetch(
			record -> {
				values[0] = (Integer)record.get("max");
				values[1] = (Integer)record.get("min");

				return null;
			}
		);

		int maxValue = values[0];
		int minValue = values[1];

		int size = pageable.getPageSize();

		if ((maxValue == minValue) || (size == 1)) {
			SelectSelectStep<Record> selectSelectStep = _dslContext.select();

			return selectSelectStep.select(
				DSL.val(
					minValue
				).as(
					"min"
				),
				DSL.val(
					maxValue
				).as(
					"max"
				),
				DSL.count(
					DSL.asterisk()
				).as(
					"count"
				)
			).from(
				fieldTable
			).where(
				valueField.eq(minValue)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
				record -> new Distribution(
					(Integer)record.get("count"),
					new LinkedList<Object>() {
						{
							add(record.get("min"));
							add(record.get("max"));
						}
					})
			);
		}

		double diff = maxValue - minValue;

		double binSize = diff / size;

		if ((binSize != Math.ceil(binSize)) && (diff > 1)) {
			binSize = Math.ceil(binSize);
		}

		List<SelectConditionStep<Record>> selectConditionSteps =
			new ArrayList<>();

		for (int i = 0; i < size; i++) {
			Double from = minValue + (i * binSize);
			Double to = minValue + ((i + 1) * binSize);

			SelectSelectStep<Record> selectSelectStep = _dslContext.select();

			SelectJoinStep<Record> selectJoinStep = selectSelectStep.select(
				DSL.val(
					from.intValue()
				).as(
					"min"
				),
				DSL.val(
					to.intValue()
				).as(
					"max"
				),
				DSL.count(
					DSL.asterisk()
				).as(
					"count"
				)
			).from(
				fieldTable
			);

			if ((to == maxValue) && (i == (size - 1))) {
				selectConditionSteps.add(
					selectJoinStep.where(
						DSL.and(
							valueField.ge(from.intValue()),
							valueField.le(to.intValue()))));
			}
			else {
				selectConditionSteps.add(
					selectJoinStep.where(
						DSL.and(
							valueField.ge(from.intValue()),
							valueField.lt(to.intValue()))));
			}
		}

		SelectOrderByStep<Record> selectOrderByStep = selectConditionSteps.get(
			0);

		if (selectConditionSteps.size() > 1) {
			for (int i = 1; i < selectConditionSteps.size(); i++) {
				selectOrderByStep.union(selectConditionSteps.get(i));
			}
		}

		Sort sort = pageable.getSort();

		Collection<SortField<?>> sortFields = new ArrayList<>();

		for (Sort.Order order : sort.toList()) {
			Field<?> field = DSL.field(order.getProperty());

			if (StringUtils.equals(order.getProperty(), "values")) {
				field = DSL.field(
					"min"
				).cast(
					Integer.class
				);
			}

			if (order.getDirection() == Sort.Direction.ASC) {
				sortFields.add(field.asc());
			}
			else {
				sortFields.add(field.desc());
			}
		}

		return selectOrderByStep.orderBy(
			sortFields
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Distribution(
				(Integer)record.get("count"),
				new LinkedList<Object>() {
					{
						add(record.get("min"));
						add(record.get("max"));
					}
				})
		);
	}

	private SelectOnConditionStep<?> _getSelectOnConditionStep(
		FilterHelper filterHelper, SelectSelectStep<?> selectSelectStep) {

		SelectOnConditionStep<?> selectOnConditionStep = selectSelectStep.from(
			"Individual"
		).leftJoin(
			"DataSourceIndividual"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("datasourceindividual.individualid")
			)
		).leftJoin(
			"IndividualChannel"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("individualchannel.individualid")
			)
		).leftJoin(
			"Field"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("field.ownerid")
			)
		);

		String filterString = filterHelper.getFilterString();

		if (StringUtils.isNotEmpty(filterString) &&
			filterString.contains("organizations.filter")) {

			selectOnConditionStep = selectOnConditionStep.leftJoin(
				"Organization"
			).on(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.organizationids")),
						Long[].class)
				).contains(
					DSL.cast(
						DSL.array(DSL.field("organization.id")), Long[].class)
				)
			);
		}

		return selectOnConditionStep;
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return Collections.singletonMap("name", "values");
	}

	private void _populateDataSourceIndividuals(
		Map<Long, Individual> individualsById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("individualid");

		selectSelectStep.from(
			"DataSourceIndividual"
		).where(
			field.in(individualsById.keySet())
		).fetch(
		).forEach(
			record -> {
				Individual individual = individualsById.get(
					record.get("individualid"));

				individual.addDataSourceIndividual(
					new DataSourceIndividual(record.intoMap()));
			}
		);
	}

	private Individual _populateIndividual(Individual individual) {
		if (individual == null) {
			return null;
		}

		List<Individual> individuals = _populateIndividuals(
			Collections.singletonList(individual));

		return individuals.get(0);
	}

	private void _populateIndividualChannels(
		Map<Long, Individual> individualsById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("individualid");

		selectSelectStep.from(
			"IndividualChannel"
		).where(
			field.in(individualsById.keySet())
		).fetch(
		).forEach(
			record -> {
				Individual individual = individualsById.get(
					record.get("individualid"));

				IndividualChannel individualChannel = new IndividualChannel(
					record.intoMap());

				individual.addChannelId(individualChannel.getChannelId());

				Set<Individual.ActivitiesCount> activitiesCounts =
					individual.getActivitiesCounts();

				activitiesCounts.add(
					new Individual.ActivitiesCount(individualChannel));

				Set<IndividualChannel> individualChannels =
					individual.getIndividualChannels();

				individualChannels.add(individualChannel);

				Set<Individual.ActivityDate> lastActivityDates =
					individual.getLastActivityDates();

				lastActivityDates.add(
					new Individual.ActivityDate(individualChannel));

				if (individualChannel.getPreviousActivityDate() != null) {
					Set<Individual.ActivityDate> previousActivityDates =
						individual.getPreviousActivityDates();

					previousActivityDates.add(
						new Individual.ActivityDate(
							individualChannel.getPreviousActivityDate(),
							individualChannel.getChannelId()));
				}
			}
		);
	}

	private List<Individual> _populateIndividuals(
		List<Individual> individuals) {

		if (individuals.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<Individual> stream = individuals.stream();

		Map<Long, Individual> individualsById = stream.collect(
			Collectors.toMap(
				Individual::getId, Function.identity(), (id, individual) -> id,
				LinkedHashMap::new));

		_populateDataSourceIndividuals(individualsById);
		_populateIndividualChannels(individualsById);

		return new ArrayList<>(individualsById.values());
	}

	private final DSLContext _dslContext;

}