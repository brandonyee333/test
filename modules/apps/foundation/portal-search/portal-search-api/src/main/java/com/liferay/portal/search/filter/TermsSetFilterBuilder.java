/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.filter;

import aQute.bnd.annotation.ProviderType;

import java.util.List;

/**
 * @author André de Oliveira
 */
@ProviderType
public interface TermsSetFilterBuilder {

	public TermsSetFilter build();

	public void setFieldName(String fieldName);

	public void setMinimumShouldMatchField(String minimumShouldMatchField);

	public void setValues(List<String> values);

}