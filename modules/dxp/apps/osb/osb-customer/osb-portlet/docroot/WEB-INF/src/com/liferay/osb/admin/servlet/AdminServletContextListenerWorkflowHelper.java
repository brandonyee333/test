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

package com.liferay.osb.admin.servlet;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Amos Fong
 */
public class AdminServletContextListenerWorkflowHelper {

	public static void setup() throws Exception {
		InputStream inputStream = null;

		try {
			ClassLoader classLoader =
				AdminServletContextListenerWorkflowHelper.class.
					getClassLoader();

			inputStream = classLoader.getResourceAsStream(
				"META-INF/definitions/osb-provisioning-review-definition.xml");

			byte[] bytes = FileUtil.getBytes(inputStream);

			Document document = SAXReaderUtil.read(
				new ByteArrayInputStream(bytes));

			Element rootElement = document.getRootElement();

			Element versionElement = rootElement.element("version");

			int version = GetterUtil.getInteger(versionElement.getData());

			try {
				WorkflowDefinitionLink workflowDefinitionLink =
					WorkflowDefinitionLinkLocalServiceUtil.
						getWorkflowDefinitionLink(
							OSBConstants.COMPANY_ID, 0,
							AccountEntry.class.getName(), 0, 0);

				if (workflowDefinitionLink.getWorkflowDefinitionVersion() >=
						version) {

					return;
				}
			}
			catch (NoSuchWorkflowDefinitionLinkException nswdle) {
			}

			WorkflowDefinition workflowDefinition =
				WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
					OSBConstants.COMPANY_ID,
					OSBConstants.USER_AMOS_FONG_USER_ID,
					"OSB Provisioning Approval", bytes);

			WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
				OSBConstants.USER_AMOS_FONG_USER_ID, OSBConstants.COMPANY_ID, 0,
				AccountEntry.class.getName(), 0, 0,
				workflowDefinition.getName(), workflowDefinition.getVersion());

			WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
				OSBConstants.USER_AMOS_FONG_USER_ID, OSBConstants.COMPANY_ID, 0,
				OrderEntry.class.getName(), 0, 0, workflowDefinition.getName(),
				workflowDefinition.getVersion());
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

}