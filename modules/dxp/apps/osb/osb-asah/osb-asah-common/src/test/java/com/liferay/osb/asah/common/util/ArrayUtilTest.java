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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vishal Reddy
 */
public class ArrayUtilTest {

	@Test
	public void testAppend() {
		Assert.assertArrayEquals(
			new char[] {'a', 'b', 'c', 'd', 'e', 'f'},
			ArrayUtil.append(
				new char[] {'a', 'b', 'c'}, new char[] {'d', 'e', 'f'}));
		Assert.assertArrayEquals(
			new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'},
			ArrayUtil.append(
				new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'}, new char[0]));
	}

	@Test
	public void testContains() {
		Assert.assertFalse(ArrayUtil.contains(new String[] {"a", "b"}, "c"));
		Assert.assertTrue(ArrayUtil.contains(new String[] {"a", "b"}, "a"));
	}

	@Test
	public void testIsEmptyObjectArray() {
		Assert.assertFalse(ArrayUtil.isEmpty(new Object[] {"test"}));
		Assert.assertTrue(ArrayUtil.isEmpty(new Object[0]));
		Assert.assertTrue(ArrayUtil.isEmpty(null));
	}

	@Test
	public void testIsNotEmptyObjectArray() {
		Assert.assertTrue(ArrayUtil.isNotEmpty(new Object[] {"test"}));
		Assert.assertFalse(ArrayUtil.isNotEmpty(new Object[0]));
		Assert.assertFalse(ArrayUtil.isNotEmpty(null));
	}

	@Test
	public void testSubsetCharacterArray() {
		char[] array = {'a', 'b', 'c', 'd'};

		Assert.assertArrayEquals(array, ArrayUtil.subset(array, -3, -1));
		Assert.assertArrayEquals(array, ArrayUtil.subset(array, -1, 3));
		Assert.assertArrayEquals(array, ArrayUtil.subset(array, 1, 0));
		Assert.assertArrayEquals(
			new char[] {'a', 'b', 'c'}, ArrayUtil.subset(array, 0, 3));
		Assert.assertArrayEquals(
			new char[] {'b', 'c'}, ArrayUtil.subset(array, 1, 3));
		Assert.assertArrayEquals(new char[0], ArrayUtil.subset(array, 3, 3));
	}

	@Test
	public void testSubsetIntegerArray() {
		Integer[] array = {1, 2, 3, 4};

		Assert.assertArrayEquals(array, ArrayUtil.subset(array, -3, -1));
		Assert.assertArrayEquals(array, ArrayUtil.subset(array, -1, 3));
		Assert.assertArrayEquals(array, ArrayUtil.subset(array, 1, 0));
		Assert.assertArrayEquals(
			new Integer[] {1, 2, 3}, ArrayUtil.subset(array, 0, 3));
		Assert.assertArrayEquals(
			new Integer[] {2, 3}, ArrayUtil.subset(array, 1, 3));
		Assert.assertArrayEquals(new Integer[0], ArrayUtil.subset(array, 3, 3));
	}

	@Test
	public void testSubsetStringArray() {
		String[] array = {"a", "b", "c", "d"};

		Assert.assertArrayEquals(array, ArrayUtil.subset(array, -3, -1));
		Assert.assertArrayEquals(array, ArrayUtil.subset(array, -1, 3));
		Assert.assertArrayEquals(array, ArrayUtil.subset(array, 1, 0));
		Assert.assertArrayEquals(
			new String[] {"a", "b", "c"}, ArrayUtil.subset(array, 0, 3));
		Assert.assertArrayEquals(
			new String[] {"b", "c"}, ArrayUtil.subset(array, 1, 3));
		Assert.assertArrayEquals(new String[0], ArrayUtil.subset(array, 3, 3));
	}

}