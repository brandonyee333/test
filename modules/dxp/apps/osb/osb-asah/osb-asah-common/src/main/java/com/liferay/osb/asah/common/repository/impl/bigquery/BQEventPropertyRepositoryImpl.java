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

package com.liferay.osb.asah.common.repository.impl.bigquery;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.repository.CustomBQEventPropertyRepository;
import com.liferay.osb.asah.common.repository.helper.BigQueryHelper;
import com.liferay.osb.asah.common.repository.impl.BaseEventPropertyRepository;
import com.liferay.osb.asah.common.repository.util.BigQueryUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;

import org.jooq.DSLContext;
import org.jooq.SelectFinalStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * @author Matthew Kong
 */
public class BQEventPropertyRepositoryImpl
	extends BaseEventPropertyRepository
	implements CustomBQEventPropertyRepository {

	public BQEventPropertyRepositoryImpl(DSLContext dslContext) {
		super(dslContext);
	}

	@Override
	public long countValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		SelectFinalStep selectFinalStep = getCountValuesSelect(
			channelId, eventAttributeDefinitionId, eventDefinitionId, keywords);

		TableResult tableResult = _bigQueryHelper.query(
			String.valueOf(selectFinalStep.getQuery()));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			FieldValue fieldValue = fieldValueList.get(0);

			return fieldValue.getLongValue();
		}

		return 0;
	}

	@Override
	public Optional<BQEventProperty> findByEventAttributeDefinitionIdAndEventId(
		Long eventAttributeDefinitionId, Long eventId) {

		SelectFinalStep selectFinalStep =
			getFindByEventAttributeDefinitionIdAndEventIdSelect(
				eventAttributeDefinitionId, eventId);

		TableResult tableResult = _bigQueryHelper.query(
			selectFinalStep.getSQL());

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			BQEventProperty bqEventProperty = new BQEventProperty();

			bqEventProperty.setChannelId(
				BigQueryUtil.getLong(fieldValueList.get("channelId")));
			bqEventProperty.setEventDate(
				BigQueryUtil.getDate(fieldValueList.get("eventDate")));
			bqEventProperty.setId(
				BigQueryUtil.getString(fieldValueList.get("id")));
			bqEventProperty.setName(
				BigQueryUtil.getString(fieldValueList.get("name")));
			bqEventProperty.setProjectId(
				BigQueryUtil.getString(fieldValueList.get("projectId")));
			bqEventProperty.setValue(
				BigQueryUtil.getString(fieldValueList.get("value")));

			return Optional.of(bqEventProperty);
		}

		return null;
	}

	@Override
	public List<String> searchValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Pageable pageable) {

		SelectFinalStep selectFinalStep = getSearchValuesSelect(
			channelId, eventAttributeDefinitionId, eventDefinitionId, keywords,
			pageable);

		TableResult tableResult = _bigQueryHelper.query(
			selectFinalStep.getSQL());

		List<FieldValueList> fieldValueLists = IterableUtils.toList(
			tableResult.iterateAll());

		Stream<FieldValueList> stream = fieldValueLists.stream();

		return stream.map(
			fieldValues -> fieldValues.get(0)
		).map(
			fieldValue -> fieldValue.getStringValue()
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private BigQueryHelper _bigQueryHelper;

}