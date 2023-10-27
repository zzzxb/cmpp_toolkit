let boostrap_status = false;

function bootstrap() {
    boostrap_status ? stop() : start();
}

function start() {
    let input_host = $('#input_host');
    let input_port = $('#input_port');

    let host = input_host.val();
    let port = input_port.val();
    if (host === '' || port === '') {
        appendConsoleMsg("请输入 host 和 port");
        return;
    }

    startReq(host, port);
}

function startReq(host, port) {
    let input_host = $('#input_host');
    let input_port = $('#input_port');
    let bootstrap_button = $('#bootstrap_button');

    axios.post("/client/start", {
        host: host,
        port: port
    }).then(function (response) {
        let startupStatus = response.data === 'ok';
        input_host.css("border", startupStatus ? "1px solid green" : "1px solid red");
        input_port.css("border", startupStatus ? "1px solid green" : "1px solid red");
        input_host.prop("disabled", startupStatus)
        input_port.prop("disabled", startupStatus)

        if (startupStatus) {
            boostrap_status = true;
            appendConsoleMsg("启动成功!");
            bootstrap_button.css("border", "1px solid red");
            bootstrap_button.text("停止");
        } else {
            appendConsoleMsg("启动失败-" + response.data);
        }
    }).catch(function (error) {
        appendConsoleMsg("启动失败-" + error);
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

function appendConsoleMsg(msg) {
    let out = $("#console");
    out.val(out.val() + "\n" + dateTime() + " " + msg);
    out.prop("scrollTop", out.prop("scrollHeight"));
}

function dateTime() {
    let currentDate = new Date();
    let year = currentDate.getFullYear();
    let month = currentDate.getMonth() + 1;
    let date = currentDate.getDate();
    let hours = currentDate.getHours();
    let minutes = currentDate.getMinutes();
    let seconds = currentDate.getSeconds();
    let milliseconds = currentDate.getMilliseconds();
    let ms = milliseconds < 10 ? "00" + milliseconds : (milliseconds < 100) ? "0" + milliseconds : milliseconds;
    return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds + "." + ms;
}

