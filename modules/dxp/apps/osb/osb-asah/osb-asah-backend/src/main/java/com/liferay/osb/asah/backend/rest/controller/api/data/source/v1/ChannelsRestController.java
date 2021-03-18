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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1;

import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dto.ChannelDTO;
import com.liferay.osb.asah.common.dto.PageDTO;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.math.NumberUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 */
@RequestMapping(produces = "application/json", value = "/api/1.0/channels")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.ChannelsRestController"
)
public class ChannelsRestController {

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
	public List<ChannelDTO> postChannels(@RequestBody String json) {
		Map<Long, String> channelNamesByGroupIds = new HashedMap<>();

		JSONObject jsonObject = new JSONObject(json);

		JSONArray groupsJSONArray = jsonObject.getJSONArray("groups");

		for (int i = 0; i < groupsJSONArray.length(); i++) {
			JSONObject groupJSONObject = groupsJSONArray.getJSONObject(i);

			channelNamesByGroupIds.put(
				Long.valueOf(groupJSONObject.getString("id")),
				groupJSONObject.getString("name"));
		}

		return _toChannelDTOs(
			_channelDog.addChannels(
				channelNamesByGroupIds, jsonObject.getString("channelType"),
				Long.valueOf(jsonObject.getString("dataSourceId"))));
	}

	@PostMapping("/query_channel_names")
	public Map<Long, String> queryChannelNames(@RequestBody String json) {
		Set<Long> groupIds = new HashSet<>();

		JSONObject groupsJSONObject = new JSONObject(json);

		JSONArray groupIdsJSONArray = groupsJSONObject.getJSONArray("groupIds");

		for (int i = 0; i < groupIdsJSONArray.length(); i++) {
			groupIds.add(Long.valueOf(groupIdsJSONArray.getString(i)));
		}

		return _channelDog.getChannelNamesByGroupIds(
			Long.valueOf(groupsJSONObject.getString("dataSourceId")), groupIds);
	}

	private List<ChannelDTO> _toChannelDTOs(List<Channel> channels) {
		return ListUtil.map(channels, ChannelDTO::new);
	}

	private PageDTO<ChannelDTO> _toPageDTO(Page<Channel> channelsPage) {
		return new PageDTO<>(
			"_embedded", new ChannelDTO(channelsPage.getContent()),
			channelsPage.getNumber(), channelsPage.getSize(),
			channelsPage.getTotalElements(), channelsPage.getTotalPages());
	}

	@Autowired
	private ChannelDog _channelDog;

}