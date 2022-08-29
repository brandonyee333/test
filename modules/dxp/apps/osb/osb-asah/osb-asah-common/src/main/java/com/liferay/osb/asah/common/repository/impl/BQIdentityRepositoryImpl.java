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

import com.liferay.osb.asah.common.model.IndividualMetricType;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.repository.CustomBQIdentityRepository;

import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

/**
 * @author Ivica Cardic
 */
public class BQIdentityRepositoryImpl
	extends BaseRepository implements CustomBQIdentityRepository {

	public BQIdentityRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long getIndividualsCount(
		Boolean active, Long channelId, LocalDate localDate,
		MetricType metricType) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		SelectJoinStep<Record1<Integer>> selectJoinStep = selectSelectStep.from(
			DSL.table(
				"BQIdentity"
			).as(
				"identity"
			));

		if ((active != null) && active) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIdentityChannel"
				).as(
					"identityChannel"
				)
			).on(
				DSL.field(
					"identity.id"
				).eq(
					DSL.field("identityChannel.identityId")
				)
			);
		}

		if (channelId != null) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIdentityActivity"
				).as(
					"identityActivity"
				)
			).on(
				DSL.field(
					"identity.id"
				).eq(
					DSL.field("identityActivity.identityId")
				)
			);
		}

		if (metricType == IndividualMetricType.KNOWN_INDIVIDUALS) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIndividual"
				).as(
					"individual"
				)
			).on(
				DSL.field(
					"identity.emailAddressHashed"
				).eq(
					DSL.field("individual.emailAddressHashed")
				)
			);
		}

		return selectJoinStep.where(
			_getConditions(active, channelId, localDate, metricType)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	private List<Condition> _getConditions(
		Boolean active, Long channelId, LocalDate localDate,
		MetricType metricType) {

		LocalDateTime localDateTime = localDate.atTime(LocalTime.MAX);

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				metricType.getFieldName()
			).lessOrEqual(
				Timestamp.valueOf(localDateTime)
			));

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"identityActivity.channelId"
				).eq(
					channelId
				));
		}

		if (metricType == IndividualMetricType.ANONYMOUS_INDIVIDUALS) {
			conditions.add(
				DSL.field(
					"identity.emailAddressHashed"
				).isNull());
		}

		if ((active != null) && active) {
			LocalDateTime nowLocalDateTime = LocalDateTime.now();

			conditions.add(
				DSL.field(
					"identityChannel.lastActivityDate"
				).greaterThan(
					nowLocalDateTime.minusDays(30)
				));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}