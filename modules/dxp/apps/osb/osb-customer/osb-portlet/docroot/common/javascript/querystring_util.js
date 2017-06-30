AUI().add(
	'querystring-util',
	function(A) {
		var QueryStringUtil = {
			_anchorString: '',
			_keys: [],
			_queryString: '',
			_urlPath: '',
			_vals: []
		};

		QueryStringUtil.changeURL = function(refresh) {
			if (typeof refresh === 'undefined') {
				refresh = false;
			}

			var instance = this;

			var fullURL = instance._urlPath;

			if ((instance._keys.length > 0) && (instance._vals.length > 0) && (instance._keys.length == instance._vals.length)) {
				fullURL += '?';

				var tempQueryString = '';

				for (var i = 0; i < instance._keys.length; i++) {
					tempQueryString += instance._keys[i] + '=' + instance._vals[i] + '&';
				}

				instance._queryString = tempQueryString.substring(0, tempQueryString.length - 1);

				fullURL += instance._queryString;
			}

			if (instance._anchorString.length > 0) {
				fullURL += '#' + instance._anchorString;
			}

			if (refresh) {
				window.location = fullURL;
			}
			else {
				window.history.pushState({}, '', fullURL);
			}
		};

		QueryStringUtil.getParam = function(key) {
			var instance = this;

			if (instance._urlPath.length < 1) {
				instance.splitURL(document.URL);
			}

			if (instance._keys.length > 0) {
				for (var i = 0; i < instance._keys.length; i++) {
					if (instance._keys[i] == key) {
						return instance._vals[i];
					}
				}
			}
		}

		QueryStringUtil.removeParam = function(key, refresh) {
			var instance = this;

			if (instance._urlPath.length < 1) {
				instance.splitURL(document.URL);
			}

			if (instance._keys.length > 0) {
				for (var i = 0; i < instance._keys.length; i++) {
					if (instance._keys[i] == key) {
						instance._keys.splice(i, 1);
						instance._vals.splice(i, 1);
					}
				}
			}

			return instance.changeURL(refresh);
		};

		QueryStringUtil.splitURL = function(fullURL) {
			var instance = this;

			if (fullURL.length < 1) {
				fullURL = document.URL;
			}

			var questionMarkPos = fullURL.indexOf('?');

			if (questionMarkPos > -1) {
				instance._urlPath = fullURL.substring(0, questionMarkPos);

				instance._queryString = fullURL.substring(questionMarkPos + 1, fullURL.length);

				var anchorPos = instance._queryString.indexOf('#');

				if (anchorPos > -1) {
					instance._queryString = instance._queryString.substring(0, anchorPos);

					instance._anchorString = instance._queryString.substring(anchorPos + 1, instance._queryString.length);
				}

				if (instance._queryString.length > 3) {
					var params = instance._queryString.split('&');

					instance._keys = new Array(params.length);
					instance._vals = new Array(params.length);

					for (var i = 0; i < params.length; i++) {
						var paramArray = params[i].split('=');

						instance._keys[i] = paramArray[0];
						instance._vals[i] = paramArray[1];
					}
				}
			}
			else {
				var anchorPos = fullURL.indexOf('#');

				if (anchorPos > -1) {
					instance._urlPath = fullURL.substring(0, anchorPos);

					instance._anchorString = fullURL.substring(anchorPos + 1, fullURL.length);
				}
				else {
					instance._urlPath = fullURL;
				}
			}
		};

		QueryStringUtil.updateParam = function(key, val, refresh) {
			var instance = this;

			if (instance._urlPath.length < 1) {
				instance.splitURL(document.URL);
			}

			if (instance._keys.length < 1) {
				return;
			}
			else {
				for (var i = 0; i < instance._keys.length; i++) {
					if (instance._keys[i] == key) {
						instance._vals[i] = val;

						return instance.changeURL(refresh);
					}
				}

				instance._keys[instance._keys.length] = key;
				instance._vals[instance._vals.length] = val;

				return instance.changeURL(refresh);
			}
		};

		Liferay.QueryStringUtil = QueryStringUtil;
	}
);