/* global IncrementalDOM */

import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

class MarkdownContent extends Component {
	render() {
		const {inline, message} = this.props;

		const classNames = getCN(
			'markdown-content-container',
			{inline}
		);

		IncrementalDOM.elementOpen('div', null, null, 'class', classNames);

		const node = IncrementalDOM.elementClose('div');

		node.innerHTML = message;

		return node;
	}
}

MarkdownContent.PROPS = {
	inline: Config.bool(),
	message: Config.string()
};

export default MarkdownContent;