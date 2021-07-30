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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Component
public class UserSessionDog {

	public List<UserSession> findByIds(Collection<String> ids) {
		JSONArray userSessionJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"user-sessions", QueryBuilders.termsQuery("id", ids));

		return userSessionJSONArray.toList(
		).stream(
		).map(
			userSessionJSONObject -> _objectMapper.convertValue(
				userSessionJSONObject, UserSession.class)
		).collect(
			Collectors.toList()
		);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}