/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.servlet.HttpMethods;

/**
 * @author Igor Spasic
 */
public class CamelFooService {

	@JSONWebService("cool-new-world")
	public static void braveNewWorld() {
	}

	public static void hello() {
	}

	public static void helloWorld() {
	}

	@JSONWebService(method = HttpMethods.POST)
	public static String post(String value) {
		return "post " + value;
	}

}