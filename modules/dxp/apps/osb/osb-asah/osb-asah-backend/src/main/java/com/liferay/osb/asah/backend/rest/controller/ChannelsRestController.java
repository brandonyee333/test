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

import com.liferay.osb.asah.backend.rest.response.embedded.ChannelsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoChannelDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.PostResponse;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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
		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"ClearChannelsNanite", JSONUtil.put("channelIds", ids));
	}

	@DeleteMapping
	public void deleteChannels(@RequestBody List<String> ids) {
		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"DeleteChannelsNanite", JSONUtil.put("channelIds", ids));
	}

	@GetMapping("/{id}")
	public String getChannel(
			@PathVariable String id,
			@RequestParam(required = false) String expand)
		throws Exception {

		if (StringUtils.isEmpty(expand)) {
			return toItemGetResponse("channels", id);
		}

		return toItemGetResponse(
			"channels",
			new ChannelsEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker, expand),
			id);
	}

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
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			sorts);
	}

	@PatchMapping("/{id}")
	public String patchChannel(
		@PathVariable String id, @RequestBody String json) {

		JSONObject jsonObject = _faroInfoChannelDog.patchChannel(
			id, new JSONObject(json));

		return jsonObject.toString();
	}

	@PostMapping
	public String postChannel(@RequestBody String json) throws Exception {
		PostResponse postResponse = new PostResponse() {

			@Override
			protected JSONObject invokeElasticsearch(JSONObject jsonObject) {
				try {
					return _faroInfoChannelDog.addChannel(
						Collections.emptyList(), jsonObject.getString("name"));
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		};

		postResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		postResponse.setJSON(json);

		return postResponse.respond();
	}

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

}