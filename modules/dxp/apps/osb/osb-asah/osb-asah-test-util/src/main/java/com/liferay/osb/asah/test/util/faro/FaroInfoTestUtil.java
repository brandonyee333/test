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
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.model.Goal;
import com.liferay.osb.asah.common.model.GoalMetric;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class FaroInfoTestUtil {

	public static Account buildAccount(DataSource dataSource) {
		String accountPK = RandomStringUtils.randomAlphanumeric(18);
		Long dataSourceId = dataSource.getId();
		Date date = new Date();
		Long id = _timeOrderedUuidGenerator.generateIdAsLong();

		Account account = new Account();

		account.setAccountPK(accountPK);
		account.setActivitiesCount(0L);
		account.setCreateDate(date);
		account.setDataSourceId(dataSourceId);
		account.setId(id);
		account.setIndividualsCount(0L);

		Field field = new Field();

		field.setContext("organization");
		field.setDataSourceId(dataSourceId);
		field.setDataSourceName(dataSource.getName());
		field.setFieldType("Text");
		field.setModifiedDate(date);
		field.setName("accountId");
		field.setOwnerId(id);
		field.setOwnerType("account");
		field.setSourceName("id");
		field.setValue("accountPK");

		account.setFields(Collections.singleton(field));

		return account;
	}

	public static FieldMapping buildAccountFieldMapping(
		String dataSourceId, String dataSourceFieldName, String fieldName,
		String fieldType) {

		return buildFieldMapping(
			_getFieldMappingAuthor(), "organization",
			Collections.singletonMap(dataSourceId, dataSourceFieldName),
			fieldName, fieldType, "account");
	}

	public static Segment buildAccountSegment(Account account, Long channelId) {
		Date date = new Date();

		Segment segment = new Segment();

		segment.setActivitiesCount(0L);
		segment.setChannelId(channelId);
		segment.setCreateDate(date);
		segment.setFilter(
			"((dataSourceAccountPKs/accountPKs eq '" + account.getAccountPK() +
				"'))");
		segment.setIndividualsCount(0L);
		segment.setModifiedDate(date);
		segment.setName("Account: " + account.getId());
		segment.setScope("PROJECT");
		segment.setStatus("INACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		return segment;
	}

	public static ActivityGroup buildActivityGroup(
		Long dataSourceId, Date date, Individual individual) {

		return buildActivityGroup(
			Long.parseLong(RandomStringUtils.randomNumeric(4)), dataSourceId,
			date, individual);
	}

	public static ActivityGroup buildActivityGroup(
		Long dataSourceId, Individual individual) {

		return buildActivityGroup(dataSourceId, new Date(), individual);
	}

	public static ActivityGroup buildActivityGroup(
		Long channelId, Long dataSourceId, Date date, Individual individual) {

		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs =
			individual.getDataSourceIndividualPKs();

		Stream<Individual.DataSourceIndividualPK> stream =
			dataSourceIndividualPKs.stream();

		Individual.DataSourceIndividualPK dataSourceIndividualPK =
			stream.filter(
				individualPK -> Objects.equals(
					individualPK.getDataSourceId(), dataSourceId)
			).findFirst(
			).orElse(
				null
			);

		Set<String> individualPKs = new HashSet<>();

		if (dataSourceIndividualPK != null) {
			individualPKs = dataSourceIndividualPK.getIndividualPKs();
		}

		ActivityGroup activityGroup = new ActivityGroup();

		activityGroup.setActivityType("BROWSE");
		activityGroup.setChannelId(channelId);
		activityGroup.setDataSourceId(dataSourceId);
		activityGroup.setDayDate(date);
		activityGroup.setEndDate(date);
		activityGroup.setOwnerId(individual.getId());
		activityGroup.setStartDate(date);
		activityGroup.setUserId(individualPKs.toArray(new String[0])[0]);

		return activityGroup;
	}

	public static JSONObject buildActivityJSONObject(
		JSONObject activityGroupJSONObject, JSONObject assetJSONObject,
		Long channelId, String eventId, String[] eventProperties) {

		if ((eventProperties.length % 2) != 0) {
			throw new IllegalArgumentException(
				"Event properties must be an even length");
		}

		String applicationId = assetJSONObject.getString("assetType");
		String assetId = assetJSONObject.getString("id");

		JSONObject eventPropertiesJSONObject = new JSONObject();

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
			"channelId", String.valueOf(channelId)
		).put(
			"dataSourceId",
			String.valueOf(activityGroupJSONObject.getLong("dataSourceId"))
		).put(
			"day", activityGroupJSONObject.getString("day")
		).put(
			"endTime", activityGroupJSONObject.getString("endTime")
		).put(
			"eventId", eventId
		).put(
			"eventProperties", eventPropertiesJSONObject
		).put(
			"groupId", String.valueOf(activityGroupJSONObject.getLong("id"))
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
			"ownerId", activityGroupJSONObject.getString("ownerId")
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
			activityGroupJSONObject, assetJSONObject,
			Long.parseLong(RandomStringUtils.randomNumeric(4)), eventId,
			eventProperties);
	}

	public static JSONObject buildAssetJSONObject(
		String assetType, Long dataSourceId) {

		return buildAssetJSONObject(
			assetType, Long.parseLong(RandomStringUtils.randomNumeric(4)),
			dataSourceId);
	}

	public static JSONObject buildAssetJSONObject(
		String assetType, Long channelId, Long dataSourceId) {

		return JSONUtil.put(
			"assetType", assetType
		).put(
			"channelIds", JSONUtil.put(String.valueOf(channelId))
		).put(
			"dataSourceAssetPK", String.valueOf(RandomTestUtil.randomNumber())
		).put(
			"dataSourceId", String.valueOf(dataSourceId)
		).put(
			"name", RandomTestUtil.randomMultipleWordString(5, 20)
		);
	}

	public static BQMembership buildBQMembership(
		Long individualId, Long segmentId) {

		Date date = new Date();

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(date);
		bqMembership.setIndividualId(individualId);
		bqMembership.setIndividualSegmentId(segmentId);
		bqMembership.setModifiedDate(date);
		bqMembership.setStatus("ACTIVE");

		return bqMembership;
	}

	public static BQMembershipChange buildBQMembershipChange(
		Long individualSegmentId) {

		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setCreateDate(new Date());
		bqMembershipChange.setIndividualSegmentId(individualSegmentId);
		bqMembershipChange.setIndividualsCount(RandomUtils.nextLong());

		return bqMembershipChange;
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

	public static DataSource buildCSVDataSource() {
		DataSource dataSource = new DataSource();

		dataSource.setAuthorId(
			Long.valueOf(RandomStringUtils.randomNumeric(5, 7)));
		dataSource.setAuthorName(RandomTestUtil.randomFullName());

		Date date = new Date();

		dataSource.setCreateDate(date);

		dataSource.setId(Long.valueOf(_timeOrderedUuidGenerator.generateId()));
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setModifiedDate(date);
		dataSource.setName(RandomTestUtil.randomMultipleWordString(5, 20));
		dataSource.setProviderType("CSV");
		dataSource.setStatus("ACTIVE");

		return dataSource;
	}

	public static CSVIndividual buildCSVIndividual(
		Long dataSourceId, String dataSourceIndividualPK,
		JSONObject fieldsMap) {

		CSVIndividual csvIndividual = new CSVIndividual();

		csvIndividual.setDataSourceId(dataSourceId);
		csvIndividual.setDataSourceIndividualPK(dataSourceIndividualPK);
		csvIndividual.setFieldsJSONObject(fieldsMap);
		csvIndividual.setId(
			Long.valueOf(_timeOrderedUuidGenerator.generateId()));
		csvIndividual.setIsNew(Boolean.TRUE);

		return csvIndividual;
	}

	public static Segment buildDynamicSegment(
		Long channelId, String filterString) {

		Segment segment = new Segment();

		segment.setActiveIndividualsCount(0L);

		if (channelId != null) {
			segment.setChannelId(channelId);
		}

		Date date = new Date();

		segment.setCreateDate(date);

		segment.setFilter(filterString);
		segment.setIndividualsCount(0L);
		segment.setModifiedDate(date);
		segment.setName(RandomTestUtil.randomString());
		segment.setScope("PROJECT");
		segment.setStatus("ACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		return segment;
	}

	public static Segment buildDynamicSegment(String filterString) {
		Segment segment = new Segment();

		segment.setActiveIndividualsCount(0L);
		segment.setChannelId(
			Long.parseLong(RandomStringUtils.randomNumeric(4)));

		Date date = new Date();

		segment.setCreateDate(date);

		segment.setFilter(filterString);
		segment.setIndividualsCount(0L);
		segment.setModifiedDate(date);
		segment.setName(RandomTestUtil.randomString());
		segment.setScope("PROJECT");
		segment.setStatus("ACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		return segment;
	}

	public static Experiment buildExperiment(
		ExperimentStatus experimentStatus, GoalMetric goalMetric, Long id) {

		Experiment experiment = new Experiment();

		experiment.setChannelId(1L);
		experiment.setDataSourceId(1L);
		experiment.setExperimentStatus(experimentStatus);
		experiment.setId(id);

		Goal goal = new Goal();

		goal.setGoalMetric(goalMetric);

		experiment.setGoal(goal);

		return experiment;
	}

	public static JSONObject buildFieldJSONObject(
		String dataSourceId, String dataSourceName) {

		return JSONUtil.put(
			"context", "demographics"
		).put(
			"dataSourceId", dataSourceId
		).put(
			"dataSourceName", dataSourceName
		).put(
			"dateModified", DateUtil.newDateString()
		).put(
			"fieldType", "Text"
		).put(
			"name", "givenName"
		).put(
			"ownerId", RandomTestUtil.randomId()
		).put(
			"ownerType", "individual"
		).put(
			"sourceName", "firstName"
		).put(
			"value", RandomTestUtil.randomString()
		);
	}

	public static FieldMapping buildFieldMapping(
		FieldMapping.Author author, String context,
		Map<String, String> dataSourceFieldNames, String fieldName,
		String fieldType, String ownerType) {

		FieldMapping fieldMapping = new FieldMapping();

		Date date = new Date();

		fieldMapping.setAuthor(author);
		fieldMapping.setContext(context);
		fieldMapping.setCreateDate(date);
		fieldMapping.setDataSourceFieldNames(dataSourceFieldNames);
		fieldMapping.setDisplayName(fieldName);
		fieldMapping.setFieldName(fieldName);
		fieldMapping.setFieldType(fieldType);
		fieldMapping.setModifiedDate(date);
		fieldMapping.setOwnerType(ownerType);
		fieldMapping.setStrategyConfiguration(new JSONObject());
		fieldMapping.setStrategyKey("MOST_RECENT");

		return fieldMapping;
	}

	public static Individual buildIndividual(DataSource dataSource) {
		return buildIndividual(
			Long.parseLong(RandomStringUtils.randomNumeric(4)), dataSource);
	}

	public static Individual buildIndividual(
		Long channelId, DataSource dataSource) {

		Long dataSourceId = dataSource.getId();
		String dataId = RandomTestUtil.randomUUID();
		Date date = new Date();
		Long individualId = _timeOrderedUuidGenerator.generateIdAsLong();
		String providerType = dataSource.getProviderType();

		String sourceName = "email";

		if (providerType.equals("LIFERAY")) {
			sourceName = "emailAddress";
		}

		Individual individual = new Individual();

		individual.setChannelIds(Collections.singleton(channelId));
		individual.setCreateDate(date);
		individual.setId(individualId);
		individual.setModifiedDate(date);
		individual.setSegmentIds(Collections.emptySet());

		DataSourceIndividual dataSourceIndividual = new DataSourceIndividual();

		dataSourceIndividual.setAccountPKs(Collections.emptySet());
		dataSourceIndividual.setDataSourceId(dataSourceId);
		dataSourceIndividual.setIndividualId(individualId);
		dataSourceIndividual.setIndividualPKs(Collections.singleton(dataId));

		individual.setDataSourceIndividuals(
			Collections.singleton(dataSourceIndividual));

		Field field = new Field();

		field.setContext("demographics");
		field.setDataSourceId(dataSourceId);
		field.setDataSourceName(dataSource.getName());
		field.setFieldType("http://schema.org/email");
		field.setModifiedDate(date);
		field.setName("email");
		field.setOwnerId(individualId);
		field.setOwnerType("individual");
		field.setSourceName(sourceName);
		field.setValue(RandomTestUtil.randomEmailAddress());

		individual.setFields(Collections.singleton(field));

		return individual;
	}

	public static Field buildIndividualField(
		DataSource dataSource, String fieldName, String fieldType,
		String fieldValue, Individual individual, String sourceName) {

		Field field = new Field();

		field.setContext("demographics");
		field.setDataSourceId(dataSource.getId());
		field.setDataSourceName(dataSource.getName());
		field.setFieldType(fieldType);
		field.setId(Long.parseLong(RandomStringUtils.randomNumeric(4)));
		field.setModifiedDate(new Date());
		field.setName(fieldName);
		field.setOwnerId(individual.getId());
		field.setOwnerType("individual");
		field.setSourceName(sourceName);
		field.setValue(fieldValue);

		return field;
	}

	public static FieldMapping buildIndividualFieldMapping(
		FieldMapping.Author author, Long dataSourceId,
		String dataSourceFieldName, String fieldName, String fieldType) {

		return buildFieldMapping(
			author, "demographics",
			Collections.singletonMap(
				String.valueOf(dataSourceId), dataSourceFieldName),
			fieldName, fieldType, "individual");
	}

	public static FieldMapping buildIndividualFieldMapping(
		Long dataSourceId, String dataSourceFieldName, String fieldName,
		String fieldType) {

		return buildFieldMapping(
			_getFieldMappingAuthor(), "demographics",
			Collections.singletonMap(
				String.valueOf(dataSourceId), dataSourceFieldName),
			fieldName, fieldType, "individual");
	}

	public static FieldMapping buildIndividualFieldMapping(
		Map<String, String> dataSourceFieldNames, String fieldName,
		String fieldType) {

		return buildFieldMapping(
			_getFieldMappingAuthor(), "demographics", dataSourceFieldNames,
			fieldName, fieldType, "individual");
	}

	public static FieldMapping buildIndividualFieldMapping(
		String context, Long dataSourceId, String dataSourceFieldName,
		String fieldName, String fieldType) {

		return buildFieldMapping(
			_getFieldMappingAuthor(), context,
			Collections.singletonMap(
				String.valueOf(dataSourceId), dataSourceFieldName),
			fieldName, fieldType, "individual");
	}

	public static JSONArray buildIndividualInterestsJSONArray(
		JSONObject assetJSONObject, String dayDateString, Long individualId,
		double score, int views) {

		return buildInterestsJSONArray(
			dayDateString, assetJSONObject.getJSONArray("keywords"),
			individualId, "individual", score, views);
	}

	public static JSONArray buildIndividualSegmentVisitedPagesJSONArray(
		JSONObject assetJSONObject, String dayDateString,
		Long individualSegmentId, int uniqueVisitsCount) {

		return buildVisitedPagesJSONArray(
			assetJSONObject, dayDateString, individualSegmentId,
			"individual-segment", uniqueVisitsCount);
	}

	public static JSONArray buildIndividualVisitedPagesJSONArray(
		JSONObject assetJSONObject, String dayDateString, Long individualId,
		int uniqueVisitsCount) {

		return buildVisitedPagesJSONArray(
			assetJSONObject, dayDateString, individualId, "individual",
			uniqueVisitsCount);
	}

	public static JSONArray buildInterestsJSONArray(
		String dayDateString, JSONArray keywordsJSONArray, Long ownerId,
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
					"ownerId", String.valueOf(ownerId)
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

	public static DataSource buildLiferayDataSource() {
		return buildLiferayDataSource(
			RandomTestUtil.randomMultipleWordString(5, 20),
			RandomTestUtil.randomURL());
	}

	public static DataSource buildLiferayDataSource(String name, String url) {
		return buildLiferayDataSource("Token Authentication", name, url);
	}

	public static DataSource buildLiferayDataSource(
		String authenticationType, String name, String url) {

		Date date = new Date();

		JSONObject authorJSONObject = _getAuthorJSONObject();
		JSONObject credentialsJSONObject = _getCredentialsJSONObject(
			authenticationType);

		DataSource dataSource = new DataSource();

		dataSource.setAuthorId(authorJSONObject.getLong("id"));
		dataSource.setAuthorName(authorJSONObject.getString("name"));
		dataSource.setCreateDate(date);
		dataSource.setCredentialType(authenticationType);
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setModifiedDate(date);
		dataSource.setName(name);
		dataSource.setURL(url);
		dataSource.setState("CREDENTIALS_VALID");
		dataSource.setStatus("ACTIVE");

		if (authenticationType.equals("Basic Authentication")) {
			dataSource.setLogin(credentialsJSONObject.getString("login"));
			dataSource.setPassword(credentialsJSONObject.getString("password"));
		}
		else if (authenticationType.equals("OAuth 2 Authentication")) {
			JSONObject ownerJSONObject = credentialsJSONObject.getJSONObject(
				"oAuthOwner");

			dataSource.setOAuthClientId(
				credentialsJSONObject.getString("oAuthClientId"));
			dataSource.setOAuthClientSecret(
				credentialsJSONObject.getString("oAuthClientSecret"));
			dataSource.setOAuthOwnerEmailAddress(
				ownerJSONObject.getString("email"));
			dataSource.setOAuthOwnerName(ownerJSONObject.getString("name"));
			dataSource.setOAuthRefreshToken(
				credentialsJSONObject.getString("oAuthRefreshToken"));
		}
		else if (authenticationType.equals("Token Authentication")) {
			dataSource.setPrivateKey(
				credentialsJSONObject.getString("privateKey"));
			dataSource.setPublicKey(
				credentialsJSONObject.getString("publicKey"));
		}

		DataSource.Provider provider = new DataSource.Provider();

		DataSource.AnalyticsConfiguration analyticsConfiguration =
			new DataSource.AnalyticsConfiguration();

		analyticsConfiguration.setEnableAllSites(Boolean.TRUE);
		analyticsConfiguration.setDataSourceSites(Collections.emptySet());

		provider.setAnalyticsConfiguration(analyticsConfiguration);

		DataSource.ContactsConfiguration contactsConfiguration =
			new DataSource.ContactsConfiguration();

		contactsConfiguration.setEnableAllContacts(Boolean.TRUE);
		contactsConfiguration.setDataSourceOrganizations(
			Collections.emptySet());
		contactsConfiguration.setDataSourceUserGroups(Collections.emptySet());

		provider.setContactsConfiguration(contactsConfiguration);

		provider.setType("LIFERAY");

		dataSource.setProvider(provider);
		dataSource.setProviderType("LIFERAY");

		return dataSource;
	}

	public static Organization buildOrganization(Long dataSourceId) {
		Date date = new Date();
		String name = RandomTestUtil.randomString();
		long organizationPK = RandomTestUtil.randomNumber();

		Organization organization = new Organization();

		organization.setCreateDate(date);
		organization.setDataSourceId(dataSourceId);
		organization.setId(_timeOrderedUuidGenerator.generateIdAsLong());
		organization.setName(name);
		organization.setNameTreePath(name);
		organization.setOrganizationPK(organizationPK);
		organization.setParentName("");
		organization.setParentOrganizationPK(0L);
		organization.setTreePath("/" + organizationPK + "/");
		organization.setType("organization");

		return organization;
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

	public static JSONObject buildPageAssetJSONObject(Long dataSourceId) {
		return buildPageAssetJSONObject(
			dataSourceId, _randomKeywordsJSONArray());
	}

	public static JSONObject buildPageAssetJSONObject(
		Long dataSourceId, JSONArray keywordsJSONArray) {

		return JSONUtil.put(
			"assetType", "Page"
		).put(
			"canonicalUrl", RandomTestUtil.randomURL()
		).put(
			"dataSourceAssetPK", RandomTestUtil.randomURL()
		).put(
			"dataSourceId", String.valueOf(dataSourceId)
		).put(
			"keywords", keywordsJSONArray
		).put(
			"name", RandomTestUtil.randomMultipleWordString(5, 20)
		);
	}

	public static DataSource buildSalesforceDataSource() {
		return buildSalesforceDataSource(
			_getCredentialsJSONObject(
				RandomTestUtil.selectRandom(
					"Basic Authentication", "OAuth 2 Authentication")),
			RandomTestUtil.randomURL());
	}

	public static DataSource buildSalesforceDataSource(
		JSONObject credentialsJSONObject, String url) {

		DataSource dataSource = new DataSource();

		dataSource.setAuthorId(
			Long.valueOf(RandomStringUtils.randomNumeric(5, 7)));
		dataSource.setAuthorName(RandomTestUtil.randomFullName());

		Date date = new Date();

		dataSource.setCreateDate(date);

		dataSource.setCredentialType(credentialsJSONObject.getString("type"));
		dataSource.setEnableAllAccounts(true);
		dataSource.setEnableAllContacts(true);
		dataSource.setEnableAllLeads(true);
		dataSource.setId(Long.valueOf(_timeOrderedUuidGenerator.generateId()));
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setModifiedDate(date);
		dataSource.setName(RandomTestUtil.randomMultipleWordString(5, 20));
		dataSource.setProviderType("SALESFORCE");
		dataSource.setStatus("ACTIVE");
		dataSource.setURL(url);

		if (Objects.equals(
				dataSource.getCredentialType(), "Basic Authentication")) {

			dataSource.setLogin(credentialsJSONObject.getString("login"));
			dataSource.setPassword(credentialsJSONObject.getString("password"));
		}
		else if (Objects.equals(
					dataSource.getCredentialType(), "OAuth 2 Authentication")) {

			dataSource.setOAuthClientId(
				credentialsJSONObject.getString("oAuthClientId"));
			dataSource.setOAuthClientSecret(
				credentialsJSONObject.getString("oAuthClientSecret"));

			JSONObject oAuthOwnerJSONObject =
				credentialsJSONObject.getJSONObject("oAuthOwner");

			dataSource.setOAuthOwnerEmailAddress(
				oAuthOwnerJSONObject.getString("emailAddress"));
			dataSource.setOAuthOwnerName(
				oAuthOwnerJSONObject.getString("name"));

			dataSource.setOAuthRefreshToken(
				credentialsJSONObject.getString("oAuthRefreshToken"));
		}

		return dataSource;
	}

	public static DataSource buildSalesforceDataSource(
		String login, String password, String url) {

		return buildSalesforceDataSource(
			_getBasicCredentialsJSONObject(login, password), url);
	}

	public static Segment buildStaticSegment() {
		Segment segment = new Segment();

		segment.setActiveIndividualsCount(0L);

		Date date = new Date();

		segment.setCreateDate(date);

		segment.setIndividualsCount(0L);
		segment.setModifiedDate(date);
		segment.setName(RandomTestUtil.randomString());
		segment.setScope("PROJECT");
		segment.setStatus("ACTIVE");
		segment.setType(Segment.Type.STATIC);

		return segment;
	}

	public static JSONObject buildUserSessionJSONObject(
		Map<String, String> parameters) {

		return JSONUtil.put(
			"acquisition",
			JSONUtil.put(
				"channel", parameters.getOrDefault("channel", "direct")
			).put(
				"medium", parameters.getOrDefault("medium", "referral")
			)
		).put(
			"bounced", parameters.getOrDefault("bounced", "false")
		).put(
			"channelId", parameters.getOrDefault("channelId", "1")
		).put(
			"city", parameters.getOrDefault("city", "Diamond Bar")
		).put(
			"completed", parameters.getOrDefault("completed", "true")
		).put(
			"completeDate",
			parameters.getOrDefault("completeDate", DateUtil.newDateString())
		).put(
			"completeReason",
			parameters.getOrDefault("completeReason", "expired")
		).put(
			"country", parameters.getOrDefault("country", "United States")
		).put(
			"dataSourceId", parameters.getOrDefault("dataSourceId", "0")
		).put(
			"date", parameters.getOrDefault("date", DateUtil.newDateString())
		).put(
			"deviceType", parameters.getOrDefault("deviceType", "Desktop")
		).put(
			"duration", 0
		).put(
			"entryPage",
			parameters.getOrDefault("entryPage", RandomTestUtil.randomURL())
		).put(
			"exitPage",
			parameters.getOrDefault("exitPage", RandomTestUtil.randomURL())
		).put(
			"firstEventDate",
			parameters.getOrDefault("firstEventDate", DateUtil.newDateString())
		).put(
			"id", RandomTestUtil.randomId()
		).put(
			"individualId", parameters.getOrDefault("individualId", "10000")
		).put(
			"interactionsCount", 0
		).put(
			"lastEventDate",
			parameters.getOrDefault("lastEventDate", DateUtil.newDateString())
		).put(
			"pageViewsCount", parameters.getOrDefault("pageViewsCount", "0")
		).put(
			"platformName", parameters.getOrDefault("platformName", "Windows")
		).put(
			"referrers",
			JSONUtil.put(
				RandomTestUtil.randomURL()
			).toString()
		).put(
			"region", parameters.getOrDefault("region", "California")
		).put(
			"urls",
			JSONUtil.put(
				RandomTestUtil.randomURL()
			).toString()
		).put(
			"userId",
			parameters.getOrDefault("userId", RandomTestUtil.randomUUID())
		);
	}

	public static JSONArray buildVisitedPagesJSONArray(
		JSONObject assetJSONObject, String dayDateString, Long ownerId,
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
					"ownerId", String.valueOf(ownerId)
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

		if (type.equals("OAuth 2 Authentication")) {
			return _getOAuth2CredentialsJSONObject();
		}

		if (type.equals("Token Authentication")) {
			return _getTokenAuthenticationCredentialsJSONObject();
		}

		throw new IllegalArgumentException(
			"Unsupported authentication type: " + type);
	}

	private static FieldMapping.Author _getFieldMappingAuthor() {
		return new FieldMapping.Author(
			RandomStringUtils.randomNumeric(5, 7),
			RandomTestUtil.randomFullName());
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

	private static final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}