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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.util;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Kyle Bischof
 */
public class ZendeskUtil {

	public static String convertToTag(String s) {
		s = StringUtil.toLowerCase(s);

		s = StringUtil.replace(s, CharPool.SPACE, CharPool.UNDERLINE);
		s = StringUtil.replace(s, CharPool.OPEN_PARENTHESIS, StringPool.BLANK);
		s = StringUtil.replace(s, CharPool.CLOSE_PARENTHESIS, StringPool.BLANK);

		return s;
	}

}