/* global IncrementalDOM */

import JSXComponent, {Config} from 'metal-jsx';

class HTMLRenderer extends JSXComponent {
	render() {
		const {classNames, content = ''} = this.props;

		IncrementalDOM.elementOpen('div', null, null, 'class', classNames);

		const node = IncrementalDOM.elementClose('div');

		node.innerHTML = content;

		return node;
	}
}

HTMLRenderer.PROPS = {
	classNames: Config.string().value(''),
	content: Config.string().value('')
};

export default HTMLRenderer;