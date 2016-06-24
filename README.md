Xunil.io wants to provide encrypted anonymous communication cools direct from a browser.

Current Features:
-----------------

- Targeted Chat
- No usernames, no logins

Roadmap:
--------

- Encrypted chat (See communication details below)
    - Generate RSA Keys in Browser
    - Enable handshake on server (provide public key to server)
    - Distribute public key to client who chooses recipient
- Allow users to opt in for:
    - Display ID on roster
    - Display own messages in chat
- Message Box
    - Create box for set amount of time
    - Read message from box
    - Delete message on read or expiration

Chat Communication Workflow:
----------------------------

- Client loads page
- Client Generates RSA Key pair
- Client Generates UUID Seed
- Client sends Public RSA cert and UUID Seed in HTTP Message to Server
- Server uses UUID Seed and its own Random Seed to generate UUID for this client
- Server stores generated UUID and RSA Cert in associated Memory store (maybe H2 Database)
- Server responds to Client with generated UUID
- Client stores UUID in memory

-- HTTP Handshake Complete --

- Client connects to WebSocket
- Server responds to connection with message 'please register'
- Client responds with previously retrieved UUID
- Server stores client's WebSocket ID with UUID

-- WebSocket Handshake Complete --
-- *Handshake Complete* --

On Disconnect:

- Server receives disconnect message
- Server cleans up associated UUID and RSA Key

Chat Client to Client Communication:
------------------------------------

- Client provides ID of recipient via HTTP, asking for public RSA cert
- Server asks recipient if they wish to share RSA cert with client (to 'connect')
- If recipient responds 'Ok', server sends client the recipient's RSA cert
    - A recipient who accepts request to 'connect', receives an the client's public RSA Key over Websocket
    - Server creates a bidirectional relationship in memory between two clients
- If recipient responds 'no', server sends back 'request declined' message
- If recipient does not exist, server waits random amount of time (1-30 seconds) and declines as above

-- Client to Client handshake Complete --

- Client encrypts a message using the recipient's RSA cert and sends it with the UUID of the recipient to the server
- The server validates client relationship and sends encrypted data to target recipient
- Recipient uses their private RSA key to decrypt and display message

Hourly Audit:
-------------

- Run scheduled thread hourly looking for old connections
- Clean up old RSA Keys / UUID records
