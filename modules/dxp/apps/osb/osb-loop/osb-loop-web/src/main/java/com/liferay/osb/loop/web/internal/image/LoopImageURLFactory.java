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
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Timothy Bell
 */
public class LoopImageURLFactory {

	public static LoopImageURL createLoopImageURL(
			long classNameId, long classPK, String keyword, String type)
		throws PortalException {

		if (classNameId == PortalUtil.getClassNameId(LoopDivision.class)) {
			LoopDivision loopDivision =
				LoopDivisionLocalServiceUtil.getLoopDivision(classPK);

			return new LoopDivisionImageURL(loopDivision, keyword, type);
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopPerson.class)) {
			LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
				classPK);

			return new LoopPersonImageURL(loopPerson, keyword, type);
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopTopic.class)) {
			LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
				classPK);

			return new LoopImageURL(loopTopic, keyword, type);
		}
		else {
			return new LoopImageURL(null, StringPool.BLANK, StringPool.BLANK);
		}
	}

}