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

package com.liferay.osb.asah.common.dog.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class SegmentDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 3; i++) {
			JSONObject liferayDataSourceJSONObject = _objectMapper.convertValue(
				_dataSourceDog.addDataSource(
					_objectMapper.convertValue(
						FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
						DataSource.class)),
				JSONObject.class);

			String liferayDataSourceId = liferayDataSourceJSONObject.getString(
				"id");

			_liferayDataSourceIdsJSONArray.put(liferayDataSourceId);

			JSONArray assetIdsJSONArray = new JSONArray();

			for (int j = 0; j < 3; j++) {
				JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
					"assets",
					FaroInfoTestUtil.buildPageAssetJSONObject(
						liferayDataSourceId));

				assetIdsJSONArray.put(assetJSONObject.getString("id"));
			}

			_liferayDataSourceAssetIdsJSONObject.put(
				liferayDataSourceId, assetIdsJSONArray);

			_liferayDataSourceOrganizationIdsJSONObject.put(
				liferayDataSourceId,
				faroInfoElasticsearchInvoker.add(
					"organizations",
					FaroInfoTestUtil.buildOrganizationJSONObject(
						liferayDataSourceId)));
		}

		for (String fieldName : _FIELD_NAMES) {
			JSONObject fieldMappingJSONObject =
				faroInfoElasticsearchInvoker.add(
					"field-mappings",
					FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
						"", null, fieldName, "Text"));

			_fieldMappingNameIds.put(
				fieldName, fieldMappingJSONObject.getString("id"));
		}
	}

	@Test
	public void testAddAnd() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds(
				"favoriteColor", "favoriteGenre"),
			"demographics/favoriteColor/value eq 'blue' and " +
				"demographics/favoriteGenre/value eq 'Science Fiction'");
	}

	@Test
	public void testAddBehavioralCriteriaWithMultipleAssetsFromDifferentDataSources()
		throws Exception {

		String[] expectedReferencedAssetDataSourceIds =
			new String[_liferayDataSourceIdsJSONArray.length() - 1];

		List<String> expectedReferencedAssetIds = new ArrayList<>();

		StringBuilder filterSB = new StringBuilder();

		for (int i = 0; i < (_liferayDataSourceIdsJSONArray.length() - 1);
			 i++) {

			String dataSourceId = _liferayDataSourceIdsJSONArray.getString(i);

			expectedReferencedAssetDataSourceIds[i] = dataSourceId;

			JSONArray assetIdsJSONArray =
				_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

			String assetId = assetIdsJSONArray.getString(
				RandomUtils.nextInt(0, assetIdsJSONArray.length()));

			expectedReferencedAssetIds.add(assetId);

			filterSB.append("activities/ever eq 'page#pageViewed#");
			filterSB.append(assetId);
			filterSB.append("'");

			if (i < (_liferayDataSourceIdsJSONArray.length() - 2)) {
				filterSB.append(" and ");
			}
		}

		_assertAddSetsReferencedObjectIds(
			expectedReferencedAssetDataSourceIds,
			expectedReferencedAssetIds.toArray(new String[0]), new String[0],
			filterSB.toString());
	}

	@Test
	public void testAddBehavioralCriteriaWithMultipleAssetsFromSameDataSource()
		throws Exception {

		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		StringBuilder filterSB = new StringBuilder();

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		for (int i = 0; i < assetIdsJSONArray.length(); i++) {
			filterSB.append("activities/ever eq 'page#pageViewed#");
			filterSB.append(assetIdsJSONArray.getString(i));
			filterSB.append("'");

			if (i < (assetIdsJSONArray.length() - 1)) {
				filterSB.append(" and ");
			}
		}

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			JSONUtil.toStringArray(assetIdsJSONArray), new String[0],
			filterSB.toString());
	}

	@Test
	public void testAddBehavioralCriterionEqEver() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/ever eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqLast7Days() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/last7Days eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqLast30Days() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/last30Days eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqLastYear() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/lastYear eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqToday() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/today eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeEver() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/ever ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeLast7Days() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/last7Days ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeLast30Days() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/last30Days ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeLastYear() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/lastYear ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeToday() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/today ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddContainsFunction() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("jobTitle"),
			"contains(demographics/jobTitle/value, 'Engineer')");
	}

	@Test
	public void testAddEndsWithFunction() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("email"),
			"endsWith(demographics/email/value, '@liferay.com')");
	}

	@Test
	public void testAddEqOperator() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("address"),
			"demographics/address/value eq '221B Baker Street'");
	}

	@Test
	public void testAddGeOperator() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("age"),
			"demographics/age/value ge 18");
	}

	@Test
	public void testAddGroupIdEq() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject groupJSONObject = _dxpRawElasticsearchInvoker.add(
			"groups", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {groupJSONObject.getString("id")},
			"groupIds eq '" + groupJSONObject.getString("id") + "'",
			"referencedGroupIds");
	}

	@Test
	public void testAddGtOperator() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("birthDate"),
			"demographics/birthDate/value gt '1989-11-09T00:00:00.000Z'");
	}

	@Test
	public void testAddLeOperator() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("postalCode"),
			"demographics/postalCode/value le '09999'");
	}

	@Test
	public void testAddLtOperator() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("familyName"),
			"demographics/familyName/value lt 'N'");
	}

	@Test
	public void testAddNeOperator() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("favoritePokemon"),
			"demographics/favoritePokemon/value ne 'Thundurus'");
	}

	@Test
	public void testAddNotStringFunction() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("telephone"),
			"not contains(demographics/telephone/value, '909')");
	}

	@Test
	public void testAddOr() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("country", "favoriteArtist"),
			"demographics/country/value eq 'France' or " +
				"demographics/favoriteArtist/value eq 'Laurice Deauxnim'");
	}

	@Test
	public void testAddOrganizationContainsNameTreePath() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0], new String[0], new String[0],
			"(organizations.filter(filter='(contains(nameTreePath, " +
				"''childOrg1''))'))");
	}

	@Test
	public void testAddOrganizationCustomField() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildFieldMappingJSONObject(
				null, "custom", JSONUtil.put(dataSourceId, "department"),
				"department", "Text", "organization"));

		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			new String[] {fieldMappingJSONObject.getString("id")},
			"(organizations.filter(filter='(custom/department/value eq " +
				"''engineering'')'))");
	}

	@Test
	public void testAddOrganizationCustomStringFunctionField()
		throws Exception {

		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildFieldMappingJSONObject(
				null, "custom", JSONUtil.put(dataSourceId, "department"),
				"department", "Text", "organization"));

		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			new String[] {fieldMappingJSONObject.getString("id")},
			"(organizations.filter(filter='(contains(" +
				"custom/department/value, ''life''))'))");
	}

	@Test
	public void testAddOrganizationIdEq() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				dataSourceId);

		String organizationId = organizationJSONObject.getString("id");

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[0], new String[0],
			new String[] {organizationId},
			"(organizations.filter(filter='(id eq ''" + organizationId +
				"'')'))");
	}

	@Test
	public void testAddOrganizationModifiedDate() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0], new String[0], new String[0],
			"organizations.filter(filter='(dateModified gt 1580256740750)')");
	}

	@Test
	public void testAddOrganizationParentIdNe() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				dataSourceId);

		String organizationId = organizationJSONObject.getString("id");

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId}, new String[0], new String[0],
			new String[] {organizationId},
			"(organizations.filter(filter='(parentId ne ''" + organizationId +
				"'')'))");
	}

	@Test
	public void testAddOrganizationWithMultipleClauses() throws Exception {
		List<String> expectedReferencedAssetDataSourceIds = new ArrayList<>();
		List<String> expectedReferencedOrganizationIds = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("(organizations.filter(filter='(contains(nameTreePath, ");
		sb.append("''childOrg1'')) ");

		for (String dataSourceId :
				_liferayDataSourceOrganizationIdsJSONObject.keySet()) {

			expectedReferencedAssetDataSourceIds.add(dataSourceId);

			JSONObject organizationJSONObject =
				_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
					dataSourceId);

			String organizationId = organizationJSONObject.getString("id");

			expectedReferencedOrganizationIds.add(organizationId);

			sb.append("or id eq ''");
			sb.append(organizationId);
			sb.append("'' ");
		}

		sb.append("'))");

		_assertAddSetsReferencedObjectIds(
			expectedReferencedAssetDataSourceIds.toArray(new String[0]),
			new String[0], new String[0],
			expectedReferencedOrganizationIds.toArray(new String[0]),
			sb.toString());
	}

	@Test
	public void testAddParentheses() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("worksFor"),
			"(((demographics/worksFor/value eq 'Bluecorp')))");
	}

	@Test
	public void testAddRoleIdEq() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject roleJSONObject = _dxpRawElasticsearchInvoker.add(
			"roles", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {roleJSONObject.getString("id")},
			"roleIds eq '" + roleJSONObject.getString("id") + "'",
			"referencedRoleIds");
	}

	@Test
	public void testAddSameFieldMappingReferencedMultipleTimesOnlyAppearsOnce()
		throws Exception {

		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("state"),
			"demographics/state/value eq 'Alaska' or " +
				"demographics/state/value eq 'Hawaii'");
	}

	@Test
	public void testAddStartsWithFunction() throws Exception {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("givenName"),
			"startsWith(demographics/givenName/value, 'B')");
	}

	@Test
	public void testAddTeamIdEq() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject teamJSONObject = _dxpRawElasticsearchInvoker.add(
			"teams", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {teamJSONObject.getString("id")},
			"teamIds eq '" + teamJSONObject.getString("id") + "'",
			"referencedTeamIds");
	}

	@Test
	public void testAddUserGroupIdEq() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject userGroupJSONObject = _dxpRawElasticsearchInvoker.add(
			"user-groups", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {userGroupJSONObject.getString("id")},
			"userGroupIds eq '" + userGroupJSONObject.getString("id") + "'",
			"referencedUserGroupIds");
	}

	@Test
	public void testAddUserIdEq() throws Exception {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		/*elasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				elasticsearchInvoker.get("data-sources", dataSourceId)));
*/
		JSONObject userJSONObject = _dxpRawElasticsearchInvoker.add(
			"users", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {userJSONObject.getString("id")},
			"userId eq '" + userJSONObject.getString("id") + "'",
			"referencedUserIds");
	}

	@Test
	public void testFieldNameDoesNotNeedValueSuffixToAddReferencedFieldMappingId()
		throws Exception {

		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("image"),
			"demographics/image ne null");
	}

	@Test
	public void testFieldNameMustBePrefixedWithDemographicsToAddReferencedFieldMappingId()
		throws Exception {

		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0], new String[0],
			"street eq 'Broadway'");
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetSegments() {
		List<Segment> segments = _segmentDog.getSegments(
			Arrays.asList(338511398116723457L, 338511451975440187L));

		Assert.assertEquals(segments.toString(), 2, segments.size());

		_assertSegment(2L, "Test Segment 0", segments.get(0));
		_assertSegment(1L, "Test Segment 2", segments.get(1));
	}

	@Test
	public void testReferencedAssetDataSourceIdsAddedOnUpdate()
		throws Exception {

		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertUpdateSetsReferencedObjectIds(
			"id ne null", new String[0], new String[0], new String[0],
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			"activities/ever eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testReferencedAssetDataSourceIdsEmptiedOnUpdate()
		throws Exception {

		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(dataSourceId);

		String assetId = assetIdsJSONArray.getString(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertUpdateSetsReferencedObjectIds(
			"activities/ever eq 'page#pageViewed#" + assetId + "'",
			new String[] {dataSourceId}, new String[] {assetId}, new String[0],
			new String[0], new String[0], new String[0], "");
	}

	@Test
	public void testReferencedAssetDataSourceIdsModifiedOnUpdate()
		throws Exception {

		int addDataSourceIdIndex = RandomUtils.nextInt(
			0, _liferayDataSourceIdsJSONArray.length());

		String addDataSourceId = _liferayDataSourceIdsJSONArray.getString(
			addDataSourceIdIndex);

		JSONArray addAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(addDataSourceId);

		String addAssetId = addAssetIdsJSONArray.getString(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		String updateDataSourceId = _liferayDataSourceIdsJSONArray.getString(
			(addDataSourceIdIndex + 1) %
				_liferayDataSourceIdsJSONArray.length());

		JSONArray updateAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				updateDataSourceId);

		String updateAssetId = updateAssetIdsJSONArray.getString(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		_assertUpdateSetsReferencedObjectIds(
			"activities/ever eq 'page#pageViewed#" + addAssetId + "'",
			new String[] {addDataSourceId}, new String[] {addAssetId},
			new String[0], new String[] {updateDataSourceId},
			new String[] {updateAssetId}, new String[0],
			"activities/ever eq 'page#pageViewed#" + updateAssetId + "'");
	}

	@Test
	public void testReferencedFieldMappingIdsAddedOnUpdate() throws Exception {
		_assertUpdateSetsReferencedObjectIds(
			"", new String[0], new String[0], new String[0], new String[0],
			new String[0],
			_convertFieldNamesToFieldMappingIds("additionalName"),
			"demographics/additionalName/value eq 'Miles'");
	}

	@Test
	public void testReferencedFieldMappingIdsEmptiedOnUpdate()
		throws Exception {

		_assertUpdateSetsReferencedObjectIds(
			"demographics/city/value eq 'Los Angeles'", new String[0],
			new String[0], _convertFieldNamesToFieldMappingIds("city"),
			new String[0], new String[0], new String[0], "");
	}

	@Test
	public void testReferencedFieldMappingIdsModifiedOnUpdate()
		throws Exception {

		_assertUpdateSetsReferencedObjectIds(
			"demographics/honorificPrefix/value eq 'Mrs.'", new String[0],
			new String[0],
			_convertFieldNamesToFieldMappingIds("honorificPrefix"),
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("gender"),
			"demographics/gender/value eq 'Female'");
	}

	@Test
	public void testReferencedOrganizationIdsEmptiedOnUpdate()
		throws Exception {

		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				dataSourceId);

		String organizationId = organizationJSONObject.getString("id");

		_assertUpdateSetsReferencedObjectIds(
			"(organizations.filter(filter='(id eq ''" + organizationId +
				"'')'))",
			new String[] {dataSourceId}, new String[0], new String[0],
			new String[] {organizationId}, new String[0], new String[0],
			new String[0], new String[0], "");
	}

	@Test
	public void testReferencedOrganizationIdsModifiedOnUpdate()
		throws Exception {

		String addDataSourceId = _liferayDataSourceIdsJSONArray.getString(0);

		JSONObject addOrganizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				addDataSourceId);

		String addOrganizationId = addOrganizationJSONObject.getString("id");

		String updateDataSourceId = _liferayDataSourceIdsJSONArray.getString(1);

		JSONObject updateOrganizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				updateDataSourceId);

		String updateOrganizationId = updateOrganizationJSONObject.getString(
			"id");

		_assertUpdateSetsReferencedObjectIds(
			"(organizations.filter(filter='(id eq ''" + addOrganizationId +
				"'')'))",
			new String[] {addDataSourceId}, new String[0], new String[0],
			new String[] {addOrganizationId}, new String[] {updateDataSourceId},
			new String[0], new String[0], new String[] {updateOrganizationId},
			"(organizations.filter(filter='(parentId eq ''" +
				updateOrganizationId + "'')'))");
	}

	@Test
	public void testUpdateFreestyle() throws Exception {
		StringBuilder addFilterSB = new StringBuilder();

		addFilterSB.append("((demographics/age/value ge 21 or ((startsWith(");
		addFilterSB.append("demographics/favoritePokemon/value, 'Tapu')) or ");
		addFilterSB.append("(activities/last7Days ne 'page#pageViewed#");

		int addDataSourceIdIndex = RandomUtils.nextInt(
			0, _liferayDataSourceIdsJSONArray.length());

		String addDataSourceId = _liferayDataSourceIdsJSONArray.getString(
			addDataSourceIdIndex);

		JSONArray addAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(addDataSourceId);

		String addAssetId = addAssetIdsJSONArray.getString(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		addFilterSB.append(addAssetId);

		addFilterSB.append("' and demographics/additionalName/value lt 'S'))");
		addFilterSB.append("and demographics/image/value ne null ");
		addFilterSB.append("and organizations.filter(filter='(id eq ''");

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				addDataSourceId);

		String organizationId = organizationJSONObject.getString("id");

		addFilterSB.append(organizationId);

		addFilterSB.append("'')')))");

		StringBuilder updateFilterSB = new StringBuilder();

		updateFilterSB.append("((demographics/worksFor/value ne null and ");
		updateFilterSB.append("activities/ever eq 'page#pageViewed#");

		String updateDataSourceId = _liferayDataSourceIdsJSONArray.getString(
			(addDataSourceIdIndex + 1) %
				_liferayDataSourceIdsJSONArray.length());

		JSONArray updateAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				updateDataSourceId);

		String updateAssetId = updateAssetIdsJSONArray.getString(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		updateFilterSB.append(updateAssetId);

		updateFilterSB.append("') or ((demographics/telephone/value eq ");
		updateFilterSB.append("'page#pageViewed#");
		updateFilterSB.append(addAssetId);
		updateFilterSB.append("' and demographics/gender/value eq true) or ");
		updateFilterSB.append("(demographics/age/value lt 55 and ");
		updateFilterSB.append("additionalName/value gt 'M')))");

		_assertUpdateSetsReferencedObjectIds(
			addFilterSB.toString(), new String[] {addDataSourceId},
			new String[] {addAssetId},
			_convertFieldNamesToFieldMappingIds(
				"additionalName", "age", "favoritePokemon", "image"),
			new String[] {organizationId}, new String[] {updateDataSourceId},
			new String[] {updateAssetId},
			_convertFieldNamesToFieldMappingIds(
				"age", "gender", "telephone", "worksFor"),
			new String[0], updateFilterSB.toString());
	}

	private void _assertAddSetsReferencedObjectIds(
			String[] expectedReferencedAssetDataSourceIds,
			String[] expectedReferencedIds, String filterString, String key)
		throws Exception {

		JSONObject individualSegmentJSONObject =
			_segmentDog.addIndividualSegment(
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					filterString));

		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(
				"referencedAssetDataSourceIds"),
			expectedReferencedAssetDataSourceIds);
		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(key),
			expectedReferencedIds);
	}

	private void _assertAddSetsReferencedObjectIds(
			String[] expectedReferencedAssetDataSourceIds,
			String[] expectedReferencedAssetIds,
			String[] expectedReferencedFieldMappingIds, String filterString)
		throws Exception {

		_assertAddSetsReferencedObjectIds(
			expectedReferencedAssetDataSourceIds, expectedReferencedAssetIds,
			expectedReferencedFieldMappingIds, new String[0], filterString);
	}

	private JSONObject _assertAddSetsReferencedObjectIds(
			String[] expectedReferencedAssetDataSourceIds,
			String[] expectedReferencedAssetIds,
			String[] expectedReferencedFieldMappingIds,
			String[] expectedReferencedOrganizationIds, String filterString)
		throws Exception {

		JSONObject individualSegmentJSONObject =
			_segmentDog.addIndividualSegment(
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					filterString));

		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(
				"referencedAssetDataSourceIds"),
			expectedReferencedAssetDataSourceIds);
		_assertSameContents(
			individualSegmentJSONObject.getJSONArray("referencedAssetIds"),
			expectedReferencedAssetIds);
		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(
				"referencedFieldMappingIds"),
			expectedReferencedFieldMappingIds);
		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(
				"referencedOrganizationIds"),
			expectedReferencedOrganizationIds);

		return individualSegmentJSONObject;
	}

	private void _assertSameContents(
		JSONArray actualValuesJSONArray, String[] expectedValues) {

		Assert.assertEquals(
			expectedValues.length, actualValuesJSONArray.length());

		for (String value : expectedValues) {
			Assert.assertTrue(
				"Expected to find value " + value + " in JSONArray " +
					actualValuesJSONArray,
				JSONUtil.hasValue(actualValuesJSONArray, value));
		}
	}

	private void _assertSegment(
		Long expectedSegmentIndividualCount, String expectedSegmentName,
		Segment actualSegment) {

		Assert.assertEquals(expectedSegmentName, actualSegment.getName());
		Assert.assertEquals(
			expectedSegmentIndividualCount, actualSegment.getIndividualCount());
	}

	private void _assertUpdateSetsReferencedObjectIds(
			String addFilter, String[] expectedAddReferencedAssetDataSourceIds,
			String[] expectedAddReferencedAssetIds,
			String[] expectedAddReferencedFieldMappingIds,
			String[] expectedUpdateReferencedAssetDataSourceIds,
			String[] expectedUpdateReferencedAssetIds,
			String[] expectedUpdateReferencedFieldMappingIds,
			String updateFilter)
		throws Exception {

		_assertUpdateSetsReferencedObjectIds(
			addFilter, expectedAddReferencedAssetDataSourceIds,
			expectedAddReferencedAssetIds, expectedAddReferencedFieldMappingIds,
			new String[0], expectedUpdateReferencedAssetDataSourceIds,
			expectedUpdateReferencedAssetIds,
			expectedUpdateReferencedFieldMappingIds, new String[0],
			updateFilter);
	}

	private void _assertUpdateSetsReferencedObjectIds(
			String addFilter, String[] expectedAddReferencedAssetDataSourceIds,
			String[] expectedAddReferencedAssetIds,
			String[] expectedAddReferencedFieldMappingIds,
			String[] expectedAddReferencedOrganizationIds,
			String[] expectedUpdateReferencedAssetDataSourceIds,
			String[] expectedUpdateReferencedAssetIds,
			String[] expectedUpdateReferencedFieldMappingIds,
			String[] expectedUpdateReferencedOrganizationIds,
			String updateFilter)
		throws Exception {

		JSONObject individualSegmentJSONObject =
			_assertAddSetsReferencedObjectIds(
				expectedAddReferencedAssetDataSourceIds,
				expectedAddReferencedAssetIds,
				expectedAddReferencedFieldMappingIds,
				expectedAddReferencedOrganizationIds, addFilter);

		individualSegmentJSONObject = _segmentDog.updateIndividualSegment(
			individualSegmentJSONObject.getLong("id"),
			JSONUtil.put("filter", updateFilter));

		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(
				"referencedAssetDataSourceIds"),
			expectedUpdateReferencedAssetDataSourceIds);
		_assertSameContents(
			individualSegmentJSONObject.getJSONArray("referencedAssetIds"),
			expectedUpdateReferencedAssetIds);
		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(
				"referencedFieldMappingIds"),
			expectedUpdateReferencedFieldMappingIds);
		_assertSameContents(
			individualSegmentJSONObject.getJSONArray(
				"referencedOrganizationIds"),
			expectedUpdateReferencedOrganizationIds);
	}

	private String[] _convertFieldNamesToFieldMappingIds(String... fieldNames) {
		String[] fieldMappingIds = new String[fieldNames.length];

		for (int i = 0; i < fieldNames.length; i++) {
			fieldMappingIds[i] = _fieldMappingNameIds.getString(fieldNames[i]);
		}

		return fieldMappingIds;
	}

	private static final String[] _FIELD_NAMES = {
		"additionalName", "address", "age", "birthDate", "city", "country",
		"email", "familyName", "favoriteArtist", "favoriteColor",
		"favoriteGenre", "favoritePokemon", "gender", "givenName",
		"honorificPrefix", "image", "jobTitle", "postalCode", "state", "street",
		"telephone", "worksFor"
	};

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	private final JSONObject _fieldMappingNameIds = new JSONObject();
	private final JSONObject _liferayDataSourceAssetIdsJSONObject =
		new JSONObject();
	private final JSONArray _liferayDataSourceIdsJSONArray = new JSONArray();
	private final JSONObject _liferayDataSourceOrganizationIdsJSONObject =
		new JSONObject();

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}