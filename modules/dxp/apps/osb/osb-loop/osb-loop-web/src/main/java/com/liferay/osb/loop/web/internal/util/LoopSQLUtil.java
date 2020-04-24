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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sherry Yang
 */
public class LoopSQLUtil {

	public static Map<String, Map<String, Object[]>> createWhereConditions(
		String tableName, Object... attributes) {

		if ((attributes.length == 0) || ((attributes.length % 2) != 0)) {
			throw new IllegalArgumentException(
				"Attributes length is not an even number");
		}

		Map<String, Map<String, Object[]>> whereConditions = new HashMap<>();

		Map<String, Object[]> whereColumnNamesAndValues = new HashMap<>();

		for (int i = 0; i < attributes.length; i += 2) {
			String key = String.valueOf(attributes[i]);
			Object value = attributes[i + 1];

			if (value instanceof Object[]) {
				whereColumnNamesAndValues.put(key, (Object[])value);
			}
			else {
				whereColumnNamesAndValues.put(key, new Object[] {value});
			}
		}

		whereConditions.put(tableName, whereColumnNamesAndValues);

		return whereConditions;
	}

	public static String getCustomSQL(String className, String queryName) {
		return CustomSQLUtil.get(
			LoopSQLUtil.class, className + StringPool.PERIOD + queryName);
	}

	public static String getInnerJoin(
		String tableName1, String tableName2, String columnName1,
		String columnName2) {

		if (Validator.isNull(tableName1) || Validator.isNull(tableName2) ||
			Validator.isNull(columnName1) || Validator.isNull(columnName2)) {

			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(11);

		sb.append("INNER JOIN ");
		sb.append(tableName1);
		sb.append(" ON (");
		sb.append(tableName2);
		sb.append(StringPool.PERIOD);
		sb.append(columnName2);
		sb.append(" = ");
		sb.append(tableName1);
		sb.append(StringPool.PERIOD);
		sb.append(columnName1);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	public static String getLimit(int start, int end) {
		if ((start == QueryUtil.ALL_POS) || (end == QueryUtil.ALL_POS)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(" LIMIT ");
		sb.append(end - start);
		sb.append(" OFFSET ");
		sb.append(start);

		return sb.toString();
	}

	public static String getOrderBy(List<OrderByComparator> obcs) {
		if (ListUtil.isEmpty(obcs)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(obcs.size() * 2);

		sb.append(" ORDER BY ");

		for (int i = 0; i < obcs.size(); i++) {
			if (i != 0) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}

			OrderByComparator obc = obcs.get(i);

			sb.append(obc.getOrderBy());
		}

		return sb.toString();
	}

	public static String getWhereClause(
		Map<String, Map<String, Object[]>> whereConditions) {

		if ((whereConditions == null) || whereConditions.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		sb.append(" WHERE ");

		for (Map.Entry<String, Map<String, Object[]>> entry :
				whereConditions.entrySet()) {

			getWhereClause(sb, entry.getKey(), entry.getValue());
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected static void getWhereClause(
		StringBundler sb, String tableName,
		Map<String, Object[]> whereColumnNamesAndValues) {

		for (Map.Entry<String, Object[]> entry :
				whereColumnNamesAndValues.entrySet()) {

			Object[] whereValues = entry.getValue();

			if (whereValues.length == 1) {
				sb.append(tableName);
				sb.append(StringPool.PERIOD);
				sb.append(entry.getKey());
				sb.append(StringPool.EQUAL);
				sb.append(whereValues[0]);
			}
			else {
				sb.append(tableName);
				sb.append(StringPool.PERIOD);
				sb.append(entry.getKey());
				sb.append(" IN (");
				sb.append(StringUtil.merge(whereValues));
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			sb.append(" AND ");
		}
	}

}