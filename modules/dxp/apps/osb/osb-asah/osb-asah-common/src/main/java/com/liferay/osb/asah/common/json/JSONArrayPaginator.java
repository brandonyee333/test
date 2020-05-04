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

package com.liferay.osb.asah.common.json;

import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;

import org.json.JSONArray;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class JSONArrayPaginator {

	public JSONArrayPaginator() throws Exception {
		this(_DELTA);
	}

	public JSONArrayPaginator(int delta) throws Exception {
		if (delta <= 0) {
			delta = _DELTA;
		}

		int start = 0;
		int end = delta;

		while (true) {
			JSONArray jsonArray = paginate(start, end);

			if (jsonArray.length() == 0) {
				break;
			}

			start = end;

			end += delta;
		}
	}

	protected abstract JSONArray paginate(int start, int end) throws Exception;

	@SuppressFBWarnings("URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
	protected int processedCount;

	private static final int _DELTA = 500;

}