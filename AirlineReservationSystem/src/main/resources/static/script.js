let timer;
let timerDuration = 60;

function startTimer() {
    clearInterval(timer);
    let timeRemaining = timerDuration;
    const timerDiv = document.getElementById('timer');
    timerDiv.innerHTML = `Time remaining<br>${timeRemaining} seconds`;

    timer = setInterval(async () => {
        timeRemaining--;
        timerDiv.innerHTML = `Time remaining<br>${timeRemaining} seconds`;
        if (timeRemaining <= 0) {
            clearInterval(timer);
            await cancelAllSelections();
            fetchSeats();
            timerDiv.innerHTML = `Time remaining<br>is over, refresh`;
        }
    }, 1000);
}


async function fetchSeats() {
    const response = await fetch('/seats');
    const seats = await response.json();
    const seatsDiv = document.getElementById('seats');
    seatsDiv.innerHTML = '';
    const userId = document.getElementById('userId').value;
    seats.forEach(seat => {
        const seatDiv = document.createElement('div');
        seatDiv.classList.add('seat');
        if (seat.reserved) {
            seatDiv.classList.add('reserved');
            if (seat.reservedBy === userId) {
                seatDiv.classList.add('selected');
                seatDiv.innerHTML = `${seat.id}<br>yours`;
            } else {
                seatDiv.innerText = seat.id;
            }
        } else {
            seatDiv.classList.add('available');
            seatDiv.innerText = seat.id;
        }
        seatDiv.onclick = async () => {
            if (seat.reserved && seat.reservedBy !== userId) {
                alert('This seat is reserved by another user.');
            } else if (seat.reserved && seat.reservedBy === userId) {
                await fetch(`/seats/${seat.id}/cancel?userId=${userId}`, { method: 'POST' });
            } else if (!seat.reserved) {
                await fetch(`/seats/${seat.id}/reserve?userId=${userId}`, { method: 'POST' });
                startTimer();
            }
            fetchSeats();
        };
        seatsDiv.appendChild(seatDiv);
    });
}

async function cancelAllSelections() {
    const userId = document.getElementById('userId').value;
    const response = await fetch('/seats');
    const seats = await response.json();
    for (const seat of seats) {
        if (seat.reserved && seat.reservedBy === userId) {
            await fetch(`/seats/${seat.id}/cancel?userId=${userId}`, { method: 'POST' });
        }
    }
}

fetchSeats();
setInterval(fetchSeats, 3000);