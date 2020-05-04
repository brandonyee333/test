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

package com.liferay.osb.asah.test.util.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author Brian Wing Shun Chan
 */
public class RandomTestUtil {

	public static String randomEmailAddress() {
		return RandomStringUtils.randomAlphanumeric(3, 8) + "@" +
			RandomStringUtils.randomAlphanumeric(3, 8) + ".com";
	}

	public static String randomFullName() {
		return RandomStringUtils.randomAlphabetic(3, 8) + " " +
			RandomStringUtils.randomAlphabetic(3, 8);
	}

	public static String randomHexString(int length) {
		return RandomStringUtils.random(length, "0123456789abcdef");
	}

	public static String randomId() {
		return RandomStringUtils.randomAlphabetic(4);
	}

	public static String randomMultipleWordString(
		int minLengthInclusive, int maxLengthExclusive) {

		StringBuilder sb = new StringBuilder();

		int length = RandomUtils.nextInt(
			minLengthInclusive, maxLengthExclusive);

		while (sb.length() < length) {
			sb.append(RandomStringUtils.randomAlphabetic(3, 8));
			sb.append(" ");
		}

		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public static long randomNumber() {
		return RandomUtils.nextLong();
	}

	public static String randomString() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	public static String randomURL() {
		return "http://" + RandomStringUtils.randomAlphanumeric(8, 20) + ".com";
	}

	public static String randomUUID() {
		return randomHexString(8) + "-" + randomHexString(4) + "-" +
			randomHexString(4) + "-" + randomHexString(4) + "-" +
				randomHexString(12);
	}

	public static String randomWeDeployId() {
		return RandomStringUtils.randomNumeric(18);
	}

	public static <T> T selectRandom(T... values) {
		return values[RandomUtils.nextInt(0, values.length)];
	}

}