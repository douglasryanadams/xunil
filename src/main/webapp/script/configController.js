/**
 * Created on 7/21/16.
 */

var ConfigController = function (rosterSocket) {
    this.rosterSocket = rosterSocket;
};

ConfigController.prototype.init = function () {
    var thisConfig = this;
    $("#show_me").click(function () {
        var showMe = this.checked;
        var msg = {"senderId" : clientMemory["id"]};
        if (showMe) {
            msg["type"] = "showMe";
            thisConfig.showing_me = true;
        } else {
            msg["type"] = "hideMe";
            thisConfig.showing_me = false;
        }
        thisConfig.rosterSocket.sendMessage(JSON.stringify(msg));
    });

    $("#enter_sends").click(function () {
        var enterSends = this.checked;
        if (enterSends) {
            $("#chat_input").keypress(function (k) {
                if (k.which == 13 && !k.shiftKey) {
                    k.preventDefault();
                    $("#chat_submit").click();
                }
            });
        } else {
            $("#chat_input").unbind("keypress");
        }
    });

    $("#show_my_own_messages").click(function () {
        var showMyOwn = this.checked;
        if (showMyOwn) {
            clientMemory["showMyOwnMessages"] = true;
        } else {
            clientMemory["showMyOwnMessages"] = false;
        }
    });


};
