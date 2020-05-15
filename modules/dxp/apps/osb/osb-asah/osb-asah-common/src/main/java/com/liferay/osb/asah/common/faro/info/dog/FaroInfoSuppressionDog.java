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

package com.liferay.osb.asah.common.faro.info.dog;

import org.apache.commons.lang.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class FaroInfoSuppressionDog extends BaseFaroInfoDog {

	public boolean isSuppressed(
		String emailAddress, String emailAddressHashed) {

		if (StringUtils.isNotEmpty(emailAddress)) {
			return elasticsearchInvoker.exists(
				"suppressions",
				QueryBuilders.termsQuery("emailAddress", emailAddress));
		}

		if (StringUtils.isNotEmpty(emailAddressHashed)) {
			return elasticsearchInvoker.exists(
				"suppressions",
				QueryBuilders.termsQuery(
					"emailAddressHashed", emailAddressHashed));
		}

		return false;
	}

}