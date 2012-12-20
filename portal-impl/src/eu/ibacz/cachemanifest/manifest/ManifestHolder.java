/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package eu.ibacz.cachemanifest.manifest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds all generated Application Cache Manifests.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 */
public class ManifestHolder {

    private static Map<String, Manifest> manifests = new HashMap<String, Manifest>();

    /**
     * Adds manifest to the holder.
     *
     * @param manifest
     */
    public static synchronized void addManifest(Manifest manifest) {
        manifests.put(manifest.getName(), manifest);
    }

    /**
     * Sets list of manifests to be used instead of current one.
     *
     * @param ml
     */
    public static synchronized void setManifests(List<Manifest> ml) {
        manifests.clear();
        for (Manifest manifest : ml) {
            manifests.put(manifest.getName(), manifest);
        }
    }

    /**
     * Returns manifest stored under the given name.
     *
     * @param manifestName
     * @return the manifest content
     */
    public static synchronized Manifest getManifest(String manifestName) {
        return manifests.get(manifestName);
    }

    /**
     * Removes manifest stored under the given name.
     *
     * @param manifestName
     */
    public static synchronized void removeManifest(String manifestName) {
        manifests.remove(manifestName);
    }
}
