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

package com.liferay.osb.asah.backend.rest.controller.api.external;

import com.liferay.osb.asah.backend.rest.controller.BaseRestController;

import org.apache.commons.codec.digest.DigestUtils;

import org.json.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping(
	produces = "application/json", value = "/api/content-recommendations"
)
@RestController
public class ContentRecommendationRestController extends BaseRestController {

	@PostMapping("/recommended-items")
	public String getRecommendedItems(@RequestBody String json)
		throws Exception {

		JSONObject jsonObject = new JSONObject(json);

		return toItemGetResponse(
			"recommended-items",
			DigestUtils.sha1Hex(jsonObject.getString("item")));
	}

}