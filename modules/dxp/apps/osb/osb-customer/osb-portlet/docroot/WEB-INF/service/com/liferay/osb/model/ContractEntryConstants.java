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

package com.liferay.osb.model;

/**
 * @author Kyle Bischof
 */
public class ContractEntryConstants {

	public static final long DEFAULT_CLASS_NAME_ID = 0;

	public static final long DEFAULT_CLASS_PK = 0;

	public static final int TYPE_APP_EULA = 0;

	public static final int TYPE_DEVELOPER_AGREEMENT = 1;

	public static final int TYPE_RESELLER_LICENSE_AGREEMENT = 3;

	public static final int TYPE_TERMS_OF_SERVICE = 2;

	public static final int TYPE_TRAINING_EXAM_EULA = 4;

	public static final int[] TYPES_MARKETPLACE = {
		TYPE_APP_EULA, TYPE_DEVELOPER_AGREEMENT, TYPE_TERMS_OF_SERVICE,
		TYPE_RESELLER_LICENSE_AGREEMENT
	};

	public static String getDefaultTypeLabel(int type) {
		if (type == TYPE_APP_EULA) {
			return "default-app-eula";
		}
		else if (type == TYPE_DEVELOPER_AGREEMENT) {
			return "liferay-developer-agreement";
		}
		else if (type == TYPE_RESELLER_LICENSE_AGREEMENT) {
			return "reseller-license-agreement";
		}
		else if (type == TYPE_TERMS_OF_SERVICE) {
			return "liferay-terms-of-service";
		}
		else if (type == TYPE_TRAINING_EXAM_EULA) {
			return "training-exam-eula";
		}

		return "contract";
	}

	public static String getTypeLabel(int type) {
		if (type == TYPE_APP_EULA) {
			return "end-user-license-agreement";
		}
		else if (type == TYPE_DEVELOPER_AGREEMENT) {
			return "liferay-developer-agreement";
		}
		else if (type == TYPE_RESELLER_LICENSE_AGREEMENT) {
			return "reseller-license-agreement";
		}
		else if (type == TYPE_TERMS_OF_SERVICE) {
			return "liferay-terms-of-service";
		}
		else if (type == TYPE_TRAINING_EXAM_EULA) {
			return "training-exam-eula";
		}

		return "contract";
	}

}