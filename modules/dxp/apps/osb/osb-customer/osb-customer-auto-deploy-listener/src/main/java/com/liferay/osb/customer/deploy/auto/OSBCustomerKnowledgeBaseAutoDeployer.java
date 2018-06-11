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

package com.liferay.osb.customer.deploy.auto;

import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBFolderLocalServiceUtil;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.service.OSBCustomerKnowledgeBaseLocalServiceUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Fu
 */
public class OSBCustomerKnowledgeBaseAutoDeployer implements AutoDeployer {

	@Override
	public int autoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException {

		try {
			return doAutoDeploy(autoDeploymentContext);
		}
		catch (Exception e) {
			throw new AutoDeployException(e);
		}
	}

	@Override
	public AutoDeployer cloneAutoDeployer() {
		return new OSBCustomerKnowledgeBaseAutoDeployer();
	}

	protected int doAutoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		File file = autoDeploymentContext.getFile();

		String[] fileParts = StringUtil.split(file.getName(), StringPool.DASH);

		List<String> urlTitles = new ArrayList<>();

		for (int i = 0; i < fileParts.length; i++) {
			if (fileParts[i].equals("dxp") || fileParts[i].equals("lp")) {
				continue;
			}

			String urlTitle = StringUtil.replace(
				fileParts[i], ".zip", StringPool.BLANK);

			urlTitle = StringUtil.replace(
				urlTitle, CharPool.PERIOD, CharPool.DASH);

			urlTitles.add(urlTitle);
		}

		long kbFolderId = 0;

		for (String urlTitle : urlTitles) {
			KBFolder kbFolder =
				KBFolderLocalServiceUtil.fetchKBFolderByUrlTitle(
					OSBCustomerConstants.GROUP_KNOWLEDGE_ID, kbFolderId,
					urlTitle);

			if (kbFolder == null) {
				_log.error(
					"Unable to find matching folder for " + file.getName());

				return AutoDeployer.CODE_NOT_APPLICABLE;
			}

			kbFolderId = kbFolder.getKbFolderId();
		}

		OSBCustomerKnowledgeBaseLocalServiceUtil.updateOSBKnowledgeBase(
			kbFolderId, file, urlTitles);

		return AutoDeployer.CODE_DEFAULT;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OSBCustomerKnowledgeBaseAutoDeployer.class);

}