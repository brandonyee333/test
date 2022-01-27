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

package com.liferay.osb.asah.common.postgresql.converter.helper;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class DataSourceFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Map<String, String> getFieldNameConversionMap() {
		return new HashMap<String, String>() {
			{
				put("credentials/type", "credentialType");
				put("provider/type", "providerType");
			}
		};
	}

	@Override
	public Condition getLogicFunctionCondition(
		String fieldName, String operator, String valueString) {

		if (fieldName.equals("channelId") && operator.equals("eq")) {
			Set<Long> dataSourceIds = _getDataSourceIds(valueString);

			if (!dataSourceIds.isEmpty()) {
				Field<Long> field = DSL.field("id", Long.class);

				return field.in(dataSourceIds);
			}
		}

		return null;
	}

	private Set<Long> _getDataSourceIds(String valueString) {
		Object value = StringUtil.toObject(valueString);

		List<Long> channelIds;

		if (value instanceof JSONArray) {
			channelIds = JSONUtil.toLongList((JSONArray)value);
		}
		else {
			channelIds = Collections.singletonList((Long)value);
		}

		List<Channel> channels = _channelDog.getChannels(channelIds);

		Stream<Channel> stream = channels.stream();

		return stream.flatMap(
			channel -> channel.getChannelDataSources(
			).stream()
		).map(
			ChannelDataSource::getDataSourceId
		).collect(
			Collectors.toSet()
		);
	}

	@Autowired
	private ChannelDog _channelDog;

}