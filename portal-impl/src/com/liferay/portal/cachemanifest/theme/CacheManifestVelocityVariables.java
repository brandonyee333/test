/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package com.liferay.portal.cachemanifest.theme;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Adds Velocity variables for Cache Manifest.
 * Added variables:
 *  - cacheManifest         - whole attribute "manifest" with a value, or empty string, if offline access is not enabled
 *  - cacheManifestEnabled  - whether offline access is enabled
 *  - cacheManifestUrl      - URL of the manifest file
 *
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class CacheManifestVelocityVariables extends Action {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        _log.debug("Running CacheManifestVelocityVariables: " + themeDisplay);

        if (themeDisplay != null) {
            _log.trace("Adding VM variables for Application Cache Manifest.");

            Layout layout = themeDisplay.getLayout();

            boolean offlineAccessEnabled = CacheManifestUtil.isOfflineAccessEnabled(layout);
            String manifestUrl = CacheManifestUtil.getCacheManifestUrl(layout);

            Map<String, Object> vmVariables = new HashMap<String, Object>();
            vmVariables.put(VELOCITY_PARAM_CACHE_MANIFEST_ENABLED, offlineAccessEnabled);
            vmVariables.put(VELOCITY_PARAM_CACHE_MANIFEST_URL, manifestUrl);

            if (offlineAccessEnabled) {
                vmVariables.put(VELOCITY_PARAM_CACHE_MANIFEST, "manifest=\"" + manifestUrl + "\"");
            } else {
                vmVariables.put(VELOCITY_PARAM_CACHE_MANIFEST, "");
            }

            request.setAttribute(WebKeys.VM_VARIABLES, vmVariables);
        }
    }

    private static final String VELOCITY_PARAM_CACHE_MANIFEST_ENABLED = "cacheManifestEnabled";
    private static final String VELOCITY_PARAM_CACHE_MANIFEST_URL= "cacheManifestUrl";
    private static final String VELOCITY_PARAM_CACHE_MANIFEST= "cacheManifest";

    private static final Log _log = LogFactoryUtil.getLog(CacheManifestVelocityVariables.class);
}
