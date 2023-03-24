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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.filter.expression.FilterExpressionParserException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Marcos Martins
 */
public class SegmentDogTest {

	@Test
	public void testValidateFilterString() {
		Assertions.assertThrowsExactly(
			FilterExpressionParserException.class,
			() -> ReflectionTestUtils.invokeMethod(
				_segmentDog, "_validateFilterString",
				"demographics/age/value ge " +
					"12345678901234567262899398937898378787878"));
		Assertions.assertThrowsExactly(
			FilterExpressionParserException.class,
			() -> ReflectionTestUtils.invokeMethod(
				_segmentDog, "_validateFilterString",
				"demographics/age/value ge 1.2345678901234568e+21"));
		Assertions.assertThrowsExactly(
			FilterExpressionParserException.class,
			() -> ReflectionTestUtils.invokeMethod(
				_segmentDog, "_validateFilterString",
				"organizations.filter(filter='(dateModified gt " +
					"1580256740750)')"));
	}

	private final SegmentDog _segmentDog = new SegmentDog();

}