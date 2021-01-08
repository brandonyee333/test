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

package com.liferay.osb.asah.stream.curator.bot.nanite.session;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.arm.FinalizeSessionArm;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class SessionFinalizerNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "user-session";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE * 5;
	}

	@PostConstruct
	public void init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private JSONObject _getOSBAsahMarkerJSONObject() {
		JSONObject osbAsahMarkerJSONObject =
			_cerebroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", "SessionNanite");

		if (osbAsahMarkerJSONObject == null) {
			osbAsahMarkerJSONObject = JSONUtil.put("id", "SessionNanite");

			_cerebroInfoElasticsearchInvoker.add(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}

		return osbAsahMarkerJSONObject;
	}

	private QueryBuilder _getQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("completed", false)
		).filter(
			BoolQueryBuilderUtil.should(
				QueryBuilders.rangeQuery(
					"lastEventDate"
				).lt(
					"now-30m"
				)
			).should(
				QueryBuilders.rangeQuery(
					"lastEventDate"
				).lt(
					"now/d"
				)
			)
		);
	}

	private void _run() throws Exception {
		JSONObject osbAsahMarkerJSONObject = _getOSBAsahMarkerJSONObject();

		String lastSuccessfulSessionFinalizerDate =
			osbAsahMarkerJSONObject.optString(
				"lastSuccessfulSessionFinalizerDate", "now-30m");

		String dateString = DateUtil.newDateString();

		JSONArrayIterator.of(
			"user-sessions", _cerebroInfoElasticsearchInvoker,
			userSessionJSONObject -> {
				_finalizeSessionArm.updateAssets(
					_objectMapper.readValue(
						userSessionJSONObject.toString(), UserSession.class));

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("completed", true)
			).filter(
				QueryBuilders.rangeQuery(
					"completeDate"
				).gt(
					lastSuccessfulSessionFinalizerDate
				)
			)
		).iterate();

		while (true) {
			long start = System.currentTimeMillis();

			String userSessionsJSON = _cerebroInfoElasticsearchInvoker.get(
				"user-sessions",
				searchSourceBuilder -> {
					searchSourceBuilder.fetchSource(null, "interactions");
					searchSourceBuilder.query(_getQueryBuilder());
					searchSourceBuilder.size(50);
				});

			UserSession[] userSessions = _objectMapper.readValue(
				userSessionsJSON, UserSession[].class);

			if (userSessions.length == 0) {
				break;
			}

			for (UserSession userSession : userSessions) {
				_finalizeSessionArm.processSession(userSession);
			}

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info(
					String.format(
						"%s processed %d sessions in %d ms",
						clazz.getSimpleName(), userSessions.length,
						System.currentTimeMillis() - start));
			}
		}

		_cerebroInfoElasticsearchInvoker.update(
			"OSBAsahMarkers",
			osbAsahMarkerJSONObject.put(
				"lastSuccessfulSessionFinalizerDate", dateString));
	}

	private static final Log _log = LogFactory.getLog(
		SessionFinalizerNanite.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FinalizeSessionArm _finalizeSessionArm;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}