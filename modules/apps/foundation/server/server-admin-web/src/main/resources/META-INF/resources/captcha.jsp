<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error key="reCaptchaPrivateKey" message="the-recaptcha-private-key-is-not-valid" />
<liferay-ui:error key="reCaptchaPublicKey" message="the-recaptcha-public-key-is-not-valid" />

<aui:fieldset-group markupView="lexicon">
	<aui:fieldset>
		<aui:input label="enable-recaptcha" name="reCaptchaEnabled" type="checkbox" value="<%= PrefsPropsUtil.getString(PropsKeys.CAPTCHA_ENGINE_IMPL, PropsValues.CAPTCHA_ENGINE_IMPL).equals(ReCaptchaImpl.class.getName()) %>" />

		<div class="alert alert-info">
			<liferay-ui:message arguments="https://www.google.com/recaptcha/admin/create" key="recaptcha-help" />
		</div>

		<aui:input cssClass="lfr-input-text-container" label="recaptcha-public-key" name="reCaptchaPublicKey" type="text" value="<%= PrefsPropsUtil.getString(PropsKeys.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC, PropsValues.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC) %>" />

		<aui:input cssClass="lfr-input-text-container" label="recaptcha-private-key" name="reCaptchaPrivateKey" type="text" value="<%= PrefsPropsUtil.getString(PropsKeys.CAPTCHA_ENGINE_RECAPTCHA_KEY_PRIVATE, PropsValues.CAPTCHA_ENGINE_RECAPTCHA_KEY_PRIVATE) %>" />
	</aui:fieldset>
</aui:fieldset-group>

<aui:button-row>
	<aui:button cssClass="btn-lg save-server-button" data-cmd="updateCaptcha" value="save" />
</aui:button-row>