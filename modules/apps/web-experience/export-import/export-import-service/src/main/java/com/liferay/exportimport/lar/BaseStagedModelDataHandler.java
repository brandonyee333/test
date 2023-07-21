/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.lar;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.content.processor.ExportImportContentProcessorRegistryUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.StagedModel;

import java.util.Collections;
import java.util.List;

/**
 * @author Daniel Kocsis
 */
public abstract class BaseStagedModelDataHandler<T extends StagedModel>
	extends com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler<T> {

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		StagedModelRepository<T> stagedModelRepository =
			getStagedModelRepository();

		if (stagedModelRepository == null) {
			return;
		}

		stagedModelRepository.deleteStagedModel(
			uuid, groupId, className, extraData);
	}

	@Override
	public void deleteStagedModel(T stagedModel) throws PortalException {
		StagedModelRepository<T> stagedModelRepository =
			getStagedModelRepository();

		if (stagedModelRepository == null) {
			return;
		}

		stagedModelRepository.deleteStagedModel(stagedModel);
	}

	@Override
	public void exportStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException {

		super.exportStagedModel(portletDataContext, stagedModel);
	}

	@Override
	public T fetchMissingReference(String uuid, long groupId) {
		StagedModelRepository<T> stagedModelRepository =
			getStagedModelRepository();

		if (stagedModelRepository == null) {
			return super.fetchMissingReference(uuid, groupId);
		}

		return stagedModelRepository.fetchMissingReference(uuid, groupId);
	}

	@Override
	public T fetchStagedModelByUuidAndGroupId(String uuid, long groupId) {
		StagedModelRepository<T> stagedModelRepository =
			getStagedModelRepository();

		if (stagedModelRepository == null) {
			return super.fetchStagedModelByUuidAndGroupId(uuid, groupId);
		}

		return stagedModelRepository.fetchStagedModelByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<T> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		StagedModelRepository<T> stagedModelRepository =
			getStagedModelRepository();

		if (stagedModelRepository == null) {
			return Collections.<T>emptyList();
		}

		return stagedModelRepository.fetchStagedModelsByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException {

		StagedModelRepository<T> stagedModelRepository =
			getStagedModelRepository();

		if (stagedModelRepository == null) {
			super.restoreStagedModel(portletDataContext, stagedModel);

			return;
		}

		stagedModelRepository.restoreStagedModel(
			portletDataContext, stagedModel);
	}

	protected ExportImportContentProcessor getExportImportContentProcessor(
		Class<T> clazz) {

		return ExportImportContentProcessorRegistryUtil.
			getExportImportContentProcessor(clazz.getName());
	}

	protected StagedModelRepository<T> getStagedModelRepository() {
		return null;
	}

}