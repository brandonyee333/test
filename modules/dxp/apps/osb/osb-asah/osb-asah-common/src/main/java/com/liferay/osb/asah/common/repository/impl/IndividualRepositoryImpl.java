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

import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class IndividualRepositoryImpl extends BaseRepository {

	public IndividualRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countByIdsInAndKeywords(
		List<Long> individualIds, @Nullable String keywords) {

		return _dslContext.select(
			DSL.countDistinct(DSL.field("individual.id"))
		).from(
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
			_getConditions(individualIds, keywords)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countByQueryAndSegmentId(
		@Nullable String query, @Nullable Long segmentId) {

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

			return _dslContext.select(
				DSL.countDistinct(DSL.field("individual.id"))
			).from(
				"Individual"
			).where(
				conditions
			).fetchOptional(
				0, Long.class
			).orElse(
				0L
			);
		}

		List<FieldMapping> fieldMappings =
			_fieldMappingRepository.findByContextAndFieldTypeAndOwnerType(
				"demographics", "Text", "individual");

		Condition condition = DSL.noCondition();

		for (FieldMapping fieldMapping : fieldMappings) {
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
						fieldMapping.getFieldName()
					)));
		}

		return _dslContext.select(
			DSL.countDistinct(DSL.field("individual.id"))
		).from(
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
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countIndividuals(
		@Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId) {

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

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

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Individual"
		).where(
			condition
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countKnownIndividuals(List<Long> ids) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Individual"
		).innerJoin(
			"Field"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("field.ownerid")
			),
			DSL.field(
				"field.name"
			).eq(
				"email"
			),
			DSL.field(
				"field.value"
			).isNotNull()
		).where(
			DSL.field(
				"individual.id"
			).in(
				ids
			)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countKnownIndividuals(Long segmentId) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Individual"
		).innerJoin(
			"Field"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("field.ownerid")
			),
			DSL.field(
				"field.name"
			).eq(
				"email"
			),
			DSL.field(
				"field.value"
			).isNotNull()
		).where(
			DSL.field(
				DSL.cast(
					DSL.array(DSL.field("individual.segmentids")), Long[].class)
			).contains(
				DSL.cast(DSL.array(segmentId), Long[].class)
			)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, @Nullable String filterString,
		@Nullable Long individualId) {

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

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

		if (individualId != null) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).eq(
					individualId
				));
		}

		return _dslContext.fetchExists(
			DSL.selectOne(
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
			).join(
				"Field"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("field.ownerid")
				)
			).where(
				condition
			));
	}

	public boolean
		existsByChannelIdAndFilterStringAndIncludeAnonymousUsersAndId(
			@Nullable Long channelId, @Nullable String filterString,
			Boolean includeAnonymousUsers, @Nullable Long individualId) {

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

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
					"individual.emailAddressHashed"
				).isNotNull());
		}

		if (individualId != null) {
			condition = condition.and(
				DSL.field(
					"individual.id"
				).eq(
					individualId
				));
		}

		return _dslContext.fetchExists(
			DSL.selectOne(
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
			).join(
				"Field"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("field.ownerid")
				)
			).where(
				condition
			));
	}

	public boolean existsByFilterStringAndId(
		@Nullable String filterString, @Nullable Long individualId) {

		return existsByChannelIdAndFilterStringAndId(
			null, filterString, individualId);
	}

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
				"individualchannel.individualId"
			).eq(
				DSL.field("individual.id")
			)
		).where(
			condition
		).groupBy(
			DSL.field("channelId")
		).fetch(
		).map(
			record -> {
				BigDecimal activitiesCount = (BigDecimal)record.get(
					"activitiesCount");
				Long channelId = (Long)record.get("channelId");

				return new Individual.ActivitiesCount(
					activitiesCount.longValue(), channelId);
			}
		);
	}

	public Optional<Individual>
		findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
			Long associatedId, Long dataSourceId, String fieldName,
			String individualPK) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

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
		).fetchOptional(
			record -> new Individual(record.intoMap())
		);
	}

	public List<Individual> findByChannelIdAndSegmentId(
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

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.asterisk()
		).from(
			"Individual"
		).where(
			conditions
		).fetch(
		).map(
			record -> new Individual(record.intoMap())
		);
	}

	public List<Individual> findByDataSourceId(
		Long dataSourceId, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.table(
				"Individual"
			).asterisk()
		).from(
			"Individual"
		).join(
			"DataSourceIndividual"
		).on(
			DSL.field(
				"individual.id"
			).eq(
				DSL.field("datasourceindividual.individualId")
			)
		).where(
			DSL.field(
				"datasourceindividual.datasourceid"
			).eq(
				dataSourceId
			)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Individual(record.intoMap())
		);
	}

	public Optional<Individual> findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.table(
				"Individual"
			).asterisk()
		).from(
			"DataSourceIndividual"
		).join(
			"Individual"
		).on(
			DSL.field(
				"datasourceindividual.individualId"
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
		).fetchOptional(
			record -> new Individual(record.intoMap())
		);
	}

	public Optional<Individual> findByEmailAddressOrEmailAddressHashed(
		@Nullable String emailAddress, @Nullable String emailAddressHashed) {

		if (StringUtils.isBlank(emailAddress)) {
			return _individualRepository.findByEmailAddressHashed(
				emailAddressHashed);
		}

		Optional<Individual> individualOptional =
			_individualRepository.findByEmailAddress(emailAddress);

		if (!individualOptional.isPresent()) {
			return _individualRepository.findByEmailAddressHashed(
				emailAddressHashed);
		}

		return individualOptional;
	}

	public List<Individual> findByIdsInAndKeywords(
		List<Long> individualIds, @Nullable String keywords,
		Pageable pageable) {

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
				DSL.field("field.ownerid")
			)
		).where(
			_getConditions(individualIds, keywords)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getPageNumber()
		).fetch(
		).map(
			record -> new Individual(record.intoMap())
		);
	}

	public List<Individual> findByQueryAndSegmentId(
		@Nullable String query, @Nullable Long segmentId, Pageable pageable) {

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

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Individual"
			).asterisk());

		if (StringUtils.isEmpty(query)) {
			if (conditions.isEmpty()) {
				conditions.add(DSL.noCondition());
			}

			return selectSelectStep.from(
				"Individual"
			).where(
				conditions
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
			).map(
				record -> new Individual(record.intoMap())
			);
		}

		List<FieldMapping> fieldMappings =
			_fieldMappingRepository.findByContextAndFieldTypeAndOwnerType(
				"demographics", "Text", "individual");

		Condition condition = DSL.noCondition();

		for (FieldMapping fieldMapping : fieldMappings) {
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
						fieldMapping.getFieldName()
					)));
		}

		conditions.add(condition);

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
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Individual(record.intoMap())
		);
	}

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
				DSL.field("individualId")
			).as(
				"individualcounts"
			)
		).from(
			"IndividualChannel"
		).join(
			"Individual"
		).on(
			DSL.field(
				"individualchannel.individualId"
			).eq(
				DSL.field("individual.id")
			)
		).where(
			condition
		).groupBy(
			DSL.field("individualchannel.channelId")
		).fetch(
		).map(
			record -> {
				Integer count = (Integer)record.get("individualcounts");

				individualCounts.put(
					(Long)record.get("channelid"), count.longValue());

				return null;
			}
		);

		return individualCounts;
	}

	public List<Distribution> getIndividualDistributions(
		String fieldName, String fieldType, @Nullable String filterString,
		Pageable pageable) {

		if (fieldType.equals("Number")) {
			return _getIndividualNumbersDistributions(
				fieldName, filterString, pageable);
		}

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> modifiedDateField = DSL.field("modifiedDate");
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Object> valueField = DSL.field("value");

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
		).map(
			record -> new Distribution(
				(Integer)record.get("count"),
				Collections.singletonList(record.get("values")))
		);
	}

	public List<Transformation> getIndividualTransformations(
		String apply, @Nullable Long channelId, @Nullable String filterString,
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

		Condition fieldCondition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

		fieldCondition = fieldCondition.and(
			_getIncludeCondition(containsField));

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			modifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), nameField.as("name"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				fieldCondition.and(nameField.eq(fieldName))
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
		).map(
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField, record.get("terms"))),
				(Integer)record.get("totalelements"))
		);
	}

	public List<Individual> searchIndividuals(
		@Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Individual"
			).asterisk());

		SelectOnConditionStep<Record> selectOnConditionStep =
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
			).join(
				"DataSourceIndividual"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("datasourceindividual.individualid")
				)
			);

		if (StringUtils.isNotEmpty(filterString) &&
			filterString.contains("organizations.filter")) {

			selectOnConditionStep = selectOnConditionStep.join(
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

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

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

		return selectOnConditionStep.where(
			condition
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Individual(record.intoMap())
		);
	}

	public List<Individual> searchIndividuals(
		String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Individual"
			).asterisk());

		SelectOnConditionStep<Record> selectOnConditionStep =
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
			).join(
				"DataSourceIndividual"
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("datasourceindividual.individualid")
				)
			);

		if (filterString.contains("organizations.filter")) {
			selectOnConditionStep = selectOnConditionStep.join(
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

		return selectOnConditionStep.where(
			ConditionUtil.toCondition(
				filterString, _individualsFilterStringConverterHelper)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Individual(record.intoMap())
		);
	}

	public void updateAssociatedIds(
		String fieldName, Set<Long> ids, Long individualId) {

		UpdateSetFirstStep<Record> update = _dslContext.update(
			DSL.table("Individual"));

		update.set(
			DSL.field(fieldName), DSL.array(ids.toArray(new Long[0]))
		).where(
			DSL.field(
				"id"
			).eq(
				individualId
			)
		).execute();
	}

	private List<Condition> _getConditions(
		List<Long> individualIds, String keywords) {

		List<Condition> conditions = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(individualIds)) {
			conditions.add(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
			conditions.add(
				DSL.field(
					"individual.id"
				).in(
					individualIds
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
		String fieldName, String filterString, Pageable pageable) {

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Integer> valueField = DSL.field(
			"value"
		).cast(
			Integer.class
		);

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

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
					"modifiedDate"
				).eq(
					maxModifiedDateTable.field("modifiedDate")
				),
				ownerIdField.eq(maxModifiedDateTable.field("ownerId")))
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
		).map(
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
			).map(
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
		).map(
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

	private Map<String, String> _getSortFieldNameConversionMap() {
		return Collections.singletonMap("name", "values");
	}

	private final DSLContext _dslContext;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

}