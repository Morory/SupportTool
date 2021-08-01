document.addEventListener('DOMContentLoaded', getListByAjax);

let size;
document.querySelector('.size_btn select').addEventListener('change', sizeChange, false);

function sizeChange() {
    size = this.options[this.selectedIndex].value;
    getListByAjax();
}

function pageMove() {
    let pageNum = parseInt(this.querySelector('span').innerText);
    getListByAjax(pageNum);
}

function pageMovePrevOrNext() {
    let thisPageNum = parseInt(document.querySelector('.selected span').innerText);
    let pageNum;
    if(this.id === 'page-prev') {
        pageNum = thisPageNum - 1;
    } else {
        pageNum = thisPageNum + 1;
    }
    getListByAjax(pageNum);
}

function getListByAjax(pageNum) {
    let url;
    if(typeof pageNum === 'number') {
        url = "/matter/list?size=" + size + "&page=" + pageNum;
    } else {
        url = "/matter/list?size=" + size;
    }
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(xhr.readyState === xhr.DONE) {
            if(xhr.status === 200 || xhr.status === 201) {
                console.log(xhr.responseText);
                setList(xhr.responseText);
            } else {
                console.error(xhr.responseText);
            }
        }
    };
    xhr.open('GET', url);
    xhr.send();
}

function setList(responseText) {
    let obj = JSON.parse(responseText);

    // table 테이블 처리
    let table = document.querySelector('.pm_table_row_group');
    table.innerHTML = "";

    let list = obj.content;
    for(let i = 0; i < list.length ; i++) {
        let problem = list[i];

        let row = document.createElement('div');
        row.className = 'pm_table_row';

        let id = document.createElement('div');
        id.className = 'pm_table_cell';
        id.innerText = problem.id;
        row.append(id);

        let title = document.createElement('div');
        title.className = 'pm_table_cell';
        let a = document.createElement('a');
        a.innerText = problem.title;
        a.href = '/matter/show/' + problem.id;
        title.append(a);
        row.append(title);

        let username = document.createElement('div');
        username.className = 'pm_table_cell';
        username.innerText = problem.username;
        row.append(username);

        let updatedDate = document.createElement('div');
        updatedDate.className = 'pm_table_cell';
        updatedDate.innerText = problem.modifiedDate;
        row.append(updatedDate);

        let completeStatus = document.createElement('div');
        completeStatus.className = 'pm_table_cell';
        if('N' === problem.completeStatus.toString()) {
            completeStatus.innerText = '処理中';
        } else if('Y' === problem.completeStatus.toString()) {
            completeStatus.innerText = '処理完了';
        } else {
            completeStatus.innerText = 'その他';
        }
        row.append(completeStatus);

        table.append(row);
    }

    // pagination 페이지 처리
    let totalPages = parseInt(obj.totalPages);
    let thisPage = parseInt(obj.number) + 1;
    let fcList = document.querySelector('.fc-list');
    let totalPageFloor = Math.floor(totalPages / 10);
    let thisPageFloor = Math.floor(thisPage / 10);
    let startPageOfThisPageFloor;
    let lastPageOfThisPageFloor;
    let prev = document.getElementById('page-prev');
    let next = document.getElementById('page-next');

    fcList.innerHTML = "";
    prev.className = "";
    next.className = "";


    if (thisPageFloor === 0) {
        startPageOfThisPageFloor = 1;
    } else {
        startPageOfThisPageFloor = thisPageFloor * 10;
    }
    if(thisPageFloor === totalPageFloor) {
        lastPageOfThisPageFloor = totalPages;
    } else {
        lastPageOfThisPageFloor = thisPageFloor * 10 + 9;
    }

    if(thisPageFloor > 0) {
        let first = document.createElement('li');
        let firstSpan = document.createElement('span');
        firstSpan.innerText = '1';
        first.append(firstSpan);
        first.className = 'available';
        first.onclick = pageMove;
        fcList.append(first);

        let ec = document.createElement('li');
        let ecSpan = document.createElement('span');
        ecSpan.innerText = '..';
        ec.append(ecSpan);
        ec.className = 'ellipsis';
        fcList.append(ec);
    }

    for(let i = startPageOfThisPageFloor; i <= lastPageOfThisPageFloor; i++) {
        let li = document.createElement('li');
        let span = document.createElement('span');
        span.innerText = i.toString();
        li.append(span);

        if(i === thisPage) {
            li.className = 'selected';
        } else {
            li.className = 'available';
            li.onclick = pageMove;
        }
        fcList.append(li);
    }

    if(totalPageFloor > thisPageFloor) {
        let ec = document.createElement('li');
        let ecSpan = document.createElement('span');
        ecSpan.innerText = '..';
        ec.append(ecSpan);
        ec.className = 'ellipsis';
        fcList.append(ec);

        let final = document.createElement('li');
        let finalSpan = document.createElement('span');
        finalSpan.innerText = totalPages.toString();
        final.append(finalSpan);
        final.className = 'available';
        final.onclick = pageMove;
        fcList.append(final);
    }

    if('true' === obj.first.toString() ) {
        prev.className = 'unavailable';
    } else {
        prev.className = 'available';
        prev.onclick = pageMovePrevOrNext;
    }
    if('true' === obj.last.toString()) {
        next.className = 'unavailable';
    } else {
        next.className = 'available';
        next.onclick = pageMovePrevOrNext;
    }
}