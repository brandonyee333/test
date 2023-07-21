/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.web.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class WSRPConsumerPortletStagedModelDataHandler
	extends BaseStagedModelDataHandler<WSRPConsumerPortlet> {

	public static final String[] CLASS_NAMES = {
		WSRPConsumerPortlet.class.getName()
	};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		WSRPConsumerPortlet wsrpConsumerPortlet =
			WSRPConsumerPortletLocalServiceUtil.
				fetchWSRPConsumerPortletByUuidAndCompanyId(
					uuid, group.getCompanyId());

		if (wsrpConsumerPortlet != null) {
			deleteStagedModel(wsrpConsumerPortlet);
		}
	}

	@Override
	public void deleteStagedModel(WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException {

		WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet(
			wsrpConsumerPortlet);
	}

	@Override
	public List<WSRPConsumerPortlet> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return ListUtil.fromArray(
			WSRPConsumerPortletLocalServiceUtil.
				fetchWSRPConsumerPortletByUuidAndCompanyId(uuid, companyId));
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(WSRPConsumerPortlet wsrpConsumerPortlet) {
		return wsrpConsumerPortlet.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws Exception {

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, wsrpConsumerPortlet, wsrpConsumer,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element wsrpConsumerPortletElement =
			portletDataContext.getExportDataElement(wsrpConsumerPortlet);

		portletDataContext.addClassedModel(
			wsrpConsumerPortletElement,
			ExportImportPathUtil.getModelPath(wsrpConsumerPortlet),
			wsrpConsumerPortlet);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws Exception {

		Map<Long, Long> wsrpConsumerIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				WSRPConsumer.class);

		long wsrpConsumerId = MapUtil.getLong(
			wsrpConsumerIds, wsrpConsumerPortlet.getWsrpConsumerId(),
			wsrpConsumerPortlet.getWsrpConsumerId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			wsrpConsumerPortlet);

		WSRPConsumerPortlet importedWSRPConsumerPortlet = null;

		if (portletDataContext.isDataStrategyMirror()) {
			WSRPConsumerPortlet existingWSRPConsumerPortlet =
				WSRPConsumerPortletLocalServiceUtil.
					fetchWSRPConsumerPortletByUuidAndCompanyId(
						wsrpConsumerPortlet.getUuid(),
						portletDataContext.getCompanyId());

			if (existingWSRPConsumerPortlet == null) {
				serviceContext.setUuid(wsrpConsumerPortlet.getUuid());

				importedWSRPConsumerPortlet =
					WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
						wsrpConsumerId, wsrpConsumerPortlet.getName(),
						wsrpConsumerPortlet.getPortletHandle(), serviceContext);
			}
			else {
				existingWSRPConsumerPortlet.setWsrpConsumerId(wsrpConsumerId);
				existingWSRPConsumerPortlet.setName(
					wsrpConsumerPortlet.getName());
				existingWSRPConsumerPortlet.setPortletHandle(
					wsrpConsumerPortlet.getPortletHandle());

				importedWSRPConsumerPortlet =
					WSRPConsumerPortletLocalServiceUtil.
						updateWSRPConsumerPortlet(existingWSRPConsumerPortlet);
			}
		}
		else {
			importedWSRPConsumerPortlet =
				WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
					wsrpConsumerId, wsrpConsumerPortlet.getName(),
					wsrpConsumerPortlet.getPortletHandle(), serviceContext);
		}

		portletDataContext.importClassedModel(
			wsrpConsumerPortlet, importedWSRPConsumerPortlet);
	}

}