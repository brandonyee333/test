import {OrderedMap} from 'immutable';
import Row from './Row.js';

function List({data = new OrderedMap()}) {
	const rows = data.map((entry, i) => <Row entry={entry} key={`row-${i}`} />).toArray();

	return ({rows});
}

export default List;