let $chat = $('#chat');
let $txtMessage = $('#txtMessage');
let patientId = $('#patientId')
let doctorId = $('#doctorId')

$txtMessage.on('keypress', function (e) {
    if (e.which === 13) {
        sendMessage();
    }
});

let socket = new SockJS('/ws');
let stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    stompClient.subscribe('/user/message/' + patientId.val() + "&" + doctorId.val(), function (data)
    {
        console.log('mtav');
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

function sendMessage() {
    let message = $txtMessage.val();
    if (message.toString().trim() != '') {
        send(message);
    }
}

function send(message) {
    let data = {
        "patientId": patientId.val(),
        "doctorId": doctorId.val(),
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