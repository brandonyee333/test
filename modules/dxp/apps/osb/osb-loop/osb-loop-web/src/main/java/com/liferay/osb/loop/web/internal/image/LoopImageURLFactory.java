/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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

		return new LoopImageURL(null, StringPool.BLANK, StringPool.BLANK);
	}

}