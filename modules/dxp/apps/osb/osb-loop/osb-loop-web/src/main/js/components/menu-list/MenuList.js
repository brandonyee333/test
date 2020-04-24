import Component, {Config} from 'metal-jsx';
import {noop} from 'lodash';

class MenuList extends Component {
	render() {
		const {children, hideOverlay} = this.props;

		return (
			<ul {...this.otherProps()} class="menu-list-container">
				{children.map(
					(child, i) => {
						child.props = {
							...child.props,
							hideOverlay,
							key: i
						};

						return child;
					}
				)}
			</ul>
		);
	}
}

MenuList.PROPS = {
	hideOverlay: Config.func().value(noop)
};

export default MenuList;