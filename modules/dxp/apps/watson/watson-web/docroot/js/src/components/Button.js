function Button(props) {
	const {className = '', icon, label, onClick, ...otherProps} = props;

	delete otherProps.children;

	return (
		<button {...otherProps} class={`watson-button ${className}`} data-onclick={onClick}>
			{icon &&
				<span class={`icon ${icon}`} />
			}

			<span>{label}</span>
		</button>
	);
}

export default Button;