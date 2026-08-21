const body = document.getElementById("content");
const input = document.createElement("input");

window.onload = () => {
    input.id = "textInput";
    input.autofocus = true;
    window.addEventListener("keydown", () => input.focus());
    initialize();
};

async function initialize() {
    const response = await fetch("/api/game");
    const gameState = await response.json();

    if(!gameState.state.isInitialized) {
        body.innerHTML = "Name your character: ";
        createInput();
        input.addEventListener("keydown", sendStartKeyEvent);

    } else if(gameState.state.isGameOver) {
        // Reset game
        await fetch("api/game/restart", {
            method: "POST"
        });
        initialize();
    } else {
        gameStep(gameState);
    }
}

async function start() {
    // Send data to server
    await fetch("api/game/start", {
        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            playerName: input.value
        })

    });
    var response = await fetch("api/game");
    var data = await response.json();

    inputEntered();

    await gameStep(data);

}

function createInput() {
    body.appendChild(input);
    input.hidden = false;
    input.focus();
}

function inputEntered() {
    body.innerHTML += input.value;
    body.innerHTML += "<br>";
    body.innerHTML += "<br>";
    input.value = "";
    input.removeEventListener("keydown", sendStartKeyEvent);
    input.removeEventListener("keydown", sendMessageKeyEvent);
    document.getElementById("textInput").remove()
}

async function gameStep(gameState) {
    for(i in gameState.messages) {
        body.innerHTML += gameState.messages[i] + "<br>";
    }
    if(!gameState.state.isGameOver) {
        createInput();
        input.addEventListener("keydown", sendMessageKeyEvent);
    } else {
        input.remove();
    }
    body.scrollTop = body.scrollHeight;
}
async function sendMessage() {
    var response = await fetch("api/game/command", {
        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            message: input.value
        })

    });
    var data = await response.json();

    await inputEntered();
    gameStep(data);
}

async function sendMessageKeyEvent(e) {
    if(e.code == "Enter") {
        sendMessage();
    }
}
async function sendStartKeyEvent(e) {
if(e.code == "Enter") {
    start();
}
}