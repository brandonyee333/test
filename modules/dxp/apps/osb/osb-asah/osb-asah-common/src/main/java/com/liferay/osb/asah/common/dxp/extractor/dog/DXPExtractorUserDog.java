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

package com.liferay.osb.asah.common.dxp.extractor.dog;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class DXPExtractorUserDog {

	public JSONObject processGenderField(JSONObject userJSONObject) {
		if (userJSONObject == null) {
			return null;
		}

		if (userJSONObject.has("male")) {
			if (userJSONObject.getBoolean("male")) {
				userJSONObject.put("gender", "male");
			}
			else {
				userJSONObject.put("gender", "female");
			}
		}

		JSONObject contactJSONObject = userJSONObject.optJSONObject("contact");

		if (contactJSONObject == null) {
			return userJSONObject;
		}

		if (contactJSONObject.has("male")) {
			if (contactJSONObject.getBoolean("male")) {
				contactJSONObject.put("gender", "male");
			}
			else {
				contactJSONObject.put("gender", "female");
			}
		}

		return userJSONObject;
	}

}