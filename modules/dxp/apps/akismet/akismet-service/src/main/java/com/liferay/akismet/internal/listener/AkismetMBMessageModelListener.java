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

package com.liferay.akismet.internal.listener;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.akismet.service.AkismetLocalService;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jamie Sammons
 */
@Component(immediate = true, service = ModelListener.class)
public class AkismetMBMessageModelListener
	extends BaseModelListener<MBMessage> {

	@Override
	public void onAfterRemove(MBMessage message) {
		try {
			_akismetLocalService.deleteAkismetData(
				MBMessage.class.getName(), message.getMessageId());
		}
		catch (Exception e) {
		}
	}

	@Reference
	private AkismetLocalService _akismetLocalService;

}