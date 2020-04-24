import Component from 'metal-jsx';
import {bindAll} from 'lodash';
import {Provider} from 'metal-redux';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import mockStore from '../../tests/mock-store';
import PageEditor from '../PageEditor';

class PageEditorKit extends Component {
	created() {
		bindAll(
			this,
			'handleCancel_',
			'handleSubmit_'
		);
	}

	handleCancel_() {
		alert('Cancel clicked!');
	}

	handleSubmit_({content, title}) {
		alert(`Your title is ${title}`);
	}

	render() {
		return (
			<Kit card={false} header="LoopPageEditor">
				<ElementContainer>
					<Provider store={mockStore()}>
						<PageEditor
							contentPlaceholder="Content Placeholder..."
							onCancel={this.handleCancel_}
							onSubmit={this.handleSubmit_}
							titlePlaceholder="Title (Required)"
						/>
					</Provider>
				</ElementContainer>
			</Kit>
		);
	}
}

export default PageEditorKit;