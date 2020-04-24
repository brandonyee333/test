jest.unmock('../../lib/draft-dependencies');

import Editor from '../Editor';
import sendRequest from '../../lib/request';

describe(
	'Editor',
	() => {
		let component;

		window.getSelection = jest.fn(() => ({}));

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new Editor();

				expect(component.element).toBeTruthy();
			}
		);

		it(
			'should call onchange callback with editorState',
			() => {
				const onChangeMock = jest.fn();

				component = new Editor(
					{
						onChange: onChangeMock
					}
				);

				component.addBaseDecorators_();

				expect(onChangeMock.mock.calls[0]).not.toBeNull();
			}
		);

		it(
			'should set index to 0 and open to false',
			() => {
				component = new Editor();

				component.setState(
					{
						highlightedIndex_: 4,
						open_: true
					}
				);

				jest.runAllTimers();

				component.closeMenu_();

				expect(component.state.open_).toBeFalsy();
				expect(component.state.highlightedIndex_).toBe(0);
			}
		);

		it(
			'should return two decorators',
			() => {
				component = new Editor();

				const decorator = component.createDecorators_();

				expect(decorator.constructor.name).toEqual('CompositeDraftDecorator');
			}
		);

		it(
			'should focus draft.js Editor component',
			() => {
				const spy = jest.fn();

				component = new Editor();

				component.components.editor.getInstance().focus = spy;

				component.focus();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should return an object of styles with keys of top',
			() => {
				component = new Editor();

				const styles = component.getMenuStyles_();

				expect(Object.keys(styles)).toEqual(['top']);
			}
		);

		it(
			'should return instance of SelectionState',
			() => {
				component = new Editor();

				const selection = component.getNewSelection_();

				expect(selection.constructor.name).toEqual('SelectionState');
			}
		);

		it(
			'should request autocomplete results if a query and queryStart are passed',
			() => {
				component = new Editor();

				expect(sendRequest).not.toBeCalled();

				component.handleAutocomplete_('test test', 0);

				expect(sendRequest).toBeCalled();
			}
		);

		it(
			'should setState open:false if a query and queryStart are not passed',
			() => {
				component = new Editor();

				component.handleAutocomplete_(null, null);

				expect(component.open_).toBeFalsy();
			}
		);

		it(
			'should call selectItem',
			() => {
				const selectItemSpy = jest.fn();

				component = new Editor();

				component.selectItem_ = selectItemSpy;

				component.handleAutocompleteMenuClick_({data: {}});

				expect(selectItemSpy).toBeCalled();
			}
		);

		it(
			'should set ignoreBlur to false',
			() => {
				component = new Editor();

				component._ignoreBlur = false;

				component.handleAutocompleteMenuMouseDown_();

				expect(component._ignoreBlur).toBe(true);
			}
		);

		it(
			'should set highlightedIndex_ to value',
			() => {
				component = new Editor();

				component.handleAutocompleteMenuMouseEnter_(1);

				expect(component.state.highlightedIndex_).toBe(1);

				component.handleAutocompleteMenuMouseEnter_(0);

				expect(component.state.highlightedIndex_).toBe(0);
			}
		);

		it(
			'should call onBlur and closeMenu if ignoreBlur is false',
			() => {
				const closeMenuSpy = jest.fn();
				const onBlurSpy = jest.fn();

				component = new Editor(
					{
						onBlur: onBlurSpy
					}
				);

				component.closeMenu_ = closeMenuSpy;

				component._ignoreBlur = true;
				component.handleBlur_();

				expect(onBlurSpy).toBeCalled();
				expect(closeMenuSpy).not.toBeCalled();

				component._ignoreBlur = false;
				component.handleBlur_();

				expect(onBlurSpy).toBeCalled();
				expect(closeMenuSpy).toBeCalled();
			}
		);

		it(
			'should set index to 0 if and escape/downArrow/upArrow are not pressed',
			() => {
				component = new Editor();

				component.handleKeys_({keycode: null});

				expect(component.state.highlightedIndex_).toBe(0);
			}
		);

		it(
			'should move index down one number',
			() => {
				component = new Editor();

				component.setState(
					{
						highlightedIndex_: 2,
						open_: true
					}
				);

				component.handleKeys_(
					{
						keyCode: 38,
						preventDefault: jest.fn()
					}
				);

				expect(component.state.highlightedIndex_).toEqual(1);
			}
		);

		it(
			'should move index up one number',
			() => {
				component = new Editor();

				component.setState(
					{
						highlightedIndex_: 2,
						open_: true
					}
				);

				component.handleKeys_(
					{
						keyCode: 40,
						preventDefault: jest.fn()
					}
				);

				expect(component.state.highlightedIndex_).toEqual(3);
			}
		);

		it(
			'should set index to 0',
			() => {
				component = new Editor();

				component.setState(
					{
						highlightedIndex_: 2,
						open_: true
					}
				);

				component.handleKeys_(
					{
						keyCode: 27,
						preventDefault: jest.fn()
					}
				);

				expect(component.state.highlightedIndex_).toBe(0);
			}
		);

		it(
			'should return true when open_ is true',
			() => {
				component = new Editor();

				component.state.autocompleteResults_ = [
					{
						data: {
							name: 'foo'
						}
					}
				];

				component.state.highlightedIndex_ = 0;
				component.state.open_ = true;

				const retVal = component.handleReturn_();

				expect(retVal).toBe(true);
			}
		);

		it(
			'should call onEnter and blur editor when open_ is false',
			() => {
				const blurSpy = jest.fn();
				const onEnterSpy = jest.fn(() => 'onEnterReturn');

				component = new Editor(
					{
						onEnter: onEnterSpy
					}
				);

				component.state.open_ = false;

				const editorInstance = component.components.editor.getInstance();

				editorInstance._realBlur = editorInstance.blur;

				editorInstance.blur = blurSpy;

				const retVal = component.handleReturn_();

				expect(blurSpy).toBeCalled();
				expect(onEnterSpy).toBeCalled();
				expect(retVal).toBe('onEnterReturn');

				editorInstance.blur = editorInstance._realBlur;

				delete editorInstance._realBlur;
			}
		);

		it(
			'should call closeMenu_',
			() => {
				const spy = jest.fn();

				component = new Editor();

				component.closeMenu_ = spy;

				component.handleAutocompleteMenuClickOutside_();

				expect(spy).toBeCalled();
			}
		);
	}
);