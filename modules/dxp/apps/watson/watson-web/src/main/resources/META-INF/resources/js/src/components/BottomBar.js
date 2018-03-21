import {noop} from 'lodash';

import Button from './Button';

function BottomBar({buttons, formIsValid, handleSubmit, message, messageCssClass = '', optionalButtons}) {
	const renderedButtons = buttons.map(
		(item, index) => {
			let {
				action: buttonAction,
				className: buttonCssClass
			} = item;

			const {label, submitButton} = item;

			if (submitButton) {
				buttonAction = handleSubmit;

				if (!formIsValid) {
					buttonAction = noop;
					buttonCssClass += ' disabled';
				}
			}

			return <Button className={buttonCssClass} key={`btn-${index}`} label={label} onClick={buttonAction} />;
		}
	);

	return (
		<div class="bottom-bar">
			<div class={`bottom-bar-message ${messageCssClass}`}>{message}</div>

			<div class="buttons">
				{optionalButtons}
				{renderedButtons}
			</div>
		</div>
	);
}

export default BottomBar;