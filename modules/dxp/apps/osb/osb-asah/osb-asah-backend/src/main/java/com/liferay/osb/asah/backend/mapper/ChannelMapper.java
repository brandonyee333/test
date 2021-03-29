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

package com.liferay.osb.asah.common.mapper;

import com.liferay.osb.asah.common.dto.ChannelDTO;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.ChannelDataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class ChannelMapper extends Mapper<Channel, ChannelDTO> {

	@Override
	protected ChannelDTO toDTO(Channel channel) {
		return new ChannelDTO(channel);
	}

	@Override
	protected Channel toModel(JSONObject jsonObject) {
		Channel channel = new Channel();

		if (jsonObject.has("dataSources") &&
			!jsonObject.isNull("dataSources")) {

			JSONArray dataSourcesJSONArray = jsonObject.getJSONArray(
				"dataSources");

			for (int j = 0; j < dataSourcesJSONArray.length(); j++) {
				JSONObject dataSourceJSONObject =
					dataSourcesJSONArray.getJSONObject(j);

				ChannelDataSource channelDataSource = new ChannelDataSource();

				if (dataSourceJSONObject.has("id") &&
					!dataSourceJSONObject.isNull("id")) {

					channelDataSource.setDataSourceId(
						dataSourceJSONObject.getLong("id"));
				}

				if (dataSourceJSONObject.has("groupIds") &&
					!dataSourceJSONObject.isNull("groupIds")) {

					channelDataSource.setGroupIds(
						JSONUtil.toLongSet(
							dataSourceJSONObject.getJSONArray("groupIds")));
				}

				channel.addChannelDataSource(channelDataSource);
			}
		}

		if (jsonObject.has("createDate") && !jsonObject.isNull("createDate")) {
			channel.setCreateDate(toUTCDate(jsonObject.get("createDate")));
		}
		else if (jsonObject.has("dateCreated") &&
				 !jsonObject.isNull("dateCreated")) {

			channel.setCreateDate(toUTCDate(jsonObject.get("dateCreated")));
		}

		if (jsonObject.has("id") && !jsonObject.isNull("id")) {
			channel.setId(jsonObject.getLong("id"));
		}

		if (jsonObject.has("name") && !jsonObject.isNull("name")) {
			channel.setName(jsonObject.getString("name"));
		}

		return channel;
	}

}