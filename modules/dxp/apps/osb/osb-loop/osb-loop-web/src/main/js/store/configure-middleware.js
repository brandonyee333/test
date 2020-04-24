import thunk from 'redux-thunk';
import {applyMiddleware} from 'redux';

import api from '../middleware/api';
import include from '../middleware/include';
import normalizer from '../middleware/normalizer';
import posts from '../middleware/posts';

const middleware = applyMiddleware(
	api,
	posts,
	include,
	normalizer,
	thunk
);

export default middleware;