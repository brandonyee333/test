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

package com.liferay.osb.asah.common.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class FieldMappingConstants {

	public static final Map<String, String> demographicsDisplayNames =
		Collections.unmodifiableMap(
			new HashMap<String, String>() {
				{
					put("addresses", "address");
					put("birthday", "birthDate");
					put("emailAddress", "email");
					put("firstName", "givenName");
					put("lastName", "familyName");
					put("middleName", "additionalName");
					put("phones", "telephone");
				}
			});

}