const body = document.getElementById("content");
var input = document.createElement("input");
var words = [];
var commandHistory = [];
var currentCommand = -1;
const commands = [
    "die",
    "help",
    "hit",
    "inventory", 
    "look", 
    "move", 
    "pickup",
    "use", 
    "interact", 
    "equip", 
    "unequip",
    "nsfw", 
    "stats"
];

window.onload = () => {
    input.id = "textInput";
    input.autofocus = true;
    window.addEventListener("keydown", (e) => keydown(e));
    initialize();
};
function keydown(e) {
    input.focus();
}

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
    // Add possible word options to JS
    words = gameState.state.entityNames;
    for(i in gameState.messages) {
        body.innerHTML += "<div>" + gameState.messages[i] + "</div>";
    }
    while(body.childElementCount > 500) {
        body.firstElementChild.remove();
    }
    if(!gameState.state.isGameOver) {
        createInput();
        input.addEventListener("keydown", sendMessageKeyEvent);input.innerHTML.indexOf(' ') + 1
    } else {
        input.remove();
    }
    body.scrollTop = body.scrollHeight;
}
async function sendMessage() {
    currentCommand = -1; // Reset command history
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
        commandHistory.unshift(input.value);
        commandHistory.splice(100);
        sendMessage(e);
    }
    if(e.key == "Tab") { // Autocomplete tab
        autoComplete(e);
    }
    if(e.key == "ArrowUp") {
        lastMessage(e);
    }
    if(e.key == "ArrowDown") {
        nextMessage(e);
    }
}
function lastMessage(e) {
    if(currentCommand + 1 < commandHistory.length) currentCommand++;
    if(commandHistory.length == 0) currentCommand = -1;
    input.value = commandHistory[currentCommand];

    input.setSelectionRange(input.value.length, input.value.length);
}
function nextMessage(e) {
    if(currentCommand >= 0) {
        currentCommand--;
    }
    if(currentCommand >= 0) {
        input.value = commandHistory[currentCommand];
    } else {
        input.value = "";
    }
    input.setSelectionRange(input.value.length, input.value.length);
}
async function sendStartKeyEvent(e) {
    if(e.code == "Enter") {
        start();
    }
}
function autoComplete(e) {
    var incompleteText = input.value;
    var firstWord = incompleteText.split(" ")[0]
    e.preventDefault();
    
    var inputText = incompleteText.slice(input.value.indexOf(' ') + 1); // remove command word
    inputText = inputText.toLowerCase();
    var options = [];
    var wordList = [];
    console.log(firstWord);
    switch (firstWord.toLowerCase().trim()) {
        case "look":
        case "hit":
        case "pickup":
        case "interact":
            wordList = words;
        break;
        case "use":
        case "equip":
        case "unequip":
            // TODO: add inventory variable with inventory content names
            wordList = inventory;
        break;
        default:
            // Command list
            wordList = commands;
    }
    for(var i=0; i<wordList.length; i++) {
        var word = wordList[i].toLowerCase();
        var validWord = true;
        for(var char=0; char<inputText.length; char++) {
            if(inputText[char] != word[char]) validWord = false;
        }
        if(validWord) options.push(wordList[i]);
    }
    if(options.length == 1) {
        if(wordList == commands) {
            input.value = options[0] + " ";
        } else {
            input.value = incompleteText.split(" ")[0] + " " + options[0];
        }
    } else if(wordList != commands) {
        input.remove();
        body.innerHTML += "<br>";
        for(i=0; i<options.length; i++) {
            body.innerHTML += options[i] + " ";
        }
        body.innerHTML += "<br>";
        body.appendChild(input);
    }
}