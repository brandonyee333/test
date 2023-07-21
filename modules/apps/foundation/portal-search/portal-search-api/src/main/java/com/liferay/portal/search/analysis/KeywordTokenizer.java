/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.analysis;

import aQute.bnd.annotation.ProviderType;

import java.util.List;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface KeywordTokenizer {

	public boolean requiresTokenization(String keyword);

	public List<String> tokenize(String value);

}