/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package com.liferay.portal.cachemanifest.manifest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds all generated Application Cache Manifests.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 */
public class ManifestHolder {

    /**
     * Adds manifest to the holder.
     *
     * @param manifest
     */
    public static synchronized void addManifest(Manifest manifest) {
        _manifests.put(manifest.getName(), manifest);
    }

    /**
     * Sets list of manifests to be used instead of current one.
     *
     * @param ml
     */
    public static synchronized void setManifests(List<Manifest> ml) {
        _manifests.clear();
        for (Manifest manifest : ml) {
            _manifests.put(manifest.getName(), manifest);
        }
    }

    /**
     * Returns manifest stored under the given name.
     *
     * @param manifestName
     * @return the manifest content
     */
    public static synchronized Manifest getManifest(String manifestName) {
        return _manifests.get(manifestName);
    }

    /**
     * Removes manifest stored under the given name.
     *
     * @param manifestName
     */
    public static synchronized void removeManifest(String manifestName) {
        _manifests.remove(manifestName);
    }

    private static Map<String, Manifest> _manifests = new HashMap<String, Manifest>();
}
