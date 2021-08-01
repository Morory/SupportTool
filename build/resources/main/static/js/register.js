function chkMatter() {
    let title = document.getElementById('title').value;
    if(title === '') {
        alert('タイトルを入力してください');
        return;
    }

    document.getElementById('reset').style.display = 'none';
    document.getElementById('goback').style.display = 'inline';
    document.getElementById('chk').style.display = 'none';
    document.getElementById('submit').style.display = 'inline';
    document.getElementById('chk_comment').style.display = 'inline';
    document.getElementsByClassName('reg_table').item(0).style.paddingTop = '10px';

    document.getElementById('title').style.display = 'none';
    document.getElementById('chk_title').innerText = document.getElementById('title').value;
    document.getElementById('chk_title').style.display = 'inline';

    let option_value = document.getElementById('complete_status').selectedIndex;
    document.getElementById('complete_status').style.display = 'none';
    document.getElementById('chk_status').innerText = document.getElementById('complete_status').options[option_value].text;
    document.getElementById('chk_status').style.display = 'inline';

    document.getElementById('content').style.display = 'none';
    document.getElementById('chk_content').innerText = document.getElementById('content').value;
    document.getElementById('chk_content').style.display = 'inline';
}

function goBack() {
    document.getElementById('chk_comment').style.display = 'none';

    document.getElementById('chk_title').style.display = 'none';
    document.getElementById('chk_status').style.display = 'none';
    document.getElementById('chk_content').style.display = 'none';

    document.getElementById('title').style.display = 'inline';
    document.getElementById('complete_status').style.display = 'inline';
    document.getElementById('content').style.display = 'inline';

    document.getElementById('reset').innerText = 'リセット';

    document.getElementById('submit').style.display = 'none';
    document.getElementById('chk').style.display = 'inline';
    document.getElementById('goback').style.display = 'none';
    document.getElementById('reset').style.display = 'inline';
}

function lastChk() {
    document.getElementById('loading').style.display = 'inline';
}

document.addEventListener('keydown', function(event) {
    if (event.key === 'Enter' && event.target.type !== 'textarea' ) {
        event.preventDefault();
    }
}, true);