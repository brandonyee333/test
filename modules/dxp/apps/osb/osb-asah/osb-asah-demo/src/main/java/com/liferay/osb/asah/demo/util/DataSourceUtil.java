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

package com.liferay.osb.asah.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Inácio Nery
 * @author Adolfo Pérez
 */
public class DataSourceUtil {

	public static Map<String, Object> getContext() {
		Map<String, Object> context = new HashMap<>();

		context.put("browserName", _getBrowserName());

		String[] location = _getLocation();

		context.put("city", location[2]);

		context.put("contentLanguageId", "en-US");
		context.put("country", location[0]);
		context.put("crawler", "False");
		context.put("deviceType", _getDeviceType());
		context.put("languageId", "en-US");
		context.put("platformName", _getPlatformName());
		context.put("referrer", _getReferrer());
		context.put("region", location[1]);
		context.put("title", getString() + " " + getString());
		context.put("url", getURL());

		return context;
	}

	public static String getDepth() {
		return _choose(_DEPTH);
	}

	public static int getInt(int end) {
		return _random.nextInt(end);
	}

	public static int getInt(int start, int end) {
		IntStream intStream = _random.ints(1, start, end + 1);

		OptionalInt optionalInt = intStream.findAny();

		return optionalInt.getAsInt();
	}

	public static long getModelId() {
		return getInt(1, 100);
	}

	public static String getSocialNetwork() {
		return _choose(_SOCIAL_NETWORKS);
	}

	public static String getString() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public static String getTagName() {
		return _choose(_TAG_NAMES);
	}

	public static String getURL() {
		return "http://liferay.com/" + getInt(1, 100);
	}

	public static String getUserId() {
		return String.valueOf(getInt(1, 100));
	}

	private static Object _choose(Object[] objects) {
		return objects[getInt(0, objects.length - 1)];
	}

	private static String _choose(String[] strings) {
		return strings[getInt(0, strings.length - 1)];
	}

	private static String _getBrowserName() {
		return _choose(_BROWSER_NAMES);
	}

	private static String _getDeviceType() {
		return _choose(_DEVICE_TYPES);
	}

	private static String[] _getLocation() {
		return (String[])_choose(_LOCATIONS);
	}

	private static String _getPlatformName() {
		return _choose(_PLATFORM_NAMES);
	}

	private static String _getReferrer() {
		return _choose(_REFERRERS);
	}

	private static final String[] _BROWSER_NAMES = {"Chrome", "Firefox", "IE"};

	private static final String[] _DEPTH = {"0", "25", "50", "75", "100"};

	private static final String[] _DEVICE_TYPES = {
		"Desktop", "Smartphone", "Tablet"
	};

	private static final String[][] _LOCATIONS = {
		{"Argentina", "Pampas", "Buenos Aires"},
		{"Brazil", "Bahia", "Salvador"}, {"Brazil", "Pernambuco", "Recife"},
		{"Brazil", "Rio de Janeiro", "Rio de Janeiro"},
		{"Brazil", "Sergipe", "Aracaju"}, {"Germany", "Bavaria", "Munich"},
		{"Portugal", "Lisbon", "Lisbon"}, {"Spain", "Catalonia", "Barcelona"},
		{"Spain", "Madrid", "Madrid"}, {"USA", "California", "Los Angeles"},
		{"USA", "Florida", "Orlando"}, {"USA", "Illinois", "Chicago"}
	};

	private static final String[] _PLATFORM_NAMES = {
		"Linux", "MacOS", "Windows"
	};

	private static final String[] _REFERRERS = {
		"", "http://facebook.com", "http://twitter.com",
		"http://www.google.com", "http://www.liferay.com"
	};

	private static final String[] _SOCIAL_NETWORKS = {
		"facebook", "linkedin", "twitter"
	};

	private static final String[] _TAG_NAMES = {"a", "img", "other"};

	private static final Random _random = new Random();

}