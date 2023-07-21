/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.blogs.linkback;

/**
 * @author André de Oliveira
 */
public class LinkbackConsumerUtil {

	public static void addNewTrackback(
		long commentId, String url, String entryURL) {

		_linkbackConsumer.addNewTrackback(commentId, url, entryURL);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static LinkbackConsumer getLinkbackConsumer() {
		return _linkbackConsumer;
	}

	public static void verifyNewTrackbacks() {
		_linkbackConsumer.verifyNewTrackbacks();
	}

	public static void verifyTrackback(
		long commentId, String url, String entryURL) {

		_linkbackConsumer.verifyTrackback(commentId, url, entryURL);
	}

	private static final LinkbackConsumer _linkbackConsumer =
		new LinkbackConsumerImpl();

}