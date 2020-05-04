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

package com.liferay.osb.asah.publisher.rest.controller;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.rest.controller.BaseContextRestController;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@RestController
public class ContextRestController extends BaseContextRestController {

	@Override
	protected String getExternalURL() {
		return ServiceConstants.URL_PUBLISHER;
	}

}