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

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.UpdateSetFirstStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class IndividualRepositoryImpl extends BaseRepository {

	public IndividualRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countIndividuals(
		@Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentId) {

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(new Long[] {channelId}), Long[].class)
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

	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, @Nullable String filterString,
		@Nullable Long individualId) {

		Condition condition = ConditionUtil.toCondition(
			filterString, _individualsFilterStringConverterHelper);

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
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(new Long[] {channelId}), Long[].class)
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
			condition.and(
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
					DSL.cast(
						DSL.array(new Object[] {individualPK}), String[].class)
				),
				DSL.field(
					DSL.cast(DSL.array(DSL.field(fieldName)), Long[].class)
				).notContains(
					DSL.cast(DSL.array(new Long[] {associatedId}), Long[].class)
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
					DSL.cast(
						DSL.array(new Object[] {individualPK}), String[].class)
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

	public Map<Long, Long> findIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		Condition condition = DSL.field(
			DSL.cast(
				DSL.array(DSL.field("individual.segmentids")), Long[].class)
		).contains(
			DSL.cast(DSL.array(segmentId), Long[].class)
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

	public List<Individual> searchIndividuals(
		@Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentId,
		Pageable pageable) {

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

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("individual.channelids")),
						Long[].class)
				).contains(
					DSL.cast(DSL.array(new Long[] {channelId}), Long[].class)
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

	private final DSLContext _dslContext;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

}