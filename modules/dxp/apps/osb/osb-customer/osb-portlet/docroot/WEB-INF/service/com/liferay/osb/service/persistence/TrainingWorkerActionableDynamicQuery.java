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

import com.liferay.osb.model.TrainingWorker;
import com.liferay.osb.service.TrainingWorkerLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class TrainingWorkerActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public TrainingWorkerActionableDynamicQuery() throws SystemException {
		setBaseLocalService(TrainingWorkerLocalServiceUtil.getService());
		setClass(TrainingWorker.class);

		setClassLoader(com.liferay.osb.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("trainingWorkerId");
	}
}