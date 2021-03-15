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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dto.ChannelDTO;
import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.dto.PageDTO;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.ChannelDataSource;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Geyson Silva
 * @author André Miranda
 */
@RequestMapping("/channels")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.ChannelsRestController"
)
public class ChannelsRestController extends BaseRestController {

	@PostMapping("/clear")
	public void clearChannel(@RequestBody List<String> ids) {
		_asahTaskDog.scheduleAsahTask(
			"ClearChannelsNanite", JSONUtil.put("channelIds", ids));
	}

	@DeleteMapping
	public void deleteChannels(@RequestBody List<String> ids) {
		_asahTaskDog.scheduleAsahTask(
			"DeleteChannelsNanite", JSONUtil.put("channelIds", ids));
	}

	@GetMapping("/{id}")
	public ChannelDTO getChannel(
			@PathVariable String id,
			@RequestParam(required = false) String expand)
		throws Exception {

		Channel channel = _channelDog.getChannel(Long.valueOf(id));

		if (StringUtils.isEmpty(expand)) {
			return new ChannelDTO(channel);
		}

		List<DataSource> dataSources = _dataSourceDog.getDataSources(
			ListUtil.map(
				channel.getChannelDataSources(),
				ChannelDataSource::getDataSourceId));

		return new ChannelDTO(
			channel,
			Collections.singletonMap(
				"data-sources", SetUtil.map(dataSources, DataSourceDTO::new)));
	}

	@GetMapping
	public PageDTO<ChannelDTO> getChannelDTOsPageDTO(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return _toPageDTO(
			_channelDog.getChannelsPage(filterString, page, size, sorts));
	}

	@PatchMapping("/{id}")
	public ChannelDTO patchChannel(
		@PathVariable String id, @RequestBody String json) {

		Set<Long> groupIds = new HashSet<>();

		JSONObject jsonObject = new JSONObject(json);

		JSONArray groupsJSONArray = jsonObject.optJSONArray("groups");

		if (groupsJSONArray != null) {
			for (int i = 0; i < groupsJSONArray.length(); i++) {
				JSONObject groupJSONObject = groupsJSONArray.getJSONObject(i);

				groupIds.add(Long.valueOf(groupJSONObject.getString("id")));
			}
		}

		Set<Long> removedGroupIds = _channelDog.getRemovedGroupIds(
			Long.valueOf(id),
			NumberUtils.createLong(jsonObject.optString("dataSourceId", null)),
			groupIds);

		return new ChannelDTO(
			_channelDog.patchChannel(
				Long.valueOf(id),
				NumberUtils.createLong(
					jsonObject.optString("dataSourceId", null)),
				groupIds, jsonObject.optString("name")),
			removedGroupIds);
	}

	@PostMapping
	public ChannelDTO postChannel(@RequestBody String json) throws Exception {
		JSONObject jsonObject = new JSONObject(json);

		return new ChannelDTO(
			_channelDog.addChannel(jsonObject.getString("name")));
	}

	private PageDTO<ChannelDTO> _toPageDTO(Page<Channel> channelsPage) {
		return new PageDTO<>(
			"_embedded", new ChannelDTO(channelsPage.getContent()),
			channelsPage.getNumber(), channelsPage.getSize(),
			channelsPage.getTotalElements(), channelsPage.getTotalPages());
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

}