/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.model.impl;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Amos Fong
 */
public class ZendeskCategoryImpl extends ZendeskCategoryBaseImpl {

	public ZendeskCategoryImpl() {
	}

	public String[] getRemoteLabelNames() {
		return StringUtil.split(getArticleLabels(), StringPool.NEW_LINE);
	}

}