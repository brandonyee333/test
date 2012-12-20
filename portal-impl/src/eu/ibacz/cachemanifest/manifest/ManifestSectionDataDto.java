/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package eu.ibacz.cachemanifest.manifest;

/**
 * Object holding form data about Application Cache Manifest.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 */
public class ManifestSectionDataDto {

    private String cache;
    private String network;
    private String fallback;

    public ManifestSectionDataDto(String cache, String network, String fallback) {
        this.cache = cache;
        this.network = network;
        this.fallback = fallback;
    }

    public String getCache() {
        return cache;
    }

    public String getFallback() {
        return fallback;
    }

    public String getNetwork() {
        return network;
    }
}
