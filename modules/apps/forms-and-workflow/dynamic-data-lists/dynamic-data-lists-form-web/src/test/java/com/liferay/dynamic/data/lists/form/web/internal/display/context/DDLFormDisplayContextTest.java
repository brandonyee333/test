/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.form.web.internal.display.context;

import com.liferay.dynamic.data.lists.service.DDLRecordSetService;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderer;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingContext;
import com.liferay.dynamic.data.mapping.form.values.factory.DDMFormValuesFactory;
import com.liferay.dynamic.data.mapping.io.DDMFormFieldTypesJSONSerializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsImpl;

import java.util.Locale;

import javax.portlet.RenderRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;

/**
 * @author Adam Brandizzi
 */
public class DDLFormDisplayContextTest extends PowerMockito {

	@BeforeClass
	public static void setUpClass() throws Exception {
		PropsUtil.setProps(new PropsImpl());
	}

	@Before
	public void setUp() throws PortalException {
		setUpDDLFormDisplayContext();
		setUpPortalUtil();
	}

	@Test
	public void testDDMFormRenderingContextLocaleIsSiteLocale() {
		DDMForm ddmForm = createDDMForm(LocaleUtil.BRAZIL);

		DDMFormRenderingContext ddmFormRenderingContext =
			_ddlFormDisplayContext.createDDMFormRenderingContext(ddmForm);

		Assert.assertEquals(
			LocaleUtil.BRAZIL, ddmFormRenderingContext.getLocale());
	}

	protected DDMForm createDDMForm(Locale locale) {
		DDMForm ddmForm = new DDMForm();

		ddmForm.setDefaultLocale(locale);

		return ddmForm;
	}

	protected RenderRequest mockRenderRequest() {
		RenderRequest renderRequest = new MockRenderRequest();

		renderRequest.setAttribute(WebKeys.THEME_DISPLAY, new ThemeDisplay());

		return renderRequest;
	}

	protected void setUpDDLFormDisplayContext() throws PortalException {
		_ddlFormDisplayContext = new DDLFormDisplayContext(
			mockRenderRequest(), new MockRenderResponse(),
			mock(DDLRecordSetService.class),
			mock(DDMFormFieldTypesJSONSerializer.class),
			mock(DDMFormFieldTypeServicesTracker.class),
			mock(DDMFormRenderer.class), mock(DDMFormValuesFactory.class),
			new JSONFactoryImpl(),
			mock(WorkflowDefinitionLinkLocalService.class));
	}

	protected void setUpPortalUtil() {
		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(mock(Portal.class));
	}

	private DDLFormDisplayContext _ddlFormDisplayContext;

}