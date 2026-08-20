const body = document.getElementById("content");

window.onload = () => {
    initialize();
};

async function initialize() {
    const response = await fetch("/api/game");
    const gameState = await response.json();

    if(!gameState.state.isInitialized) {
        body.innerHTML = "Name your character: ";
        var input = createInput();
        input.addEventListener("keydown", async (e) => {
            if(e.code == "Enter") {
                start(input);
            }
        })
    }
}

async function start(input) {
    // Send data to server
    await fetch("api/game/start", {

    });

    inputEntered(input);

}

function createInput() {
    var input = document.createElement("input");
    input.id = "textInput";
    body.appendChild(input);
    return input;
}

async function inputEntered(input) {
    body.innerHTML += input.value;
    body.innerHTML += "\n";
    document.getElementById("textInput").remove();
}