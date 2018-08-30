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

package com.liferay.osb.customer.ticket.attachment.internal.repository;

import com.liferay.osb.customer.ticket.attachment.configuration.PortletPropsKeys;
import com.liferay.osb.customer.ticket.attachment.configuration.TicketAttachmentConfigurationUtil;
import com.liferay.osb.customer.ticket.attachment.configuration.TicketAttachmentConfigurationValues;
import com.liferay.osb.customer.ticket.attachment.repository.FileRepository;
import com.liferay.osb.customer.ticket.attachment.repository.FileRepositoryManager;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = FileRepositoryManager.class)
public class FileRepositoryManagerImpl implements FileRepositoryManager {

	public FileRepository getFileRepository(long supportRegionId) {
		FileRepository defaultFileRepository = null;

		for (String fileRepositoryId :
				TicketAttachmentConfigurationValues.FILE_REPOSITORY_IDS) {

			FileRepository fileRepository = getFileRepository(fileRepositoryId);

			if (ArrayUtil.contains(
					fileRepository.getSupportRegionIds(), supportRegionId)) {

				return fileRepository;
			}

			if (defaultFileRepository == null) {
				defaultFileRepository = fileRepository;
			}
		}

		return defaultFileRepository;
	}

	public FileRepository getFileRepository(String fileRepositoryId) {
		if (Validator.isNull(fileRepositoryId)) {
			return null;
		}

		String host = TicketAttachmentConfigurationUtil.get(
			PortletPropsKeys.FILE_REPOSITORY_HOST,
			new Filter(fileRepositoryId));
		String name = TicketAttachmentConfigurationUtil.get(
			PortletPropsKeys.FILE_REPOSITORY_NAME,
			new Filter(fileRepositoryId));
		long[] supportRegionIds = GetterUtil.getLongValues(
			TicketAttachmentConfigurationUtil.getArray(
				PortletPropsKeys.FILE_REPOSITORY_SUPPORT_REGION_IDS,
				new Filter(fileRepositoryId)));

		return new FileRepository(
			fileRepositoryId, name, host, supportRegionIds);
	}

}