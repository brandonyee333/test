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

package com.liferay.osb.asah.extractor.fiftyonedegrees;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Inácio Nery
 */
@RunWith(SpringRunner.class)
public class FiftyOneDegreesEngineTest {

	@Test
	public void testGetBrowser1() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_MAC_OS_CHROME_DESKTOP);

		Assert.assertEquals(
			"Chrome", fiftyOneDegreesEngineDevice.getBrowserName());
	}

	@Test
	public void testGetBrowser2() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(
				_USER_AGENT_MAC_OS_FIREFOX_DESKTOP);

		Assert.assertEquals(
			"Firefox", fiftyOneDegreesEngineDevice.getBrowserName());
	}

	@Test
	public void testGetCrawler1() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_CRAWLER_AGENT_YANDEX);

		Assert.assertEquals("True", fiftyOneDegreesEngineDevice.getCrawler());
	}

	@Test
	public void testGetCrawler2() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_CRAWLER_AGENT_GOOGLEBOT);

		Assert.assertEquals("True", fiftyOneDegreesEngineDevice.getCrawler());
	}

	@Test
	public void testGetCrawler3() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_ANDROID_CHROME_MOBILE);

		Assert.assertEquals("False", fiftyOneDegreesEngineDevice.getCrawler());
	}

	@Test
	public void testGetDeviceType1() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_ANDROID_CHROME_MOBILE);

		Assert.assertEquals(
			"SmartPhone", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetDeviceType2() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_MAC_OS_CHROME_DESKTOP);

		Assert.assertEquals(
			"Desktop", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetDeviceType3() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_IOS_WEBKIT_IPOD);

		Assert.assertEquals(
			"Mobile", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetDeviceType4() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_ANDROID_WEBKIT_TABLET);

		Assert.assertEquals(
			"Tablet", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetDeviceType5() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_IOS_WEBKIT_IPAD);

		Assert.assertEquals(
			"Tablet", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetDeviceType6() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice("");

		Assert.assertEquals(
			"Unknown", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetDeviceType7() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice("   ");

		Assert.assertEquals(
			"Unknown", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetDeviceType8() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(null);

		Assert.assertEquals(
			"Unknown", fiftyOneDegreesEngineDevice.getDeviceType());
	}

	@Test
	public void testGetPlatform1() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_MAC_OS_CHROME_DESKTOP);

		Assert.assertEquals(
			"macOS", fiftyOneDegreesEngineDevice.getPlatformName());
	}

	@Test
	public void testGetPlatform2() {
		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(_USER_AGENT_ANDROID_CHROME_MOBILE);

		Assert.assertEquals(
			"Android", fiftyOneDegreesEngineDevice.getPlatformName());
	}

	@Configuration
	@Import(FiftyOneDegreesEngine.class)
	public static class TestConfiguration {
	}

	private static final String _CRAWLER_AGENT_GOOGLEBOT =
		"Mozilla/5.0 (compatible; Googlebot/2.1; " +
			"+http://www.google.com/bot.html)";

	private static final String _CRAWLER_AGENT_YANDEX =
		"Mozilla/5.0 (compatible; YandexBot/3.0; +http://yandex.com/bots)";

	private static final String _USER_AGENT_ANDROID_CHROME_MOBILE =
		"Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) " +
			"AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 " +
				"Mobile Safari/535.19";

	private static final String _USER_AGENT_ANDROID_WEBKIT_TABLET =
		"Mozilla/5.0 (Linux; Android 4.4.3; KFTHWI Build/KTU84M) " +
			"AppleWebKit/537.36 (KHTML, like Gecko) Silk/47.1.79 like " +
				"Chrome/47.0.2526.80 Safari/537.36";

	private static final String _USER_AGENT_IOS_WEBKIT_IPAD =
		"Mozilla/5.0 (iPad; CPU OS 5_1 like Mac OS X; en-us) " +
			"AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B176 " +
				"Safari/7534.48.3";

	private static final String _USER_AGENT_IOS_WEBKIT_IPOD =
		"Mozilla/5.0 (iPod touch; CPU iPhone OS 10_2 like Mac OS X)" +
			"AppleWebKit/602.3.12 (KHTML, like Gecko) Version/10.0 " +
				"Mobile/14C92 Safari/602.1";

	private static final String _USER_AGENT_MAC_OS_CHROME_DESKTOP =
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 " +
			"(KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";

	private static final String _USER_AGENT_MAC_OS_FIREFOX_DESKTOP =
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:45.0) " +
			"Gecko/20100101 Firefox/45.0";

	@Autowired
	private FiftyOneDegreesEngine _fiftyOneDegreesEngine;

}