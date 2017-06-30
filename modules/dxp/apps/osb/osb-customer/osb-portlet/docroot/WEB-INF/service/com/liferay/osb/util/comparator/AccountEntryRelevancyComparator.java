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

package com.liferay.osb.util.comparator;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.AccountEntry;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Amos Fong
 */
public class AccountEntryRelevancyComparator extends OrderByComparator {

	public AccountEntryRelevancyComparator(String keywords) {
		_keywords = keywords.toLowerCase();
		_keywords = _keywords.trim();

		_sanitizedKeywords = StringUtil.extractChars(keywords);
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AccountEntry accountEntry1 = (AccountEntry)obj1;
		AccountEntry accountEntry2 = (AccountEntry)obj2;

		String name1 = accountEntry1.getName().toLowerCase();

		if (name1.equals(_keywords)) {
			return 1;
		}

		String name2 = accountEntry2.getName().toLowerCase();

		if (name2.equals(_keywords)) {
			return -1;
		}

		return name1.compareTo(name2);
	}

	@Override
	public String getOrderBy() {
		String[] keywordsArray = _keywords.split("\\s+");

		StringBundler sb = new StringBundler((keywordsArray.length * 3) + 8);

		if (Validator.isNotNull(_sanitizedKeywords)) {
			sb.append("accountEntryCode = '");
			sb.append(_sanitizedKeywords);
			sb.append("' DESC, accountEntryName = '");
			sb.append(_sanitizedKeywords);
			sb.append("' DESC, accountEntryCode like '%");
			sb.append(_sanitizedKeywords);
			sb.append("%' DESC, ");
		}

		for (int i = 0; i < keywordsArray.length; i++) {
			String sanitizedKeywords = StringUtil.extractChars(
				keywordsArray[i]);

			if (Validator.isNull(sanitizedKeywords)) {
				continue;
			}

			sb.append("accountEntryName like '");

			if (i == 0) {
				sb.append(sanitizedKeywords);
				sb.append(StringPool.PERCENT);
			}
			else if (i == (keywordsArray.length - 1)) {
				sb.append(StringPool.PERCENT);
				sb.append(sanitizedKeywords);
			}
			else {
				sb.append(StringPool.PERCENT);
				sb.append(keywordsArray[i]);
				sb.append(StringPool.PERCENT);
			}

			sb.append("' DESC, ");
		}

		sb.append("accountEntryName ASC");

		return sb.toString();
	}

	private String _keywords;
	private String _sanitizedKeywords;

}