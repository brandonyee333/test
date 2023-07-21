/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.markdown.converter.factory;

import com.liferay.knowledge.base.markdown.converter.MarkdownConverter;
import com.liferay.knowledge.base.markdown.converter.internal.pegdown.factory.MarkdownConverterFactoryImpl;

/**
 * @author Sergio González
 */
public class MarkdownConverterFactoryUtil {

	public static MarkdownConverter create() {
		return _markdownConverterFactory.create();
	}

	private static final MarkdownConverterFactory _markdownConverterFactory =
		new MarkdownConverterFactoryImpl();

}