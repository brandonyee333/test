/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.repository.capabilities;

import com.liferay.portal.kernel.repository.DocumentRepository;

/**
 * @author Adolfo Pérez
 */
public interface PortalCapabilityLocator {

	public BulkOperationCapability getBulkOperationCapability(
		DocumentRepository documentRepository);

	public CommentCapability getCommentCapability(
		DocumentRepository documentRepository);

	public ConfigurationCapability getConfigurationCapability(
		DocumentRepository documentRepository);

	public ProcessorCapability getProcessorCapability(
		DocumentRepository documentRepository);

	public RelatedModelCapability getRelatedModelCapability(
		DocumentRepository documentRepository);

	public SyncCapability getSyncCapability(
		DocumentRepository documentRepository);

	public TemporaryFileEntriesCapability getTemporaryFileEntriesCapability(
		DocumentRepository documentRepository);

	public ThumbnailCapability getThumbnailCapability(
		DocumentRepository documentRepository);

	public TrashCapability getTrashCapability(
		DocumentRepository documentRepository);

	public WorkflowCapability getWorkflowCapability(
		DocumentRepository documentRepository,
		WorkflowCapability.OperationMode operationMode);

}