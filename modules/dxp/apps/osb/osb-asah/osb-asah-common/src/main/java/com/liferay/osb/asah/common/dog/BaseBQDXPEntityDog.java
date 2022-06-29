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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.model.ExpandoField;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcos Martins
 */
public abstract class BaseBQDXPEntityDog {

	protected List<Long> getDataSourceIds(Long channelId) {
		if (channelId == null) {
			return Collections.emptyList();
		}

		Channel channel = _channelDog.fetchChannel(channelId);

		if (channel == null) {
			return Collections.emptyList();
		}

		return ListUtil.map(
			channel.getChannelDataSources(),
			ChannelDataSource::getDataSourceId);
	}

	protected List<ExpandoField> getExpandoFields(
		List<BQExpandoValue> bqExpandoValues) {

		List<ExpandoField> expandoFields = new ArrayList<>();

		Map<String, BQExpandoValue> bqExpandoValuesMap = new HashMap<>();

		for (BQExpandoValue bqExpandoValue : bqExpandoValues) {
			bqExpandoValuesMap.put(
				bqExpandoValue.getColumnId(), bqExpandoValue);
		}

		for (Map.Entry<String, BQExpandoValue> entry :
				bqExpandoValuesMap.entrySet()) {

			Optional<BQExpandoColumn> bqExpandoColumnOptional =
				_bqExpandoColumnRepository.findById(entry.getKey());

			if (!bqExpandoColumnOptional.isPresent()) {
				continue;
			}

			BQExpandoValue bqExpandoValue = bqExpandoValuesMap.get(
				entry.getKey());

			if (bqExpandoValue == null) {
				continue;
			}

			expandoFields.add(
				new ExpandoField(
					bqExpandoColumnOptional.get(), bqExpandoValue));
		}

		return expandoFields;
	}

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private ChannelDog _channelDog;

}