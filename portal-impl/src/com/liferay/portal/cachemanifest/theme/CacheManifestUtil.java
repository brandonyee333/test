/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package com.liferay.portal.cachemanifest.theme;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Layout;
import com.liferay.portal.cachemanifest.manifest.ManifestConstants;
import com.liferay.portal.cachemanifest.manifest.Util;

/**
 * Utilities for retrieving data usable in themes.
 *
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class CacheManifestUtil {

    /**
     * Determines whether offline access is allowed for the given layout.
     *
     * @param layout the layout to check offline support for
     * @return whether offline access is allowed or not
     */
    public static boolean isOfflineAccessEnabled(Layout layout) {
        boolean allow = false;
        UnicodeProperties properties;

        try {
            properties = layout.getLayoutSet().getSettingsProperties();

            allow = "true".equals(properties.getProperty(ManifestConstants.PROPERTIES_ALLOW));
        } catch (Exception e) {
            _log.error("Cannot determine offline settings for layout: " + layout.getLayoutId(), e);
        }

        if (allow) {
            properties = layout.getTypeSettingsProperties();

            allow = !"true".equals(properties.getProperty(ManifestConstants.PROPERTIES_DISABLE));
        }

        return allow;
    }

    /**
     * Returns Application Cache Manifest location for the given layout.
     *
     * @param layout the layout to find manifest location for
     * @return URL of the manifest
     */
    public static String getCacheManifestUrl(Layout layout) {
        try {
            StringBuilder sb = new StringBuilder(APPLICATION_CACHE_MANIFEST_PREFIX)
                    .append(Util.generateManifestName(layout.getLayoutSet()))
                    .append(APPLICATION_CACHE_MANIFEST_POSTFIX);

            return sb.toString();
        } catch (Exception e) {
            _log.error("Cannot determine Application Cache Manifest location for layout: " + layout.getLayoutId(), e);

            return null;
        }
    }

    private static final String APPLICATION_CACHE_MANIFEST_PREFIX = "/";
    private static final String APPLICATION_CACHE_MANIFEST_POSTFIX = ".manifest";

    private static final Log _log = LogFactoryUtil.getLog(CacheManifestUtil.class);
}
