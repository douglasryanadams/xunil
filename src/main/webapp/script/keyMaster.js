/**
 * Created on 7/21/16.
 */

var KeyMaster = function () {
    var keys = sjcl.ecc.ecdsa.generateKeys(521);
    this.publicKey = keys['pub'];
    this.privateKey = keys['sec'];
};

// TODO
// KeyMaster.prototype.encryptMessage = function (message) {
//     return encryptedMessage;
// };
