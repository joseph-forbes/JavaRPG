const body = document.getElementById("content");
var inputDiv = document.createElement("div");
var input = document.createElement("input");
var documentWordList = document.createElement("div");
var worldEntityNames = [];
var inventoryItemNames = [];
var movementDirectionNames = [];
var validWords = [];
var commandIdx = 0;

var commandHistory = [];
var currentCommand = -1;


var wordList = [];
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
    inputDiv.id = "inputDiv";
    inputDiv.appendChild(input);
    inputDiv.appendChild(documentWordList);
    input.id = "textInput";
    documentWordList.id = "wordList";
    input.autofocus = true;
    window.addEventListener("keydown", (e) => keydown(e));
    initialize();
};
function keydown(e) {
    input.focus();
}
/**
 * Fires on page load to determine what state the game is currently in
 */
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
/**
 * Send start signal to server
 */
async function start() {
    // Send data to server
    await fetch("api/game/start", {
        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            playerName: input.value.trim()
        })

    });
    var response = await fetch("api/game");
    var data = await response.json();

    inputEntered();

    await gameStep(data);

}

function createInput() {
    body.appendChild(inputDiv);
    inputDiv.hidden = false;
    input.focus();
}
function inputEntered() {
    body.innerHTML += input.value;
    body.innerHTML += "<br>";
    body.innerHTML += "<br>";
    input.value = "";
    input.removeEventListener("keydown", sendStartKeyEvent);
    input.removeEventListener("keydown", sendMessageKeyEvent);
    document.getElementById("inputDiv").remove();
}
function updateTerminal(gameState) {
    // Add possible word options to JS
    if(gameState.state) {
        worldEntityNames = gameState.state.entityNames;
        inventoryItemNames = gameState.state.itemNames;
        movementDirectionNames = gameState.state.validDirections;
    }
    for(i in gameState.messages) {
        body.innerHTML += "<div>" + gameState.messages[i] + "</div>";
    }
    while(body.childElementCount > 500) {
        body.firstElementChild.remove();
    }
}
async function gameStep(gameState) {
    updateTerminal(gameState);
    if(!gameState.state.isGameOver) {
        createInput();
        input.addEventListener("keydown", sendMessageKeyEvent);
    } else {
        inputDiv.remove();
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

/**
 * During the main game loop, this checks whenever the user inputs a character,
 * finds what words are still valid for commands,
 * checks to see if the player is attempting to autocomplete,
 * and sends the message upon an enter input
 * 
 */ 
async function sendMessageKeyEvent(e) {
    if(e.code == "Enter" && commandIdx == -1) {
        if(input.value != commandHistory[0])
            commandHistory.unshift(input.value);
        commandHistory.splice(100);
        documentWordList.innerHTML = "";
        sendMessage(e);
        return;
    } else if(e.code == "Enter") {
        autoComplete(e);
    }
    if(e.key == "Tab") { // Autocomplete tab
        autoComplete(e);
    }
    // Happens after tab press to avoid messing with validWords with things such as "looTab"
    findValidWords(e);
    if(e.key == "ArrowUp") {
        e.preventDefault();
        if(documentWordList.children.length == 0) {
            lastMessage(e);
        } else {
            moveCmdIdxUp(e);   
        }
    }
    if(e.key == "ArrowDown") {
        e.preventDefault();
        if(documentWordList.children.length == 0) {
            nextMessage(e);
        } else {
            moveCmdIdxDown(e); 
        }
    }
}

function moveCmdIdxDown(e) {
    setCommandIdx(Math.min(commandIdx + 1, documentWordList.childElementCount - 1));
    ensureSelectedCommandIsVisible(e);

}
function moveCmdIdxUp(e) {
    setCommandIdx(Math.max(commandIdx - 1, 0));
    ensureSelectedCommandIsVisible(e);
}
/**
 * On up arrow press, go to last message
 * 
 */ 
function lastMessage(e) {
    if(currentCommand + 1 < commandHistory.length) currentCommand++;
    if(commandHistory.length != 0)
        input.value = commandHistory[currentCommand];

    input.setSelectionRange(input.value.length, input.value.length);
}


/**
 * On up down press, go to next message
 * 
 */ 
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
/**
 * At the beginning of the game, this fires to let the Java application
 * know what the player's name is
 * 
 */ 
async function sendStartKeyEvent(e) {
    if(e.code == "Enter") {
        start();
    }
}
/**
 * On tab press, finish word (if possible)
 * 
 */ 
function autoComplete(e) {
    if(e)
        e.preventDefault();
    if(validWords.length == 0) return;
    
    if(wordList == commands) { // first word 
        input.value = validWords[commandIdx] + " ";
    } else {
        var firstWord = input.value.split(" ")[0] + " ";
        input.value = firstWord + validWords[commandIdx] + " ";
    }
    findValidWords();
}
/**
 * On key press, identify possible words
 * 
 */ 
function findValidWords(event) {
    // Don't re-find words on tab/arrow key press
    if (event && event.key.length > 1) return;
    validWords = [];
    setCommandIdx(-1);

    // The key has been pressed but not quite added to the input yet
    var incompleteText = event ? input.value + event.key : input.value;

    var firstWord = incompleteText.split(" ")[0];
    
    var inputText = incompleteText.slice(input.value.indexOf(' ') + 1); // remove first (command) word
    inputText = inputText.toLowerCase();
    if(input.value.includes(" ")) {
        switch (firstWord.toLowerCase().trim()) { 
            // Check first word to see if it is a complete command
            // If it is, match the command to the options it can have 
            // (i.e. "look" is matched wtih world entities you can look at)
            case "look":
            case "hit":
            case "pickup":
            case "interact":
                wordList = [...worldEntityNames];
            break;
            case "use":
            case "equip":
                wordList = [...inventoryItemNames];
            break;
            case "unequip":
                wordList = ["armor", "weapon"];
            break;
            case "move":
                wordList = [...movementDirectionNames];
            break;
            default: // First word is a typo
                wordList = [];
                
        }
        if(firstWord.toLowerCase().trim() == "look") {
            wordList.unshift("around");
        }
    } else {
        // Incomplete command
        wordList = commands;
    }
    for(var i=0; i<wordList.length; i++) {
        var word = wordList[i].toLowerCase();
        var validWord = true;
        for(var char=0; char<inputText.length; char++) {
            if(inputText[char] != word[char]) validWord = false;
        }
        if(validWord) validWords.push(wordList[i].toLowerCase());
    }

    if(validWords.length > 0)
        setCommandIdx(0);

    displayValidWords();
}
function displayValidWords() {
    documentWordList.innerHTML = "";
    for(var i in validWords) {
        var word = validWords[i];
        var div = document.createElement("div");
        div.innerHTML = word;
        if(i == commandIdx) {
            div.classList.add("selected");
        }
        div.onmouseenter = (event) => {
            setCommandIdx([...documentWordList.children].indexOf(event.target));
            resetIdxHighlight();
        }
        div.onclick = (event) => {
            input.focus();
            validWords = [event.target.innerHTML.trim()];
            setCommandIdx(0);
            autoComplete();
        }
        documentWordList.appendChild(div);
    }
    body.scrollTop = body.scrollHeight;
}
function setCommandIdx(idx) {
    commandIdx = idx;
    resetIdxHighlight();
}
function resetIdxHighlight() {
    for(var i in documentWordList.children) {
        if(documentWordList.children[i].classList) 
            documentWordList.children[i].classList.remove("selected");
        if(i == commandIdx) {
            documentWordList.children[i].classList.add("selected");
        }
    }
}
function ensureSelectedCommandIsVisible(event) {
    const div = document.getElementsByClassName('selected')[0];

    // Calculates the element's position relative to the container and sets the scroll
    if(event.key == "ArrowUp") {
        // Stick container to top
        if(div.offsetTop < documentWordList.scrollTop + documentWordList.offsetTop)
            documentWordList.scrollTop =  div.offsetTop - documentWordList.offsetTop;
    } else {
        // Down arrow pressed
        if(div.offsetTop + div.getBoundingClientRect().height > documentWordList.getBoundingClientRect().height + documentWordList.offsetTop)
            documentWordList.scrollTop = div.offsetTop + div.getBoundingClientRect().height - documentWordList.offsetTop - documentWordList.getBoundingClientRect().height;
    }
}