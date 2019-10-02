let $chat = $('#chat');
let $txtMessage = $('#txtMessage');
let patientId = $('#patientId');
let doctorId = $('#doctorId');
let url = $('#url');

$txtMessage.on('keypress', function (e) {
    if (e.which === 13) {
        if (url.val().toString().indexOf("doctor") != -1) {
            sendMessage(doctorId.val(), "doctor");
        } else {
            sendMessage(patientId.val(), "patient");
        }
    }
});

let socket = new SockJS('/ws');
let stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    stompClient.subscribe('/user/message/' + patientId.val() + "&" + doctorId.val(), function (data)
    {
        let message = data.body;
        let $element;
        let $div;
        if (message.indexOf('@') == -1) {
            $element = $('<p/>');
            $div = $('<div/>')
                .append(message + ' is offline');
        } else {
            $element = $('<li/>');
            $div = $('<div/>')
                .append(message);
        }

        $chat.append($element.addClass('offline').append($div));
    });
});

function sendMessage(senderId, sender) {
    let message = $txtMessage.val();
    if (message.toString().trim() != '') {
        console.log(sender);
        send(message, senderId, sender);
    }
}

function send(message, senderId, sender) {
    let data = {
        "patientId": patientId.val(),
        "doctorId": doctorId.val(),
        "sender": sender,
        "senderId": senderId,
        "message": message
    };
    $.ajax('/api/send', {
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (response) {
            let $li = $('<li/>').addClass('me');
            let $div = $('<div/>')
                .append(message);

            $chat.append($li.append($div));
            $txtMessage.val('');
        }
    });
}