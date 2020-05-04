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

package com.liferay.osb.asah.test.util.faro;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class FaroInfoTestUtil {

	public static JSONObject buildAccountFieldMappingJSONObject(
		String dataSourceId, String dataSourceFieldName, String fieldName,
		String fieldType) {

		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"author", _getAuthorJSONObject()
		).put(
			"context", "organization"
		).put(
			"dataSourceFieldNames",
			JSONUtil.put(dataSourceId, dataSourceFieldName)
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"fieldName", fieldName
		).put(
			"fieldType", fieldType
		).put(
			"ownerType", "account"
		).put(
			"strategy",
			JSONUtil.put(
				"configuration", new JSONObject()
			).put(
				"key", "MOST_RECENT"
			)
		);
	}

	public static JSONObject buildAccountIndividualSegmentJSONObject(
		JSONObject accountJSONObject) {

		return buildAccountIndividualSegmentJSONObject(
			accountJSONObject, RandomTestUtil.randomId());
	}

	public static JSONObject buildAccountIndividualSegmentJSONObject(
		JSONObject accountJSONObject, String channelId) {

		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"activitiesCount", 0
		).put(
			"channelId", channelId
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"filter",
			"((dataSourceAccountPKs/accountPKs eq '" +
				accountJSONObject.getString("accountPK") + "'))"
		).put(
			"individualCount", 0
		).put(
			"name", "Account: " + accountJSONObject.getString("id")
		).put(
			"scope", "PROJECT"
		).put(
			"segmentType", "DYNAMIC"
		).put(
			"status", "INACTIVE"
		);
	}

	public static JSONObject buildAccountJSONObject(
		JSONObject dataSourceJSONObject) {

		String accountPK = RandomStringUtils.randomAlphanumeric(18);
		String dataSourceId = dataSourceJSONObject.getString("id");
		String dateString = DateUtil.newDateString();
		String id = _timeOrderedUuidGenerator.generateId();

		return JSONUtil.put(
			"accountPK", accountPK
		).put(
			"activitiesCount", 0
		).put(
			"dataSourceId", dataSourceId
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"engagementScore", 0
		).put(
			"id", id
		).put(
			"individualCount", 0
		).put(
			"organization",
			JSONUtil.put(
				"accountId",
				JSONUtil.put(
					JSONUtil.put(
						"context", "organization"
					).put(
						"dataSourceId", dataSourceId
					).put(
						"dataSourceName", dataSourceJSONObject.getString("name")
					).put(
						"dateModified", dateString
					).put(
						"fieldType", "Text"
					).put(
						"name", "accountId"
					).put(
						"ownerId", id
					).put(
						"ownerType", "account"
					).put(
						"sourceName", "id"
					).put(
						"value", accountPK
					)))
		);
	}

	public static JSONObject buildActivityGroupJSONObject(
			String dataSourceId, JSONObject individualJSONObject)
		throws Exception {

		return buildActivityGroupJSONObject(
			dataSourceId, DateUtil.newDateString(), individualJSONObject);
	}

	public static JSONObject buildActivityGroupJSONObject(
			String dataSourceId, String dateString,
			JSONObject individualJSONObject)
		throws Exception {

		return buildActivityGroupJSONObject(
			RandomTestUtil.randomId(), dataSourceId, dateString,
			individualJSONObject);
	}

	public static JSONObject buildActivityGroupJSONObject(
			String channelId, String dataSourceId, String dateString,
			JSONObject individualJSONObject)
		throws Exception {

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceIndividualPKs");

		JSONArray individualPKsJSONArray =
			FaroInfoIndividualUtil.getIndividualPKsJSONArray(
				dataSourceId, dataSourceIndividualPKsJSONArray);

		return JSONUtil.put(
			"activityType", "BROWSE"
		).put(
			"channelId", channelId
		).put(
			"dataSourceId", dataSourceId
		).put(
			"day", DateUtil.newDayDateString(dateString)
		).put(
			"endTime", dateString
		).put(
			"ownerId", individualJSONObject.getString("id")
		).put(
			"startTime", dateString
		).put(
			"userId", individualPKsJSONArray.get(0)
		);
	}

	public static JSONObject buildActivityJSONObject(
		JSONObject activityGroupJSONObject, JSONObject assetJSONObject,
		String channelId, String eventId, String[] eventProperties) {

		return buildActivityJSONObject(
			activityGroupJSONObject, assetJSONObject, channelId, eventId,
			eventProperties, RandomTestUtil.randomUUID());
	}

	public static JSONObject buildActivityJSONObject(
		JSONObject activityGroupJSONObject, JSONObject assetJSONObject,
		String channelId, String eventId, String[] eventProperties,
		String pageViewActivityId) {

		if ((eventProperties.length % 2) != 0) {
			throw new IllegalArgumentException(
				"Event properties must be an even length");
		}

		String applicationId = assetJSONObject.getString("assetType");
		String assetId = assetJSONObject.getString("id");

		JSONObject eventPropertiesJSONObject = JSONUtil.put(
			"pageViewActivityId", pageViewActivityId);

		for (int i = 0; i < eventProperties.length; i += 2) {
			eventPropertiesJSONObject.put(
				String.valueOf(eventProperties[i]), eventProperties[i + 1]);
		}

		return JSONUtil.put(
			"activityKey", applicationId + "#" + eventId + "#" + assetId
		).put(
			"activityType", "BROWSE"
		).put(
			"applicationId", applicationId
		).put(
			"channelId", channelId
		).put(
			"dataSourceId", activityGroupJSONObject.getString("dataSourceId")
		).put(
			"day", activityGroupJSONObject.getString("day")
		).put(
			"endTime", activityGroupJSONObject.getString("endTime")
		).put(
			"eventId", eventId
		).put(
			"eventProperties", eventPropertiesJSONObject
		).put(
			"groupId", activityGroupJSONObject.getString("id")
		).put(
			"object",
			JSONUtil.put(
				"dataSourceAssetPK",
				assetJSONObject.getString("dataSourceAssetPK")
			).put(
				"id", assetId
			).put(
				"name", assetJSONObject.getString("name")
			).put(
				"objectType", applicationId
			)
		).put(
			"ownerId", activityGroupJSONObject.optString("ownerId")
		).put(
			"startTime", activityGroupJSONObject.getString("startTime")
		).put(
			"url", RandomTestUtil.randomURL()
		).put(
			"userId", activityGroupJSONObject.getString("userId")
		);
	}

	public static JSONObject buildActivityJSONObject(
		JSONObject activityGroupJSONObject, JSONObject assetJSONObject,
		String eventId, String[] eventProperties) {

		return buildActivityJSONObject(
			activityGroupJSONObject, assetJSONObject, RandomTestUtil.randomId(),
			eventId, eventProperties);
	}

	public static JSONObject buildActivityJSONObject(
		JSONObject activityGroupJSONObject, JSONObject assetJSONObject,
		String eventId, String[] eventProperties, String pageViewActivityId) {

		return buildActivityJSONObject(
			activityGroupJSONObject, assetJSONObject, RandomTestUtil.randomId(),
			eventId, eventProperties, pageViewActivityId);
	}

	public static JSONObject buildAssetJSONObject(
		String assetType, String dataSourceId) {

		return buildAssetJSONObject(
			assetType, RandomTestUtil.randomId(), dataSourceId);
	}

	public static JSONObject buildAssetJSONObject(
		String assetType, String channelId, String dataSourceId) {

		return JSONUtil.put(
			"assetType", assetType
		).put(
			"channelIds", JSONUtil.put(channelId)
		).put(
			"dataSourceAssetPK", String.valueOf(RandomTestUtil.randomNumber())
		).put(
			"dataSourceId", dataSourceId
		).put(
			"name", RandomTestUtil.randomMultipleWordString(5, 20)
		);
	}

	public static JSONObject buildChannelJSONObject(
		String dataSourceId, String channelType) {

		return JSONUtil.put(
			"channelType", channelType
		).put(
			"dataSourceId", dataSourceId
		).put(
			"groups",
			JSONUtil.putAll(
				JSONUtil.put(
					"id", RandomTestUtil.randomId()
				).put(
					"name", RandomTestUtil.randomString()
				),
				JSONUtil.put(
					"id", RandomTestUtil.randomId()
				).put(
					"name", RandomTestUtil.randomString()
				))
		);
	}

	public static JSONObject buildCSVDataSourceJSONObject() {
		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"about", RandomTestUtil.randomMultipleWordString(20, 50)
		).put(
			"author", _getAuthorJSONObject()
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"description", RandomTestUtil.randomMultipleWordString(20, 50)
		).put(
			"name", RandomTestUtil.randomMultipleWordString(5, 20)
		).put(
			"provider", JSONUtil.put("type", "CSV")
		).put(
			"status", "ACTIVE"
		);
	}

	public static JSONObject buildCSVIndividualJSONObject(
		String dataSourceId, String dataSourceIndividualPK,
		Map<String, Object> fieldsMap) {

		return JSONUtil.put(
			"dataSourceId", dataSourceId
		).put(
			"dataSourceIndividualPK", dataSourceIndividualPK
		).put(
			"fields", new JSONObject(fieldsMap)
		).put(
			"individualSegmentIds", new JSONArray()
		).put(
			"projectId", _PROJECT_ID
		);
	}

	public static JSONObject buildDynamicIndividualSegmentJSONObject(
		String filterString) {

		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"activitiesCount", 0
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"filter", filterString
		).put(
			"individualCount", 0
		).put(
			"name", RandomTestUtil.randomString()
		).put(
			"scope", "PROJECT"
		).put(
			"segmentType", "DYNAMIC"
		).put(
			"status", "ACTIVE"
		);
	}

	public static JSONObject buildEngagementJSONObject(
		String dayDateString, String emailAddress,
		JSONArray individualSegmentIds, String name, String ownerId,
		String ownerType, double score) {

		return JSONUtil.put(
			"dateRecorded", dayDateString
		).put(
			"emailAddress", emailAddress
		).put(
			"individualSegmentIds", individualSegmentIds
		).put(
			"name", name
		).put(
			"ownerId", ownerId
		).put(
			"ownerType", ownerType
		).put(
			"score", score
		);
	}

	public static JSONObject buildExperimentJSONObject(
		String id, String metric, String status) {

		return JSONUtil.put(
			"dataSourceId", "1"
		).put(
			"goal", JSONUtil.put("metric", metric)
		).put(
			"id", id
		).put(
			"status", status
		);
	}

	public static JSONObject buildFieldMappingJSONObject(
		JSONObject authorJSONObject, String context,
		JSONObject dataSourceFieldNamesJSONObject, String fieldName,
		String fieldType, String ownerType) {

		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"author", authorJSONObject
		).put(
			"context", context
		).put(
			"dataSourceFieldNames", dataSourceFieldNamesJSONObject
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"fieldName", fieldName
		).put(
			"fieldType", fieldType
		).put(
			"ownerType", ownerType
		).put(
			"strategy",
			JSONUtil.put(
				"configuration", new JSONObject()
			).put(
				"key", "MOST_RECENT"
			)
		);
	}

	public static JSONObject buildIndividualEngagementJSONObject(
		String dayDateString, JSONObject individualJSONObject, double score) {

		return buildEngagementJSONObject(
			dayDateString,
			FaroInfoIndividualUtil.getIndividualEmail(individualJSONObject),
			individualJSONObject.getJSONArray("individualSegmentIds"),
			FaroInfoIndividualUtil.getIndividualName(individualJSONObject),
			individualJSONObject.getString("id"), "individual", score);
	}

	public static JSONObject buildIndividualFieldJSONObject(
		JSONObject dataSourceJSONObject, String fieldName, String fieldType,
		String fieldValue, JSONObject individualJSONObject, String sourceName) {

		return JSONUtil.put(
			"context", "demographics"
		).put(
			"dataSourceId", dataSourceJSONObject.getString("id")
		).put(
			"dataSourceName", dataSourceJSONObject.getString("name")
		).put(
			"dateModified", DateUtil.newDayDateString()
		).put(
			"fieldType", fieldType
		).put(
			"id", RandomTestUtil.randomId()
		).put(
			"name", fieldName
		).put(
			"ownerId", individualJSONObject.getString("id")
		).put(
			"ownerType", "individual"
		).put(
			"sourceName", sourceName
		).put(
			"value", fieldValue
		);
	}

	public static JSONObject buildIndividualFieldMappingJSONObject(
		JSONObject dataSourceFieldNamesJSONObject, String fieldName,
		String fieldType) {

		return buildFieldMappingJSONObject(
			_getAuthorJSONObject(), "demographics",
			dataSourceFieldNamesJSONObject, fieldName, fieldType, "individual");
	}

	public static JSONObject buildIndividualFieldMappingJSONObject(
		JSONObject authorJSONObject, String dataSourceId,
		String dataSourceFieldName, String fieldName, String fieldType) {

		return buildFieldMappingJSONObject(
			authorJSONObject, "demographics",
			JSONUtil.put(dataSourceId, dataSourceFieldName), fieldName,
			fieldType, "individual");
	}

	public static JSONObject buildIndividualFieldMappingJSONObject(
		String dataSourceId, String dataSourceFieldName, String fieldName,
		String fieldType) {

		return buildFieldMappingJSONObject(
			_getAuthorJSONObject(), "demographics",
			JSONUtil.put(dataSourceId, dataSourceFieldName), fieldName,
			fieldType, "individual");
	}

	public static JSONArray buildIndividualInterestsJSONArray(
		JSONObject assetJSONObject, String dayDateString, String individualId,
		double score, int views) {

		return buildInterestsJSONArray(
			dayDateString, assetJSONObject.getJSONArray("keywords"),
			individualId, "individual", score, views);
	}

	public static JSONObject buildIndividualJSONObject(
		JSONObject dataSourceJSONObject) {

		return buildIndividualJSONObject(
			RandomTestUtil.randomId(), dataSourceJSONObject);
	}

	public static JSONObject buildIndividualJSONObject(
		String channelId, JSONObject dataSourceJSONObject) {

		String dataSourceId = dataSourceJSONObject.getString("id");
		String dataId = RandomTestUtil.randomUUID();
		String dateString = DateUtil.newDateString();
		String individualId = _timeOrderedUuidGenerator.generateId();
		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		String sourceName = "email";

		String type = _getDataSourceType(dataSourceJSONObject);

		if (type.equals("LIFERAY")) {
			sourceName = "emailAddress";
		}

		return JSONUtil.put(
			"activitiesCounts", new JSONArray()
		).put(
			"channelIds", JSONUtil.put(channelId)
		).put(
			"dataSourceAccountPKs", new JSONArray()
		).put(
			"dataSourceIndividualPKs",
			JSONUtil.put(
				JSONUtil.put(
					"dataSourceId", dataSourceId
				).put(
					"dataSourceType", providerJSONObject.getString("type")
				).put(
					"individualPKs", JSONUtil.put(dataId)
				))
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"demographics",
			JSONUtil.put(
				"email",
				JSONUtil.put(
					JSONUtil.put(
						"context", "demographics"
					).put(
						"dataSourceId", dataSourceId
					).put(
						"dataSourceName", dataSourceJSONObject.getString("name")
					).put(
						"dateModified", dateString
					).put(
						"fieldType", "http://schema.org/email"
					).put(
						"name", "email"
					).put(
						"ownerId", individualId
					).put(
						"ownerType", "individual"
					).put(
						"sourceName", sourceName
					).put(
						"value", RandomTestUtil.randomEmailAddress()
					)))
		).put(
			"id", individualId
		).put(
			"individualSegmentIds", new JSONArray()
		);
	}

	public static JSONObject buildIndividualSegmentEngagementJSONObject(
		String dayDateString, String individualSegmentId, double score) {

		return buildEngagementJSONObject(
			dayDateString, null, null, null, individualSegmentId,
			"individual-segment", score);
	}

	public static JSONArray buildIndividualSegmentVisitedPagesJSONArray(
		JSONObject assetJSONObject, String dayDateString,
		String individualSegmentId, int uniqueVisitsCount) {

		return buildVisitedPagesJSONArray(
			assetJSONObject, dayDateString, individualSegmentId,
			"individual-segment", uniqueVisitsCount);
	}

	public static JSONArray buildIndividualVisitedPagesJSONArray(
		JSONObject assetJSONObject, String dayDateString, String individualId,
		int uniqueVisitsCount) {

		return buildVisitedPagesJSONArray(
			assetJSONObject, dayDateString, individualId, "individual",
			uniqueVisitsCount);
	}

	public static JSONArray buildInterestsJSONArray(
		String dayDateString, JSONArray keywordsJSONArray, String ownerId,
		String ownerType, double score, int views) {

		JSONArray interestsJSONArray = new JSONArray();

		for (int i = 0; i < keywordsJSONArray.length(); i++) {
			JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(i);

			interestsJSONArray.put(
				JSONUtil.put(
					"dateRecorded", dayDateString
				).put(
					"name", keywordJSONObject.getString("keyword")
				).put(
					"ownerId", ownerId
				).put(
					"ownerType", ownerType
				).put(
					"score", score
				).put(
					"views", views
				));
		}

		return interestsJSONArray;
	}

	public static JSONObject buildLiferayDataSourceJSONObject() {
		return buildLiferayDataSourceJSONObject(
			RandomTestUtil.selectRandom(
				"Basic Authentication", "OAuth 1 Authentication",
				"OAuth 2 Authentication"),
			RandomTestUtil.randomMultipleWordString(5, 20),
			RandomTestUtil.randomURL());
	}

	public static JSONObject buildLiferayDataSourceJSONObject(String url) {
		return buildLiferayDataSourceJSONObject(
			RandomTestUtil.selectRandom(
				"Basic Authentication", "OAuth 1 Authentication",
				"OAuth 2 Authentication"),
			RandomTestUtil.randomMultipleWordString(5, 20), url);
	}

	public static JSONObject buildLiferayDataSourceJSONObject(
		String name, String url) {

		return buildLiferayDataSourceJSONObject(
			RandomTestUtil.selectRandom(
				"Basic Authentication", "OAuth 1 Authentication",
				"OAuth 2 Authentication"),
			name, url);
	}

	public static JSONObject buildLiferayDataSourceJSONObject(
		String authenticationType, String name, String url) {

		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"about", RandomTestUtil.randomMultipleWordString(20, 50)
		).put(
			"author", _getAuthorJSONObject()
		).put(
			"credentials", _getCredentialsJSONObject(authenticationType)
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"description", RandomTestUtil.randomMultipleWordString(20, 50)
		).put(
			"name", name
		).put(
			"provider",
			JSONUtil.put(
				"analyticsConfiguration",
				JSONUtil.put(
					"enableAllSites", true
				).put(
					"sites", new JSONArray()
				)
			).put(
				"contactsConfiguration",
				JSONUtil.put(
					"enableAllContacts", true
				).put(
					"organizations", new JSONArray()
				).put(
					"userGroups", new JSONArray()
				)
			).put(
				"type", "LIFERAY"
			)
		).put(
			"state", "CREDENTIALS_VALID"
		).put(
			"status", "ACTIVE"
		).put(
			"url", url
		);
	}

	public static JSONObject buildMembershipChangeJSONObject(
		boolean individualDeleted, JSONObject individualJSONObject,
		String individualSegmentId, String operationType) {

		JSONObject demographicsJSONObject = individualJSONObject.getJSONObject(
			"demographics");

		JSONArray emailJSONArray = demographicsJSONObject.getJSONArray("email");

		JSONObject emailJSONObject = emailJSONArray.getJSONObject(0);

		return JSONUtil.put(
			"dateChanged", DateUtil.newDayDateString()
		).put(
			"dateFirst", DateUtil.newDayDateString()
		).put(
			"individualDeleted", individualDeleted
		).put(
			"individualEmail", emailJSONObject.getString("value")
		).put(
			"individualId",
			FaroInfoIndividualUtil.getIndividualEmail(individualJSONObject)
		).put(
			"individualName",
			FaroInfoIndividualUtil.getIndividualName(individualJSONObject)
		).put(
			"individualsCount", RandomUtils.nextInt()
		).put(
			"individualSegmentId", individualSegmentId
		).put(
			"operation", operationType
		);
	}

	public static JSONObject buildMembershipJSONObject(
		String individualId, String individualSegmentId) {

		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"individualId", individualId
		).put(
			"individualSegmentId", individualSegmentId
		).put(
			"status", "ACTIVE"
		);
	}

	public static JSONObject buildOrganizationJSONObject(String dataSourceId) {
		String name = RandomTestUtil.randomString();
		long organizationPK = RandomTestUtil.randomNumber();

		return JSONUtil.put(
			"dataSourceId", dataSourceId
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"dateModified", DateUtil.newDateString()
		).put(
			"id", _timeOrderedUuidGenerator.generateId()
		).put(
			"name", name
		).put(
			"nameTreePath", name
		).put(
			"organizationPK", organizationPK
		).put(
			"parentName", ""
		).put(
			"parentOrganizationPK", "0"
		).put(
			"treePath", "/" + organizationPK + "/"
		).put(
			"type", "organization"
		);
	}

	public static JSONObject buildPageAssetJSONObject(String dataSourceId) {
		return buildPageAssetJSONObject(
			dataSourceId, _randomKeywordsJSONArray());
	}

	public static JSONObject buildPageAssetJSONObject(
		String dataSourceId, JSONArray keywordsJSONArray) {

		return JSONUtil.put(
			"assetType", "Page"
		).put(
			"dataSourceAssetPK", RandomTestUtil.randomURL()
		).put(
			"dataSourceId", dataSourceId
		).put(
			"keywords", keywordsJSONArray
		).put(
			"name", RandomTestUtil.randomMultipleWordString(5, 20)
		);
	}

	public static JSONObject buildSalesforceDataSourceJSONObject() {
		return buildSalesforceDataSourceJSONObject(
			_getCredentialsJSONObject(
				RandomTestUtil.selectRandom(
					"Basic Authentication", "OAuth 2 Authentication")),
			RandomTestUtil.randomURL());
	}

	public static JSONObject buildSalesforceDataSourceJSONObject(
		JSONObject credentialsJSONObject, String url) {

		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"about", RandomTestUtil.randomMultipleWordString(20, 50)
		).put(
			"author", _getAuthorJSONObject()
		).put(
			"credentials", credentialsJSONObject
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"description", RandomTestUtil.randomMultipleWordString(20, 50)
		).put(
			"id", _timeOrderedUuidGenerator.generateId()
		).put(
			"name", RandomTestUtil.randomMultipleWordString(5, 20)
		).put(
			"provider",
			JSONUtil.put(
				"accountsConfiguration", JSONUtil.put("enableAllAccounts", true)
			).put(
				"contactsConfiguration",
				JSONUtil.put(
					"enableAllContacts", true
				).put(
					"enableAllLeads", true
				)
			).put(
				"type", "SALESFORCE"
			)
		).put(
			"status", "ACTIVE"
		).put(
			"url", url
		);
	}

	public static JSONObject buildSalesforceDataSourceJSONObject(
		String login, String password, String url) {

		return buildSalesforceDataSourceJSONObject(
			_getBasicCredentialsJSONObject(login, password), url);
	}

	public static JSONObject buildStaticIndividualSegmentJSONObject() {
		String dateString = DateUtil.newDateString();

		return JSONUtil.put(
			"activitiesCount", 0
		).put(
			"dateCreated", dateString
		).put(
			"dateModified", dateString
		).put(
			"individualCount", 0
		).put(
			"name", RandomTestUtil.randomString()
		).put(
			"scope", "PROJECT"
		).put(
			"segmentType", "STATIC"
		).put(
			"status", "ACTIVE"
		);
	}

	public static JSONArray buildVisitedPagesJSONArray(
		JSONObject assetJSONObject, String dayDateString, String ownerId,
		String ownerType, int uniqueVisitsCount) {

		JSONArray visitedPagesJSONArray = new JSONArray();

		JSONArray keywordsJSONArray = assetJSONObject.getJSONArray("keywords");

		for (int i = 0; i < keywordsJSONArray.length(); i++) {
			JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(i);

			visitedPagesJSONArray.put(
				JSONUtil.put(
					"day", dayDateString
				).put(
					"description",
					assetJSONObject.optString("description", null)
				).put(
					"interestName", keywordJSONObject.getString("keyword")
				).put(
					"ownerId", ownerId
				).put(
					"ownerType", ownerType
				).put(
					"title", assetJSONObject.getString("name")
				).put(
					"uniqueVisitsCount", uniqueVisitsCount
				).put(
					"url", assetJSONObject.getString("dataSourceAssetPK")
				));
		}

		return visitedPagesJSONArray;
	}

	private static JSONObject _getAuthorJSONObject() {
		return JSONUtil.put(
			"id", RandomStringUtils.randomNumeric(5, 7)
		).put(
			"name", RandomTestUtil.randomFullName()
		);
	}

	private static JSONObject _getBasicCredentialsJSONObject(
		String login, String password) {

		return JSONUtil.put(
			"login", login
		).put(
			"password", password
		).put(
			"type", "Basic Authentication"
		);
	}

	private static JSONObject _getCredentialsJSONObject(String type) {
		if (type.equals("Basic Authentication")) {
			return _getBasicCredentialsJSONObject(
				RandomTestUtil.randomEmailAddress(),
				RandomStringUtils.randomAlphanumeric(6, 10));
		}
		else if (type.equals("OAuth 1 Authentication")) {
			return _getOAuth1CredentialsJSONObject();
		}
		else if (type.equals("OAuth 2 Authentication")) {
			return _getOAuth2CredentialsJSONObject();
		}
		else if (type.equals("Token Authentication")) {
			return _getTokenAuthenticationCredentialsJSONObject();
		}

		throw new IllegalArgumentException(
			"Unsupported authentication type: " + type);
	}

	private static String _getDataSourceType(JSONObject dataSourceJSONObject) {
		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		return providerJSONObject.getString("type");
	}

	private static JSONObject _getOAuth1CredentialsJSONObject() {
		return JSONUtil.put(
			"oAuthAccessSecret", RandomTestUtil.randomHexString(32)
		).put(
			"oAuthAccessToken", RandomTestUtil.randomHexString(32)
		).put(
			"oAuthConsumerKey", RandomTestUtil.randomUUID()
		).put(
			"oAuthConsumerSecret", RandomTestUtil.randomHexString(32)
		).put(
			"oAuthOwner",
			JSONUtil.put(
				"emailAddress", RandomTestUtil.randomEmailAddress()
			).put(
				"name", RandomTestUtil.randomFullName()
			)
		).put(
			"type", "OAuth 1 Authentication"
		);
	}

	private static JSONObject _getOAuth2CredentialsJSONObject() {
		return JSONUtil.put(
			"oAuthClientId", "id-" + RandomTestUtil.randomUUID()
		).put(
			"oAuthClientSecret", "secret-" + RandomTestUtil.randomUUID()
		).put(
			"oAuthOwner",
			JSONUtil.put(
				"emailAddress", RandomTestUtil.randomEmailAddress()
			).put(
				"name", RandomTestUtil.randomFullName()
			)
		).put(
			"oAuthRefreshToken",
			RandomTestUtil.randomHexString(RandomUtils.nextInt(59, 65))
		).put(
			"type", "OAuth 2 Authentication"
		);
	}

	private static JSONObject _getTokenAuthenticationCredentialsJSONObject() {
		return JSONUtil.put(
			"privateKey", RandomTestUtil.randomUUID()
		).put(
			"publicKey", RandomTestUtil.randomUUID()
		).put(
			"type", "Token Authentication"
		);
	}

	private static JSONArray _randomKeywordsJSONArray() {
		JSONArray keywordsJSONArray = new JSONArray();
		int length = RandomUtils.nextInt(3, 10);
		String[] types = {"keyword", "title", "description"};

		for (int i = 0; i < length; i++) {
			keywordsJSONArray.put(
				JSONUtil.put(
					"keyword", RandomStringUtils.randomAlphabetic(3, 10)
				).put(
					"type", types[RandomUtils.nextInt(0, types.length)]
				));
		}

		return keywordsJSONArray;
	}

	private static final String _PROJECT_ID = RandomTestUtil.randomString();

	private static final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}