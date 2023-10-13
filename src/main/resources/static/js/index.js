let boostrap_status = false;

function bootstrap(){
    if (!boostrap_status) {
        start();
    }else {
        stop()
    }
}

function start() {
    let input_host = $('#input_host');
    let input_port = $('#input_port');
    let bootstrap_button = $('#bootstrap_button');

    let host = input_host.val();
    let port = input_port.val();
    input_host.css("border", host === '' ? "1px solid red" : "1px solid green");
    input_port.css("border", port === '' ? "1px solid red" : "1px solid green");
    if (host === '' || port === '') {
        return;
    }

    input_host.prop("disabled", true)
    input_port.prop("disabled", true)

    axios.post("/client/start", {
        host: host,
        port: port
    }).then(function (response) {
        if(response.data === 'ok') {
            boostrap_status = true;
            bootstrap_button.css("border", "1px solid red");
            bootstrap_button.text("停止");
        }else {
            input_host.prop("disabled", false)
            input_port.prop("disabled", false)
        }
    })
}

function stop() {
    boostrap_status = false;
    let input_host = $('#input_host');
    let input_port = $('#input_port');
    let bootstrap_button = $('#bootstrap_button');

    input_host.prop("disabled", false)
    input_port.prop("disabled", false)

    input_host.css("border", "1px solid black");
    input_port.css("border", "1px solid black");

    bootstrap_button.css("border", "1px solid black");
    bootstrap_button.text("启动");
}

