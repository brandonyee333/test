package com.liferay.portal.cache.ehcache;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SocketUtil.ServerSocketConfigurator;
import com.liferay.portal.kernel.util.SocketUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

import java.nio.channels.ServerSocketChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
public class EhcacheStreamBootstrapHelpUtil {

	public static void acquireCachePeers(Ehcache cache) throws Exception {
		List<Address> clusterNodeAddresses =
			ClusterExecutorUtil.getClusterNodeAddresses();

		System.out.println(clusterNodeAddresses);

		int clusterNodeAddressesCount = clusterNodeAddresses.size();

		if (clusterNodeAddressesCount <= 1) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Can not get caches because there is either one portal" +
					" instance or no portal instances in the cluster");
			}
		}
		else {
			loadCachesFromCluster(cache);
		}
	}

	public static ServerSocket createServerSocket(int startPort)
			throws Exception {

		InetAddress inetAddress = InetAddressUtil.getLocalInetAddress();

		ServerSocketConfigurator serverSocketConfigurator =
			new SocketCacheServerSocketConfiguration();

		ServerSocketChannel serverSocketChannel =
			SocketUtil.createServerSocketChannel(
				inetAddress, startPort, serverSocketConfigurator);

		ServerSocket serverSocket = serverSocketChannel.socket();

		return serverSocket;
	}

	public static Element toEhcacheElement(
		LiferayEhcacheElement liferayEhcacheElementHelper) {

		Object key = liferayEhcacheElementHelper.getKey();
		Object value = liferayEhcacheElementHelper.getValue();

		return new Element(key, value);
	}

	public static SocketAddress createServerSocketFromCluster(String cacheName)
			throws Exception {

		System.out.println("remote node executed");

		ServerSocket serverSocket = createServerSocket(startPort);

		CacheStreamRunnable cacheStreamRunnable = new CacheStreamRunnable(
			serverSocket, cacheName);

		Thread thread = new Thread(cacheStreamRunnable);
		thread.setDaemon(true);
		thread.start();

		return serverSocket.getLocalSocketAddress();
	}

	protected static void loadCachesFromCluster(Ehcache cache)
			throws Exception {

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			new MethodHandler(
				_createServerSocketFromClusterMethodKey, cache.getName()),
				true);

		FutureClusterResponses futureClusterResponses =
			ClusterExecutorUtil.execute(clusterRequest);

		BlockingQueue<ClusterNodeResponse> clusterNodeResponses =
			futureClusterResponses.getPartialResults();

		Socket socket = null;

		try {
			ClusterNodeResponse clusterNodeResponse = clusterNodeResponses.poll(
				_BOOTUP_CLUSTER_NODE_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

			SocketAddress remoteSocketAddress =
				(SocketAddress)clusterNodeResponse.getResult();

			if (Validator.isNotNull(remoteSocketAddress)) {
				socket = new Socket();
				socket.connect(remoteSocketAddress);
				socket.shutdownOutput();
				
				ObjectInputStream objectInputStream = new ObjectInputStream(
					socket.getInputStream());

				while(true) {
					//read cache from remote
					Object object = objectInputStream.readObject();

					if (object instanceof List){
						List<LiferayEhcacheElement> liferayEhcacheElements =
							(List<LiferayEhcacheElement>)object;

						for(LiferayEhcacheElement liferayEhcacheElement :
							liferayEhcacheElements) {

							Element element =
								toEhcacheElement(liferayEhcacheElement);

							cache.put(element, true);
						}
					}
					else if(object instanceof String) {
						if(((String)object).equals(_socket_close)) {

							break;
						}
						else if(((String)object).equals(_cache_tx_start)) {

							String cacheName = 
								(String)objectInputStream.readObject();

							if(!cacheName.equals(cache.getName())) {
								break;
							}
						}
					}
					else {
						throw new SystemException("Socket transaction failed"); 
					}
				}
				objectInputStream.close();
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			socket.close();
		}
	}

	private static final long _BOOTUP_CLUSTER_NODE_RESPONSE_TIMEOUT = 1000000;

	private static final String _cache_tx_start = "${CACHE_TX_START}";
	
	private static final String _socket_close = "${SOCKET_CLOSE}";

	private static final String _MULTI_VM_PORTAL_CACHE_MANAGER_BEAN_NAME =
		"com.liferay.portal.kernel.cache.MultiVMPortalCacheManager";

	private static Log _log = LogFactoryUtil.getLog(
		EhcacheStreamBootstrapHelpUtil.class);

	private static MethodKey _createServerSocketFromClusterMethodKey =
		new MethodKey(
			EhcacheStreamBootstrapHelpUtil.class,
			"createServerSocketFromCluster", String.class);

	private final static int soTimeout =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.EHCACHE_SOCKET_SO_TIMEOUT));

	private final static int startPort =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.EHCACHE_SOCKET_START_PORT));

	private static class LiferayEhcacheElement {
		
		public LiferayEhcacheElement (Object key, Object value) {
			this._key = key;
			this._value = value;
		}		

		public Object getKey() {
			return _key;
		}

		
		public Object getValue() {
			return _value;
		}

		private Object _key;
		private Object _value;
		
	}

	private static class CacheStreamRunnable implements Runnable {

		public CacheStreamRunnable(ServerSocket serverSocket, String cacheName) {
			_serverSocket = serverSocket;
			_cacheName = cacheName;
			EhcachePortalCacheManager ehcachePortalCacheManager =
				(EhcachePortalCacheManager)PortalBeanLocatorUtil.locate(
					_MULTI_VM_PORTAL_CACHE_MANAGER_BEAN_NAME);
	
			 _portalCacheManager =
				ehcachePortalCacheManager.getEhcacheManager();
		}

		public void run() {
			Socket socket = null;
			try {
				socket = _serverSocket.accept();
				socket.shutdownInput();

				ObjectOutputStream objectOutputStream =
					new ObjectOutputStream(socket.getOutputStream());

				Ehcache ehcache = _portalCacheManager.getCache(_cacheName);

				if (Validator.isNotNull(ehcache)) {
					List<Object> keys = ehcache.getKeys();

					List<LiferayEhcacheElement> liferayEhcacheElements =
						new ArrayList<LiferayEhcacheElement>();

					for(Object key : keys) {
						if(!(key instanceof Serializable)) {
							continue;
						}

						Element element = ehcache.get(key);
						Object value = element.getValue();

						if(!(value instanceof Serializable)) {
							continue;
						}

						liferayEhcacheElements.add(
							new LiferayEhcacheElement(key, value));
					}

					objectOutputStream.writeObject(_cache_tx_start);
					objectOutputStream.writeObject(_cacheName);

					objectOutputStream.writeObject(liferayEhcacheElements);
				}
				else {
					_portalCacheManager.addCache(_cacheName);
				}

				objectOutputStream.writeObject(_socket_close);

				objectOutputStream.close();
			}
			catch (Exception e) {
				try {
					throw new Exception(e);
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			finally {
				if (socket != null) {
					try {
						socket.close();
					}
					catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				if(_serverSocket != null) {
					try {
						_serverSocket.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e);
					}
				}
			}
		}

		private ServerSocket _serverSocket;
		private String _cacheName;
		private CacheManager _portalCacheManager;
	}

	private static class SocketCacheServerSocketConfiguration
		implements ServerSocketConfigurator {

		@Override
		public void configure(ServerSocket serverSocket)
				throws SocketException {

			serverSocket.setSoTimeout(soTimeout);
		}
	}

}