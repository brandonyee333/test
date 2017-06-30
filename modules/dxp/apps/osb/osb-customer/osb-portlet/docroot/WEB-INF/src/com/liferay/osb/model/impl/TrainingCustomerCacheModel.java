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

import com.liferay.osb.model.TrainingCustomer;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing TrainingCustomer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCustomer
 * @generated
 */
public class TrainingCustomerCacheModel implements CacheModel<TrainingCustomer>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{trainingCustomerId=");
		sb.append(trainingCustomerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", userProfileHistoryId=");
		sb.append(userProfileHistoryId);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public TrainingCustomer toEntityModel() {
		TrainingCustomerImpl trainingCustomerImpl = new TrainingCustomerImpl();

		trainingCustomerImpl.setTrainingCustomerId(trainingCustomerId);
		trainingCustomerImpl.setUserId(userId);
		trainingCustomerImpl.setClassNameId(classNameId);
		trainingCustomerImpl.setClassPK(classPK);
		trainingCustomerImpl.setUserProfileHistoryId(userProfileHistoryId);

		if (comments == null) {
			trainingCustomerImpl.setComments(StringPool.BLANK);
		}
		else {
			trainingCustomerImpl.setComments(comments);
		}

		trainingCustomerImpl.setStatus(status);

		trainingCustomerImpl.resetOriginalValues();

		return trainingCustomerImpl;
	}

	public long trainingCustomerId;
	public long userId;
	public long classNameId;
	public long classPK;
	public long userProfileHistoryId;
	public String comments;
	public int status;
}