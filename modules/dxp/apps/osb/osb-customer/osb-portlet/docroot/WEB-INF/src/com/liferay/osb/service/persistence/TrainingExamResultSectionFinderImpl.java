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

package com.liferay.osb.service.persistence;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.TrainingExamResultSection;
import com.liferay.osb.model.impl.TrainingExamResultSectionImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Calvin Keum
 */
public class TrainingExamResultSectionFinderImpl
	extends BasePersistenceImpl<AccountEntry>
		implements TrainingExamResultSectionFinder {

	public static final String FILTER_BY_NO_SECTION_KEY =
		TrainingExamResultSectionFinder.class.getName() +
			".filterByNoSectionKey";

	public static final String FIND_BY_TRAINING_EXAM_RESULT_ID =
		TrainingExamResultSectionFinder.class.getName() +
			".findByTrainingExamResultId";

	public List<TrainingExamResultSection> findByTrainingExamResultId(
			long trainingExamResultId, LinkedHashMap<String, Object> params)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_TRAINING_EXAM_RESULT_ID);

		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(
				"OSB_TrainingExamResultSection",
				TrainingExamResultSectionImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(trainingExamResultId);

			return q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("noSectionKey")) {
			join = CustomSQLUtil.get(FILTER_BY_NO_SECTION_KEY);

			String[] sectionKeys = (String[])value;

			StringBundler sb = new StringBundler(3);

			sb.append(StringPool.APOSTROPHE);
			sb.append(
				StringUtil.merge(
					sectionKeys,
					StringPool.APOSTROPHE + StringPool.COMMA +
						StringPool.APOSTROPHE));
			sb.append(StringPool.APOSTROPHE);

			join = StringUtil.replace(
				join, "[$TRAINING_EXAM_RESULT_SECTION_KEY$]", sb.toString());
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5, join.length()).concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

}