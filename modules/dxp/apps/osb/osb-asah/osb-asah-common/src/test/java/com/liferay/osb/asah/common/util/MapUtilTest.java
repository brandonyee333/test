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

package com.liferay.osb.asah.common.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vishal Reddy
 */
public class MapUtilTest {

	@Test
	public void testGetLong() {
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				put("int", 5);
				put("long", 5L);
				put("negative", "-5");
				put("non-numeric", "test");
				put("null", null);
				put("positive", "+5");
				put("string", "5");
				put("stringArray", new String[] {"5", "4", "3", "2", "1"});
			}
		};

		Assert.assertEquals(5, MapUtil.getLong(map, "int"));
		Assert.assertEquals(5, MapUtil.getLong(map, "long"));
		Assert.assertEquals(-5, MapUtil.getLong(map, "negative"));
		Assert.assertEquals(5, MapUtil.getLong(map, "non-numeric", 5));
		Assert.assertEquals(0, MapUtil.getLong(map, "null"));
		Assert.assertEquals(5, MapUtil.getLong(map, "positive"));
		Assert.assertEquals(5, MapUtil.getLong(map, "string"));
		Assert.assertEquals(5, MapUtil.getLong(map, "stringArray"));
	}

	@Test
	public void testGetString() {
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				put("int", 5);
				put("long", 5L);
				put("null", null);
				put("string", "5");
				put("stringArray", new String[] {"5", "4", "3", "2", "1"});
			}
		};

		Assert.assertEquals("5", MapUtil.getString(map, "int"));
		Assert.assertEquals("5", MapUtil.getString(map, "long"));
		Assert.assertNull(MapUtil.getString(map, "null"));
		Assert.assertEquals("5", MapUtil.getString(map, "null", "5"));
		Assert.assertEquals("5", MapUtil.getString(map, "string"));
		Assert.assertEquals("5", MapUtil.getString(map, "stringArray"));
	}

	@Test
	public void testMerge() {
		Map<String, Integer> map1 = new HashMap<String, Integer>() {
			{
				put("test1", 1);
				put("test2", 2);
			}
		};

		Map<String, Integer> map2 = new HashMap<String, Integer>() {
			{
				put("test1", 4);
				put("test3", 0);
			}
		};

		Assert.assertEquals(
			new HashMap<String, Integer>() {
				{
					put("test1", 5);
					put("test2", 2);
					put("test3", 0);
				}
			},
			MapUtil.merge(Integer::sum, map1, map2));
	}

}