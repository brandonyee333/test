/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.internal;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;

/**
 * @author Marcellus Tavares
 */
@DDMForm
@DDMFormLayout(
	{
		@DDMFormLayoutPage(
			{
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"url", "key", "value", "username", "password",
								"cacheable"
							}
						)
					}
				)
			}
		)
	}
)
public interface DDMRESTDataProviderSettings {

	@DDMFormField(
		label = "%cache-data-on-the-first-request",
		properties = "showAsSwitcher=true"
	)
	public boolean cacheable();

	@DDMFormField(
		label = "%displayed-json-attribute",
		properties = {
			"placeholder=%enter-the-attribute-to-be-displayed",
			"tooltip=%the-attribute-whose-value-is-displayed-to-the-end-user-for-selection"
		},
		required = true
	)
	public String key();

	@DDMFormField(
		label = "%password",
		properties = {
			"placeholder=%enter-a-password",
			"tooltip=%provide-the-password-for-authenticating-to-the-rest-provider"
		},
		type = "password"
	)
	public String password();

	@DDMFormField(
		label = "%url", properties = "placeholder=%enter-the-rest-service-url",
		required = true
	)
	public String url();

	@DDMFormField(
		label = "%user-name",
		properties = {
			"placeholder=%enter-a-user-name",
			"tooltip=%provide-the-user-name-for-authenticating-to-the-rest-provider"
		}
	)
	public String username();

	@DDMFormField(
		label = "%stored-json-attribute",
		properties = {
			"placeholder=%enter-the-attribute-to-be-stored",
			"tooltip=%the-attribute-whose-value-is-stored-in-the-database-when-selected-by-a-user"
		},
		required = true
	)
	public String value();

}