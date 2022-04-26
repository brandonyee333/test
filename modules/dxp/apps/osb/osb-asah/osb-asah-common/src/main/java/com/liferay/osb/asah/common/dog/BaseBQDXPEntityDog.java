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
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcos Martins
 */
public abstract class BaseBQDXPEntityDog {

	protected List<Long> getDataSourceIds(Long channelId) {
		List<Long> dataSourceIds = new ArrayList<>();

		if (channelId != null) {
			Channel channel = _channelDog.fetchChannel(channelId);

			if (channel != null) {
				dataSourceIds = ListUtil.map(
					channel.getChannelDataSources(),
					ChannelDataSource::getDataSourceId);
			}
		}

		return dataSourceIds;
	}

	protected List<ExpandoField> getExpandoFields(
		List<Long> expandoColumnIds, List<String> expandoValueIds) {

		List<ExpandoField> expandoFields = new ArrayList<>();

		Map<Long, BQExpandoColumn> bqExpandoColumns = _getExpandoColumns(
			expandoColumnIds);

		Map<Long, BQExpandoValue> bqExpandoValues = _getExpandoValues(
			expandoValueIds);

		for (long columnId : expandoColumnIds) {
			BQExpandoColumn bqExpandoColumn = bqExpandoColumns.get(columnId);

			if (bqExpandoColumn == null) {
				continue;
			}

			BQExpandoValue bqExpandoValue = bqExpandoValues.get(columnId);

			if (bqExpandoValue == null) {
				continue;
			}

			expandoFields.add(
				new ExpandoField(bqExpandoColumn, bqExpandoValue));
		}

		return expandoFields;
	}

	private Map<Long, BQExpandoColumn> _getExpandoColumns(
		List<Long> expandoColumnIds) {

		Map<Long, BQExpandoColumn> bqExpandoColumns = new HashMap<>();

		for (BQExpandoColumn bqExpandoColumn :
				_bqExpandoColumnRepository.findByColumnIdIn(expandoColumnIds)) {

			bqExpandoColumns.put(
				bqExpandoColumn.getColumnId(), bqExpandoColumn);
		}

		return bqExpandoColumns;
	}

	private Map<Long, BQExpandoValue> _getExpandoValues(
		List<String> expandoValueIds) {

		Map<Long, BQExpandoValue> bqExpandoValues = new HashMap<>();

		for (BQExpandoValue bqExpandoValue :
				IterableUtils.toList(
					_bqExpandoValueRepository.findAllById(expandoValueIds))) {

			bqExpandoValues.put(bqExpandoValue.getColumnId(), bqExpandoValue);
		}

		return bqExpandoValues;
	}

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private BQExpandoValueRepository _bqExpandoValueRepository;

	@Autowired
	private ChannelDog _channelDog;

}