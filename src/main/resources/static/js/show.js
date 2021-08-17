document.getElementById('complete').addEventListener('click', complete);

function complete() {
    let at = confirm("本当に完了処理しますか？");
    if (at === false) {
        return;
    }

    let id = document.getElementById('matter_id').innerText;

    let url = "/matter/modify/" + id;
    let xhr = new XMLHttpRequest();
    let data = {
        completeStatus: 'Y',
    };
    xhr.onreadystatechange = function () {
        if (xhr.readyState === xhr.DONE) {
            if (xhr.status === 200 || xhr.status === 201) {
                alert("完了処理されました。");
                location.href = "/matter/show/" + id;
            } else {
                alert("完了処理に失敗しました。");
            }
        }
    };
    xhr.open('PUT', url);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(data));
}