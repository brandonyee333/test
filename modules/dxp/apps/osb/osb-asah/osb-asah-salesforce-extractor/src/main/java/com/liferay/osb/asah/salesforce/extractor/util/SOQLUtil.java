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

package com.liferay.osb.asah.salesforce.extractor.util;

import com.liferay.osb.asah.common.array.ArrayUtil;

import com.sforce.soap.partner.DescribeSObjectResult;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
public class SOQLUtil {

	public static int getBatchSize(
		DescribeSObjectResult describeSObjectResult, String[] salesforceKeys) {

		int batchSize = _SOQL_CHARACTERS_MAX / _ID_LENGTH;

		if (batchSize > salesforceKeys.length) {
			batchSize = salesforceKeys.length;
		}

		if (batchSize == 0) {
			return batchSize;
		}

		String soql = getSOQL(describeSObjectResult, salesforceKeys);

		while (soql.length() > _SOQL_CHARACTERS_MAX) {
			if (batchSize > 100) {
				batchSize = batchSize - 100;
			}
			else {
				batchSize = batchSize - 10;
			}

			soql = getSOQL(
				describeSObjectResult,
				ArrayUtil.subset(salesforceKeys, 0, batchSize));
		}

		if (batchSize < 1) {
			batchSize = 1;
		}

		return batchSize;
	}

	public static String getCountSOQL(String typeName) {
		return "SELECT COUNT() FROM " + typeName;
	}

	public static String getSOQL(
		DescribeSObjectResult describeSObjectResult, String[] salesforceKeys) {

		String[] fieldNames = SchemaUtil.getFieldNames(describeSObjectResult);

		if (salesforceKeys == null) {
			String s = String.join(", ", fieldNames);

			return "SELECT " + s + " FROM " + describeSObjectResult.getName();
		}

		StringBuilder sb = new StringBuilder(salesforceKeys.length * 4 + 5);

		sb.append("SELECT ");
		sb.append(String.join(", ", fieldNames));
		sb.append(" FROM ");
		sb.append(describeSObjectResult.getName());
		sb.append(" WHERE Id IN (");

		for (int i = 0; i < salesforceKeys.length; i++) {
			sb.append("'");
			sb.append(salesforceKeys[i]);
			sb.append("'");

			if ((i + 1) != salesforceKeys.length) {
				sb.append(",");
			}
		}

		if (salesforceKeys.length == 0) {
			sb.append("'");
			sb.append("000000000000000000");
			sb.append("'");
		}

		sb.append(")");

		return sb.toString();
	}

	private static final int _ID_LENGTH = 18;

	private static final int _SOQL_CHARACTERS_MAX = 20000;

}