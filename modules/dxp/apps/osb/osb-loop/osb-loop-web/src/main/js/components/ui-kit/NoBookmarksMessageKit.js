import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import NoBookmarksMessage from '../NoBookmarksMessage';

class NoBookmarksMessageKit extends Component {
	render() {
		return (
			<Kit header="NoBookmarksMessage">
				<ElementContainer>
					<NoBookmarksMessage />
				</ElementContainer>
			</Kit>
		);
	}
}

export default NoBookmarksMessageKit;