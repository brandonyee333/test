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

import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.UpdateSetFirstStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class IndividualRepositoryImpl extends BaseRepository {

	public IndividualRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public boolean existsByChannelIdAndFilterAndId(
		Long channelId, String filter, Long individualId) {

		Condition condition = ConditionUtil.toCondition(
			filter, _individualsFilterStringConverterHelper);

		if (individualId != null) {
			condition.and(
				DSL.field(
					"individual.id"
				).eq(
					individualId
				));
		}

		if (channelId != null) {
			condition.and(
				DSL.field(
					"individual.channelids"
				).contains(
					channelId
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
					"datasourceindividual.individualid"
				)
			).where(
				condition
			));
	}

	public boolean existsByFilterAndId(String filter, Long individualId) {
		return existsByChannelIdAndFilterAndId(null, filter, individualId);
	}

	public Optional<Individual>
		findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
			Long associatedId, Long dataSourceId, String fieldName,
			String individualPK) {

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
				"datasourceindividual.individualId"
			)
		).where(
			DSL.and(
				DSL.field(
					"datasourceindividual.datasourceid"
				).eq(
					dataSourceId
				),
				DSL.field(
					"datasourceindividual.individualpks"
				).contains(
					individualPK
				),
				DSL.field(
					fieldName
				).notContains(
					Collections.singleton(associatedId)
				))
		).limit(
			1
		).fetchOptional(
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
				"datasourceindividual.individualId"
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
				"individual.id"
			)
		).where(
			DSL.and(
				DSL.field(
					"dataSourceId"
				).eq(
					dataSourceId
				),
				DSL.field(
					"individualPKs"
				).contains(
					individualPK
				))
		).limit(
			1
		).fetchOptional(
			record -> new Individual(record.intoMap())
		);
	}

	public Optional<Individual> findByEmailAddressOrEmailAddressHashed(
		@Nullable String emailAddress, String emailAddressHashed) {

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

	public List<Individual.ActivitiesCount> getActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Condition condition = DSL.field(
			"individual.segmentids"
		).contains(
			segmentId
		);

		if (!includeAnonymousUsers) {
			condition.and(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
		}

		return selectSelectStep.select(
			DSL.sum(
				DSL.field(
					"individualchannel.activitiescount"
				).cast(
					Long.class
				)
			).as(
				"activitiescount"
			),
			DSL.field("individualchannel.channelid")
		).from(
			"IndividualChannel"
		).join(
			"Individual"
		).on(
			DSL.field(
				"individualchannel.individualId"
			).eq(
				"individual.id"
			)
		).where(
			condition
		).fetch(
		).map(
			record -> new Individual.ActivitiesCount(record.intoMap())
		);
	}

	public Map<Long, Long> getIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		Condition condition = DSL.field(
			"individual.segmentids"
		).contains(
			segmentId
		);

		if (!includeAnonymousUsers) {
			condition.and(
				DSL.field(
					"individual.emailaddresshashed"
				).isNotNull());
		}

		Map<Long, Long> individualCounts = new HashMap<>();

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		selectSelectStep.select(
			DSL.table(
				"IndividualChannel"
			).field(
				"channelId"
			),
			DSL.countDistinct(
				DSL.table(
					"IndividualChannel"
				).field(
					"individualId"
				)
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
				"individual.id"
			)
		).where(
			condition
		).groupBy(
			DSL.field("channelId")
		).fetch(
		).map(
			record -> individualCounts.put(
				(Long)record.get("channelid"),
				(Long)record.get("individualcounts"))
		);

		return individualCounts;
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
			DSL.field(fieldName), ids
		).where(
			DSL.field(
				"id"
			).eq(
				individualId
			)
		);
	}

	private final DSLContext _dslContext;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

}