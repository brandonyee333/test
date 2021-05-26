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
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
	public void setUp() {
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
			FieldMapping fieldMapping = _fieldMappingRepository.save(
				FaroInfoTestUtil.buildIndividualFieldMapping(
					new HashMap<String, String>() {
						{
							put(
								_liferayDataSourceIdsJSONArray.getString(0),
								fieldName);
							put(
								_liferayDataSourceIdsJSONArray.getString(1),
								fieldName);
							put(
								_liferayDataSourceIdsJSONArray.getString(2),
								fieldName);
						}
					},
					fieldName, "Text"));

			_fieldMappingNameIds.put(
				fieldName, String.valueOf(fieldMapping.getId()));
		}
	}

	@Test
	public void testAddAnd() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds(
				"favoriteColor", "favoriteGenre"),
			"demographics/favoriteColor/value eq 'blue' and " +
				"demographics/favoriteGenre/value eq 'Science Fiction'");
	}

	@Test
	public void testAddBehavioralCriteriaWithMultipleAssetsFromDifferentDataSources() {
		Long[] expectedReferencedAssetDataSourceIds =
			new Long[_liferayDataSourceIdsJSONArray.length() - 1];

		List<Long> expectedReferencedAssetIds = new ArrayList<>();

		StringBuilder filterSB = new StringBuilder();

		for (int i = 0; i < (_liferayDataSourceIdsJSONArray.length() - 1);
			 i++) {

			Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(i);

			expectedReferencedAssetDataSourceIds[i] = dataSourceId;

			JSONArray assetIdsJSONArray =
				_liferayDataSourceAssetIdsJSONObject.getJSONArray(
					dataSourceId.toString());

			Long assetId = assetIdsJSONArray.getLong(
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
			expectedReferencedAssetIds.toArray(new Long[0]), new Long[0],
			filterSB.toString());
	}

	@Test
	public void testAddBehavioralCriteriaWithMultipleAssetsFromSameDataSource() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		StringBuilder filterSB = new StringBuilder();

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		for (int i = 0; i < assetIdsJSONArray.length(); i++) {
			filterSB.append("activities/ever eq 'page#pageViewed#");
			filterSB.append(assetIdsJSONArray.getString(i));
			filterSB.append("'");

			if (i < (assetIdsJSONArray.length() - 1)) {
				filterSB.append(" and ");
			}
		}

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId},
			JSONUtil.toLongList(
				assetIdsJSONArray
			).toArray(
				new Long[0]
			),
			new Long[0], filterSB.toString());
	}

	@Test
	public void testAddBehavioralCriterionEqEver() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/ever eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqLast7Days() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/last7Days eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqLast30Days() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/last30Days eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqLastYear() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/lastYear eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionEqToday() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/today eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeEver() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/ever ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeLast7Days() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/last7Days ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeLast30Days() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/last30Days ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeLastYear() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/lastYear ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddBehavioralCriterionNeToday() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/today ne 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testAddContainsFunction() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("jobTitle"),
			"contains(demographics/jobTitle/value, 'Engineer')");
	}

	@Test
	public void testAddEndsWithFunction() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("email"),
			"endsWith(demographics/email/value, '@liferay.com')");
	}

	@Test
	public void testAddEqOperator() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("address"),
			"demographics/address/value eq '221B Baker Street'");
	}

	@Test
	public void testAddGeOperator() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("age"),
			"demographics/age/value ge 18");
	}

	@Test
	public void testAddGroupIdEq() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject groupJSONObject = _dxpRawElasticsearchInvoker.add(
			"groups", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId},
			new Long[] {groupJSONObject.getLong("id")},
			"groupIds eq '" + groupJSONObject.getString("id") + "'",
			"referencedGroupIds");
	}

	@Test
	public void testAddGtOperator() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("birthDate"),
			"demographics/birthDate/value gt '1989-11-09T00:00:00.000Z'");
	}

	@Test
	public void testAddLeOperator() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("postalCode"),
			"demographics/postalCode/value le '09999'");
	}

	@Test
	public void testAddLtOperator() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("familyName"),
			"demographics/familyName/value lt 'N'");
	}

	@Test
	public void testAddNeOperator() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("favoritePokemon"),
			"demographics/favoritePokemon/value ne 'Thundurus'");
	}

	@Test
	public void testAddNotStringFunction() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("telephone"),
			"not contains(demographics/telephone/value, '909')");
	}

	@Test
	public void testAddOr() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("country", "favoriteArtist"),
			"demographics/country/value eq 'France' or " +
				"demographics/favoriteArtist/value eq 'Laurice Deauxnim'");
	}

	@Test
	public void testAddOrganizationContainsNameTreePath() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0], new Long[0], new Long[0],
			"(organizations.filter(filter='(contains(nameTreePath, " +
				"''childOrg1''))'))");
	}

	@Test
	public void testAddOrganizationCustomField() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		FieldMapping fieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				new FieldMapping.Author("FARO_SYSTEM", "FARO_SYSTEM"), "custom",
				Collections.singletonMap(dataSourceId.toString(), "department"),
				"department", "Text", "organization"));

		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0], new Long[] {fieldMapping.getId()},
			"(organizations.filter(filter='(custom/department/value eq " +
				"''engineering'')'))");
	}

	@Test
	public void testAddOrganizationCustomStringFunctionField() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		FieldMapping fieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				null, "custom",
				Collections.singletonMap(dataSourceId.toString(), "department"),
				"department", "Text", "organization"));

		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0], new Long[] {fieldMapping.getId()},
			"(organizations.filter(filter='(contains(" +
				"custom/department/value, ''life''))'))");
	}

	@Test
	public void testAddOrganizationIdEq() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				dataSourceId.toString());

		Long organizationId = organizationJSONObject.getLong("id");

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[0], new Long[0],
			new Long[] {organizationId},
			"(organizations.filter(filter='(id eq ''" + organizationId +
				"'')'))");
	}

	@Test
	public void testAddOrganizationModifiedDate() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0], new Long[0], new Long[0],
			"organizations.filter(filter='(dateModified gt 1580256740750)')");
	}

	@Test
	public void testAddOrganizationParentIdNe() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				dataSourceId.toString());

		Long organizationId = organizationJSONObject.getLong("id");

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId}, new Long[0], new Long[0],
			new Long[] {organizationId},
			"(organizations.filter(filter='(parentId ne ''" + organizationId +
				"'')'))");
	}

	@Test
	public void testAddOrganizationWithMultipleClauses() {
		List<Long> expectedReferencedAssetDataSourceIds = new ArrayList<>();
		List<Long> expectedReferencedOrganizationIds = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("(organizations.filter(filter='(contains(nameTreePath, ");
		sb.append("''childOrg1'')) ");

		for (String dataSourceId :
				_liferayDataSourceOrganizationIdsJSONObject.keySet()) {

			expectedReferencedAssetDataSourceIds.add(
				Long.parseLong(dataSourceId));

			JSONObject organizationJSONObject =
				_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
					dataSourceId);

			Long organizationId = organizationJSONObject.getLong("id");

			expectedReferencedOrganizationIds.add(organizationId);

			sb.append("or id eq ''");
			sb.append(organizationId);
			sb.append("'' ");
		}

		sb.append("'))");

		_assertAddSetsReferencedObjectIds(
			expectedReferencedAssetDataSourceIds.toArray(new Long[0]),
			new Long[0], new Long[0],
			expectedReferencedOrganizationIds.toArray(new Long[0]),
			sb.toString());
	}

	@Test
	public void testAddParentheses() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("worksFor"),
			"(((demographics/worksFor/value eq 'Bluecorp')))");
	}

	@Test
	public void testAddRoleIdEq() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject roleJSONObject = _dxpRawElasticsearchInvoker.add(
			"roles", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId},
			new Long[] {roleJSONObject.getLong("id")},
			"roleIds eq '" + roleJSONObject.getString("id") + "'",
			"referencedRoleIds");
	}

	@Test
	public void testAddSameFieldMappingReferencedMultipleTimesOnlyAppearsOnce() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("state"),
			"demographics/state/value eq 'Alaska' or " +
				"demographics/state/value eq 'Hawaii'");
	}

	@Test
	public void testAddStartsWithFunction() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("givenName"),
			"startsWith(demographics/givenName/value, 'B')");
	}

	@Test
	public void testAddTeamIdEq() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject teamJSONObject = _dxpRawElasticsearchInvoker.add(
			"teams", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId},
			new Long[] {teamJSONObject.getLong("id")},
			"teamIds eq '" + teamJSONObject.getString("id") + "'",
			"referencedTeamIds");
	}

	@Test
	public void testAddUserGroupIdEq() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject userGroupJSONObject = _dxpRawElasticsearchInvoker.add(
			"user-groups", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId},
			new Long[] {userGroupJSONObject.getLong("id")},
			"userGroupIds eq '" + userGroupJSONObject.getString("id") + "'",
			"referencedUserGroupIds");
	}

	@Test
	public void testAddUserIdEq() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject userJSONObject = _dxpRawElasticsearchInvoker.add(
			"users", JSONUtil.put("osbAsahDataSourceId", dataSourceId));

		_assertAddSetsReferencedObjectIds(
			new Long[] {dataSourceId},
			new Long[] {userJSONObject.getLong("id")},
			"userId eq '" + userJSONObject.getString("id") + "'",
			"referencedUserIds");
	}

	@Test
	public void testFieldNameDoesNotNeedValueSuffixToAddReferencedFieldMappingId() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("image"),
			"demographics/image ne null");
	}

	@Test
	public void testFieldNameMustBePrefixedWithDemographicsToAddReferencedFieldMappingId() {
		_assertAddSetsReferencedObjectIds(
			new Long[0], new Long[0], new Long[0], "street eq 'Broadway'");
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
	public void testReferencedAssetDataSourceIdsAddedOnUpdate() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertUpdateSetsReferencedObjectIds(
			"id ne null", new Long[0], new Long[0], new Long[0],
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			"activities/ever eq 'page#pageViewed#" + assetId + "'");
	}

	@Test
	public void testReferencedAssetDataSourceIdsEmptiedOnUpdate() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONArray assetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				dataSourceId.toString());

		Long assetId = assetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, assetIdsJSONArray.length()));

		_assertUpdateSetsReferencedObjectIds(
			"activities/ever eq 'page#pageViewed#" + assetId + "'",
			new Long[] {dataSourceId}, new Long[] {assetId}, new Long[0],
			new Long[0], new Long[0], new Long[0], "");
	}

	@Test
	public void testReferencedAssetDataSourceIdsModifiedOnUpdate() {
		int addDataSourceIdIndex = RandomUtils.nextInt(
			0, _liferayDataSourceIdsJSONArray.length());

		Long addDataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			addDataSourceIdIndex);

		JSONArray addAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				addDataSourceId.toString());

		Long addAssetId = addAssetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		Long updateDataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			(addDataSourceIdIndex + 1) %
				_liferayDataSourceIdsJSONArray.length());

		JSONArray updateAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				updateDataSourceId.toString());

		Long updateAssetId = updateAssetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		_assertUpdateSetsReferencedObjectIds(
			"activities/ever eq 'page#pageViewed#" + addAssetId + "'",
			new Long[] {addDataSourceId}, new Long[] {addAssetId}, new Long[0],
			new Long[] {updateDataSourceId}, new Long[] {updateAssetId},
			new Long[0],
			"activities/ever eq 'page#pageViewed#" + updateAssetId + "'");
	}

	@Test
	public void testReferencedFieldMappingIdsAddedOnUpdate() {
		_assertUpdateSetsReferencedObjectIds(
			"", new Long[0], new Long[0], new Long[0], new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("additionalName"),
			"demographics/additionalName/value eq 'Miles'");
	}

	@Test
	public void testReferencedFieldMappingIdsEmptiedOnUpdate() {
		_assertUpdateSetsReferencedObjectIds(
			"demographics/city/value eq 'Los Angeles'", new Long[0],
			new Long[0], _convertFieldNamesToFieldMappingIds("city"),
			new Long[0], new Long[0], new Long[0], "");
	}

	@Test
	public void testReferencedFieldMappingIdsModifiedOnUpdate() {
		_assertUpdateSetsReferencedObjectIds(
			"demographics/honorificPrefix/value eq 'Mrs.'", new Long[0],
			new Long[0], _convertFieldNamesToFieldMappingIds("honorificPrefix"),
			new Long[0], new Long[0],
			_convertFieldNamesToFieldMappingIds("gender"),
			"demographics/gender/value eq 'Female'");
	}

	@Test
	public void testReferencedOrganizationIdsEmptiedOnUpdate() {
		Long dataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				dataSourceId.toString());

		Long organizationId = organizationJSONObject.getLong("id");

		_assertUpdateSetsReferencedObjectIds(
			"(organizations.filter(filter='(id eq ''" + organizationId +
				"'')'))",
			new Long[] {dataSourceId}, new Long[0], new Long[0],
			new Long[] {organizationId}, new Long[0], new Long[0], new Long[0],
			new Long[0], "");
	}

	@Test
	public void testReferencedOrganizationIdsModifiedOnUpdate() {
		Long addDataSourceId = _liferayDataSourceIdsJSONArray.getLong(0);

		JSONObject addOrganizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				addDataSourceId.toString());

		Long addOrganizationId = addOrganizationJSONObject.getLong("id");

		Long updateDataSourceId = _liferayDataSourceIdsJSONArray.getLong(1);

		JSONObject updateOrganizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				updateDataSourceId.toString());

		Long updateOrganizationId = updateOrganizationJSONObject.getLong("id");

		_assertUpdateSetsReferencedObjectIds(
			"(organizations.filter(filter='(id eq ''" + addOrganizationId +
				"'')'))",
			new Long[] {addDataSourceId}, new Long[0], new Long[0],
			new Long[] {addOrganizationId}, new Long[] {updateDataSourceId},
			new Long[0], new Long[0], new Long[] {updateOrganizationId},
			"(organizations.filter(filter='(parentId eq ''" +
				updateOrganizationId + "'')'))");
	}

	@Test
	public void testUpdateFreestyle() {
		StringBuilder addFilterSB = new StringBuilder();

		addFilterSB.append("((demographics/age/value ge 21 or ((startsWith(");
		addFilterSB.append("demographics/favoritePokemon/value, 'Tapu')) or ");
		addFilterSB.append("(activities/last7Days ne 'page#pageViewed#");

		int addDataSourceIdIndex = RandomUtils.nextInt(
			0, _liferayDataSourceIdsJSONArray.length());

		Long addDataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			addDataSourceIdIndex);

		JSONArray addAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				addDataSourceId.toString());

		Long addAssetId = addAssetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		addFilterSB.append(addAssetId);

		addFilterSB.append("' and demographics/additionalName/value lt 'S'))");
		addFilterSB.append("and demographics/image/value ne null ");
		addFilterSB.append("and organizations.filter(filter='(id eq ''");

		JSONObject organizationJSONObject =
			_liferayDataSourceOrganizationIdsJSONObject.getJSONObject(
				addDataSourceId.toString());

		Long organizationId = organizationJSONObject.getLong("id");

		addFilterSB.append(organizationId);

		addFilterSB.append("'')')))");

		StringBuilder updateFilterSB = new StringBuilder();

		updateFilterSB.append("((demographics/worksFor/value ne null and ");
		updateFilterSB.append("activities/ever eq 'page#pageViewed#");

		Long updateDataSourceId = _liferayDataSourceIdsJSONArray.getLong(
			(addDataSourceIdIndex + 1) %
				_liferayDataSourceIdsJSONArray.length());

		JSONArray updateAssetIdsJSONArray =
			_liferayDataSourceAssetIdsJSONObject.getJSONArray(
				updateDataSourceId.toString());

		Long updateAssetId = updateAssetIdsJSONArray.getLong(
			RandomUtils.nextInt(0, addAssetIdsJSONArray.length()));

		updateFilterSB.append(updateAssetId);

		updateFilterSB.append("') or ((demographics/telephone/value eq ");
		updateFilterSB.append("'page#pageViewed#");
		updateFilterSB.append(addAssetId);
		updateFilterSB.append("' and demographics/gender/value eq true) or ");
		updateFilterSB.append("(demographics/age/value lt 55 and ");
		updateFilterSB.append("additionalName/value gt 'M')))");

		_assertUpdateSetsReferencedObjectIds(
			addFilterSB.toString(), new Long[] {addDataSourceId},
			new Long[] {addAssetId},
			_convertFieldNamesToFieldMappingIds(
				"additionalName", "age", "favoritePokemon", "image"),
			new Long[] {organizationId}, new Long[] {updateDataSourceId},
			new Long[] {updateAssetId},
			_convertFieldNamesToFieldMappingIds(
				"age", "gender", "telephone", "worksFor"),
			new Long[0], updateFilterSB.toString());
	}

	private Segment _assertAddSetsReferencedObjectIds(
		Long[] expectedReferencedAssetDataSourceIds,
		Long[] expectedReferencedAssetIds,
		Long[] expectedReferencedFieldMappingIds,
		Long[] expectedReferencedOrganizationIds, String filterString) {

		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildDynamicSegment(filterString));

		_assertSameContents(
			segment.getReferencedAssetDataSourceIds(),
			expectedReferencedAssetDataSourceIds);
		_assertSameContents(
			segment.getReferencedAssetIds(), expectedReferencedAssetIds);
		_assertSameContents(
			segment.getReferencedFieldMappingIds(),
			expectedReferencedFieldMappingIds);
		_assertSameContents(
			segment.getReferencedOrganizationIds(),
			expectedReferencedOrganizationIds);

		return segment;
	}

	private void _assertAddSetsReferencedObjectIds(
		Long[] expectedReferencedAssetDataSourceIds,
		Long[] expectedReferencedAssetIds,
		Long[] expectedReferencedFieldMappingIds, String filterString) {

		_assertAddSetsReferencedObjectIds(
			expectedReferencedAssetDataSourceIds, expectedReferencedAssetIds,
			expectedReferencedFieldMappingIds, new Long[0], filterString);
	}

	private void _assertAddSetsReferencedObjectIds(
		Long[] expectedReferencedAssetDataSourceIds,
		Long[] expectedReferencedIds, String filterString, String key) {

		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildDynamicSegment(filterString));

		_assertSameContents(
			segment.getReferencedAssetDataSourceIds(),
			expectedReferencedAssetDataSourceIds);

		Set<Long> actualReferencedIds = null;

		if (Objects.equals(key, "referencedGroupIds")) {
			actualReferencedIds = segment.getReferencedGroupIds();
		}
		else if (Objects.equals(key, "referencedRoleIds")) {
			actualReferencedIds = segment.getReferencedRoleIds();
		}
		else if (Objects.equals(key, "referencedTeamIds")) {
			actualReferencedIds = segment.getReferencedTeamIds();
		}
		else if (Objects.equals(key, "referencedUserGroupIds")) {
			actualReferencedIds = segment.getReferencedUserGroupIds();
		}
		else if (Objects.equals(key, "referencedUserIds")) {
			actualReferencedIds = segment.getReferencedUserIds();
		}

		_assertSameContents(actualReferencedIds, expectedReferencedIds);
	}

	private void _assertSameContents(
		Set<Long> actualValues, Long[] expectedValues) {

		Assert.assertEquals(
			actualValues.toString(), expectedValues.length,
			actualValues.size());

		for (Long value : expectedValues) {
			Assert.assertTrue(
				"Expected to find value " + value + " in set " + actualValues,
				actualValues.contains(value));
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
		String addFilter, Long[] expectedAddReferencedAssetDataSourceIds,
		Long[] expectedAddReferencedAssetIds,
		Long[] expectedAddReferencedFieldMappingIds,
		Long[] expectedAddReferencedOrganizationIds,
		Long[] expectedUpdateReferencedAssetDataSourceIds,
		Long[] expectedUpdateReferencedAssetIds,
		Long[] expectedUpdateReferencedFieldMappingIds,
		Long[] expectedUpdateReferencedOrganizationIds, String updateFilter) {

		Segment segment = _assertAddSetsReferencedObjectIds(
			expectedAddReferencedAssetDataSourceIds,
			expectedAddReferencedAssetIds, expectedAddReferencedFieldMappingIds,
			expectedAddReferencedOrganizationIds, addFilter);

		Segment partialSegment = new Segment();

		partialSegment.setFilter(updateFilter);

		segment = _segmentDog.updateSegment(partialSegment, segment.getId());

		_assertSameContents(
			segment.getReferencedAssetDataSourceIds(),
			expectedUpdateReferencedAssetDataSourceIds);
		_assertSameContents(
			segment.getReferencedAssetIds(), expectedUpdateReferencedAssetIds);
		_assertSameContents(
			segment.getReferencedFieldMappingIds(),
			expectedUpdateReferencedFieldMappingIds);
		_assertSameContents(
			segment.getReferencedOrganizationIds(),
			expectedUpdateReferencedOrganizationIds);
	}

	private void _assertUpdateSetsReferencedObjectIds(
		String addFilter, Long[] expectedAddReferencedAssetDataSourceIds,
		Long[] expectedAddReferencedAssetIds,
		Long[] expectedAddReferencedFieldMappingIds,
		Long[] expectedUpdateReferencedAssetDataSourceIds,
		Long[] expectedUpdateReferencedAssetIds,
		Long[] expectedUpdateReferencedFieldMappingIds, String updateFilter) {

		_assertUpdateSetsReferencedObjectIds(
			addFilter, expectedAddReferencedAssetDataSourceIds,
			expectedAddReferencedAssetIds, expectedAddReferencedFieldMappingIds,
			new Long[0], expectedUpdateReferencedAssetDataSourceIds,
			expectedUpdateReferencedAssetIds,
			expectedUpdateReferencedFieldMappingIds, new Long[0], updateFilter);
	}

	private Long[] _convertFieldNamesToFieldMappingIds(String... fieldNames) {
		Long[] fieldMappingIds = new Long[fieldNames.length];

		for (int i = 0; i < fieldNames.length; i++) {
			fieldMappingIds[i] = _fieldMappingNameIds.getLong(fieldNames[i]);
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

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

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