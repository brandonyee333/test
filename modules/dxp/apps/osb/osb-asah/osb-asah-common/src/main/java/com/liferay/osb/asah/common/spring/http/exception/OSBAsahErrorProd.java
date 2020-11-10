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

package com.liferay.osb.asah.common.spring.http.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@Profile("prod")
public class OSBAsahErrorProd extends OSBAsahError {

	@Override
	protected List<String> getErrorAttributeFilterList() {
		return new ArrayList<String>() {
			{
				add("error");
				add("path");
				add("status");
				add("timestamp");
			}
		};
	}

}