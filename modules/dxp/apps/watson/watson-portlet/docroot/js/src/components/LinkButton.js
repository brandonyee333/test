function LinkButton({className, download = '', href, label}) {
	return (
		<a class={`watson-button ${className || ''}`} download={download} href={href} >
			<span class="text">{label}</span>
		</a>
	);
}

export default LinkButton;