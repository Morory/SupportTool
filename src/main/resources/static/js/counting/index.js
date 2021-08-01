google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    let completeNum = parseInt(document.getElementById('complete').value);
    let uncompleteNum = parseInt(document.getElementById('uncomplete').value);
    let etcNum = parseInt(document.getElementById('etc').value);

    let data = google.visualization.arrayToDataTable([
        ['Status', 'Num'],
        ['処理完了', completeNum],
        ['未処理', uncompleteNum],
        ['その外', etcNum]
    ]);

    let options = {
        width: 700,
        height: 400,
        title: '集計グラフ',
        is3D: true,
        colors: ['#4179ef', '#e6693e', '#f6c7b6']
    };

    let chart = new google.visualization.PieChart(document.getElementById('chart'));
    chart.draw(data, options);
}

let date = document.getElementById('date').value;
let latestDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
date.innerText = latestDate;

const instance = flatpickr('#selectedDate', {
    dateFormat: "Y-m-d",
    defaultDate: latestDate.toString(),
    maxDate: "today",
    locale: "ja",
    onChange: function(dateStr) {
        let selected = new Date(dateStr);
        document.getElementById('date').value = getFormatDate(selected);
        getData(getFormatDate(selected));
    }
});

function getData(selected) {
    let url = "/counting/show/" + selected;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if(xhr.readyState === xhr.DONE) {
            if(xhr.status === 200 || xhr.status === 201) {
                console.log(xhr.responseText);
                setData(xhr.responseText);
            } else {
                console.error(xhr.responseText);
                alert("データがありません");
            }
        }
    };
    xhr.open('GET', url);
    xhr.send();
}

function setData(responseText) {
    let obj = JSON.parse(responseText);

    document.getElementById('complete').value = obj.completedAmount;
    document.getElementById('uncomplete').value = obj.uncompletedAmount;
    document.getElementById('etc').value = obj.etcAmount;
    document.getElementById('dailyTotal').innerText = obj.dailyTotal;
    document.getElementById('completedAmount').innerText = obj.completedAmount;
    document.getElementById('uncompletedAmount').innerText = obj.uncompletedAmount;
    document.getElementById('etcAmount').innerText = obj.etcAmount;

    document.getElementById('chart').innerHTML = "";
    drawChart();
}

function getFormatDate(date){
    let year = date.getFullYear();              //yyyy
    let month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    let day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '' + month + '' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}