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

package com.liferay.message.boards.web.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.DataLevel;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryRegistryUtil;
import com.liferay.message.boards.kernel.model.MBBan;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.model.MBThreadFlag;
import com.liferay.message.boards.kernel.service.MBBanLocalService;
import com.liferay.message.boards.kernel.service.MBCategoryLocalService;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.kernel.service.MBStatsUserLocalService;
import com.liferay.message.boards.kernel.service.MBThreadFlagLocalService;
import com.liferay.message.boards.kernel.service.MBThreadLocalService;
import com.liferay.message.boards.web.constants.MBPortletKeys;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.util.PropsValues;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 * @author Raymond Augé
 * @author Daniel Kocsis
 * @author Gergely Mathe
 */
@Component(
	property = "javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
	service = PortletDataHandler.class
)
public class MBPortletDataHandler extends BasePortletDataHandler {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             MBAdminPortletDataHandler#NAMESPACE}
	 */
	@Deprecated
	public static final String NAMESPACE = "message_boards";

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             MBAdminPortletDataHandler#SCHEMA_VERSION}
	 */
	@Deprecated
	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public PortletPreferences deleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return _mbAdminPortletDataHandler.deleteData(
			portletDataContext, portletId, portletPreferences);
	}

	@Override
	public String exportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return _mbAdminPortletDataHandler.exportData(
			portletDataContext, portletId, portletPreferences);
	}

	@Override
	public String getNamespace() {
		return _mbAdminPortletDataHandler.getNamespace();
	}

	@Override
	public String getSchemaVersion() {
		return _mbAdminPortletDataHandler.getSchemaVersion();
	}

	@Override
	public String getServiceName() {
		return _mbAdminPortletDataHandler.getServiceName();
	}

	@Override
	public PortletPreferences importData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws PortletDataException {

		return _mbAdminPortletDataHandler.importData(
			portletDataContext, portletId, portletPreferences, data);
	}

	@Override
	public void prepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		_mbAdminPortletDataHandler.prepareManifestSummary(
			portletDataContext, portletPreferences);
	}

	@Activate
	protected void activate() {
		setDataLevel(DataLevel.PORTLET_INSTANCE);
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(MBBan.class),
			new StagedModelType(MBCategory.class),
			new StagedModelType(MBMessage.class),
			new StagedModelType(MBThread.class),
			new StagedModelType(MBThreadFlag.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				getNamespace(), "categories", true, false, null,
				MBCategory.class.getName()),
			new PortletDataHandlerBoolean(
				getNamespace(), "messages", true, false, null,
				MBMessage.class.getName(),
				StagedModelType.REFERRER_CLASS_NAME_ALL),
			new PortletDataHandlerBoolean(
				getNamespace(), "thread-flags", true, false, null,
				MBThreadFlag.class.getName()),
			new PortletDataHandlerBoolean(
				getNamespace(), "user-bans", true, false, null,
				MBBan.class.getName()));
		setImportControls(getExportControls());
		setPublishToLiveByDefault(
			PropsValues.MESSAGE_BOARDS_PUBLISH_TO_LIVE_BY_DEFAULT);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #deleteData(PortletDataContext, String, PortletPreferences)}
	 */
	@Deprecated
	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		return deleteData(portletDataContext, portletId, portletPreferences);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #exportData(PortletDataContext, String, PortletPreferences)}
	 */
	@Deprecated
	@Override
	protected String doExportData(
			final PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		return exportData(portletDataContext, portletId, portletPreferences);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #importData(PortletDataContext, String, PortletPreferences, String)}
	 */
	@Deprecated
	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		return importData(
			portletDataContext, portletId, portletPreferences, data);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #prepareManifestSummary(PortletDataContext, PortletPreferences)}
	 */
	@Deprecated
	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		prepareManifestSummary(portletDataContext, portletPreferences);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected ActionableDynamicQuery getMessageActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		StagedModelRepository<?> mbMessageStagedModelRepository =
			StagedModelRepositoryRegistryUtil.getStagedModelRepository(
				MBMessage.class.getName());

		return mbMessageStagedModelRepository.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void setMBBanLocalService(MBBanLocalService mbBanLocalService) {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void setMBCategoryLocalService(
		MBCategoryLocalService mbCategoryLocalService) {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void setMBMessageLocalService(
		MBMessageLocalService mbMessageLocalService) {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void setMBStatsUserLocalService(
		MBStatsUserLocalService mbStatsUserLocalService) {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void setMBThreadFlagLocalService(
		MBThreadFlagLocalService mbThreadFlagLocalService) {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void setMBThreadLocalService(
		MBThreadLocalService mbThreadLocalService) {
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(
		target = "(javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN + ")"
	)
	private PortletDataHandler _mbAdminPortletDataHandler;

}