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

package com.liferay.osb.asah.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Marcellus Tavares
 */
public class Assert extends org.springframework.util.Assert {

	public static void notBlank(CharSequence charSequence, String message) {
		if (StringUtils.isBlank(charSequence)) {
			throw new IllegalArgumentException(message);
		}
	}

}