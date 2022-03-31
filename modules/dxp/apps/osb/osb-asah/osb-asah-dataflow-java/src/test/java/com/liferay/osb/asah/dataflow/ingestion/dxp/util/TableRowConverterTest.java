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

package com.liferay.osb.asah.dataflow.ingestion.dxp.util;

import com.google.api.services.bigquery.model.TableRow;

import com.liferay.osb.asah.dataflow.common.ObjectMapperUtil;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.TestEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Riccardo Ferrari
 */
public class TableRowConverterTest {

	@Test
	public void testList() {
		TestEntity testEntity = _getTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object strings = tableRow.get("strings");

		Assertions.assertInstanceOf(List.class, strings);

		Assertions.assertLinesMatch(testEntity.strings, (List)strings);
	}

	@Test
	public void testMap() {
		TestEntity testEntity = _getTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object map = tableRow.get("map");

		Assertions.assertInstanceOf(String.class, map);

		Assertions.assertEquals(
			ObjectMapperUtil.writeValueAsString(testEntity.map), map);
	}

	@Test
	public void testMapList() {
		TestEntity testEntity = _getTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		List<String> expectedResult = new ArrayList<>();

		for (Map<String, String> map : testEntity.maps) {
			expectedResult.add(ObjectMapperUtil.writeValueAsString(map));
		}

		Object maps = tableRow.get("maps");

		Assertions.assertInstanceOf(List.class, maps);

		List<String> mapStrings = (List)maps;

		Assertions.assertArrayEquals(
			expectedResult.toArray(new String[0]),
			mapStrings.toArray(new String[0]));
	}

	@Test
	public void testNestedEntity() {
		TestEntity testEntity = _getTestEntity(true);

		TestEntity nestedTestEntity = testEntity.testEntity;

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object nestedObject = tableRow.get("testEntity");

		Assertions.assertInstanceOf(TableRow.class, nestedObject);

		TableRow nestedTableRow = (TableRow)nestedObject;

		Assertions.assertEquals(
			nestedTableRow, TableRowConverter.asTableRow(nestedTestEntity));
	}

	@Test
	public void testNestedEntityList() {
		TestEntity testEntity = _getTestEntity(true);

		List<TestEntity> testEntities = testEntity.testEntities;

		Stream<TestEntity> testEntityStream = testEntities.stream();

		List<TableRow> expectedTableRowEntities = testEntityStream.map(
			TableRowConverter::asTableRow
		).collect(
			Collectors.toList()
		);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object testEntitiesTableRow = tableRow.get("testEntities");

		Assertions.assertInstanceOf(List.class, testEntitiesTableRow);

		List<TableRow> tableRows = (List)testEntitiesTableRow;

		Assertions.assertArrayEquals(
			expectedTableRowEntities.toArray(new TableRow[0]),
			tableRows.toArray(new TableRow[0]));
	}

	@Test
	public void testObject() {
		TestEntity testEntity = _getTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Assertions.assertEquals(testEntity.integer, tableRow.get("integer"));

		Assertions.assertEquals(testEntity.text, tableRow.get("text"));
	}

	@Test
	public void testPrimitive() {
		TestEntity testEntity = _getTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Assertions.assertEquals(testEntity.bool, tableRow.get("bool"));

		Assertions.assertEquals(testEntity.number, tableRow.get("number"));
	}

	private TestEntity _getTestEntity(boolean includeNested) {
		return new TestEntity() {
			{
				bool = RandomUtils.nextBoolean();

				integer = RandomUtils.nextInt();

				map = new HashMap<String, String>() {
					{
						put("k1", RandomStringUtils.randomAlphanumeric(8));
						put("k2", RandomStringUtils.randomAlphanumeric(8));
						put("k3", RandomStringUtils.randomAlphanumeric(8));
					}
				};

				maps = new ArrayList<Map<String, String>>() {
					{
						add(map);
						add(map);
					}
				};

				number = RandomUtils.nextLong();

				strings = new ArrayList<String>() {
					{
						add(RandomStringUtils.randomAlphanumeric(8));
						add(RandomStringUtils.randomAlphanumeric(8));
						add(RandomStringUtils.randomAlphanumeric(8));
					}
				};

				if (includeNested) {
					testEntity = _getTestEntity(false);

					testEntities = new ArrayList<TestEntity>() {
						{
							add(_getTestEntity(false));
							add(_getTestEntity(false));
						}
					};
				}

				text = RandomStringUtils.randomAlphanumeric(8);
			}
		};
	}

}