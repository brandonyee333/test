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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoIndividualDog extends BaseFaroInfoDog {

	public boolean addDataSourceIndividualPK(
		String dataId, Long dataSourceId, String dataSourceType,
		JSONObject individualJSONObject) {

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceIndividualPKs");

		Map<String, JSONArray> individualPKsJSONArrays =
			FaroInfoIndividualUtil.getIndividualPKsJSONArrays(
				dataSourceIndividualPKsJSONArray);

		Set<String> dataSourceIds = individualPKsJSONArrays.keySet();

		if (dataSourceIds.contains(String.valueOf(dataSourceId))) {
			JSONArray individualPKsJSONArray = individualPKsJSONArrays.get(
				String.valueOf(dataSourceId));

			if (JSONUtil.hasValue(individualPKsJSONArray, dataId)) {
				return false;
			}

			individualJSONObject.put(
				"dataSourceIndividualPKs",
				JSONUtil.replace(
					dataSourceIndividualPKsJSONArray, "dataSourceId",
					JSONUtil.put(
						"dataSourceId", String.valueOf(dataSourceId)
					).put(
						"dataSourceType", dataSourceType
					).put(
						"individualPKs", individualPKsJSONArray.put(dataId)
					)));
		}
		else {
			dataSourceIndividualPKsJSONArray.put(
				JSONUtil.put(
					"dataSourceId", String.valueOf(dataSourceId)
				).put(
					"dataSourceType", dataSourceType
				).put(
					"individualPKs", JSONUtil.put(dataId)
				));
		}

		return true;
	}

	public JSONObject addIndividual(
		JSONObject individualJSONObject, boolean updateMemberships) {

		String emailAddress = StringUtils.lowerCase(
			FaroInfoIndividualUtil.getIndividualEmail(
				individualJSONObject.optJSONObject("demographics")));

		if (StringUtils.isNotBlank(emailAddress)) {
			individualJSONObject.put(
				"emailAddressHashed", DigestUtils.sha256Hex(emailAddress));
		}

		individualJSONObject = _fieldDog.addOwnerJSONObject(
			"individuals", individualJSONObject, "custom", "demographics");

		if (updateMemberships) {
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					"dateModified",
					individualJSONObject.getString("dateModified")
				).put(
					"individualJSONObject", individualJSONObject
				));
		}

		return individualJSONObject;
	}

	public JSONObject addIndividual(
		JSONObject analyticsDataJSONObject, Long channelId,
		DataSource dataSource, String emailAddressHashed, String userId) {

		JSONArray channelIds = new JSONArray();

		if (Objects.nonNull(channelId)) {
			channelIds.put(String.valueOf(channelId));
		}

		String dateString = DateUtil.newDateString();

		return addIndividual(
			JSONUtil.put(
				"activitiesCounts", new JSONArray()
			).put(
				"analyticsData", analyticsDataJSONObject
			).put(
				"channelIds", channelIds
			).put(
				"dataSourceAccountPKs", new JSONArray()
			).put(
				"dataSourceIndividualPKs",
				JSONUtil.put(
					JSONUtil.put(
						"dataSourceId", String.valueOf(dataSource.getId())
					).put(
						"dataSourceType", dataSource.getProviderType()
					).put(
						"individualPKs", JSONUtil.put(userId)
					))
			).put(
				"dateCreated", dateString
			).put(
				"dateModified", dateString
			).putOpt(
				"emailAddressHashed", emailAddressHashed
			).put(
				"individualSegmentIds", new JSONArray()
			),
			false);
	}

	public JSONObject addIndividual(
			String dataId, JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		JSONObject contextJSONObject = _fieldDog.buildContextJSONObject(
			"demographics", dataJSONObject, dataSource, "individual");

		if (contextJSONObject.optJSONArray("email") == null) {
			return null;
		}

		JSONArray dataSourceAccountPKsJSONArray = new JSONArray();
		Long dataSourceId = dataSource.getId();
		String dateString = DateUtil.newDateString();

		String providerType = dataSource.getProviderType();

		if (providerType.equals("SALESFORCE")) {
			JSONArray dataAccountPKsJSONArray = dataJSONObject.optJSONArray(
				"accountPKs");

			if (dataAccountPKsJSONArray != null) {
				dataSourceAccountPKsJSONArray.put(
					JSONUtil.put(
						"accountPKs", dataAccountPKsJSONArray
					).put(
						"dataSourceId", String.valueOf(dataSourceId)
					));
			}
		}

		JSONArray individualPKsJSONArray = new JSONArray();

		if (dataId != null) {
			individualPKsJSONArray.put(dataId);
		}

		JSONObject individualJSONObject = JSONUtil.put(
			"activitiesCount", 0
		).put(
			"custom",
			_fieldDog.buildContextJSONObject(
				"custom", dataJSONObject, dataSource, "individual")
		).put(
			"dataSourceAccountPKs", dataSourceAccountPKsJSONArray
		).put(
			"dataSourceIndividualPKs",
			JSONUtil.put(
				JSONUtil.put(
					"dataSourceId", String.valueOf(dataSourceId)
				).put(
					"dataSourceType", providerType
				).put(
					"individualPKs", individualPKsJSONArray
				))
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"demographics", contextJSONObject
		).put(
			"firstEnrichmentDate", dateString
		).put(
			"individualSegmentIds", new JSONArray()
		);

		_updateIndividualAssociations(dataJSONObject, individualJSONObject);

		return addIndividual(individualJSONObject, true);
	}

	public JSONObject addIndividualAssociation(
		DXPEntityType dxpEntityType, String id,
		JSONObject individualJSONObject) {

		JSONArray jsonArray = individualJSONObject.optJSONArray(
			dxpEntityType.getIndividualFieldName());

		if (jsonArray == null) {
			jsonArray = new JSONArray();
		}

		jsonArray.put(id);

		JSONObject partialIndividualJSONObject = JSONUtil.put(
			dxpEntityType.getIndividualFieldName(), jsonArray);

		return elasticsearchInvoker.update(
			"individuals", individualJSONObject.getString("id"),
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				_getScriptSource(partialIndividualJSONObject),
				partialIndividualJSONObject.toMap()));
	}

	public JSONObject addIndividualAssociation(
		long classPK, Long dataSourceId, DXPEntityType dxpEntityType,
		JSONObject individualJSONObject) {

		if (individualJSONObject == null) {
			return null;
		}

		List<String> associatedIds = getAssociatedIds(
			dataSourceId, dxpEntityType, Collections.singletonList(classPK));

		if (associatedIds.isEmpty()) {
			return individualJSONObject;
		}

		return addIndividualAssociation(
			dxpEntityType, associatedIds.get(0), individualJSONObject);
	}

	public void deleteIndividual(String deletionDateString, String individualId)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("ownerId", individualId)
		).filter(
			QueryBuilders.termQuery("ownerType", "individual")
		);

		elasticsearchInvoker.delete("fields", boolQueryBuilder);

		elasticsearchInvoker.delete("interests", boolQueryBuilder);

		elasticsearchInvoker.delete("visited-pages", boolQueryBuilder);

		JSONArray activeMembershipsJSONArray = elasticsearchInvoker.get(
			"memberships",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("individualId", individualId)
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			));

		for (int i = 0; i < activeMembershipsJSONArray.length(); i++) {
			JSONObject activeMembershipJSONObject =
				activeMembershipsJSONArray.getJSONObject(i);

			_membershipDog.deactivateMembership(
				deletionDateString, individualId,
				activeMembershipJSONObject.getString("individualSegmentId"));
		}

		elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("individualId", individualId), true,
			new Script("ctx._source.individualDeleted = true"),
			"membership-changes");

		elasticsearchInvoker.delete("individuals", individualId);
	}

	public JSONObject deleteIndividualAssociation(
		long classPK, Long dataSourceId, DXPEntityType dxpEntityType,
		JSONObject individualJSONObject) {

		if (individualJSONObject == null) {
			return null;
		}

		JSONArray jsonArray = individualJSONObject.optJSONArray(
			dxpEntityType.getIndividualFieldName());

		if ((jsonArray == null) || (jsonArray.length() == 0)) {
			return null;
		}

		List<String> associatedIds = getAssociatedIds(
			dataSourceId, dxpEntityType, Collections.singletonList(classPK));

		if (associatedIds.isEmpty()) {
			return null;
		}

		JSONUtil.removeValue(jsonArray, associatedIds.get(0));

		JSONObject partialIndividualJSONObject = JSONUtil.put(
			dxpEntityType.getIndividualFieldName(), jsonArray);

		return elasticsearchInvoker.update(
			"individuals", individualJSONObject.getString("id"),
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				_getScriptSource(partialIndividualJSONObject),
				partialIndividualJSONObject.toMap()));
	}

	public List<String> getAssociatedIds(
		Long dataSourceId, DXPEntityType dxpEntityType, List<Long> classPKs) {

		JSONArray associatedIdsJSONArray = null;

		if (dxpEntityType.isOrganization()) {
			associatedIdsJSONArray = elasticsearchInvoker.get(
				dxpEntityType.getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termsQuery("organizationPK", classPKs)
				));
		}
		else {
			associatedIdsJSONArray = _dxpRawElasticsearchInvoker.get(
				dxpEntityType.getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"osbAsahDataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termsQuery(
						dxpEntityType.getIdFieldName(), classPKs)
				));
		}

		return JSONUtil.toStringList(associatedIdsJSONArray, "id");
	}

	public JSONObject getIndividualJSONObject(
		Long dataSourceId, String userId) {

		return elasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId",
						String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termsQuery(
						"dataSourceIndividualPKs.individualPKs", userId)
				),
				ScoreMode.None));
	}

	public String getIndividualName(String individualId) {
		JSONObject individualJSONObject = elasticsearchInvoker.fetch(
			"individuals", individualId);

		if (individualJSONObject == null) {
			return null;
		}

		return FaroInfoIndividualUtil.getIndividualName(
			individualJSONObject.optJSONObject("demographics"));
	}

	public Set<String> getIndividualSegmentNames(
		Long channelId, JSONObject individualJSONObject) {

		if (individualJSONObject == null) {
			return Collections.emptySet();
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery(
				"id",
				JSONUtil.toStringSet(
					individualJSONObject.getJSONArray("individualSegmentIds")))
		).filter(
			QueryBuilders.termQuery("status", "ACTIVE")
		);

		if (Objects.nonNull(channelId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelId", String.valueOf(channelId)));
		}

		return JSONUtil.toStringSet(
			elasticsearchInvoker.get("individual-segments", boolQueryBuilder),
			"name");
	}

	@PostConstruct
	public void init() {
		String[] collections = JSONUtil.toStringArray(
			_elasticsearchIndexManager.getCollectionsJSONArray(
				WeDeployDataService.OSB_ASAH_CEREBRO_INFO));

		_collections = ArrayUtils.remove(
			collections, ArrayUtils.indexOf(collections, "user-sessions"));
	}

	public JSONObject removeDataSourceIndividualPKs(
		JSONObject individualJSONObject, Long dataSourceId) {

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceIndividualPKs");

		for (int i = 0; i < dataSourceIndividualPKsJSONArray.length(); i++) {
			JSONObject dataSourceIndividualPKsJSONObject =
				dataSourceIndividualPKsJSONArray.getJSONObject(i);

			if (Objects.equals(
					dataSourceIndividualPKsJSONObject.getLong("dataSourceId"),
					dataSourceId)) {

				dataSourceIndividualPKsJSONArray.remove(i);

				break;
			}
		}

		return elasticsearchInvoker.update(
			"individuals", individualJSONObject.getString("id"),
			JSONUtil.put(
				"dataSourceIndividualPKs", dataSourceIndividualPKsJSONArray));
	}

	public JSONObject updateIndividual(
		String individualId, JSONObject partialIndividualJSONObject,
		boolean updateMemberships) {

		String oldIndividualName = null;

		if (updateMemberships) {
			oldIndividualName = getIndividualName(individualId);
		}

		_setFirstEnrichmentDate(individualId, partialIndividualJSONObject);

		JSONObject individualJSONObject = elasticsearchInvoker.update(
			"individuals", individualId, partialIndividualJSONObject);

		if (updateMemberships) {
			_individualModified(individualJSONObject, oldIndividualName);
		}

		return individualJSONObject;
	}

	public JSONObject updateIndividual(
			String dataId, JSONObject dataJSONObject, DataSource dataSource,
			JSONObject individualJSONObject)
		throws Exception {

		Long dataSourceId = dataSource.getId();
		String individualId = individualJSONObject.getString("id");

		if ((dataId != null) && !dataId.equals(individualId)) {
			addDataSourceIndividualPK(
				dataId, dataSourceId, dataSource.getProviderType(),
				individualJSONObject);
		}

		boolean dataAccountPKsUpdated = false;

		String providerType = dataSource.getProviderType();

		if (providerType.equals("SALESFORCE")) {
			JSONArray dataAccountPKsJSONArray = dataJSONObject.optJSONArray(
				"accountPKs");

			if (dataAccountPKsJSONArray != null) {
				JSONArray dataSourceAccountPKsJSONArray =
					individualJSONObject.getJSONArray("dataSourceAccountPKs");

				JSONObject accountPKsJSONObject = JSONUtil.put(
					"accountPKs", dataAccountPKsJSONArray
				).put(
					"dataSourceId", String.valueOf(dataSourceId)
				);

				Set<Long> dataSourceIds = JSONUtil.toLongSet(
					dataSourceAccountPKsJSONArray, "dataSourceId");

				if (dataSourceIds.contains(dataSourceId)) {
					individualJSONObject.put(
						"dataSourceAccountPKs",
						JSONUtil.replace(
							dataSourceAccountPKsJSONArray, "dataSourceId",
							accountPKsJSONObject));
				}
				else {
					dataSourceAccountPKsJSONArray.put(accountPKsJSONObject);
				}

				dataAccountPKsUpdated = true;
			}
		}

		JSONObject previousDemographicsJSONObject = new JSONObject(
			individualJSONObject, new String[] {"demographics"});

		individualJSONObject = _fieldDog.updateContextFields(
			"custom", dataJSONObject, dataSource, individualJSONObject,
			"individual", "demographics", "email");
		individualJSONObject = _fieldDog.updateContextFields(
			"demographics", dataJSONObject, dataSource, individualJSONObject,
			"individual", "demographics", "email");

		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		if (demographicsJSONObject == null) {
			return null;
		}

		if (demographicsJSONObject.optJSONArray("email") == null) {
			deleteIndividual(DateUtil.newDateString(), individualId);

			return null;
		}

		String oldIndividualName = getIndividualName(individualId);

		JSONObject partialIndividualJSONObject = new JSONObject(
			individualJSONObject,
			new String[] {
				"custom", "dataSourceAccountPKs", "dataSourceIndividualPKs",
				"dateModified", "demographics"
			});

		_setFirstEnrichmentDate(individualId, partialIndividualJSONObject);

		JSONObject fieldsJSONObject = _fieldDog.getFieldsJSONObject(
			"demographics", dataJSONObject, dataSource);

		if ((fieldsJSONObject.names() != null) &&
			(!JSONUtil.equals(
				previousDemographicsJSONObject, demographicsJSONObject) ||
			 dataAccountPKsUpdated)) {

			partialIndividualJSONObject.put(
				"lastEnrichmentDate", DateUtil.newDateString());
		}

		if (!previousDemographicsJSONObject.has("email") &&
			demographicsJSONObject.has("email")) {

			_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualId", individualJSONObject.getString("id"))
				).filter(
					BoolQueryBuilderUtil.shouldNot(
						QueryBuilders.existsQuery("knownIndividual")
					).should(
						QueryBuilders.termQuery("knownIndividual", false)
					)
				),
				true,
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					"ctx._source.knownIndividual = true;",
					Collections.emptyMap()),
				_collections);
		}

		_updateIndividualAssociations(
			dataJSONObject, partialIndividualJSONObject);

		individualJSONObject = _updateIndividual(
			individualId, partialIndividualJSONObject);

		_individualModified(individualJSONObject, oldIndividualName);

		return individualJSONObject;
	}

	private void _buildDeleteContextFieldsScriptSource(
		String context, JSONObject individualJSONObject,
		JSONObject partialIndividualJSONObject, StringBuilder sb) {

		JSONObject contextJSONObject = individualJSONObject.optJSONObject(
			context);

		if (contextJSONObject == null) {
			return;
		}

		JSONObject newContextJSONObject =
			partialIndividualJSONObject.optJSONObject(context);

		for (String contextFieldName : contextJSONObject.keySet()) {
			if ((newContextJSONObject == null) ||
				!newContextJSONObject.has(contextFieldName)) {

				sb.append("ctx._source.");
				sb.append(context);
				sb.append(".remove('");
				sb.append(contextFieldName);
				sb.append("');");
			}
		}
	}

	private String _getScriptSource(JSONObject jsonObject) {
		StringBuilder sb = new StringBuilder();

		for (String key : jsonObject.keySet()) {
			sb.append("ctx._source.");
			sb.append(key);
			sb.append(" = params.");
			sb.append(key);
			sb.append(";");
		}

		return sb.toString();
	}

	private void _individualModified(
		JSONObject individualJSONObject, String oldIndividualName) {

		String individualId = individualJSONObject.getString("id");

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", individualJSONObject.getString("dateModified")
			).put(
				"individualJSONObject", individualJSONObject
			));

		String newIndividualName = FaroInfoIndividualUtil.getIndividualName(
			individualJSONObject.optJSONObject("demographics"));

		if (!Objects.equals(oldIndividualName, newIndividualName)) {
			elasticsearchInvoker.updateByQueryWithRetry(
				QueryBuilders.termQuery("individualId", individualId), true,
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					"ctx._source.individualName = params.individualName",
					Collections.singletonMap(
						"individualName", newIndividualName)),
				"membership-changes");
		}
	}

	private void _setFirstEnrichmentDate(
		String individualId, JSONObject individualJSONObject) {

		if (StringUtils.isEmpty(
				FaroInfoIndividualUtil.getIndividualEmail(
					individualJSONObject.optJSONObject("demographics")))) {

			return;
		}

		JSONObject oldIndividualJSONObject = elasticsearchInvoker.fetch(
			"individuals", individualId);

		if (StringUtils.isEmpty(
				FaroInfoIndividualUtil.getIndividualEmail(
					oldIndividualJSONObject.optJSONObject("demographics")))) {

			individualJSONObject.put(
				"firstEnrichmentDate", DateUtil.newDateString());
		}
	}

	private JSONObject _updateIndividual(
		String individualId, JSONObject partialIndividualJSONObject) {

		JSONObject individualJSONObject = elasticsearchInvoker.update(
			"individuals", individualId, partialIndividualJSONObject);

		StringBuilder sb = new StringBuilder();

		_buildDeleteContextFieldsScriptSource(
			"custom", individualJSONObject, partialIndividualJSONObject, sb);
		_buildDeleteContextFieldsScriptSource(
			"demographics", individualJSONObject, partialIndividualJSONObject,
			sb);

		if (sb.length() == 0) {
			return individualJSONObject;
		}

		return elasticsearchInvoker.update(
			"individuals", individualId, new Script(sb.toString()));
	}

	private void _updateIndividualAssociations(
		JSONObject dataJSONObject, JSONObject individualJSONObject) {

		JSONObject membershipsJSONObject = dataJSONObject.optJSONObject(
			"memberships");

		if (membershipsJSONObject == null) {
			return;
		}

		for (String type : membershipsJSONObject.keySet()) {
			DXPEntityType dxpEntityType = DXPEntityType.of(type);

			if (dxpEntityType == null) {
				continue;
			}

			individualJSONObject.put(
				dxpEntityType.getIndividualFieldName(),
				getAssociatedIds(
					dataJSONObject.getLong("osbAsahDataSourceId"),
					dxpEntityType,
					JSONUtil.toLongList(
						membershipsJSONObject.getJSONArray(type))));
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	private String[] _collections;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private MembershipDog _membershipDog;

}