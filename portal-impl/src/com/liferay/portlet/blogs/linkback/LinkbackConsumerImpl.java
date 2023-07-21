/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.blogs.linkback;

import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexander Chow
 * @author André de Oliveira
 */
public class LinkbackConsumerImpl implements LinkbackConsumer {

	@Override
	public void addNewTrackback(long commentId, String url, String entryURL) {
		_trackbacks.add(new Tuple(commentId, url, entryURL));
	}

	@Override
	public void verifyNewTrackbacks() {
		Tuple tuple = null;

		while (!_trackbacks.isEmpty()) {
			synchronized (_trackbacks) {
				tuple = _trackbacks.remove(0);
			}

			long commentId = (Long)tuple.getObject(0);
			String url = (String)tuple.getObject(1);
			String entryUrl = (String)tuple.getObject(2);

			verifyTrackback(commentId, url, entryUrl);
		}
	}

	@Override
	public void verifyTrackback(long commentId, String url, String entryURL) {
		try {
			String result = HttpUtil.URLtoString(url);

			if (result.contains(entryURL)) {
				return;
			}
		}
		catch (Exception e) {
		}

		try {
			CommentManagerUtil.deleteComment(commentId);
		}
		catch (Exception e) {
			_log.error("Unable to delete trackback comment " + commentId, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LinkbackConsumerImpl.class);

	private final List<Tuple> _trackbacks = Collections.synchronizedList(
		new ArrayList<Tuple>());

}