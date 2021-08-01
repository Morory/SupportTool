window.onload = function() {
    function hello() {
        let now = new Date();
        let hours = now.getHours();
        let comment;

        if(hours > 5 && hours < 11) {
            comment = 'おはようございます！';
        }
        else if(hours >= 11 && hours < 17) {
            comment = 'こんにちは！';
        }
        else {
            comment = 'こんばんは！';
        }

        document.getElementById('hello').innerText = comment;
    }

    hello();
}