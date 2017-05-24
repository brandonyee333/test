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

package com.liferay.osb.customer.deploy.auto;

import com.liferay.portal.deploy.auto.ThreadSafeAutoDeployer;
import com.liferay.portal.kernel.deploy.auto.AutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.BaseAutoDeployListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jeremy Fu
 */
@Component(immediate = true, service = AutoDeployListener.class)
public class OSBCustomerKnowledgeBaseAutoDeployListener
	extends BaseAutoDeployListener {

	@Override
	protected AutoDeployer buildAutoDeployer() {
		return new ThreadSafeAutoDeployer(
			new OSBCustomerKnowledgeBaseAutoDeployer());
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

	private static final Log _log = LogFactoryUtil.getLog(
		OSBCustomerKnowledgeBaseAutoDeployListener.class);

}