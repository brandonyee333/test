package com.liferay.client.extension.web.internal.portlet;

import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CustomCheckoutStepCETImpl implements CommerceCheckoutStep {

    public CustomCheckoutStepCETImpl(String checkoutStepLabel, String checkoutStepName) {
        _checkoutStepLabel = checkoutStepLabel;
        _checkoutStepName = checkoutStepName;
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, _checkoutStepLabel);
    }

    @Override
    public String getName() {
        return _checkoutStepName;
    }

    @Override
    public boolean isActive(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return true;
    }

    @Override
    public boolean isOrder() {
        return true;
    }

    @Override
    public boolean isSennaDisabled() {
        return false;
    }

    @Override
    public boolean isVisible(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return true;
    }

    @Override
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        System.out.println("PROCESS ACTION");
    }

    @Override
    public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("RENDER");
    }

    @Override
    public boolean showControls(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return true;
    }

    private final String _checkoutStepLabel;
    private final String _checkoutStepName;
}
