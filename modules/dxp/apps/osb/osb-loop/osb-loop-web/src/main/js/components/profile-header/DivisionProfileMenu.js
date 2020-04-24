import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import MenuList from '../menu-list';
import {getRel} from '../../lib/selectors';
import {getTypeLabel, lang} from '../../lib/lang-util';
import {modalTypes, showModal} from '../../actions/modals';

class DivisionProfileMenu extends Component {
	created() {
		bindAll(
			this,
			'handleChildrenModal_',
			'handleConvertDivisionTypeModal_',
			'handleParentModal_'
		);
	}

	getChildContext() {
		const {onMouseInside, onMouseLeave} = this.props;

		return {
			onOverlayLeave: onMouseLeave,
			onOverlayOver: onMouseInside
		};
	}

	handleChildrenModal_() {
		const {divisionIMap, showModal} = this.props;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					division: divisionIMap.toJS()
				},
				modalType: modalTypes.ASSIGN_CHILDREN
			}
		);
	}

	handleConvertDivisionTypeModal_() {
		const {divisionIMap, showModal} = this.props;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					division: divisionIMap.toJS()
				},
				modalType: modalTypes.CONVERT_DIVISION_TYPE
			}
		);
	}

	handleParentModal_() {
		const {divisionIMap, showModal} = this.props;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					division: divisionIMap.toJS()
				},
				modalType: modalTypes.ASSIGN_PARENT
			}
		);
	}

	render() {
		const {
			displayURL,
			permissionEdit,
			permissionSetChildLoopDivisions,
			permissionSetType,
			type
		} = this.props.divisionIMap.toJS();

		return (
			<MenuList>
				{permissionEdit &&
					<MenuList.Item href={`${displayURL}/edit`}>
						{Liferay.Language.get('edit-group')}
					</MenuList.Item>
				}

				{permissionSetType &&
					<MenuList.Item onClick={this.handleConvertDivisionTypeModal_}>
						{Liferay.Language.get('convert-type')}
					</MenuList.Item>
				}

				{permissionSetChildLoopDivisions &&
					<MenuList.Item onClick={this.handleParentModal_}>
						{lang(Liferay.Language.get('assign-parent-x'), [getTypeLabel(type)])}
					</MenuList.Item>
				}

				{permissionSetChildLoopDivisions &&
					<MenuList.Item onClick={this.handleChildrenModal_}>
						{lang(Liferay.Language.get('assign-child-x'), [getTypeLabel(type, true)])}
					</MenuList.Item>
				}
			</MenuList>
		);
	}
}

const STORE = {
	divisionIMap: Config.instanceOf(Map),
	showModal: Config.func()
};

DivisionProfileMenu.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, ownProps) => (
		{
			divisionIMap: getRel('divisions', state, ownProps.id, Map())
		}
	),
	{
		showModal
	}
)(DivisionProfileMenu);