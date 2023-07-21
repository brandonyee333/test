/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.internal.repository;

import com.liferay.osb.customer.ticket.configuration.PortletPropsKeys;
import com.liferay.osb.customer.ticket.configuration.TicketConfigurationUtil;
import com.liferay.osb.customer.ticket.configuration.TicketConfigurationValues;
import com.liferay.osb.customer.ticket.repository.FileRepository;
import com.liferay.osb.customer.ticket.repository.FileRepositoryManager;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = FileRepositoryManager.class)
public class FileRepositoryManagerImpl implements FileRepositoryManager {

	public FileRepository getDataRegionFileRepository(String dataRegion) {
		for (String fileRepositoryId :
				TicketConfigurationValues.FILE_REPOSITORY_IDS) {

			FileRepository fileRepository = getFileRepository(fileRepositoryId);

			if (ArrayUtil.contains(
					fileRepository.getDataRegions(), dataRegion)) {

				return fileRepository;
			}
		}

		return getFileRepository(
			TicketConfigurationValues.FILE_REPOSITORY_DEFAULT_ID);
	}

	public FileRepository getFileRepository(String fileRepositoryId) {
		if (Validator.isNull(fileRepositoryId)) {
			return null;
		}

		String host = TicketConfigurationUtil.get(
			PortletPropsKeys.FILE_REPOSITORY_HOST,
			new Filter(fileRepositoryId));
		String name = TicketConfigurationUtil.get(
			PortletPropsKeys.FILE_REPOSITORY_NAME,
			new Filter(fileRepositoryId));
		String[] dataRegions = TicketConfigurationUtil.getArray(
			PortletPropsKeys.FILE_REPOSITORY_DATA_REGIONS,
			new Filter(fileRepositoryId));
		String[] accessOrganizationNames = TicketConfigurationUtil.getArray(
			PortletPropsKeys.FILE_REPOSITORY_ACCESS_ORGANIZATION_NAMES,
			new Filter(fileRepositoryId));

		return new FileRepository(
			fileRepositoryId, name, host, dataRegions, accessOrganizationNames);
	}

}