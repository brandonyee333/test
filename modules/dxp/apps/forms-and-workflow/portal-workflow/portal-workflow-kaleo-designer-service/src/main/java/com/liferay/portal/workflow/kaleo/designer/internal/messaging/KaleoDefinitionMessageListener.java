/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.kaleo.designer.internal.messaging;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalService;

import java.util.Locale;
import java.util.Map;

/**
 * @author Kenneth Chang
 */
public class KaleoDefinitionMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	public void setKaleoDraftDefinitionLocalService(
		KaleoDraftDefinitionLocalService kaleoDraftDefinitionLocalService) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_kaleoDraftDefinitionLocalService = kaleoDraftDefinitionLocalService;
	}

	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (command.equals("create")) {
			onCreate(message);
		}
		else if (command.equals("delete")) {
			onDelete(message);
		}
	}

	protected void onCreate(Message message) throws Exception {
		String name = message.getString("name");
		int version = message.getInteger("version");

		ServiceContext serviceContext = (ServiceContext)message.get(
			"serviceContext");

		WorkflowDefinition workflowDefinition =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				serviceContext.getCompanyId(), name, version);

		_addMissingKaleoDraftDefinition(
			workflowDefinition.getName(), workflowDefinition.getVersion(),
			workflowDefinition.getTitle(), workflowDefinition.getContent(),
			serviceContext);
	}

	protected void onDelete(Message message) throws Exception {
		String name = message.getString("name");
		int version = message.getInteger("version");

		ServiceContext serviceContext = (ServiceContext)message.get(
			"serviceContext");

		_kaleoDraftDefinitionLocalService.deleteKaleoDraftDefinitions(
			name, version, serviceContext);
	}

	private void _addMissingKaleoDraftDefinition(
			String name, int version, String title, String content,
			ServiceContext serviceContext)
		throws PortalException {

		int kaleoDraftDefinitionsCount =
			_kaleoDraftDefinitionLocalService.getKaleoDraftDefinitionsCount(
				name, version, serviceContext);

		if (kaleoDraftDefinitionsCount == 0) {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				title);

			_kaleoDraftDefinitionLocalService.addKaleoDraftDefinition(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				name, titleMap, content, version, 1, serviceContext);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoDefinitionMessageListener.class);

	private KaleoDraftDefinitionLocalService _kaleoDraftDefinitionLocalService;

}