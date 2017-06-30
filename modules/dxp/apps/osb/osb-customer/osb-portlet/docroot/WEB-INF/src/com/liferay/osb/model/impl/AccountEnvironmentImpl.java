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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.ProductEntryConstants;

/**
 * @author Lin Cui
 */
public class AccountEnvironmentImpl extends AccountEnvironmentBaseImpl {

	public String getEnvASLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvAS());
	}

	public String getEnvDBLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvDB());
	}

	public String getEnvJVMLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvJVM());
	}

	public String getEnvLFRLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvLFR());
	}

	public String getEnvOSLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvOS());
	}

	public String getSupportPhaseLabel() {
		return ProductEntryConstants.getSupportPhaseLabel(getEnvLFR());
	}

}