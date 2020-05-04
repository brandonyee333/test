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

package com.liferay.osb.asah.extractor.constants;

/**
 * @author Inácio Nery
 */
public class FiftyOneDegreesPropertyNames {

	/**
	 * Indicates the name of the browser. Many mobile browsers, by default,
	 * come with an operating system (OS). Unless specifically named, these
	 * browsers are named after the accompanying OS and/or the layout engine.
	 *
	 * @review
	 */
	public static final String BROWSER_NAME = "BrowserName";

	/**
	 * Indicates the type of the device based on values set in other properties,
	 * such as IsMobile, IsTablet, IsSmartphone, IsSmallScreen etc.
	 *
	 * @review
	 */
	public static final String DEVICE_TYPE = "DeviceType";

	/**
	 * Indicates if the source of the web traffic operates without human interaction,
	 * primarily for the purpose of indexing the response.
	 *
	 * @review
	 */
	public static final String IS_CRAWLER = "IsCrawler";

	/**
	 * Indicates the name of the operating system the device is using.
	 *
	 * @review
	 */
	public static final String PLATFORM_NAME = "PlatformName";

}