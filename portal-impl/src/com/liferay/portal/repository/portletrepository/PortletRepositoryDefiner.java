/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.portletrepository;

import com.liferay.document.library.kernel.service.DLAppHelperLocalServiceUtil;
import com.liferay.portal.kernel.repository.DocumentRepository;
import com.liferay.portal.kernel.repository.RepositoryFactory;
import com.liferay.portal.kernel.repository.capabilities.ProcessorCapability;
import com.liferay.portal.kernel.repository.capabilities.RelatedModelCapability;
import com.liferay.portal.kernel.repository.capabilities.TrashCapability;
import com.liferay.portal.kernel.repository.capabilities.WorkflowCapability;
import com.liferay.portal.kernel.repository.registry.BaseRepositoryDefiner;
import com.liferay.portal.kernel.repository.registry.CapabilityRegistry;
import com.liferay.portal.kernel.repository.registry.RepositoryFactoryRegistry;
import com.liferay.portal.repository.capabilities.LiferayProcessorCapability;
import com.liferay.portal.repository.capabilities.LiferayRelatedModelCapability;
import com.liferay.portal.repository.capabilities.LiferayTrashCapability;
import com.liferay.portal.repository.capabilities.MinimalWorkflowCapability;
import com.liferay.portal.repository.capabilities.util.DLAppServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFileEntryServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFolderServiceAdapter;
import com.liferay.portal.repository.capabilities.util.RepositoryEntryChecker;
import com.liferay.portal.repository.capabilities.util.RepositoryEntryConverter;
import com.liferay.portal.repository.capabilities.util.RepositoryServiceAdapter;
import com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil;
import com.liferay.trash.kernel.service.TrashVersionLocalServiceUtil;

/**
 * @author Adolfo Pérez
 */
public class PortletRepositoryDefiner extends BaseRepositoryDefiner {

	@Override
	public String getClassName() {
		return PortletRepository.class.getName();
	}

	@Override
	public boolean isExternalRepository() {
		return false;
	}

	@Override
	public void registerCapabilities(
		CapabilityRegistry<DocumentRepository> capabilityRegistry) {

		DocumentRepository documentRepository = capabilityRegistry.getTarget();

		DLFileEntryServiceAdapter dlFileEntryServiceAdapter =
			DLFileEntryServiceAdapter.create(documentRepository);

		capabilityRegistry.addExportedCapability(
			RelatedModelCapability.class,
			new LiferayRelatedModelCapability(
				new RepositoryEntryConverter(),
				new RepositoryEntryChecker(documentRepository)));

		TrashCapability trashCapability = new LiferayTrashCapability(
			DLAppHelperLocalServiceUtil.getService(),
			DLAppServiceAdapter.create(documentRepository),
			dlFileEntryServiceAdapter,
			DLFolderServiceAdapter.create(documentRepository),
			RepositoryServiceAdapter.create(documentRepository),
			TrashEntryLocalServiceUtil.getService(),
			TrashVersionLocalServiceUtil.getService());

		capabilityRegistry.addExportedCapability(
			TrashCapability.class, trashCapability);

		capabilityRegistry.addExportedCapability(
			WorkflowCapability.class,
			new MinimalWorkflowCapability(dlFileEntryServiceAdapter));

		capabilityRegistry.addSupportedCapability(
			ProcessorCapability.class, new LiferayProcessorCapability());
	}

	@Override
	public void registerRepositoryFactory(
		RepositoryFactoryRegistry repositoryFactoryRegistry) {

		repositoryFactoryRegistry.setRepositoryFactory(_repositoryFactory);
	}

	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		_repositoryFactory = repositoryFactory;
	}

	private RepositoryFactory _repositoryFactory;

}