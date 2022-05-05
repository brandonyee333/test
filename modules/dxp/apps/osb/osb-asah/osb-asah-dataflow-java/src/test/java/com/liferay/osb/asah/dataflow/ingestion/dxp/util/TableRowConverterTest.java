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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public void testIntegerFieldType() {
		TestEntity testEntity = _newTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Assertions.assertEquals(testEntity.integer, tableRow.get("integer"));
	}

	@Test
	public void testListFieldType() {
		TestEntity testEntity = _newTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object stringList = tableRow.get("stringList");

		Assertions.assertInstanceOf(List.class, stringList);

		Assertions.assertLinesMatch(testEntity.stringList, (List)stringList);
	}

	@Test
	public void testMapFieldType() {
		TestEntity testEntity = _newTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object map = tableRow.get("map");

		Assertions.assertInstanceOf(String.class, map);

		Assertions.assertEquals(
			ObjectMapperUtil.writeValueAsString(testEntity.map), map);
	}

	@Test
	public void testMapListFieldType() {
		TestEntity testEntity = _newTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		List<String> expectedResult = new ArrayList<>();

		for (Map<String, String> map : testEntity.mapList) {
			expectedResult.add(ObjectMapperUtil.writeValueAsString(map));
		}

		Object mapList = tableRow.get("mapList");

		Assertions.assertInstanceOf(List.class, mapList);

		List<String> mapStrings = (List)mapList;

		Assertions.assertArrayEquals(
			expectedResult.toArray(new String[0]),
			mapStrings.toArray(new String[0]));
	}

	@Test
	public void testNestedEntityFieldType() {
		TestEntity testEntity = _newTestEntity(true);

		TestEntity nestedTestEntity = testEntity.testEntity;

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object nestedObject = tableRow.get("testEntity");

		Assertions.assertInstanceOf(TableRow.class, nestedObject);

		TableRow nestedTableRow = (TableRow)nestedObject;

		Assertions.assertEquals(
			nestedTableRow, TableRowConverter.asTableRow(nestedTestEntity));
	}

	@Test
	public void testNestedEntityListFieldType() {
		TestEntity testEntity = _newTestEntity(true);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Object testEntitiesTableRows = tableRow.get("testEntityList");

		Assertions.assertInstanceOf(List.class, testEntitiesTableRows);

		List<TableRow> tableRows = (List)testEntitiesTableRows;

		Stream<TestEntity> testEntityStream =
			testEntity.testEntityList.stream();

		Assertions.assertArrayEquals(
			testEntityStream.map(
				TableRowConverter::asTableRow
			).toArray(
				TableRow[]::new
			),
			tableRows.toArray(new TableRow[0]));
	}

	@Test
	public void testObjectArrayFieldType() {
		TestEntity testEntity = _newTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Assertions.assertEquals(
			Arrays.toString(testEntity.objectArray),
			tableRow.get("objectArray"));
	}

	@Test
	public void testPrimitiveFieldType() {
		TestEntity testEntity = _newTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Assertions.assertEquals(
			String.valueOf(testEntity.bool), tableRow.get("bool"));
		Assertions.assertEquals(testEntity.number, tableRow.get("number"));
	}

	@Test
	public void testStringFieldType() {
		TestEntity testEntity = _newTestEntity(false);

		TableRow tableRow = TableRowConverter.asTableRow(testEntity);

		Assertions.assertEquals(testEntity.string, tableRow.get("string"));
	}

	private TestEntity _newTestEntity(boolean includeNested) {
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

				mapList = new ArrayList<Map<String, String>>() {
					{
						add(map);
						add(map);
					}
				};

				number = RandomUtils.nextLong();
				objectArray = new Object[] {1, 2};
				string = RandomStringUtils.randomAlphanumeric(8);
				stringList = new ArrayList<String>() {
					{
						add(RandomStringUtils.randomAlphanumeric(8));
						add(RandomStringUtils.randomAlphanumeric(8));
						add(RandomStringUtils.randomAlphanumeric(8));
					}
				};

				if (includeNested) {
					testEntity = _newTestEntity(false);
					testEntityList = new ArrayList<TestEntity>() {
						{
							add(_newTestEntity(false));
							add(_newTestEntity(false));
						}
					};
				}
			}
		};
	}

}