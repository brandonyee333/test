/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Luca Pellizzon
 */
@ExtendedObjectClassDefinition(
	category = "payment", factoryInstanceLabelAttribute = "key",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.commerce.payment.internal.configuration.FunctionCommercePaymentIntegrationConfiguration",
	localization = "content/Language",
	name= "function-commerce-paymentIntegration-configuration"
)
public interface FunctionCommercePaymentIntegrationConfiguration {

	@Meta.AD(type = Meta.Type.String,description="authorizePath-description",
		name="authorizePath" , required = false)
	public String authorizePath();

	@Meta.AD(type = Meta.Type.String,description="cancelPath-description",
		name="cancelPath" , required = false)
	public String cancelPath();

	@Meta.AD(type = Meta.Type.String,description="capturePath-description",
		name="capturePath" , required = false)
	public String capturePath();

	@Meta.AD(name = "key")
	public String key();

	@Meta.AD(type = Meta.Type.String,description="oauth2-applciation-external-reference-code-description",
		name="oauth2-application-external-reference-code" , required = false)
	public String oAuth2ApplicationExternalReferenceCode();

	@Meta.AD(type = Meta.Type.String,description="refund-path-description",
		name="refund-path" , required = false)
	public String refundPath();

	@Meta.AD(type = Meta.Type.Integer,description="type-description",
		name="type" , required = false)
	public int type();

	@Meta.AD(required = false, type = Meta.Type.String,description="type-settings-description",
		name="type-settings" )
	public String typeSettings();

}