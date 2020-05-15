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

package com.liferay.osb.asah.backend.rest.response.embedded;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

/**
 * @author Matthew Kong
 */
public class ChannelsEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public ChannelsEmbeddedJSONObjectCreator(
		ElasticsearchInvoker elasticsearchInvoker, String expand) {

		_elasticsearchInvoker = elasticsearchInvoker;
		_expand = expand;
	}

	@Override
	public JSONObject create(String id) throws Exception {
		if (StringUtils.isEmpty(_expand)) {
			return null;
		}

		Set<String> expandParts = new HashSet<>(
			Arrays.asList(_expand.split(",")));

		if (!expandParts.contains("data-sources")) {
			if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + _expand);
			}

			return null;
		}

		JSONObject channelJSONObject = _elasticsearchInvoker.get(
			"channels", id);

		return JSONUtil.put(
			"data-sources",
			_elasticsearchInvoker.get(
				"data-sources",
				QueryBuilders.termsQuery(
					"id",
					JSONUtil.toStringArray(
						channelJSONObject.getJSONArray("dataSources"), "id"))));
	}

	private static final Log _log = LogFactory.getLog(
		ChannelsEmbeddedJSONObjectCreator.class);

	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final String _expand;

}