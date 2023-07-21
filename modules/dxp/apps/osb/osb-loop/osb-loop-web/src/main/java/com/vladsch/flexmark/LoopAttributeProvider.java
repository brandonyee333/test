/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.vladsch.flexmark;

import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.LinkNode;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.util.html.Attributes;

/**
 * @author Timothy Bell
 */
public class LoopAttributeProvider implements AttributeProvider {

	@Override
	public void setAttributes(
		Node node, AttributablePart attributablePart, Attributes attributes) {

		if (!(node instanceof LinkNode) ||
			(attributablePart != AttributablePart.LINK)) {

			return;
		}

		String destination = attributes.getValue("href");

		if ((node instanceof AutoLink) &&
			!destination.startsWith(Http.HTTP_WITH_SLASH) &&
			!destination.startsWith(Http.HTTPS_WITH_SLASH)) {

			attributes.addValue("href", Http.HTTP_WITH_SLASH + destination);
		}

		if (!destination.startsWith(StringPool.POUND)) {
			attributes.addValue("rel", "noopener noreferrer");
			attributes.addValue("target", "_blank");
		}
	}

}