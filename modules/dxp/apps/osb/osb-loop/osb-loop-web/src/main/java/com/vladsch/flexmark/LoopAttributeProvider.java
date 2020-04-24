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