/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class RatingsScoreTag extends IncludeTag {

	public void setScore(double score) {
		_score = score;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_score = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:ratings-score:score", String.valueOf(_score));
	}

	private static final String _PAGE =
		"/html/taglib/ui/ratings_score/page.jsp";

	private double _score;

}