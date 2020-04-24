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

package com.liferay.osb.loop.web.internal.image;

import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Timothy Bell
 */
public class LoopDivisionImageURL extends LoopImageURL {

	public LoopDivisionImageURL(
		LoopDivision loopDivision, String keyword, String type) {

		super(loopDivision, keyword, type);

		_loopDivision = loopDivision;
	}

	@Override
	protected String getPlaceholderImageURL() {
		String placeHolderImageURL = StringPool.BLANK;

		if (!StringUtil.equalsIgnoreCase(keyword, "profileImagePayload") ||
			!StringUtil.equalsIgnoreCase(type, "email")) {

			return placeHolderImageURL;
		}

		if (_loopDivision.getType() == LoopDivisionConstants.TYPE_DEPARTMENT) {
			placeHolderImageURL =
				"/images/email_notification_images/department_avatar.png";
		}
		else if (_loopDivision.getType() ==
					LoopDivisionConstants.TYPE_LOCATION) {

			placeHolderImageURL =
				"/images/email_notification_images/location_avatar.png";
		}
		else if (_loopDivision.getType() == LoopDivisionConstants.TYPE_TEAM) {
			placeHolderImageURL =
				"/images/email_notification_images/team_avatar.png";
		}

		return placeHolderImageURL;
	}

	private final LoopDivision _loopDivision;

}