/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.model;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;

/**
 * @author Bruno Basto
 */
@DDMForm
@DDMFormLayout(
	{
		@DDMFormLayoutPage(
			title = "%form-options",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"requireCaptcha", "redirectURL", "storageType",
								"workflowDefinition"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%email-notifications",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"sendEmailNotification", "emailFromName",
								"emailFromAddress", "emailToAddress",
								"emailSubject", "published"
							}
						)
					}
				)
			}
		)
	}
)
public interface DDLRecordSetSettings {

	@DDMFormField(
		label = "%from-address",
		validationErrorMessage = "%please-enter-a-valid-email-address",
		validationExpression = "isEmailAddress(emailFromAddress)",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
	public String emailFromAddress();

	@DDMFormField(
		label = "%from-name",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
	public String emailFromName();

	@DDMFormField(
		label = "%subject",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
	public String emailSubject();

	@DDMFormField(
		label = "%to-address",
		validationErrorMessage = "%please-enter-one-or-more-valid-email-addresses-separated-by-commas",
		validationExpression = "isEmailAddresses(emailToAddress)",
		visibilityExpression = "sendEmailNotification == TRUE"
	)
	public String emailToAddress();

	@DDMFormField(visibilityExpression = "FALSE")
	public boolean published();

	@DDMFormField(
		label = "%redirect-url-on-success",
		properties = "placeholder=%enter-a-valid-url",
		validationErrorMessage = "%please-enter-a-valid-url",
		validationExpression = "isURL(redirectURL)"
	)
	public String redirectURL();

	@DDMFormField(
		label = "%require-captcha", properties = "showAsSwitcher=true",
		type = "checkbox"
	)
	public boolean requireCaptcha();

	@DDMFormField(
		label = "%send-an-email-notification-for-each-entry",
		properties = "showAsSwitcher=true", type = "checkbox"
	)
	public boolean sendEmailNotification();

	@DDMFormField(
		label = "%select-a-storage-type", predefinedValue = "[\"json\"]",
		properties = "dataSourceType=manual", type = "select"
	)
	public String storageType();

	@DDMFormField(
		label = "%select-a-workflow", predefinedValue = "[\"no-workflow\"]",
		properties = "dataSourceType=manual", type = "select"
	)
	public String workflowDefinition();

}