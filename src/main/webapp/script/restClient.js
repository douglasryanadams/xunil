/**
 * Created on 7/21/16.
 */

var RestClient = function (domain) {
    this.domain = domain;
    this.baseUrl = "https://" + this.domain + "/api/";
};

RestClient.prototype.register = function (publicKey, successCallback) {
    var randomSeed = sjcl.codec.base64.fromBits(sjcl.random.randomWords(4));
    var publicKeyString = KeyMaster.getPublicKeyAsString(publicKey);

    var payload = {
        "randomSeed": randomSeed,
        "publicKey": publicKeyString
    };

    var errorCallback = function (xhr, status, error) {
        var e = JSON.parse(xhr.responseText);
        ChatController.displayErrorMessage("ERROR: " + status + " | " + e["error"]);
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
        var e = JSON.parse(xhr.responseText);
        ChatController.displayErrorMessage("ERROR: " + status + " | " + e["error"]);
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
