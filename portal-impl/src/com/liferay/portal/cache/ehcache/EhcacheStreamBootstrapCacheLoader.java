package com.liferay.portal.cache.ehcache;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.bootstrap.BootstrapCacheLoader;
public class EhcacheStreamBootstrapCacheLoader implements BootstrapCacheLoader {

	public Object clone() throws CloneNotSupportedException {
		return new EhcacheStreamBootstrapCacheLoader();
	}

	@Override
	public boolean isAsynchronous() {
		return false;
	}

	@Override
	public void load(Ehcache cache) throws CacheException {
		if (_log.isDebugEnabled()) {
			_log.debug("Bootstraping " + cache + " " + hashCode());
		}

		try {
			EhcacheStreamBootstrapHelpUtil.acquireCachePeers(cache);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		EhcacheStreamBootstrapCacheLoader.class);
}