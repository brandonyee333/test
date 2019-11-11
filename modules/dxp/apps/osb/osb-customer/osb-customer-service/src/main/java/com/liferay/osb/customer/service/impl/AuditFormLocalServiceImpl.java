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

package com.liferay.osb.customer.service.impl;

import com.liferay.osb.customer.exception.EmailDomainException;
import com.liferay.osb.customer.model.AuditForm;
import com.liferay.osb.customer.service.base.AuditFormLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

/**
 * @author Kyle Bischof
 */
public class AuditFormLocalServiceImpl extends AuditFormLocalServiceBaseImpl {

	public AuditForm addAuditForm(
			long userId, Date createDate, String endUserName,
			String endUserEmail, boolean agreement)
		throws Exception {

		User user = userLocalService.getUser(userId);

		validate(user.getEmailAddress(), endUserName, endUserEmail, agreement);

		long auditFormId = counterLocalService.increment();

		AuditForm auditForm = auditFormPersistence.create(auditFormId);

		auditForm.setUserId(userId);
		auditForm.setUserName(user.getFullName());
		auditForm.setCreateDate(createDate);
		auditForm.setEndUserName(endUserName);
		auditForm.setEndUserEmail(endUserEmail);
		auditForm.setAgreement(agreement);

		return auditFormPersistence.update(auditForm);
	}

	protected void validate(
			String userEmail, String endUserName, String endUserEmail,
			boolean agreement)
		throws Exception {

		if (Validator.isNull(endUserName)) {
			throw new RequiredFieldException("endUserName", "endUserName");
		}

		if (Validator.isNull(endUserEmail)) {
			throw new RequiredFieldException("endUserEmail", "endUserEmail");
		}

		String endUserDomain = endUserEmail.substring(
			endUserEmail.indexOf(StringPool.AT));
		String userDomain = userEmail.substring(
			userEmail.indexOf(StringPool.AT));

		if (!endUserDomain.equals(userDomain)) {
			throw new EmailDomainException();
		}

		if (!agreement) {
			throw new RequiredFieldException("agreement", "agreement");
		}
	}

}