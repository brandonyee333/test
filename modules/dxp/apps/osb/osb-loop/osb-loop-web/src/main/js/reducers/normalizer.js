import {fromJS, Map} from 'immutable';

const {keys} = Object;

export default (state = Map(), action) => {
	const {entities} = action;

	let retVal = state;

	if (entities) {
		retVal = keys(entities).reduce(
			(finalResult, entity) => keys(entities[entity]).reduce(
				(result, id) => result.mergeIn(
					[entity, parseInt(id, 10)],
					{
						data: fromJS(
							{
								...result.getIn([entity, parseInt(id, 10), 'data'], Map()).toJS(),
								...entities[entity][id]
							}
						),
						loading: false
					}
				),
				finalResult
			),
			state
		);
	}

	return retVal;
};