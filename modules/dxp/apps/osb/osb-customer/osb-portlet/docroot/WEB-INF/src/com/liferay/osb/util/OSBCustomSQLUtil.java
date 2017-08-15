/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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

			whereSQL = joinSQL.substring(pos + 5, joinSQL.length());
		}

		if (valuesLength == 0) {
			return StringUtil.replace(joinSQL, whereSQL, StringPool.BLANK);
		}

		StringBundler newSql = new StringBundler((valuesLength * 4) + 1);

		newSql.append("(");

		for (int i = 0; i < valuesLength; i++) {
			if (i > 0) {
				newSql.append(" OR ");
			}

			newSql.append("(");
			newSql.append(whereSQL);
			newSql.append(")");
		}

		newSql.append(")");

		return StringUtil.replace(joinSQL, whereSQL, newSql.toString());
	}

	public static String replaceKeywords(
		String sql, String field, boolean last, int[] values,
		String comparator) {

		if ((values != null) && (values.length == 1)) {
			return sql;
		}

		StringBundler oldSql = new StringBundler(6);

		oldSql.append("(");
		oldSql.append(field);
		oldSql.append(" ");
		oldSql.append(comparator);
		oldSql.append(" ?)");

		if (!last) {
			oldSql.append(" [$AND_OR_CONNECTOR$]");
		}

		if ((values == null) || (values.length == 0)) {
			return StringUtil.replace(sql, oldSql.toString(), StringPool.BLANK);
		}

		StringBundler newSql = new StringBundler(values.length * 4 + 3);

		newSql.append("(");

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				newSql.append(" OR ");
			}

			newSql.append("(");
			newSql.append(field);
			oldSql.append(" ");
			oldSql.append(comparator);
			oldSql.append(" ?)");
		}

		newSql.append(")");

		if (!last) {
			newSql.append(" [$AND_OR_CONNECTOR$]");
		}

		return StringUtil.replace(sql, oldSql.toString(), newSql.toString());
	}

}