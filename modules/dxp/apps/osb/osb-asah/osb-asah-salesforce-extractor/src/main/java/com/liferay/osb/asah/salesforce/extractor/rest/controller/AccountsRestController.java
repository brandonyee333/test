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

package com.liferay.osb.asah.salesforce.extractor.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@RequestMapping("/accounts")
@RestController(
	"com.liferay.osb.asah.salesforce.extractor.rest.controller.AccountsRestController"
)
public class AccountsRestController extends BaseRestController {

	@GetMapping("/fields")
	public String getFields(
		@RequestParam String dataSourceId,
		@RequestParam(defaultValue = "20") int end,
		@RequestParam(defaultValue = "0") int start) {

		return getFields("Account", dataSourceId, end, start);
	}

}