/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package com.liferay.portal.cachemanifest.strutsaction;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.cachemanifest.manifest.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Struts Action fo regenerating manifest.
 *
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class RegenerateManifest extends BaseStrutsAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String layoutSetIdString = request.getParameter(LAYOUT_SET_PARAMETER_SET);

        if (layoutSetIdString != null) {
            long layoutSetId = Long.parseLong(layoutSetIdString);

            LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(layoutSetId);

            Util.initializeManifest(layoutSet);

            return null;
        } else {
            throw new NullPointerException(LAYOUT_SET_PARAMETER_SET);
        }
    }

    private static final String LAYOUT_SET_PARAMETER_SET = "layoutSetId";
}
