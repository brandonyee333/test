/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;

/**
 * @author Alberto Chaparro
 */
public class VerifyRatings extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		normalizeRatingStats();
	}

	protected void normalizeRatingStats() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(6);

			sb.append("update RatingsStats set ");
			sb.append(_SQL_UPDATE_AVERAGE_SCORE);
			sb.append(", ");
			sb.append(_SQL_UPDATE_TOTAL_ENTRIES);
			sb.append(", ");
			sb.append(_SQL_UPDATE_TOTAL_SCORE);

			try (PreparedStatement ps = connection.prepareStatement(
					sb.toString())) {

				ps.executeUpdate();
			}
		}
	}

	private static final String _SQL_FROM_WHERE_CLAUSE =
		"from RatingsEntry where RatingsStats.classPK = RatingsEntry.classPK " +
			"and RatingsStats.classNameId = RatingsEntry.classNameId group " +
				"by classNameId, classPK";

	private static final String _SQL_UPDATE_AVERAGE_SCORE =
		"averageScore = coalesce((select sum(RatingsEntry.score) / count(1) " +
			_SQL_FROM_WHERE_CLAUSE + "), 0)";

	private static final String _SQL_UPDATE_TOTAL_ENTRIES =
		"totalEntries = coalesce((select count(1) " + _SQL_FROM_WHERE_CLAUSE +
			"), 0)";

	private static final String _SQL_UPDATE_TOTAL_SCORE =
		"totalScore = coalesce((select sum(RatingsEntry.score) " +
			_SQL_FROM_WHERE_CLAUSE + "), 0)";

}