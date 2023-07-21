/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.StagedModel;

import java.util.List;

/**
 * @author Daniel Kocsis
 */
public interface StagedModelRepository<T extends StagedModel> {

	public T addStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortalException;

	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException;

	public void deleteStagedModel(T stagedModel) throws PortalException;

	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException;

	public T fetchMissingReference(String uuid, long groupId);

	public T fetchStagedModelByUuidAndGroupId(String uuid, long groupId);

	public List<T> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId);

	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	public void restoreStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException;

	public T saveStagedModel(T stagedModel) throws PortalException;

	public T updateStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortalException;

}