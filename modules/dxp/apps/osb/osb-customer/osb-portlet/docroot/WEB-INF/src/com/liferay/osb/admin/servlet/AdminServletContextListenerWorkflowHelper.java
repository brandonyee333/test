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

package com.liferay.osb.admin.servlet;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;

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
					"OSB Provisioning Approval",
					new ByteArrayInputStream(bytes));

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