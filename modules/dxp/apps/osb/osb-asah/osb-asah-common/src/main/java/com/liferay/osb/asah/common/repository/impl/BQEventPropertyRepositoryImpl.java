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

import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.repository.CustomBQEventPropertyRepository;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

import org.springframework.data.domain.Pageable;

/**
 * @author Alejo Ceballos
 */
public class BQEventPropertyRepositoryImpl
	extends BaseBQEventPropertyRepository
	implements CustomBQEventPropertyRepository {

	public BQEventPropertyRepositoryImpl(DSLContext dslContext) {
		super(dslContext);
	}

	@Override
	public long countValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		SelectFinalStep<Record1<Integer>> selectFinalStep =
			getCountValuesSelect(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords);

		return selectFinalStep.fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public Optional<BQEventProperty> findByEventAttributeDefinitionIdAndEventId(
		Long eventAttributeDefinitionId, Long eventId) {

		SelectFinalStep<Record> selectFinalStep =
			getFindByEventAttributeDefinitionIdAndEventIdSelect(
				eventAttributeDefinitionId, eventId);

		return Optional.ofNullable(
			selectFinalStep.fetchAny(
				record -> new BQEventProperty(record.intoMap())));
	}

	@Override
	public List<String> searchValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Pageable pageable) {

		SelectFinalStep<Record1<String>> selectFinalStep =
			getSearchValuesSelect(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords, pageable);

		return selectFinalStep.fetch(Record1::value1);
	}

}