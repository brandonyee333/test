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

import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoChannelDog;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ChannelsRestController extends BaseRestController {

	@GetMapping
	public String getChannels(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		if (ArrayUtils.isEmpty(sorts)) {
			sorts = new String[] {"name.sort,asc"};
		}

		return toCollectionGetResponse(
			"channels", null, page,
			QueryUtil.buildSearchQueryBuilder("name.search", filterString),
			size, sorts);
	}

	@PatchMapping("/{id}")
	public String patchChannel(
		@PathVariable String id, @RequestBody String json) {

		JSONObject jsonObject = _faroInfoChannelDog.patchChannel(
			id, new JSONObject(json));

		return jsonObject.toString();
	}

	@PostMapping
	public String postChannels(@RequestBody String json) {
		JSONArray jsonArray = new JSONArray(
			_faroInfoChannelDog.addChannels(new JSONObject(json)));

		return jsonArray.toString();
	}

	@PostMapping("/query_channel_names")
	public Map<String, String> queryChannelNames(@RequestBody String json) {
		JSONObject groupsJSONObject = new JSONObject(json);

		return _faroInfoChannelDog.getChannelNamesByGroupIds(
			groupsJSONObject.getString("dataSourceId"),
			JSONUtil.toStringSet(groupsJSONObject.getJSONArray("groupIds")));
	}

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelDog;

}