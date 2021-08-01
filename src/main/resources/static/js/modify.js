document.getElementById('modify').addEventListener('click', modify);
document.getElementById('goback').addEventListener('click', goback);

let comStatus = document.getElementById('com_status').value;

let select = document.getElementById('select_complete_status');

for(let i = 0; i < select.length; i++) {
    if(comStatus === select.options[i].value) {
        select.options[i].selected = true;
    }
}

function goback() {
    window.history.back();
}

function modify() {
    let at = confirm("本当に修正しますか?");
    if(at === false) {
        return;
    }
    let id = document.getElementById('matter_id').innerText;
    let title = document.getElementById('title').value;
    let select = document.getElementById('select_complete_status');
    let completeStatus = select.options[select.selectedIndex].value;
    let content = document.getElementById('content').value;

    let url = "/matter/modify/" + id;
    let xhr = new XMLHttpRequest();
    let data = {
        title: title,
        completeStatus: completeStatus,
        content: content
    };
    xhr.onreadystatechange = function() {
        if(xhr.readyState === xhr.DONE) {
            if(xhr.status === 200 || xhr.status === 201) {
                alert("修正されました。");
                location.href = "/matter/show/" + id;
            } else {
                alert("修正に失敗しました。");
            }
        }
    };
    xhr.open('PUT', url);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(data));
}