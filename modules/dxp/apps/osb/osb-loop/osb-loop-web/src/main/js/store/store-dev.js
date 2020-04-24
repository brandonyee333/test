import {compose, createStore} from 'redux';

import middleware from './configure-middleware';
import reducers from '../reducers';

export default function configureStore(initialState) {
	return createStore(
		reducers,
		initialState,
		compose(
			middleware,
			window.devToolsExtension ? window.devToolsExtension() : f => f
		)
	);
}