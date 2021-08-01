function toggleMenu() {
    let menuToggle = document.querySelector('.toggle');
    let navigation = document.querySelector('.navigation');
    menuToggle.classList.toggle('active');
    navigation.classList.toggle('active');
}

window.onload = function() {
    document.getElementById('loading').style.display = 'none';
}

let cre = document.getElementById('cre_date');
cre.innerText = cre.innerText.replace("T", " ");

let mod = document.getElementById('mod_date');
mod.innerText = mod.innerText.replace("T", " ");

let sta = document.getElementById('com_status');
if('N' === sta.innerText) {
    sta.innerText = '処理中';
} else if('Y' === sta.innerText) {
    sta.innerText = '処理完了';
} else {
    sta.innerText = 'その他';
}
