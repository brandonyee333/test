/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.temporaryrepository;

import com.liferay.portal.kernel.repository.DocumentRepository;
import com.liferay.portal.kernel.repository.RepositoryFactory;
import com.liferay.portal.kernel.repository.capabilities.BulkOperationCapability;
import com.liferay.portal.kernel.repository.capabilities.TemporaryFileEntriesCapability;
import com.liferay.portal.kernel.repository.capabilities.WorkflowCapability;
import com.liferay.portal.kernel.repository.registry.BaseRepositoryDefiner;
import com.liferay.portal.kernel.repository.registry.CapabilityRegistry;
import com.liferay.portal.kernel.repository.registry.RepositoryFactoryRegistry;
import com.liferay.portal.repository.capabilities.LiferayBulkOperationCapability;
import com.liferay.portal.repository.capabilities.MinimalWorkflowCapability;
import com.liferay.portal.repository.capabilities.TemporaryFileEntriesCapabilityImpl;
import com.liferay.portal.repository.capabilities.util.DLFileEntryServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFolderServiceAdapter;

/**
 * @author Iván Zaera
 */
public class TemporaryFileEntryRepositoryDefiner extends BaseRepositoryDefiner {

	public static final String CLASS_NAME =
		TemporaryFileEntryRepository.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
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
			BulkOperationCapability.class,
			new LiferayBulkOperationCapability(
				documentRepository, dlFileEntryServiceAdapter,
				DLFolderServiceAdapter.create(documentRepository)));

		capabilityRegistry.addExportedCapability(
			TemporaryFileEntriesCapability.class,
			new TemporaryFileEntriesCapabilityImpl(documentRepository));

		capabilityRegistry.addSupportedCapability(
			WorkflowCapability.class,
			new MinimalWorkflowCapability(dlFileEntryServiceAdapter));
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