/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wsrp.util.MarkupCharacterSetsHelper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true)
public class MarkupCharacterSetsImpl implements MarkupCharacterSetsHelper {

	@Override
	public String getSupportedMarkupCharacterSets(String markupCharacterSets) {
		if (Validator.isNull(markupCharacterSets)) {
			return null;
		}

		if (!markupCharacterSets.contains(StringPool.UTF8)) {
			markupCharacterSets = markupCharacterSets.concat(
				StringPool.COMMA
			).concat(
				StringPool.UTF8
			);
		}

		return markupCharacterSets;
	}

}