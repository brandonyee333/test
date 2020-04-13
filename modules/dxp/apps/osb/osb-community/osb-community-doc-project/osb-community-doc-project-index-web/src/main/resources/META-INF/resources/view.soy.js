(function() {
	class LiveSearch {
		constructor() {
			var instance = this;

			var liveSearchContainer = document.querySelector('.live-search-filter');

			var liveSearchInput = liveSearchContainer.querySelector('.input');

			instance.inputNode = liveSearchInput;
			instance.parentNode = document.querySelectorAll('.doc-project li');
			instance.counterNode = liveSearchContainer.querySelector('.count');
			instance.counter = 0;

			instance._attachListeners();
		}

		render() {
			var instance = this;

			var query = instance.inputNode.value;

			instance._filter(query);
			instance._setNodes();
			instance._updateCounter();
		}

		_attachListeners() {
			var instance = this;

			instance.inputNode.addEventListener(
				'keyup',
				function(event) {
					instance.render();
				}
			);
		}

		_filter(query) {
			var instance = this;

			var nodes = instance.parentNode;

			var normalizeQuery = instance._normalizeQuery(query);

			var counter = 0;

			var results = [];

			_.each(
				nodes,
				function(node) {
					var name = node.querySelector('.name');
					var text = instance._normalizeQuery(name.innerText);

					var match = false;

					if (text.search(normalizeQuery) != -1) {
						match = true;

						counter = counter + 1;
					}
					else {
						var tags = node.querySelector('.doc-project-tags');

						var tagText = instance._normalizeQuery(tags.innerText);

						if (tagText.search(normalizeQuery) != -1) {
							match = true;

							counter = counter + 1;
						}
					}

					results.push(
						{
							node: node,
							match: match
						}
					);
				}
			);

			instance.counter = counter;
			instance.results = results;
		}

		_normalizeQuery(query) {
			return query.trim().toLowerCase();
		}

		_setNodes() {
			var instance = this;

			var results = instance.results;

			_.each(
				results,
				function(result) {
					if (result.match) {
						result.node.classList.remove('hide');
					}
					else {
						result.node.classList.add('hide');
					}
				}
			)
		}

		_updateCounter() {
			var instance = this;

			var counter = instance.counter;

			instance.counterNode.innerText = counter + ' ' + ((counter <= 1) ? 'Project' : 'Projects');
		}
	}

	window.liveSearch = new LiveSearch();
})(this);