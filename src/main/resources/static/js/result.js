function initiate() {
    const xmlHttp = new XMLHttpRequest();
    const url = 'http://localhost:8080/chessboard/result/show';
    xmlHttp.onreadystatechange = function () {
        if (isValidHttpResponse(xmlHttp)) {
            const resultDTO = JSON.parse(this.responseText);
            updateResult(resultDTO);
        }
    }
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
}

function isValidHttpResponse(xmlHttp) {
    return xmlHttp.readyState === 4 && xmlHttp.status === 200;
}

function updateResult(resultDTO) {
    const blackTeamScore = resultDTO.blackTeamScore;
    const whiteTeamScore = resultDTO.whiteTeamScore;
    document.getElementById('black-team-score').innerText = '흑팀 : ' + blackTeamScore + '점';
    document.getElementById('white-team-score').innerText = '백팀 : ' + whiteTeamScore + '점';
    document.getElementById('winner-team').innerText = '우승팀 : ' + resultDTO.winnerTeamType;
}

initiate();
document.getElementById('restart').addEventListener('click', function (event) {
    window.location = '/';
});
