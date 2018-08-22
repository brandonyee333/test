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

package com.liferay.osb.customer.zendesk.documentation.sync.internal.deploy.auto;

import com.liferay.portal.deploy.auto.ThreadSafeAutoDeployer;
import com.liferay.portal.kernel.deploy.auto.AutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.BaseAutoDeployListener;

import java.io.File;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = AutoDeployListener.class)
public class ZendeskDocumentationSyncAutoDeployListener
	extends BaseAutoDeployListener {

	@Override
	protected AutoDeployer buildAutoDeployer() {
		return new ThreadSafeAutoDeployer(
			new ZendeskDocumentationSyncAutoDeployer());
	}

	@Override
	protected String getPluginPathInfoMessage(File file) {
		return "Importing articles for " + file.getPath();
	}

	@Override
	protected String getSuccessMessage(File file) {
		return "Successfully imported articles from " + file.getPath();
	}

	@Override
	protected boolean isDeployable(File file) {
		String fileName = file.getName();

		if (fileName.endsWith(".zip")) {
			return true;
		}

		return false;
	}

}