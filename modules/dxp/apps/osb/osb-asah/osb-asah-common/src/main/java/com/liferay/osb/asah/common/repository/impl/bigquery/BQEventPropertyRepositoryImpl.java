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

import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.repository.CustomBQEventPropertyRepository;
import com.liferay.osb.asah.common.repository.helper.BigQueryHelper;
import com.liferay.osb.asah.common.repository.impl.BaseBQEventPropertyRepository;
import com.liferay.osb.asah.common.repository.util.BigQueryUtil;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * @author Matthew Kong
 */
@ConditionalOnGoogleApplicationCredentials
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

		return _bigQueryHelper.count(
			getCountValuesSelect(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords));
	}

	@Override
	public Optional<BQEventProperty> findByEventAttributeDefinitionIdAndEventId(
		Long eventAttributeDefinitionId, Long eventId) {

		return _bigQueryHelper.get(
			Optional.empty(),
			fieldValueList -> {
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
			},
			getFindByEventAttributeDefinitionIdAndEventIdSelect(
				eventAttributeDefinitionId, eventId));
	}

	@Override
	public List<String> searchValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Pageable pageable) {

		return _bigQueryHelper.getList(
			fieldValueList -> BigQueryUtil.getString(fieldValueList.get(0)),
			getSearchValuesSelect(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords, pageable));
	}

	@Autowired
	private BigQueryHelper _bigQueryHelper;

}