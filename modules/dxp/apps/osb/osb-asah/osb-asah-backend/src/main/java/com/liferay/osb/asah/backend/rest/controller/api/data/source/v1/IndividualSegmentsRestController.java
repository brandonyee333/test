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
import com.liferay.osb.asah.backend.rest.response.MembershipChangesHistogramTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.embedded.IndividualSegmentsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.backend.rest.response.embedded.MembershipChangesEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.rest.response.PostResponse;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.spring.annotation.Cacheable;
import com.liferay.osb.asah.common.spring.annotation.SuppressErrorLogging;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 * @author Rachael Koestartyo
 */
@RequestMapping(
	produces = "application/json", value = "/api/1.0/individual-segments"
)
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualSegmentsRestController"
)
public class IndividualSegmentsRestController extends BaseRestController {

	@GetMapping(params = "!apply", value = "/{id}/individuals")
	@SuppressErrorLogging(ResourceNotFoundException.class)
	public String getIndividuals(
			@PathVariable String id,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(required = false) Boolean includeAnonymousUsers,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"individuals", null, page,
			_getIndividualsQueryBuilder(
				includeAnonymousUsers, id, filterString),
			size, sorts);
	}

	@GetMapping("/{id}")
	public String getIndividualSegment(
			@PathVariable String id,
			@RequestParam(required = false) String expand)
		throws Exception {

		if (StringUtils.isEmpty(expand)) {
			return toItemGetResponse("individual-segments", id);
		}

		return toItemGetResponse(
			"individual-segments",
			new IndividualSegmentsEmbeddedJSONObjectCreator(
				dxpRawElasticsearchInvoker, faroInfoElasticsearchInvoker,
				expand),
			id);
	}

	@GetMapping(params = "!apply")
	public String getIndividualSegments(
			@RequestParam(required = false) String dataSourceId,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"individual-segments", null, page,
			_getIndividualSegmentsQueryBuilder(dataSourceId, filterString),
			size, sorts);
	}

	@GetMapping(params = "apply")
	public String getIndividualSegmentTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"individual-segments", page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"individual-segment-transformations");
	}

	@GetMapping(params = "apply", value = "/{id}/individuals")
	public String getIndividualTransformations(
			@PathVariable String id, @RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(required = false) Boolean includeAnonymousUsers,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"individuals", page,
			_getIndividualsQueryBuilder(
				includeAnonymousUsers, id, filterString),
			size, null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"individual-transformations");
	}

	@GetMapping(params = "!apply", value = "/{id}/membership-changes")
	public String getMembershipChanges(
			@PathVariable String id,
			@RequestParam(required = false) String expand,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		EmbeddedJSONObjectCreator embeddedJSONObjectCreator = null;

		if (StringUtils.isNotEmpty(expand)) {
			embeddedJSONObjectCreator =
				new MembershipChangesEmbeddedJSONObjectCreator(
					faroInfoElasticsearchInvoker, expand);
		}

		return toCollectionGetResponse(
			"membership-changes", embeddedJSONObjectCreator, page,
			_getMembershipChangesQueryBuilder(filterString, id), size, sorts);
	}

	@Cacheable
	@GetMapping(params = "apply", value = "/{id}/membership-changes")
	public String getMembershipChangeTransformations(
			@PathVariable String id, @RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "true") boolean includeToday,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			apply, "membership-changes", page,
			_getMembershipChangesQueryBuilder(filterString, id), size,
			"dateChanged",
			new MembershipChangesHistogramTransformationJSONArrayFunction(
				includeToday),
			"membership-change-transformations");
	}

	@GetMapping("/{id}/memberships")
	public String getMemberships(
			@PathVariable String id,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"memberships", null, page,
			_getMembershipsQueryBuilder(filterString, id), size, sorts);
	}

	@PostMapping
	public String postIndividualSegment(@RequestBody String json)
		throws Exception {

		PostResponse postResponse = new PostResponse() {

			@Override
			protected JSONObject invokeElasticsearch(JSONObject jsonObject) {
				try {
					return _faroInfoIndividualSegmentDog.addIndividualSegment(
						jsonObject);
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			protected void onBeforeAdd(JSONObject jsonObject) {
				super.onBeforeAdd(jsonObject);

				jsonObject.put("activitiesCount", 0);
			}

		};

		postResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		postResponse.setJSON(json);

		return postResponse.respond();
	}

	@PostMapping("/{id}/memberships")
	public String postMembership(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		JSONObject individualSegmentJSONObject = new JSONObject(
			getIndividualSegment(id, null));

		if (!Objects.equals(
				individualSegmentJSONObject.get("segmentType"), "STATIC") ||
			!Objects.equals(
				individualSegmentJSONObject.get("status"), "ACTIVE")) {

			throw new Exception(
				"Membership POST requests can only be made to an active " +
					"static individual segment");
		}

		PostResponse postResponse = new PostResponse() {

			@Override
			public String respond() throws Exception {
				if (json.startsWith("{")) {
					JSONObject jsonObject = new JSONObject(json);

					String individualId = jsonObject.getString("individualId");

					if (_isMember(individualId, id)) {
						return null;
					}

					onBeforeAdd(jsonObject);

					JSONObject responseJSONObject =
						_membershipDog.addMembership(jsonObject);

					onBeforeReturn(responseJSONObject);

					return responseJSONObject.toString();
				}

				JSONArray jsonArray = new JSONArray(json);

				Iterator<Object> iterator = jsonArray.iterator();

				while (iterator.hasNext()) {
					JSONObject jsonObject = (JSONObject)iterator.next();

					String individualId = jsonObject.getString("individualId");

					if (_isMember(individualId, id)) {
						iterator.remove();

						continue;
					}

					onBeforeAdd(jsonObject);
				}

				if (jsonArray.length() == 0) {
					return null;
				}

				JSONArray responseJSONArray = _membershipDog.addMemberships(
					jsonArray);

				for (int i = 0; i < jsonArray.length(); i++) {
					onBeforeReturn(jsonArray.getJSONObject(i));
				}

				return responseJSONArray.toString();
			}

			@Override
			protected void onBeforeAdd(JSONObject jsonObject) {
				super.onBeforeAdd(jsonObject);

				jsonObject.put("individualSegmentId", id);
			}

			@Override
			protected void onBeforeReturn(JSONObject responseJSONObject) {
				responseJSONObject.remove("id");
			}

			private boolean _isMember(String individualId, String id) {
				if (_membershipDog.isMember(individualId, id)) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Not adding membership because individual " +
								individualId + " is already a member of " +
									"individual segment " + id);
					}

					return true;
				}

				return false;
			}

		};

		postResponse.setCollectionName("memberships");
		postResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		postResponse.setJSON(json);

		return postResponse.respond();
	}

	private QueryBuilder _getIndividualSegmentsQueryBuilder(
			String dataSourceId, String filterString)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		if (StringUtils.isEmpty(dataSourceId)) {
			return queryBuilder;
		}

		List<Channel> channels = _channelDog.getChannels(
			Long.valueOf(dataSourceId));

		if (channels.isEmpty()) {
			return queryBuilder;
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery(
				"channelId",
				ListUtil.map(
					channels, channel -> String.valueOf(channel.getId()))));

		if (queryBuilder == null) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(queryBuilder);
	}

	private QueryBuilder _getIndividualsQueryBuilder(
			Boolean includeAnonymousUsers, String individualSegmentId,
			String filterString)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"individualSegmentIds", individualSegmentId));

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.get(
				"individual-segments", individualSegmentId);

		String channelId = individualSegmentJSONObject.optString("channelId");

		if (StringUtils.isNotEmpty(channelId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("channelIds", channelId));
		}

		if (includeAnonymousUsers == null) {
			includeAnonymousUsers = individualSegmentJSONObject.optBoolean(
				"includeAnonymousUsers");
		}

		if (!includeAnonymousUsers) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("demographics.email"));
		}

		if (StringUtils.isEmpty(filterString)) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(
			FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoIndividualsFilterStringConverterHelper));
	}

	private QueryBuilder _getMembershipChangesQueryBuilder(
			String filterString, String individualSegmentId)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"individualSegmentId", individualSegmentId));

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.get(
				"individual-segments", individualSegmentId);

		if (!individualSegmentJSONObject.optBoolean("includeAnonymousUsers")) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("individualEmail"));
		}

		if (StringUtils.isEmpty(filterString)) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(
			FilterStringToQueryBuilderConverter.convert(filterString));
	}

	private QueryBuilder _getMembershipsQueryBuilder(
			String filterString, String individualSegmentId)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("individualSegmentId", individualSegmentId)
		).filter(
			QueryBuilders.termQuery("status", "ACTIVE")
		);

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.get(
				"individual-segments", individualSegmentId);

		if (!individualSegmentJSONObject.optBoolean("includeAnonymousUsers")) {
			JSONArray individualsJSONArray = faroInfoElasticsearchInvoker.get(
				"individuals",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("demographics.email")
				).filter(
					QueryBuilders.termsQuery(
						"individualSegmentIds", individualSegmentId)
				));

			boolQueryBuilder.filter(
				QueryBuilders.termsQuery(
					"individualId",
					JSONUtil.toStringSet(individualsJSONArray, "id")));
		}

		if (StringUtils.isEmpty(filterString)) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(
			FilterStringToQueryBuilderConverter.convert(filterString));
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentsRestController.class);

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private MembershipDog _membershipDog;

}