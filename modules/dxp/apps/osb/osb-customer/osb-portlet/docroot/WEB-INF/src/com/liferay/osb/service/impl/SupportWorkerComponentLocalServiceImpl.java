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

import com.liferay.osb.model.SupportWorkerComponent;
import com.liferay.osb.service.base.SupportWorkerComponentLocalServiceBaseImpl;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class SupportWorkerComponentLocalServiceImpl
	extends SupportWorkerComponentLocalServiceBaseImpl {

	public List<SupportWorkerComponent> getSupportWorkerComponents(
		long supportWorkerId) {

		return supportWorkerComponentPersistence.findBySupportWorkerId(
			supportWorkerId);
	}

	public void setSupportWorkerComponents(
		long supportWorkerId, int[] components) {

		List<SupportWorkerComponent> supportWorkerComponents =
			supportWorkerComponentPersistence.findBySupportWorkerId(
				supportWorkerId);

		for (SupportWorkerComponent supportWorkerComponent :
				supportWorkerComponents) {

			int component = supportWorkerComponent.getComponent();

			if (ArrayUtil.contains(components, component)) {
				components = ArrayUtil.remove(components, component);
			}
			else {
				supportWorkerComponentPersistence.remove(
					supportWorkerComponent);
			}
		}

		for (int component : components) {
			long supportWorkerComponentId = counterLocalService.increment();

			SupportWorkerComponent supportWorkerComponent =
				supportWorkerComponentPersistence.create(
					supportWorkerComponentId);

			supportWorkerComponent.setSupportWorkerId(supportWorkerId);
			supportWorkerComponent.setComponent(component);

			supportWorkerComponentPersistence.update(supportWorkerComponent);
		}
	}

}