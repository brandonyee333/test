/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package com.liferay.portal.cachemanifest.manifest;

/**
 * Object holding form data about Application Cache Manifest.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 */
public class ManifestSectionDataDto {

    public ManifestSectionDataDto(String cache, String network, String fallback) {
        this._cache = cache;
        this._network = network;
        this._fallback = fallback;
    }

    public String getCache() {
        return _cache;
    }

    public String getFallback() {
        return _fallback;
    }

    public String getNetwork() {
        return _network;
    }

    private String _cache;
    private String _network;
    private String _fallback;
}
