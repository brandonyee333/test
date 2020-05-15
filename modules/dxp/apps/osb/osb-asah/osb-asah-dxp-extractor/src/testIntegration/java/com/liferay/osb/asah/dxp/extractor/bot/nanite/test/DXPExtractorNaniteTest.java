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

package com.liferay.osb.asah.dxp.extractor.bot.nanite.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.dxp.extractor.bot.nanite.DXPExtractorNanite;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;
import com.liferay.osb.asah.dxp.extractor.configuration.impl.DXPExtractorRuntimeConfigurationImpl;
import com.liferay.osb.asah.dxp.extractor.dog.AuditEventDog;
import com.liferay.osb.asah.dxp.extractor.dog.GroupDog;
import com.liferay.osb.asah.dxp.extractor.dog.OrganizationDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserGroupDog;
import com.liferay.osb.asah.dxp.extractor.spring.OSBAsahDXPExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Rachael Koestartyo
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahDXPExtractorSpringBootApplication.class)
public class DXPExtractorNaniteTest {

	@Before
	public void setUp() {
		_mock();

		_elasticsearchIndexManager.clearIndices();

		_elasticsearchIndexManager.checkIndices();

		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();

		_dxpRawElasticsearchInvoker.add(
			"OSBAsahMarkers",
			JSONUtil.put(
				"id", "0"
			).put(
				"lastSuccessfulAuditEvent",
				_buildAuditEventJSONObject(
					"{}", "30000", DXPEntityType.CLASS_NAME_USER, "33003",
					1546560360000L, "ADD", null)
			).put(
				"lastSyncTime", 1546560360000L
			).put(
				"osbAsahDataSourceId", "0"
			));

		JSONObject additionalInfoJSONObject = JSONUtil.put(
			"emailAddress", "dummy@liferay.com"
		).put(
			"screenName", "dummy"
		).put(
			"userId", "32523"
		).put(
			"userName", "dummy"
		);

		_dxpRawElasticsearchInvoker.add(
			"audit-events",
			_buildAuditEventJSONObject(
				additionalInfoJSONObject.toString(), "32525",
				DXPEntityType.CLASS_NAME_USER, "32523", 1546560365674L, "ADD",
				null));

		_dxpRawElasticsearchInvoker.add(
			"audit-events",
			_buildAuditEventJSONObject(
				"{}", "32542", DXPEntityType.CLASS_NAME_ORGANIZATION, "32541",
				1546560406936L, "ADD", null));

		additionalInfoJSONObject = JSONUtil.put(
			"organizationId", "32541"
		).put(
			"organizationName", "Organization Test"
		);

		_dxpRawElasticsearchInvoker.add(
			"audit-events",
			_buildAuditEventJSONObject(
				additionalInfoJSONObject.toString(), "32577",
				DXPEntityType.CLASS_NAME_USER, "32523", 1546560430679L,
				"ASSIGN", null));

		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_faroInfoElasticsearchInvoker.add(
			"data-sources", _buildLiferayDataSourceJSONObject());
	}

	@After
	public void tearDown() {
		_dxpRawElasticsearchInvoker.delete(
			"OSBAsahMarkers", QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			"audit-events", QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			"groups", QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			"organizations", QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			"users", QueryBuilders.matchAllQuery());
		_faroInfoElasticsearchInvoker.delete(
			"data-sources", QueryBuilders.matchAllQuery());
		_faroInfoElasticsearchInvoker.delete(
			"suppressions", QueryBuilders.matchAllQuery());

		_elasticsearchIndexManager.clearIndices();
	}

	@Test
	public void test() throws Exception {
		_testAddUsersAndOrganizations();

		_testUpdateAnalyticsConfiguration();

		_testUpdateContactsConfiguration();
	}

	@Test
	public void testSuppression() throws Exception {
		_faroInfoElasticsearchInvoker.add(
			"suppressions", JSONUtil.put("emailAddress", "dummy@liferay.com"));

		_dxpExtractorNanite.run();

		Assert.assertTrue(
			_dxpRawElasticsearchInvoker.count("organizations", null) > 0);
		Assert.assertTrue(
			_dxpRawElasticsearchInvoker.count("users", null) == 0);
	}

	@TestConfiguration
	public static class DXPExtractorNaniteTestConfiguration {

		@Bean
		public DXPExtractorNanite dxpExtractorNanite() throws Exception {
			_dxpExtractorRuntimeConfigurationImpl =
				new DXPExtractorRuntimeConfigurationImpl();

			_dxpExtractorRuntimeConfigurationImpl.setDataSourceId("0");
			_dxpExtractorRuntimeConfigurationImpl.setDataSourceState(
				"CREDENTIALS_VALID");
			_dxpExtractorRuntimeConfigurationImpl.setDataSourceStatus("ACTIVE");
			_dxpExtractorRuntimeConfigurationImpl.
				setContactsConfigurationJSONObject(
					JSONUtil.put(
						"enableAllContacts", false
					).put(
						"organizations",
						JSONUtil.put(
							JSONUtil.put(
								"enableAllChildren", false
							).put(
								"id", "32541"
							))
					).put(
						"userGroups", new JSONArray()
					));

			return new DXPExtractorNanite(
				_dxpExtractorRuntimeConfigurationImpl);
		}

		@Bean
		public RunLogger runLogger() {
			return new RunLogger();
		}

	}

	@SuppressWarnings("PMD.AvoidUsingHardCodedIP")
	private static JSONObject _buildAuditEventJSONObject(
		String additionalInfo, String auditEventId, String className,
		String classPK, long createDate, String eventType, String id) {

		return JSONUtil.put(
			"additionalInfo", additionalInfo
		).put(
			"auditEventId", auditEventId
		).put(
			"className", className
		).put(
			"classPK", classPK
		).put(
			"clientHost", "172.18.0.1"
		).put(
			"clientIP", "172.18.0.1"
		).put(
			"companyId", "20099"
		).put(
			"createDate", createDate
		).put(
			"eventType", eventType
		).put(
			"id", id
		).put(
			"osbAsahDataSourceId", "0"
		).put(
			"serverName", "172.16.22.54"
		).put(
			"serverPort", "8090"
		).put(
			"sessionID", RandomStringUtils.randomAlphanumeric(32)
		).put(
			"userId", "20139"
		).put(
			"userName", "Test Test"
		);
	}

	private JSONObject _buildLiferayDataSourceJSONObject() {
		JSONObject jsonObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"OAuth 1 Authentication",
				RandomTestUtil.randomMultipleWordString(5, 20),
				"http://localhost:8090");

		jsonObject.put(
			"id", "0"
		).put(
			"state", "CREDENTIALS_VALID"
		);

		JSONObject providerJSONObject = jsonObject.getJSONObject("provider");

		JSONObject contactsConfigurationJSONObject =
			providerJSONObject.getJSONObject("contactsConfiguration");

		contactsConfigurationJSONObject.put("enableAllContacts", false);

		contactsConfigurationJSONObject.put(
			"organizations",
			JSONUtil.put(
				JSONUtil.put(
					"enableAllChildren", false
				).put(
					"id", "32541"
				)));

		return jsonObject;
	}

	private int _getConfiguredOrganizationsCount() {
		JSONArray dataSourcesJSONArray = _faroInfoElasticsearchInvoker.get(
			"data-sources");

		JSONObject dataSourceJSONObject = dataSourcesJSONArray.getJSONObject(0);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		JSONObject contactsConfigurationJSONObject =
			providerJSONObject.getJSONObject("contactsConfiguration");

		JSONArray organizationsJSONArray =
			contactsConfigurationJSONObject.getJSONArray("organizations");

		return organizationsJSONArray.length();
	}

	private void _mock() {
		Mockito.doAnswer(
			invocation -> _buildAuditEventJSONObject(
				"{}", "33004", DXPEntityType.CLASS_NAME_ORGANIZATION, "33003",
				1546900772485L, "ADD", "1")
		).when(
			_auditEventDog
		).getLatestAuditEventJSONObject(
			Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong()
		);

		Mockito.when(
			_auditEventDog.getAuditEventsJSONArray(
				Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong(),
				Mockito.any(Date.class), Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(
			new JSONArray()
		);

		Mockito.doAnswer(
			invocation -> JSONUtil.put(
				"active", "true"
			).put(
				"classNameId", "20001"
			).put(
				"classPK", "20126"
			).put(
				"companyId", "20099"
			).put(
				"creatorUserId", "20103"
			).put(
				"description", ""
			).put(
				"descriptionCurrentValue", ""
			).put(
				"descriptiveName", "Liferay"
			).put(
				"friendlyURL", "/guest"
			).put(
				"groupId", "20126"
			).put(
				"groupKey", "Guest"
			).put(
				"inheritContent", "false"
			).put(
				"liveGroupId", "0"
			).put(
				"manualMembership", "true"
			).put(
				"membershipRestriction", "0"
			).put(
				"mvccVersion", "1"
			).put(
				"name", "Guest"
			).put(
				"nameCurrentValue", "Guest"
			).put(
				"parentGroupId", "0"
			).put(
				"remoteStagingGroupCount", "0"
			).put(
				"site", "true"
			).put(
				"treePath", "/20126/"
			).put(
				"type", "1"
			).put(
				"uuid", "ca8e80b4-e317-4c04-0e82-6c8dd91c0e1e"
			)
		).when(
			_groupDog
		).getGroupJSONObject(
			Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong()
		);

		Mockito.when(
			_groupDog.getGroupsJSONArray(
				Mockito.any(DXPExtractorConfiguration.class),
				Mockito.any(long[].class))
		).thenThrow(
			new RuntimeException()
		);

		Mockito.doAnswer(
			invocation -> JSONUtil.put(
				"companyId", "20099"
			).put(
				"countryId", "31"
			).put(
				"createDate", 1546900772485L
			).put(
				"logoId", "0"
			).put(
				"modifiedDate", 1546900772485L
			).put(
				"mvccVersion", "0"
			).put(
				"name", "Organization Test"
			).put(
				"organizationId", "32541"
			).put(
				"parentOrganizationId", "0"
			).put(
				"recursable", true
			).put(
				"regionId", "0"
			).put(
				"statusId", "12017"
			).put(
				"treePath", "/32541/"
			).put(
				"type", "organization"
			).put(
				"userId", "20139"
			).put(
				"userName", "Test Test"
			).put(
				"uuid", "2397337d-e3b3-83db-9940-e5436f6b2e2d"
			)
		).when(
			_organizationDog
		).getOrganizationJSONObject(
			Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong()
		);

		Mockito.when(
			_organizationDog.getGtOrganizationsJSONArray(
				Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong(),
				Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt())
		).thenReturn(
			new JSONArray()
		);

		Mockito.when(
			_organizationDog.getOrganizationsUsersJSONArray(
				Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong(),
				Mockito.anyInt())
		).thenReturn(
			new JSONArray()
		);

		Mockito.doAnswer(
			invocation -> JSONUtil.put(
				"agreedToTermsOfUse", true
			).put(
				"companyId", "20099"
			).put(
				"contactId", "32524"
			).put(
				"createDate", 1546560365676L
			).put(
				"defaultUser", false
			).put(
				"emailAddress", "dummy@liferay.com"
			).put(
				"emailAddressVerified", true
			).put(
				"firstName", "Dummy"
			).put(
				"lastName", "Test"
			).put(
				"modifiedDate", 1546560365676L
			).put(
				"screenName", "dummy"
			).put(
				"userId", "32523"
			).put(
				"uuid", "b311fe68-0e9b-5a98-f353-6a9425abe932"
			)
		).when(
			_userDog
		).getUserJSONObject(
			Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong()
		);

		Mockito.when(
			_userGroupDog.getGtUserGroupsJSONArray(
				Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong(),
				Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt())
		).thenReturn(
			new JSONArray()
		);

		Mockito.when(
			_userGroupDog.getUserGroupsUsersJSONArray(
				Mockito.any(DXPExtractorConfiguration.class), Mockito.anyLong(),
				Mockito.anyInt())
		).thenReturn(
			new JSONArray()
		);
	}

	private void _testAddUsersAndOrganizations() throws Exception {
		_dxpExtractorNanite.run();

		Assert.assertTrue(
			_dxpRawElasticsearchInvoker.count("organizations", null) > 0);
		Assert.assertTrue(_dxpRawElasticsearchInvoker.count("users", null) > 0);
	}

	private void _testUpdateAnalyticsConfiguration() throws Exception {
		Assert.assertEquals(
			0,
			_dxpRawElasticsearchInvoker.count(
				"groups", QueryBuilders.matchAllQuery()));

		_dxpExtractorRuntimeConfigurationImpl.
			setAnalyticsConfigurationJSONObject(
				JSONUtil.put(
					"enableAllSites", false
				).put(
					"sites",
					JSONUtil.put(
						JSONUtil.put(
							"enableAllChildren", false
						).put(
							"id", "20126"
						))
				));

		_dxpExtractorNanite.run();

		Assert.assertEquals(
			1,
			_dxpRawElasticsearchInvoker.count(
				"groups", QueryBuilders.matchAllQuery()));

		_dxpExtractorRuntimeConfigurationImpl.
			setAnalyticsConfigurationJSONObject(
				JSONUtil.put(
					"enableAllSites", false
				).put(
					"sites", new JSONArray()
				));

		_dxpExtractorNanite.run();

		Assert.assertEquals(
			0,
			_dxpRawElasticsearchInvoker.count(
				"groups", QueryBuilders.matchAllQuery()));
	}

	private void _testUpdateContactsConfiguration() throws Exception {
		Assert.assertEquals(1, _getConfiguredOrganizationsCount());

		_dxpRawElasticsearchInvoker.add(
			"audit-events",
			_buildAuditEventJSONObject(
				"{}", "32698", DXPEntityType.CLASS_NAME_ORGANIZATION, "32541",
				1546625637107L, "DELETE", null));

		_dxpExtractorNanite.run();

		Assert.assertEquals(0, _getConfiguredOrganizationsCount());
	}

	private static DXPExtractorRuntimeConfigurationImpl
		_dxpExtractorRuntimeConfigurationImpl;

	@Mock
	private AuditEventDog _auditEventDog;

	@Autowired
	@InjectMocks
	private DXPExtractorNanite _dxpExtractorNanite;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Mock
	private GroupDog _groupDog;

	@Mock
	private OrganizationDog _organizationDog;

	@Mock
	private UserDog _userDog;

	@Mock
	private UserGroupDog _userGroupDog;

}