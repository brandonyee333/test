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

import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Timothy Bell
 */
public class LoopPersonImageURL extends LoopImageURL {

	public LoopPersonImageURL(
		LoopPerson loopPerson, String keyword, String type) {

		super(loopPerson, keyword, type);

		_loopPerson = loopPerson;
	}

	@Override
	protected String getPlaceholderImageURL() {
		String placeholderImageURL = StringPool.BLANK;

		if (StringUtil.equalsIgnoreCase(keyword, "profileImagePayload") &&
			StringUtil.equalsIgnoreCase(type, "email")) {

			placeholderImageURL =
				"/images/email_notification_images/person_avatar.png";
		}
		else if (StringUtil.equalsIgnoreCase(keyword, "coverImagePayload")) {
			placeholderImageURL = _getDefaultCoverImageURL();
		}

		return placeholderImageURL;
	}

	private String _getDefaultCoverImageURL() {
		JSONObject coverImagePayloadJSONObject =
			imagePayloadJSONObject.getJSONObject(keyword);

		if (coverImagePayloadJSONObject == null) {
			_log.error(
				"The Loop person with id " + _loopPerson.getLoopPersonId() +
					"does not have a default cover image.");

			return StringPool.BLANK;
		}

		int defaultCoverImageId = coverImagePayloadJSONObject.getInt(
			"defaultCoverImageId");

		StringBundler sb = new StringBundler(5);

		sb.append("/images/cover_images/cover_image_");
		sb.append(defaultCoverImageId);
		sb.append(StringPool.UNDERLINE);
		sb.append(type);
		sb.append(".jpg");

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoopPersonImageURL.class);

	private final LoopPerson _loopPerson;

}