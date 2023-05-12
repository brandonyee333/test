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

import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.entity.ExperimentMetric;
import com.liferay.osb.asah.common.entity.ExperimentVariant;
import com.liferay.osb.asah.common.entity.ExperimentVariantMetric;
import com.liferay.osb.asah.common.repository.CustomExperimentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Marcos Martins
 */
public class ExperimentRepositoryImpl
	extends BaseRepository implements CustomExperimentRepository {

	public ExperimentRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countExperimentsByChannelIdAndKeywords(
		Long channelId, String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Experiment"
		).where(
			_getConditions(channelId, keywords)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<Experiment> searchExperimentsByChannelIdAndKeywords(
		Long channelId, @Nullable String keywords, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _populateExperiments(
			selectSelectStep.from(
				"Experiment"
			).where(
				_getConditions(channelId, keywords)
			).orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
				record -> new Experiment(record.intoMap())
			));
	}

	private List<Condition> _getConditions(Long channelId, String keywords) {
		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"channelId"
			).eq(
				channelId
			));

		if (StringUtils.isNotEmpty(keywords)) {
			conditions.add(
				DSL.or(
					DSL.field(
						"name"
					).containsIgnoreCase(
						keywords
					),
					DSL.field(
						"description"
					).containsIgnoreCase(
						keywords
					)));
		}

		return conditions;
	}

	private void _populateExperimentMetrics(
		Map<Long, Experiment> experimentsById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("experimentid");

		selectSelectStep.from(
			"ExperimentMetric"
		).where(
			field.in(experimentsById.keySet())
		).fetch(
		).forEach(
			record -> {
				Experiment experiment = experimentsById.get(
					record.get("experimentid"));

				ExperimentMetric experimentMetric = new ExperimentMetric(
					record.intoMap());

				_populateExperimentVariantMetrics(
					Collections.singletonMap(
						experimentMetric.getId(), experimentMetric));

				experiment.addExperimentMetric(experimentMetric);
			}
		);
	}

	private List<Experiment> _populateExperiments(
		List<Experiment> experiments) {

		if (experiments.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<Experiment> stream = experiments.stream();

		Map<Long, Experiment> experimentsById = stream.collect(
			Collectors.toMap(
				Experiment::getId, Function.identity(), (id, experiment) -> id,
				LinkedHashMap::new));

		_populateExperimentMetrics(experimentsById);
		_populateExperimentVariants(experimentsById);

		return new ArrayList<>(experimentsById.values());
	}

	private void _populateExperimentVariantMetrics(
		Map<Long, ExperimentMetric> experimentMetricsById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("experimentmetricid");

		selectSelectStep.from(
			"ExperimentVariantMetric"
		).where(
			field.in(experimentMetricsById.keySet())
		).fetch(
		).forEach(
			record -> {
				ExperimentMetric experimentMetric = experimentMetricsById.get(
					record.get("experimentmetricid"));

				experimentMetric.addExperimentVariantMetric(
					new ExperimentVariantMetric(record.intoMap()));
			}
		);
	}

	private void _populateExperimentVariants(
		Map<Long, Experiment> experimentsById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("experimentid");

		selectSelectStep.from(
			"ExperimentVariant"
		).where(
			field.in(experimentsById.keySet())
		).fetch(
		).forEach(
			record -> {
				Experiment experiment = experimentsById.get(
					record.get("experimentid"));

				experiment.addExperimentVariant(
					new ExperimentVariant(record.intoMap()));
			}
		);
	}

	private final DSLContext _dslContext;

}