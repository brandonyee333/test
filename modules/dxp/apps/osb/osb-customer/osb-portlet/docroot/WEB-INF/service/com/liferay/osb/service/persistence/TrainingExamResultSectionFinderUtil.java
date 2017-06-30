/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingExamResultSectionFinderUtil {
	public static java.util.List<com.liferay.osb.model.TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByTrainingExamResultId(trainingExamResultId, params);
	}

	public static TrainingExamResultSectionFinder getFinder() {
		if (_finder == null) {
			_finder = (TrainingExamResultSectionFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingExamResultSectionFinder.class.getName());

			ReferenceRegistry.registerReference(TrainingExamResultSectionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TrainingExamResultSectionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TrainingExamResultSectionFinderUtil.class,
			"_finder");
	}

	private static TrainingExamResultSectionFinder _finder;
}