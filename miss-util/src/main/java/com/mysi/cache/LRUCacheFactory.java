package com.mysi.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysi.commom.ConfigUtils;


/**
 * 把最近最少使用的数据移除，让给最新读取的数据，提高系统性能
 * @author XXM
 */
public class LRUCacheFactory implements CacheFactory {

	private static final Logger logger = LoggerFactory.getLogger(LRUCacheFactory.class);

	protected LRUCacheFactory() {
	}

	/**
	 * Construct a new instance of a LRUCache.
	 */
	@Override
	public Cache createCache(String id) {
		int size = ConfigUtils.getIntProperty("cache.size",10000);
		Cache cache = new LRUCacheImpl(id, size);
		logger.debug("new cache constructed. size=" + size);
		return cache;
	}

	@Override
	public Cache createCache(String id, int size, long timeout) {
		Cache cache = new LRUCacheImpl(id, size);
		logger.debug("new cache constructed. size=" + size);
		return cache;
	}
}
