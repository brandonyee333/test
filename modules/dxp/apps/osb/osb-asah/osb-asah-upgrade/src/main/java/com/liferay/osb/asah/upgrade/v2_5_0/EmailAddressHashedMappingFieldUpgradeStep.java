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

package com.liferay.osb.asah.upgrade.v2_5_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class EmailAddressHashedMappingFieldUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_individualSegmentIdRemovalScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "individual-segment-id-removal-script.painless");

		_upgradeIndividuals(elasticsearchInvoker);
		_upgradeSuppressions(elasticsearchInvoker);

		_upgradeIndividualSegments(elasticsearchInvoker);
	}

	private long _getActiveIndividualCount(
		ElasticsearchInvoker elasticsearchInvoker, String individualSegmentId) {

		JSONObject osbAsahMarkerJSONObject = elasticsearchInvoker.fetch(
			"OSBAsahMarkers", "IndividualEngagementScoresNanite");

		if (osbAsahMarkerJSONObject == null) {
			return -1;
		}

		String lastSuccessfulDay = osbAsahMarkerJSONObject.optString(
			"lastSuccessfulDay");

		if (lastSuccessfulDay == null) {
			return -1;
		}

		return elasticsearchInvoker.count(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", lastSuccessfulDay)
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			));
	}

	private int _getActivitiesCount(
		ElasticsearchInvoker elasticsearchInvoker, String individualSegmentId) {

		SearchResponse searchResponse = elasticsearchInvoker.search(
			"individuals",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.sum(
						"activitiesCount"
					).field(
						"activitiesCount"
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"individualSegmentIds", individualSegmentId)
					).filter(
						QueryBuilders.existsQuery("demographics.email")
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Sum sum = aggregations.get("activitiesCount");

		if (sum == null) {
			return 0;
		}

		return (int)sum.getValue();
	}

	private void _removeAnonymousIndividualsFromSegment(
			ElasticsearchInvoker elasticsearchInvoker,
			String individualSegmentId)
		throws Exception {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				elasticsearchBulkRequestBuilder.update(
					"individuals", individualJSONObject.getString("id"),
					new Script(
						Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
						_individualSegmentIdRemovalScriptSource,
						Collections.singletonMap(
							"individualSegmentId", individualSegmentId)));

				String individualId = individualJSONObject.getString("id");

				_removeMemberships(
					elasticsearchInvoker, individualId, individualSegmentId);

				_removeIndividualSegmentIdFromEngagements(
					elasticsearchInvoker, individualId, individualSegmentId);

				return elasticsearchBulkRequestBuilder;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("demographics.email")
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId)
			)
		).iterate();
	}

	private void _removeIndividualSegmentIdFromEngagements(
			ElasticsearchInvoker elasticsearchInvoker, String individualId,
			String individualSegmentId)
		throws Exception {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			"engagements", elasticsearchInvoker,
			engagementJSONObject -> elasticsearchBulkRequestBuilder.update(
				"engagements", engagementJSONObject.getString("id"),
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					_individualSegmentIdRemovalScriptSource +
						"ctx._source.remove('emailAddress')",
					Collections.singletonMap(
						"individualSegmentId", individualSegmentId)))
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId)
			).filter(
				QueryBuilders.termQuery("ownerId", individualId)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			)
		).iterate();
	}

	private void _removeMemberships(
		ElasticsearchInvoker elasticsearchInvoker, String individualId,
		String individualSegmentId) {

		elasticsearchInvoker.deleteByQuery(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("individualId", individualId)
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentId", individualSegmentId)
			),
			true, "memberships", "membership-changes");
	}

	private void _upgradeAssets(String individualId) throws Exception {
		String[] assets = {
			"blog-clicks", "blog-social-shares", "blog-traffic-sources",
			"blogs", "custom-assets", "document-libraries", "forms",
			"journal-clicks", "journals", "pages", "page-referrers"
		};

		ElasticsearchInvoker cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			cerebroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		for (String asset : assets) {
			JSONArrayIterator.of(
				asset, cerebroInfoElasticsearchInvoker,
				assetJSONObject -> {
					elasticsearchBulkRequestBuilder.update(
						asset, assetJSONObject.put("knownIndividual", false));

					return elasticsearchBulkRequestBuilder;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("individualId", individualId)
				).filter(
					QueryBuilders.termQuery("knownIndividual", true)
				)
			).iterate();
		}
	}

	private void _upgradeIndividuals(ElasticsearchInvoker elasticsearchInvoker)
		throws Exception {

		_elasticsearchIndexManager.updateMapping(
			"individuals",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"emailAddressHashed", JSONUtil.put("type", "keyword"))
			).toString(),
			"individuals", WeDeployDataService.OSB_ASAH_FARO_INFO);

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				JSONObject demographicsJSONObject =
					individualJSONObject.getJSONObject("demographics");

				String email = FaroInfoIndividualUtil.getIndividualEmail(
					demographicsJSONObject);

				if (StringUtils.isBlank(email)) {
					return elasticsearchBulkRequestBuilder;
				}

				String script =
					"ctx._source['emailAddressHashed'] = " +
						"params.emailAddressHashed;";

				if (demographicsJSONObject.length() == 1) {
					String individualId = individualJSONObject.getString("id");

					if (_log.isInfoEnabled()) {
						_log.info(
							"Found un-synced known individual: " +
								individualId);
					}

					_upgradeAssets(individualId);
					_upgradeMembershipChanges(
						elasticsearchInvoker, individualId);

					script += "ctx._source.remove('demographics')";
				}

				elasticsearchBulkRequestBuilder.update(
					"individuals", individualJSONObject.getString("id"),
					new Script(
						Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
						script,
						Collections.singletonMap(
							"emailAddressHashed",
							DigestUtils.sha256Hex(email))));

				return elasticsearchBulkRequestBuilder;
			}
		).setQueryBuilder(
			QueryBuilders.existsQuery("demographics.email")
		).iterate();
	}

	private void _upgradeIndividualSegments(
			ElasticsearchInvoker elasticsearchInvoker)
		throws Exception {

		JSONArray individualSegmentsJSONArray = elasticsearchInvoker.get(
			"individual-segments");

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		for (int i = 0; i < individualSegmentsJSONArray.length(); i++) {
			JSONObject individualSegmentJSONObject =
				individualSegmentsJSONArray.getJSONObject(i);

			String individualSegmentName =
				individualSegmentJSONObject.getString("name");

			if (individualSegmentName.startsWith("Account: ")) {
				continue;
			}

			String individualSegmentId = individualSegmentJSONObject.getString(
				"id");

			long currentKnownIndividualCount = elasticsearchInvoker.count(
				"individuals",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentIds", individualSegmentId)
				).filter(
					QueryBuilders.existsQuery("demographics.email")
				));

			long previousKnownIndividualCount =
				individualSegmentJSONObject.getInt("knownIndividualCount");

			if (currentKnownIndividualCount == previousKnownIndividualCount) {
				continue;
			}

			boolean includeAnonymousIndividuals =
				individualSegmentJSONObject.optBoolean(
					"includeAnonymousUsers", true);

			StringBuilder sb = new StringBuilder();

			sb.append(
				"ctx._source.knownIndividualCount = " +
					"params.knownIndividualCount;");

			Map<String, Object> parameters = new HashMap<String, Object>() {
				{
					put("knownIndividualCount", currentKnownIndividualCount);
				}
			};

			if (includeAnonymousIndividuals) {
				long anonymousIndividualCount =
					individualSegmentJSONObject.getLong("individualCount") -
						currentKnownIndividualCount;

				sb.append("ctx._source.anonymousIndividualCount = ");
				sb.append("params.anonymousIndividualCount;");

				parameters.put(
					"anonymousIndividualCount", anonymousIndividualCount);
			}
			else {
				sb.append("ctx._source.individualCount = ");
				sb.append("params.knownIndividualCount;");

				_removeAnonymousIndividualsFromSegment(
					elasticsearchInvoker, individualSegmentId);

				sb.append(
					"ctx._source.activitiesCount = params.activitiesCount;");
				sb.append("ctx._source.activeIndividualCount = ");
				sb.append("params.activeIndividualCount;");

				parameters.put(
					"activeIndividualCount",
					_getActiveIndividualCount(
						elasticsearchInvoker, individualSegmentId));
				parameters.put(
					"activitiesCount",
					_getActivitiesCount(
						elasticsearchInvoker, individualSegmentId));
			}

			elasticsearchBulkRequestBuilder.update(
				"individual-segments", individualSegmentId,
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					sb.toString(), parameters));

			JSONArray membershipChangesJSONArray = new JSONArray(
				elasticsearchInvoker.get(
					"membership-changes",
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"individualSegmentId",
									individualSegmentId)));
						searchSourceBuilder.size(1);
						searchSourceBuilder.sort("id", SortOrder.DESC);
					}));

			if (membershipChangesJSONArray.length() > 0) {
				JSONObject membershipChangeJSONObject =
					membershipChangesJSONArray.getJSONObject(0);

				sb = new StringBuilder();

				sb.append("ctx._source.knownIndividualsCount = ");
				sb.append("params.knownIndividualCount;");

				if (!includeAnonymousIndividuals) {
					sb.append("ctx._source.individualsCount = ");
					sb.append("params.knownIndividualCount;");
				}

				elasticsearchBulkRequestBuilder.update(
					"membership-changes",
					membershipChangeJSONObject.getString("id"),
					new Script(
						Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
						sb.toString(),
						Collections.singletonMap(
							"knownIndividualCount",
							currentKnownIndividualCount)));
			}
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}
	}

	private void _upgradeMembershipChanges(
			ElasticsearchInvoker elasticsearchInvoker, String individualId)
		throws Exception {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"membership-changes", elasticsearchInvoker,
			membershipChangesJSONObject ->
				elasticsearchBulkRequestBuilder.update(
					"membership-changes",
					membershipChangesJSONObject.getString("id"),
					new Script(
						Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
						"ctx._source.remove('individualEmail')",
						Collections.emptyMap()))
		).setQueryBuilder(
			QueryBuilders.termQuery("individualId", individualId)
		).iterate();
	}

	private void _upgradeSuppressions(ElasticsearchInvoker elasticsearchInvoker)
		throws Exception {

		_elasticsearchIndexManager.updateMapping(
			"suppressions",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"emailAddressHashed", JSONUtil.put("type", "keyword"))
			).toString(),
			"suppressions", WeDeployDataService.OSB_ASAH_FARO_INFO);

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"suppressions", elasticsearchInvoker,
			suppressionJSONObject -> {
				elasticsearchBulkRequestBuilder.update(
					"suppressions",
					suppressionJSONObject.put(
						"emailAddressHashed",
						DigestUtils.sha256Hex(
							suppressionJSONObject.getString("email"))));

				return elasticsearchBulkRequestBuilder;
			}
		).iterate();
	}

	private static final Log _log = LogFactory.getLog(
		EmailAddressHashedMappingFieldUpgradeStep.class);

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private String _individualSegmentIdRemovalScriptSource;

}