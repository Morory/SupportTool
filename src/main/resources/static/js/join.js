document.addEventListener('keydown', function(event) {
    if (event.key === 'Enter' && event.target.type !== 'textarea' ) {
        event.preventDefault();
    }
}, true);