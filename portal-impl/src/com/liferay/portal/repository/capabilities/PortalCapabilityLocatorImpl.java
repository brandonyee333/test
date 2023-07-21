/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.capabilities;

import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.document.library.kernel.service.DLSyncEventLocalService;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.repository.DocumentRepository;
import com.liferay.portal.kernel.repository.capabilities.BulkOperationCapability;
import com.liferay.portal.kernel.repository.capabilities.CommentCapability;
import com.liferay.portal.kernel.repository.capabilities.ConfigurationCapability;
import com.liferay.portal.kernel.repository.capabilities.PortalCapabilityLocator;
import com.liferay.portal.kernel.repository.capabilities.ProcessorCapability;
import com.liferay.portal.kernel.repository.capabilities.RelatedModelCapability;
import com.liferay.portal.kernel.repository.capabilities.SyncCapability;
import com.liferay.portal.kernel.repository.capabilities.TemporaryFileEntriesCapability;
import com.liferay.portal.kernel.repository.capabilities.ThumbnailCapability;
import com.liferay.portal.kernel.repository.capabilities.TrashCapability;
import com.liferay.portal.kernel.repository.capabilities.WorkflowCapability;
import com.liferay.portal.repository.capabilities.util.DLAppServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFileEntryServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFileVersionServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFolderServiceAdapter;
import com.liferay.portal.repository.capabilities.util.GroupServiceAdapter;
import com.liferay.portal.repository.capabilities.util.RepositoryEntryChecker;
import com.liferay.portal.repository.capabilities.util.RepositoryEntryConverter;
import com.liferay.portal.repository.capabilities.util.RepositoryServiceAdapter;
import com.liferay.trash.kernel.service.TrashEntryLocalService;
import com.liferay.trash.kernel.service.TrashVersionLocalService;

import org.osgi.service.component.annotations.Activate;

/**
 * @author Adolfo Pérez
 */
public class PortalCapabilityLocatorImpl implements PortalCapabilityLocator {

	@Override
	public BulkOperationCapability getBulkOperationCapability(
		DocumentRepository documentRepository) {

		return new LiferayBulkOperationCapability(
			documentRepository,
			DLFileEntryServiceAdapter.create(documentRepository),
			DLFolderServiceAdapter.create(documentRepository));
	}

	@Override
	public CommentCapability getCommentCapability(
		DocumentRepository documentRepository) {

		return new LiferayCommentCapability();
	}

	@Override
	public ConfigurationCapability getConfigurationCapability(
		DocumentRepository documentRepository) {

		return new ConfigurationCapabilityImpl(
			documentRepository,
			RepositoryServiceAdapter.create(documentRepository));
	}

	@Override
	public ProcessorCapability getProcessorCapability(
		DocumentRepository documentRepository) {

		return new LiferayProcessorCapability();
	}

	@Override
	public RelatedModelCapability getRelatedModelCapability(
		DocumentRepository documentRepository) {

		RepositoryEntryConverter repositoryEntryConverter =
			new RepositoryEntryConverter();
		RepositoryEntryChecker repositoryEntryChecker =
			new RepositoryEntryChecker(documentRepository);

		return new LiferayRelatedModelCapability(
			repositoryEntryConverter, repositoryEntryChecker);
	}

	@Override
	public SyncCapability getSyncCapability(
		DocumentRepository documentRepository) {

		return new LiferaySyncCapability(
			GroupServiceAdapter.create(documentRepository),
			dlSyncEventLocalService);
	}

	@Override
	public TemporaryFileEntriesCapability getTemporaryFileEntriesCapability(
		DocumentRepository documentRepository) {

		return new TemporaryFileEntriesCapabilityImpl(documentRepository);
	}

	@Override
	public ThumbnailCapability getThumbnailCapability(
		DocumentRepository documentRepository) {

		RepositoryEntryConverter repositoryEntryConverter =
			new RepositoryEntryConverter();
		RepositoryEntryChecker repositoryEntryChecker =
			new RepositoryEntryChecker(documentRepository);

		return new LiferayThumbnailCapability(
			repositoryEntryConverter, repositoryEntryChecker);
	}

	@Override
	public TrashCapability getTrashCapability(
		DocumentRepository documentRepository) {

		return new LiferayTrashCapability(
			dlAppHelperLocalService,
			DLAppServiceAdapter.create(documentRepository),
			DLFileEntryServiceAdapter.create(documentRepository),
			DLFolderServiceAdapter.create(documentRepository),
			RepositoryServiceAdapter.create(documentRepository),
			trashEntryLocalService, trashVersionLocalService);
	}

	@Override
	public WorkflowCapability getWorkflowCapability(
		DocumentRepository documentRepository,
		WorkflowCapability.OperationMode operationMode) {

		if (operationMode == WorkflowCapability.OperationMode.MINIMAL) {
			return new MinimalWorkflowCapability(
				DLFileEntryServiceAdapter.create(documentRepository));
		}

		return new LiferayWorkflowCapability(
			DLFileEntryServiceAdapter.create(documentRepository),
			DLFileVersionServiceAdapter.create(documentRepository));
	}

	@Activate
	protected void activate() {
		_alwaysGeneratingProcessorCapability = new LiferayProcessorCapability(
			LiferayProcessorCapability.ResourceGenerationStrategy.
				ALWAYS_GENERATE);
		_reusingProcessorCapability = new LiferayProcessorCapability(
			LiferayProcessorCapability.ResourceGenerationStrategy.REUSE);
	}

	@BeanReference(type = DLAppHelperLocalService.class)
	protected DLAppHelperLocalService dlAppHelperLocalService;

	@BeanReference(type = DLSyncEventLocalService.class)
	protected DLSyncEventLocalService dlSyncEventLocalService;

	@BeanReference(type = TrashEntryLocalService.class)
	protected TrashEntryLocalService trashEntryLocalService;

	@BeanReference(type = TrashVersionLocalService.class)
	protected TrashVersionLocalService trashVersionLocalService;

	private ProcessorCapability _alwaysGeneratingProcessorCapability;
	private ProcessorCapability _reusingProcessorCapability;

}