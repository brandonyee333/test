import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import UndoCard from '../UndoCard';
import {bindAll} from 'lodash';
import {lang} from '../../lib/lang-util';

class UndoCardKit extends Component {
	created() {
		bindAll(
			this,
			'handleUndoCardClose_',
			'handleUndoCardUndo_'
		);
	}

	handleUndoCardClose_() {
		alert('This is the close event.');
	}

	handleUndoCardUndo_() {
		alert('This is the undo event.');
	}

	render() {
		return (
			<Kit card={false} header="UndoCard">
				<ElementContainer header="Short Message">
					<UndoCard message="You just unfollowed." onClose={this.handleUndoCardClose_} onUndo={this.handleUndoCardUndo_} />
				</ElementContainer>

				<ElementContainer header="Long Message">
					<UndoCard message="This is a very long message that should wrap and show us how the layout looks if the message ends up on multiple lines." onClose={this.handleUndoCardClose_} onUndo={this.handleUndoCardUndo_} />
				</ElementContainer>

				<ElementContainer header="Message with Link">
					<UndoCard
						message={
							lang(
								Liferay.Language.get('you-just-unfollowed-a-x-from-the-following-stream'),
								[<a href="javascript:;" key={0}>{Liferay.Language.get('post[asset-entry-set]')}</a>]
							)
						}
						onClose={this.handleUndoCardClose_}
						onUndo={this.handleUndoCardUndo_}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default UndoCardKit;