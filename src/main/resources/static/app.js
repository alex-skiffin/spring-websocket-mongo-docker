var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/questions');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/question', function (question) {
            showQuestion(JSON.parse(question.body));
        });
        stompClient.subscribe('/topic/like', function (question) {
            UpdateLike(JSON.parse(question.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendQuestion() {
    stompClient.send("/app/question.send", {}, JSON.stringify({'content': $("#question").val()}));
}

function sendLike(id) {
    stompClient.send("/app/question.like", {}, JSON.stringify({'id': id}));
}

function showQuestion(question) {
    $("#questions").append("<tr id='"+question.id+"' like-count='"
        + question.likeCount+"'><td>" + question.content
        + "<button onclick='sendLike(\""+question.id+"\")'>like " +
        + question.likeCount + "</button> </td></tr>");
}

function UpdateLike(question) {
    $('#'+question.id).remove();
    showQuestion(question);
    //sort();
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendQuestion(); });
});

