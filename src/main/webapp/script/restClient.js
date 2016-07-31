/**
 * Created on 7/21/16.
 */

var RestClient = function (domain) {
    this.domain = domain;
    this.baseUrl = "https://" + this.domain + "/api/";
};

RestClient.prototype.register = function (publicKey, successCallback) {
    var randomSeed = sjcl.codec.base64.fromBits(sjcl.random.randomWords(4));
    var pubKeyStringObject = {
        "xAsString": sjcl.codec.base64.fromBits(publicKey.get()['x']),
        "yAsString": sjcl.codec.base64.fromBits(publicKey.get()['y']),
    };

    var payload = {
        "randomSeed": randomSeed,
        "publicKey": pubKeyStringObject
    };

    var errorCallback = function (xhr, status, error) {
        // TODO make informative
        ChatController.displayErrorMessage("ERROR: " + status + " | " + error);
    };

    $.ajax({
        type: "POST",
        url: this.baseUrl + "chat",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: successCallback,
        error: errorCallback
    });
};

RestClient.prototype.chatConnect = function (myId, targetId, successCallback) {
    var payload = {
        "from": myId,
        "connectWith": targetId
    };

    var errorCallback = function (xhr, status, error) {
        // TODO make informative
        ChatController.displayErrorMessage("ERROR: " + status + " | " + error);
    };

    $.ajax({
        type: "POST",
        url: this.baseUrl + "chat/connection",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: successCallback,
        error: errorCallback
    })
};
