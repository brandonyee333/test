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

package com.liferay.osb.util;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class OSBCustomSQLUtil {

	public static String[] keywords(String keywords) {
		if (Validator.isNull(keywords) ||
			!keywords.startsWith(StringPool.QUOTE) ||
			!keywords.endsWith(StringPool.QUOTE)) {

			List<String> keywordsList = new ArrayList<>();

			String[] keywordsArray = CustomSQLUtil.keywords(keywords);

			for (String curKeywords : keywordsArray) {
				if (Validator.isNull(curKeywords)) {
					continue;
				}

				if (curKeywords.length() > 3) {
					keywordsList.add(curKeywords);
				}
			}

			return keywordsList.toArray(new String[0]);
		}

		keywords = StringUtil.unquote(keywords);

		return new String[] {StringUtil.quote(keywords, StringPool.PERCENT)};
	}

	public static String replaceAllKeywords(String joinSQL, int valuesLength) {
		if (valuesLength == 1) {
			return joinSQL;
		}

		String whereSQL = StringPool.BLANK;

		if (Validator.isNotNull(joinSQL)) {
			int pos = joinSQL.indexOf("WHERE");

			if (pos == -1) {
				return joinSQL;
			}

			whereSQL = joinSQL.substring(pos + 5);
		}

		if (valuesLength == 0) {
			return StringUtil.replace(joinSQL, whereSQL, StringPool.BLANK);
		}

		StringBundler newSqlSB = new StringBundler((valuesLength * 4) + 1);

		newSqlSB.append("(");

		for (int i = 0; i < valuesLength; i++) {
			if (i > 0) {
				newSqlSB.append(" OR ");
			}

			newSqlSB.append("(");
			newSqlSB.append(whereSQL);
			newSqlSB.append(")");
		}

		newSqlSB.append(")");

		return StringUtil.replace(joinSQL, whereSQL, newSqlSB.toString());
	}

	public static String replaceKeywords(
		String sql, String field, boolean last, int[] values,
		String comparator) {

		if ((values != null) && (values.length == 1)) {
			return sql;
		}

		StringBundler oldSqlSB = new StringBundler(6);

		oldSqlSB.append("(");
		oldSqlSB.append(field);
		oldSqlSB.append(" ");
		oldSqlSB.append(comparator);
		oldSqlSB.append(" ?)");

		if (!last) {
			oldSqlSB.append(" [$AND_OR_CONNECTOR$]");
		}

		if ((values == null) || (values.length == 0)) {
			return StringUtil.replace(
				sql, oldSqlSB.toString(), StringPool.BLANK);
		}

		StringBundler newSqlSB = new StringBundler(values.length * 4 + 3);

		newSqlSB.append("(");

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				newSqlSB.append(" OR ");
			}

			newSqlSB.append("(");
			newSqlSB.append(field);
			oldSqlSB.append(" ");
			oldSqlSB.append(comparator);
			oldSqlSB.append(" ?)");
		}

		newSqlSB.append(")");

		if (!last) {
			newSqlSB.append(" [$AND_OR_CONNECTOR$]");
		}

		return StringUtil.replace(
			sql, oldSqlSB.toString(), newSqlSB.toString());
	}

}