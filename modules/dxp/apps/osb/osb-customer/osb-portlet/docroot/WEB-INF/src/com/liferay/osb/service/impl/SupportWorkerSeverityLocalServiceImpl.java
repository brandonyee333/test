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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.SupportWorkerSeverity;
import com.liferay.osb.service.base.SupportWorkerSeverityLocalServiceBaseImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class SupportWorkerSeverityLocalServiceImpl
	extends SupportWorkerSeverityLocalServiceBaseImpl {

	public List<SupportWorkerSeverity> getSupportWorkerSeverities(
		long supportWorkerId) {

		return supportWorkerSeverityPersistence.findBySupportWorkerId(
			supportWorkerId);
	}

	public void setSupportWorkerSeverities(
		long supportWorkerId, int[] severities) {

		List<SupportWorkerSeverity> supportWorkerSeverities =
			supportWorkerSeverityPersistence.findBySupportWorkerId(
				supportWorkerId);

		for (SupportWorkerSeverity supportWorkerSeverity :
				supportWorkerSeverities) {

			int severity = supportWorkerSeverity.getSeverity();

			if (ArrayUtil.contains(severities, severity)) {
				severities = ArrayUtil.remove(severities, severity);
			}
			else {
				supportWorkerSeverityPersistence.remove(supportWorkerSeverity);
			}
		}

		for (int severity : severities) {
			long supportWorkerSeverityId = counterLocalService.increment();

			SupportWorkerSeverity supportWorkerSeverity =
				supportWorkerSeverityPersistence.create(
					supportWorkerSeverityId);

			supportWorkerSeverity.setSupportWorkerId(supportWorkerId);
			supportWorkerSeverity.setSeverity(severity);

			//TODO implement serviceContext how needed

			ServiceContext serviceContext = new ServiceContext();

			supportWorkerSeverityPersistence.update(
				supportWorkerSeverity, serviceContext);
		}
	}

}