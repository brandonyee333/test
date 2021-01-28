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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoChannelDog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DeleteChannelsNanite extends BaseNanite {

	@Override
	public void run(JSONObject jsonObject) throws Exception {
		List<Long> channelIds = new ArrayList<>();

		JSONArray channelIdsSONArray = jsonObject.getJSONArray("channelIds");

		for (int i = 0; i < channelIdsSONArray.length(); i++) {
			channelIds.add(Long.valueOf(channelIdsSONArray.getString(i)));
		}

		_faroInfoChannelDog.deleteChannels(
			channelIds, this::monitorProcessedCount, this::monitorQueueSize);
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(DeleteChannelsNanite.class);
	}

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelDog;

}