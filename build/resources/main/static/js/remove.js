document.getElementById('remove').addEventListener('click', remove);
document.getElementById('goback').addEventListener('click', goback);

function goback() {
    window.history.back();
}

function remove() {
    let at = confirm("本当に削除しますか?");
    if(at === false) {
        return;
    }

    let id = document.getElementById('matter_id').innerText;
    let url = "/matter/remove/" + id;

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(xhr.readyState === xhr.DONE) {
            if(xhr.status === 200 || xhr.status === 201) {
                alert("削除されました。");
                location.href = "/";
            } else {
                alert("削除に失敗しました。");
            }
        }
    };
    xhr.open('DELETE', url);
    xhr.send();
}