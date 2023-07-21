/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.web.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.DataLevel;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerControl;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class AdminPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "wsrp";

	public AdminPortletDataHandler() {
		setDataLevel(DataLevel.PORTAL);
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(WSRPConsumer.class),
			new StagedModelType(WSRPConsumerPortlet.class),
			new StagedModelType(WSRPProducer.class));
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "wsrp-producers", false),
			new PortletDataHandlerBoolean(
				NAMESPACE, "wsrp-consumers", true,
				new PortletDataHandlerControl[] {
					new PortletDataHandlerBoolean(
						NAMESPACE, "wsrp-consumer-portlets")
				}));
		setPublishToLiveByDefault(true);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				AdminPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		WSRPProducerLocalServiceUtil.deleteWSRPProducers(
			portletDataContext.getCompanyId());

		WSRPConsumerLocalServiceUtil.deleteWSRPConsumers(
			portletDataContext.getCompanyId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-producers")) {

			ActionableDynamicQuery wsrpProducerExportActionableDynamicQuery =
				WSRPProducerLocalServiceUtil.getExportActionableDynamicQuery(
					portletDataContext);

			wsrpProducerExportActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-consumers")) {

			ActionableDynamicQuery wsrpConsumerExportActionableDynamicQuery =
				WSRPConsumerLocalServiceUtil.getExportActionableDynamicQuery(
					portletDataContext);

			wsrpConsumerExportActionableDynamicQuery.performActions();

			if (portletDataContext.getBooleanParameter(
					NAMESPACE, "wsrp-consumer-portlets")) {

				ActionableDynamicQuery
					wsrpConsumerPortletExportActionableDynamicQuery =
						WSRPConsumerPortletLocalServiceUtil.
							getExportActionableDynamicQuery(portletDataContext);

				wsrpConsumerPortletExportActionableDynamicQuery.
					performActions();
			}
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-producers")) {

			Element wsrpProducersElement =
				portletDataContext.getImportDataGroupElement(
					WSRPProducer.class);

			List<Element> wsrpProducerElements =
				wsrpProducersElement.elements();

			for (Element wsrpProducerElement : wsrpProducerElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, wsrpProducerElement);
			}
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-consumers")) {

			Element wsrpConsumersElement =
				portletDataContext.getImportDataGroupElement(
					WSRPConsumer.class);

			List<Element> wsrpConsumerElements =
				wsrpConsumersElement.elements();

			for (Element wsrpConsumerElement : wsrpConsumerElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, wsrpConsumerElement);
			}

			if (portletDataContext.getBooleanParameter(
					NAMESPACE, "wsrp-consumer-portlets")) {

				Element wsrpConsumerPortletsElement =
					portletDataContext.getImportDataGroupElement(
						WSRPConsumerPortlet.class);

				List<Element> wsrpConsumerPortletElements =
					wsrpConsumerPortletsElement.elements();

				for (Element wsrpConsumerPortletElement :
						wsrpConsumerPortletElements) {

					StagedModelDataHandlerUtil.importStagedModel(
						portletDataContext, wsrpConsumerPortletElement);
				}
			}
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery wsrpConsumerExportActionableDynamicQuery =
			WSRPConsumerLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		wsrpConsumerExportActionableDynamicQuery.performCount();

		ActionableDynamicQuery wsrpConsumerPortletExportActionableDynamicQuery =
			WSRPConsumerPortletLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		wsrpConsumerPortletExportActionableDynamicQuery.performCount();

		ActionableDynamicQuery wsrpProducerExportActionableDynamicQuery =
			WSRPProducerLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		wsrpProducerExportActionableDynamicQuery.performCount();
	}

}