AUI().add(
	'marketplace-drag-drop',
	function(A) {

		/**
		 * OPTIONS
		 *
		 * Required
		 * container {string|Node}: A selector or node containing the draggables.
		 * draggables {string}: A selector for draggable nodes.
		 * dropTargets {string}: A selector for drop targets.
		 */

		var MarketplaceDragDrop = A.Component.create(
			{
				EXTENDS: A.Base,

				NAME: 'marketplace-drag-drop',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._container = A.one(config.container);
						instance._draggables = config.draggables;
						instance._dropTargets = A.all(config.dropTargets);
						instance._selectable = config.selectable || false;

						instance._setupDragDropTargets();
						instance._setupDragDropEvents();

						if (instance._selectable) {
							instance._setupSelectableEvents();
						}
					},

					addDropTarget: function(node) {
						new A.DD.Drop(
							{
								node: node
							}
						);
					},

					_setupDragDropTargets: function() {
						var instance = this;

						instance._dd = new A.DD.Delegate(
							{
								container: instance._container,
								nodes: instance._draggables
							}
						);

						instance._dd.dd.plug(
							A.Plugin.DDProxy,
							{
								borderStyle: false
							}
						);

						instance._dropTargets.each(
							function(node) {
								instance.addDropTarget(node);
							}
						);
					},

					_setupDragDropEvents: function() {
						var instance = this;

						instance._dd.on(
							'drag:drophit',
							function(event) {
								var draggable = event.drag.get('node');
								var dropTarget = event.drop.get('node');

								var selectedDraggables = A.all(instance._draggables + '.selected');

								if (selectedDraggables.indexOf(draggable) < 0) {
									selectedDraggables.push(draggable);
								}

								selectedDraggables.removeClass('selected');

								dropTarget.append(selectedDraggables);

								instance.fire(
									'drophit',
									{
										draggables: selectedDraggables,
										dropTarget: dropTarget
									}
								);
							}
						);

						instance._dd.on(
							'drag:end',
							function(event) {
								var draggable = event.target.get('node');

								draggable.removeAttribute('style');
							}
						);

						instance._dd.dd.on(
							'drag:start',
							function(event) {
								var dd = event.currentTarget;

								var draggable = dd.get('node');
								var proxyDraggable = dd.get('dragNode');

								draggable.addClass('selected');

								proxyDraggable.set('innerHTML', '');

								proxyDraggable.append(draggable.clone());

								instance._container.all('.selected').each(
									function(selectedDraggable) {
										if (selectedDraggable !== draggable) {
											proxyDraggable.append(selectedDraggable.clone());
										}
									}
								);
							}
						);
					},

					_setupSelectableEvents: function() {
						var instance = this;

						instance._container.delegate(
							'click',
							function(event) {
								event.currentTarget.toggleClass('selected');
							},
							instance._draggables
						);
					}
				}
			}
		);

		Liferay.MarketplaceDragDrop = MarketplaceDragDrop;
	},
	'',
	{
		requires: ['aui-base', 'dd-delegate', 'dd-drop', 'dd-proxy']
	}
);