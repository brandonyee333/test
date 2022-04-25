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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;

/**
 * @author Michael Bowerman
 */
public class SegmentDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		for (int i = 0; i < 3; i++) {
			DataSource dataSource = _dataSourceDog.addDataSource(
				FaroInfoTestUtil.buildLiferayDataSource());

			Long liferayDataSourceId = dataSource.getId();

			_liferayDataSourceIdsJSONArray.put(
				String.valueOf(liferayDataSourceId));

			JSONArray assetIdsJSONArray = new JSONArray();

			for (int j = 0; j < 3; j++) {
				Asset asset = _assetRepository.save(
					_objectMapper.convertValue(
						FaroInfoTestUtil.buildPageAssetJSONObject(
							liferayDataSourceId),
						Asset.class));

				assetIdsJSONArray.put(String.valueOf(asset.getId()));
			}

			_liferayDataSourceAssetIdsJSONObject.put(
				String.valueOf(liferayDataSourceId), assetIdsJSONArray);

			JSONObject organizationJSONObject =
				FaroInfoTestUtil.buildOrganizationJSONObject(
					String.valueOf(liferayDataSourceId));

			_liferayDataSourceOrganizationIdsJSONObject.put(
				String.valueOf(liferayDataSourceId),
				faroInfoElasticsearchInvoker.add(
					"organizations",
					organizationJSONObject.put(
						"type", DXPEntity.Type.ORGANIZATION)));
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
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds(
				"favoriteColor", "favoriteGenre"),
			"demographics/favoriteColor/value eq 'blue' and " +
				"demographics/favoriteGenre/value eq 'Science Fiction'");
	}

	@Test
	public void testAddBehavioralCriteriaWithMultipleAssetsFromDifferentDataSources() {
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
	public void testAddBehavioralCriteriaWithMultipleAssetsFromSameDataSource() {
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
	public void testAddBehavioralCriterionEqEver() {
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
	public void testAddBehavioralCriterionEqLast7Days() {
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
	public void testAddBehavioralCriterionEqLast30Days() {
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
	public void testAddBehavioralCriterionEqLastYear() {
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
	public void testAddBehavioralCriterionEqToday() {
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
	public void testAddBehavioralCriterionNeEver() {
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
	public void testAddBehavioralCriterionNeLast7Days() {
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
	public void testAddBehavioralCriterionNeLast30Days() {
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
	public void testAddBehavioralCriterionNeLastYear() {
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
	public void testAddBehavioralCriterionNeToday() {
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
	public void testAddContainsFunction() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("jobTitle"),
			"contains(demographics/jobTitle/value, 'Engineer')");
	}

	@Test
	public void testAddEndsWithFunction() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("email"),
			"endsWith(demographics/email/value, '@liferay.com')");
	}

	@Test
	public void testAddEqOperator() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("address"),
			"demographics/address/value eq '221B Baker Street'");
	}

	@Test
	public void testAddGeOperator() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("age"),
			"demographics/age/value ge 18");
	}

	@Test
	public void testAddGroupIdEq() {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(Long.valueOf(dataSourceId));
		dxpEntity.setType(DXPEntity.Type.GROUP);

		dxpEntity = _dxpEntityDog.addDXPEntity(dxpEntity, DXPEntity.Type.GROUP);

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {String.valueOf(dxpEntity.getId())},
			"groupIds eq '" + String.valueOf(dxpEntity.getId()) + "'",
			"referencedGroupIds");
	}

	@Test
	public void testAddGtOperator() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("birthDate"),
			"demographics/birthDate/value gt '1989-11-09T00:00:00.000Z'");
	}

	@Test
	public void testAddLeOperator() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("postalCode"),
			"demographics/postalCode/value le '09999'");
	}

	@Test
	public void testAddLtOperator() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("familyName"),
			"demographics/familyName/value lt 'N'");
	}

	@Test
	public void testAddNeOperator() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("favoritePokemon"),
			"demographics/favoritePokemon/value ne 'Thundurus'");
	}

	@Test
	public void testAddNotStringFunction() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("telephone"),
			"not contains(demographics/telephone/value, '909')");
	}

	@Test
	public void testAddOr() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("country", "favoriteArtist"),
			"demographics/country/value eq 'France' or " +
				"demographics/favoriteArtist/value eq 'Laurice Deauxnim'");
	}

	@Test
	public void testAddOrganizationContainsNameTreePath() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0], new String[0], new String[0],
			"(organizations.filter(filter='(contains(nameTreePath, " +
				"''childOrg1''))'))");
	}

	@Test
	public void testAddOrganizationCustomField() {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		FieldMapping fieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				new FieldMapping.Author("FARO_SYSTEM", "FARO_SYSTEM"), "custom",
				Collections.singletonMap(dataSourceId, "department"),
				"department", "Text", "organization"));

		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			new String[] {String.valueOf(fieldMapping.getId())},
			"(organizations.filter(filter='(custom/department/value eq " +
				"''engineering'')'))");
	}

	@Test
	public void testAddOrganizationCustomStringFunctionField() {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		FieldMapping fieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				null, "custom",
				Collections.singletonMap(dataSourceId, "department"),
				"department", "Text", "organization"));

		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			new String[] {String.valueOf(fieldMapping.getId())},
			"(organizations.filter(filter='(contains(" +
				"custom/department/value, ''life''))'))");
	}

	@Test
	public void testAddOrganizationIdEq() {
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
	public void testAddOrganizationModifiedDate() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0], new String[0], new String[0],
			"organizations.filter(filter='(dateModified gt 1580256740750)')");
	}

	@Test
	public void testAddOrganizationParentIdNe() {
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
	public void testAddOrganizationWithMultipleClauses() {
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
	public void testAddParentheses() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("worksFor"),
			"(((demographics/worksFor/value eq 'Bluecorp')))");
	}

	@Test
	public void testAddRoleIdEq() {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(Long.valueOf(dataSourceId));
		dxpEntity.setType(DXPEntity.Type.ROLE);

		dxpEntity = _dxpEntityDog.addDXPEntity(dxpEntity, DXPEntity.Type.ROLE);

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {String.valueOf(dxpEntity.getId())},
			"roleIds eq '" + String.valueOf(dxpEntity.getId()) + "'",
			"referencedRoleIds");
	}

	@Test
	public void testAddSameFieldMappingReferencedMultipleTimesOnlyAppearsOnce() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("state"),
			"demographics/state/value eq 'Alaska' or " +
				"demographics/state/value eq 'Hawaii'");
	}

	@Test
	public void testAddSegmentWithInvalidValue() {
		Segment segment = new Segment();

		segment.setFilter("demographics/age/value ge 1.2345678901234568e+21");
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 1");
		segment.setType(Segment.Type.DYNAMIC);

		segment = _segmentDog.addSegment(segment);

		Assertions.assertTrue(
			StringUtils.endsWith(
				segment.getFilter(), String.valueOf(Integer.MAX_VALUE)));
	}

	@Test
	public void testAddStartsWithFunction() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("givenName"),
			"startsWith(demographics/givenName/value, 'B')");
	}

	@Test
	public void testAddTeamIdEq() {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(Long.valueOf(dataSourceId));
		dxpEntity.setType(DXPEntity.Type.TEAM);

		dxpEntity = _dxpEntityDog.addDXPEntity(dxpEntity, DXPEntity.Type.TEAM);

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {String.valueOf(dxpEntity.getId())},
			"teamIds eq '" + String.valueOf(dxpEntity.getId()) + "'",
			"referencedTeamIds");
	}

	@Test
	public void testAddUserGroupIdEq() {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(Long.valueOf(dataSourceId));
		dxpEntity.setType(DXPEntity.Type.USER_GROUP);

		dxpEntity = _dxpEntityDog.addDXPEntity(
			dxpEntity, DXPEntity.Type.USER_GROUP);

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {String.valueOf(dxpEntity.getId())},
			"userGroupIds eq '" + String.valueOf(dxpEntity.getId()) + "'",
			"referencedUserGroupIds");
	}

	@Test
	public void testAddUserIdEq() {
		String dataSourceId = _liferayDataSourceIdsJSONArray.getString(
			RandomUtils.nextInt(0, _liferayDataSourceIdsJSONArray.length()));

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(Long.valueOf(dataSourceId));
		dxpEntity.setType(DXPEntity.Type.USER);

		dxpEntity = _dxpEntityDog.addDXPEntity(dxpEntity, DXPEntity.Type.USER);

		_assertAddSetsReferencedObjectIds(
			new String[] {dataSourceId},
			new String[] {String.valueOf(dxpEntity.getId())},
			"userId eq '" + String.valueOf(dxpEntity.getId()) + "'",
			"referencedUserIds");
	}

	@Test
	public void testFieldNameDoesNotNeedValueSuffixToAddReferencedFieldMappingId() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("image"),
			"demographics/image ne null");
	}

	@Test
	public void testFieldNameMustBePrefixedWithDemographicsToAddReferencedFieldMappingId() {
		_assertAddSetsReferencedObjectIds(
			new String[0], new String[0], new String[0],
			"street eq 'Broadway'");
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetSegmentPage() {
		Page<Segment> segmentPage = _segmentDog.getSegmentPage(
			DateUtil.toUTCDate("2022-04-02T11:00:00.000Z"), -1L, 1,
			Sort.by(Sort.Order.asc("id")),
			DateUtil.toUTCDate("2022-04-03T13:00:00.000Z"));

		Assertions.assertEquals(2, segmentPage.getTotalElements());

		List<Segment> segments = segmentPage.getContent();

		Assertions.assertEquals(1, segments.size());
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

		Assertions.assertEquals(2, segments.size(), segments.toString());

		_assertSegment(2L, "Test Segment 0", segments.get(0));
		_assertSegment(1L, "Test Segment 2", segments.get(1));
	}

	@Test
	public void testReferencedAssetDataSourceIdsAddedOnUpdate() {
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
	public void testReferencedAssetDataSourceIdsEmptiedOnUpdate() {
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
	public void testReferencedAssetDataSourceIdsModifiedOnUpdate() {
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
	public void testReferencedFieldMappingIdsAddedOnUpdate() {
		_assertUpdateSetsReferencedObjectIds(
			"", new String[0], new String[0], new String[0], new String[0],
			new String[0],
			_convertFieldNamesToFieldMappingIds("additionalName"),
			"demographics/additionalName/value eq 'Miles'");
	}

	@Test
	public void testReferencedFieldMappingIdsEmptiedOnUpdate() {
		_assertUpdateSetsReferencedObjectIds(
			"demographics/city/value eq 'Los Angeles'", new String[0],
			new String[0], _convertFieldNamesToFieldMappingIds("city"),
			new String[0], new String[0], new String[0], "");
	}

	@Test
	public void testReferencedFieldMappingIdsModifiedOnUpdate() {
		_assertUpdateSetsReferencedObjectIds(
			"demographics/honorificPrefix/value eq 'Mrs.'", new String[0],
			new String[0],
			_convertFieldNamesToFieldMappingIds("honorificPrefix"),
			new String[0], new String[0],
			_convertFieldNamesToFieldMappingIds("gender"),
			"demographics/gender/value eq 'Female'");
	}

	@Test
	public void testReferencedOrganizationIdsEmptiedOnUpdate() {
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
	public void testReferencedOrganizationIdsModifiedOnUpdate() {
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
	public void testUpdateFreestyle() {
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

	@Test
	public void testUpdateName() {
		Segment segment = new Segment();

		segment.setFilter("(demographics/favoriteColor/value eq 'blue')");
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 1");
		segment.setType(Segment.Type.DYNAMIC);

		segment = _segmentDog.addSegment(segment);

		Segment partialSegment = new Segment();

		partialSegment.setFilter(
			"(demographics/favoriteColor/value eq 'blue')");
		partialSegment.setIncludeAnonymousUsers(Boolean.FALSE);
		partialSegment.setModifiedDate(new Date());
		partialSegment.setName("Segment 2");

		partialSegment = _segmentDog.updateSegment(
			partialSegment, segment.getId());

		Assertions.assertTrue(
			CollectionUtils.isNotEmpty(
				partialSegment.getReferencedFieldMappingIds()));
		Assertions.assertEquals(
			segment.getReferencedFieldMappingIds(),
			partialSegment.getReferencedFieldMappingIds());

		segment = _segmentDog.getSegment(segment.getId());

		Assertions.assertEquals("Segment 2", segment.getName());
	}

	@Test
	public void testUpdateSegmentWithInvalidValue() {
		Segment segment = new Segment();

		segment.setFilter("demographics/age/value ge 18");
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 1");
		segment.setType(Segment.Type.DYNAMIC);

		segment = _segmentDog.addSegment(segment);

		Segment partialSegment = new Segment();

		partialSegment.setFilter(
			"demographics/age/value ge 1.2345678901234568e+21");

		partialSegment = _segmentDog.updateSegment(
			partialSegment, segment.getId());

		Assertions.assertTrue(
			StringUtils.endsWith(
				partialSegment.getFilter(), String.valueOf(Integer.MAX_VALUE)));
	}

	private void _assertAddSetsReferencedObjectIds(
		String[] expectedReferencedAssetDataSourceIds,
		String[] expectedReferencedIds, String filterString, String key) {

		JSONObject individualSegmentJSONObject = _objectMapper.convertValue(
			_segmentDog.addSegment(
				FaroInfoTestUtil.buildDynamicSegment(filterString)),
			JSONObject.class);

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
		String[] expectedReferencedFieldMappingIds, String filterString) {

		_assertAddSetsReferencedObjectIds(
			expectedReferencedAssetDataSourceIds, expectedReferencedAssetIds,
			expectedReferencedFieldMappingIds, new String[0], filterString);
	}

	private JSONObject _assertAddSetsReferencedObjectIds(
		String[] expectedReferencedAssetDataSourceIds,
		String[] expectedReferencedAssetIds,
		String[] expectedReferencedFieldMappingIds,
		String[] expectedReferencedOrganizationIds, String filterString) {

		JSONObject individualSegmentJSONObject = _objectMapper.convertValue(
			_segmentDog.addSegment(
				FaroInfoTestUtil.buildDynamicSegment(filterString)),
			JSONObject.class);

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

		Assertions.assertEquals(
			expectedValues.length, actualValuesJSONArray.length());

		for (String value : expectedValues) {
			Assertions.assertTrue(
				JSONUtil.hasValue(actualValuesJSONArray, value),
				"Expected to find value " + value + " in JSONArray " +
					actualValuesJSONArray);
		}
	}

	private void _assertSegment(
		Long expectedSegmentIndividualsCount, String expectedSegmentName,
		Segment actualSegment) {

		Assertions.assertEquals(expectedSegmentName, actualSegment.getName());
		Assertions.assertEquals(
			expectedSegmentIndividualsCount,
			actualSegment.getIndividualsCount());
	}

	private void _assertUpdateSetsReferencedObjectIds(
		String addFilter, String[] expectedAddReferencedAssetDataSourceIds,
		String[] expectedAddReferencedAssetIds,
		String[] expectedAddReferencedFieldMappingIds,
		String[] expectedUpdateReferencedAssetDataSourceIds,
		String[] expectedUpdateReferencedAssetIds,
		String[] expectedUpdateReferencedFieldMappingIds, String updateFilter) {

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
		String[] expectedUpdateReferencedOrganizationIds, String updateFilter) {

		JSONObject individualSegmentJSONObject =
			_assertAddSetsReferencedObjectIds(
				expectedAddReferencedAssetDataSourceIds,
				expectedAddReferencedAssetIds,
				expectedAddReferencedFieldMappingIds,
				expectedAddReferencedOrganizationIds, addFilter);

		Segment segment = new Segment();

		segment.setFilter(updateFilter);

		individualSegmentJSONObject = _objectMapper.convertValue(
			_segmentDog.updateSegment(
				segment, individualSegmentJSONObject.getLong("id")),
			JSONObject.class);

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

	@Autowired
	private AssetRepository _assetRepository;

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

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