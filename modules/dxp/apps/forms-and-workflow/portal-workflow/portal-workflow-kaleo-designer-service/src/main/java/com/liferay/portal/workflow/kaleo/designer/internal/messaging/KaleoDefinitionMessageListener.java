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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.designer.util.KaleoDesignerUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kenneth Chang
 */
@Component(immediate = true, service = KaleoDefinitionMessageListener.class)
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

		KaleoDesignerUtil.addMissingKaleoDraftDefinition(
			workflowDefinition.getName(), workflowDefinition.getVersion(),
			workflowDefinition.getTitle(), workflowDefinition.getContent(),
			serviceContext);
	}

	protected void onDelete(Message message) throws Exception {
		String name = message.getString("name");
		int version = message.getInteger("version");

		ServiceContext serviceContext = (ServiceContext)message.get(
			"serviceContext");

		KaleoDraftDefinitionLocalServiceUtil.deleteKaleoDraftDefinitions(
			name, version, serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoDefinitionMessageListener.class);

}