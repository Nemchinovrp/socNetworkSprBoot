window.onload = init;

function init() {
    var fireButton = document.getElementById("fireButton");
    fireButton.onclick = handleFireButton;
    var guessInput = document.getElementById("guessInput");
    guessInput.onkeypress = handleKeyPress;

    model.generateShipLocations();
};

function handleFireButton() {
    var guessInput = document.getElementById("guessInput");
    var guess = guessInput.value;
    controller.processGuess(guess);
    guessInput.value = "";
};

function handleKeyPress(e) {
    var fireButton = document.getElementById("fireButton");
    if (e.keyCode === 13) {
        fireButton.click();
        return false;
    }
};

var view = {
    displayMessgae: function (msg) {
        document.getElementById("messageArea").innerHTML = msg;
    },

    displayMiss: function (coords) {
        var cell = document.getElementById(coords);
        if (cell.className === "miss") {
            alert("You repeated your quess!");
        } else {
            cell.setAttribute("class", "miss");
        }
    },

    displayHit: function (coords) {
        var cell = document.getElementById(coords);
        if (cell.className === "hit") {
            alert("You repeated your quess!");
        } else {
            cell.setAttribute("class", "hit");
        }
    }
};

var model = {
    boardSize: 7,
    numShips: 3,
    shipsSunk: 0,
    shipLength: 3,
    ships: [{locations: ["00", "00", "00"], hits: ["", "", ""]},
        {locations: ["00", "00", "00"], hits: ["", "", ""]},
        {locations: ["00", "00", "00"], hits: ["", "", ""]}],
    fire: function (guess) {
        for (var i = 0; i < this.numShips; i++) {
            var ship = this.ships[i];
            var index = ship.locations.indexOf(guess);
            if (index >= 0) {
                ship.hits[index] = "hit";
                view.displayHit(guess);
                view.displayMessgae("HIT!");
                if (this.isSunk(ship)) {
                    view.displayMessgae("You sunk my battleship!");
                    this.shipsSunk++;
                }
                return true;
            }
        }
        view.displayMiss(guess);
        view.displayMessgae("MISS!");
        return false;
    },
    isSunk: function (ship) {
        for (var i = 0; i < this.shipLength; i++) {
            if (ship.hits[i] !== "hit") {
                return false;
            }
        }
        return true;
    },
    generateShipLocations: function () {
        var locations;
        for (var i = 0; i < this.numShips; i++) {
            do {
                locations = this.generateShip();
            } while (this.collision(locations))
            this.ships[i].locations = locations;
        }
    },
    generateShip: function () {
        var direction = Math.floor(Math.random() * 2);
        var row, col;
        if (direction === 1) {
            row = Math.floor(Math.random() * this.boardSize);
            col = Math.floor(Math.random() * (this.boardSize - this.shipLength));
        } else {
            row = Math.floor(Math.random() * (this.boardSize - this.shipLength));
            col = Math.floor(Math.random() * this.boardSize);
        }

        var newShipLocations = [];
        for (var i = 0; i < this.shipLength; i++) {
            if (direction === 1) {
                newShipLocations.push(row + "" + (col + i));
            } else {
                newShipLocations.push((row + i) + "" + col);
            }
        }

        return newShipLocations;
    },
    collision: function (locations) {
        for (var i = 0; i < this.numShips; i++) {
            var ship = model.ships[i];
            for (var j = 0; j < locations.length; j++) {
                if (ship.locations.indexOf(locations[j]) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
};

var controller = {
    guesses: 0,
    processGuess: function (guess) {
        var location = this.parseGuess(guess);
        if (location) {
            this.guesses++;
            var hit = model.fire(location);
            if (hit && model.shipsSunk === model.numShips) {
                view.displayMessgae("You sank all my battleships, in " +
                    this.guesses + " guesses.");
            }
        }
    },
    parseGuess: function (guess) {
        var alphabet = ["A", "B", "C", "D", "E", "F", "G"];

        if (guess === null || guess.length !== 2) {
            alert("Ooops, enter a letter and a number on the board.");
        } else {
            var row = alphabet.indexOf(guess.charAt(0));
            var column = guess.charAt(1);
            if (isNaN(row) || isNaN(column)) {
                alert("Oops, that is not on the board.");
            } else if (row < 0 || row >= model.boardSize ||
                column < 0 || column >= model.boardSize) {
                alert("Oops, that is off the board!");
            } else {
                return row + column;
            }
        }
        return null;
    }
};

//view.displayMessgae("It is working!");
//view.displayMiss("00");
//view.displayHit("34");
//view.displayMiss("55");
//view.displayHit("12");
//view.displayMiss("25");
//view.displayHit("26");

//model.fire("53");

//model.fire("06");
//model.fire("16");
//model.fire("26");

//model.fire("34");
//model.fire("24");
//model.fire("44");

//model.fire("12");
//model.fire("11");
//model.fire("10");

//console.log(controller.parseGuess("A0"));
//console.log(controller.parseGuess("B6"));
//console.log(controller.parseGuess("G3"));
//console.log(controller.parseGuess("H0"));
//console.log(controller.parseGuess("A7"));

//controller.processGuess("A0");

//controller.processGuess("A6");
//controller.processGuess("B6");
//controller.processGuess("C6");

//controller.processGuess("C4");
//controller.processGuess("D4");
//controller.processGuess("E4");

//controller.processGuess("B0");
//controller.processGuess("B1");
//controller.processGuess("B2");

