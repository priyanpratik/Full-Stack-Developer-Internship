const seats = document.querySelectorAll(".row .seat:not(.occupied)");
const seatContainer = document.querySelector(".row-container");
const count = document.getElementById("count");
const total = document.getElementById("total");
const movieSelect = document.getElementById("movie");

function markSelectedSeats(){
    var getByRecordRequest=createGET_RECORDRequest("90933146|-31949318775558192|90951378", "Movie Ticket Booking Database", "All Booked Tickets", 1);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(getByRecordRequest, "/api/irl");
    jQuery.ajaxSetup({async: true});
    var occupiedSeats = (JSON.parse(jsonObj.data)).id;
    var seatsArr=occupiedSeats.split(" ");
    for(var i=0;i<seatsArr.length;i++){
        var id=seatsArr[i];
        document.getElementById(id).classList.add("occupied");
    }
    
}

// occupied
function clearSelectedSeats(){
    localStorage.setItem("selectedSeats", "[]");
    location.reload();
}

// Another Approach

// seats.forEach(function(seat) {
//   seat.addEventListener("click", function(e) {
//     seat.classList.add("selected");
//     const selectedSeats = document.querySelectorAll(".container .selected");
//     selectedSeathLength = selectedSeats.length;
//     count.textContent = selectedSeathLength;
//     let ticketPrice = +movieSelect.value;
//     total.textContent = ticketPrice * selectedSeathLength;
//   });
// });

// localStorage.clear();

populateUI();

let ticketPrice = +movieSelect.value;

// Save selected movie index and price
function setMovieData(movieIndex, moviePrice) {
  localStorage.setItem("selectedMovieIndex", movieIndex);
  localStorage.setItem("selectedMoviePrice", moviePrice);
}

function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll(".container .selected");

  seatsIndex = [...selectedSeats].map(function(seat) {
    return [...seats].indexOf(seat);
  });

  localStorage.setItem("selectedSeats", JSON.stringify(seatsIndex));

  let selectedSeatsCount = selectedSeats.length;
  count.textContent = selectedSeatsCount;
  total.textContent = selectedSeatsCount * ticketPrice;
}

// Get data from localstorage and populate
function populateUI() {
  const selectedSeats = JSON.parse(localStorage.getItem("selectedSeats"));

//  if (selectedSeats !== null && selectedSeats.length > 0) {
//    seats.forEach(function(seat, index) {
//      if (selectedSeats.indexOf(index) > -1) {
//        seat.classList.add("selected");
//      }
//    });
//  }

  const selectedMovieIndex = localStorage.getItem("selectedMovieIndex");

  if (selectedMovieIndex !== null) {
    movieSelect.selectedIndex = selectedMovieIndex;
  }
}

// Movie select event

movieSelect.addEventListener("change", function(e) {
  ticketPrice = +movieSelect.value;
  setMovieData(e.target.selectedIndex, e.target.value);
  updateSelectedCount();
});

// Adding selected class to only non-occupied seats on 'click'

seatContainer.addEventListener("click", function(e) {
  if (
    e.target.classList.contains("seat") &&
    !e.target.classList.contains("occupied")
  ) {
    e.target.classList.toggle("selected");
    updateSelectedCount();
  }
});

// Initial count and total rendering
updateSelectedCount();

function bookTicket(){
    const selectedSeats = JSON.parse(localStorage.getItem("selectedSeats"));
    var selectedSeatsStr="";
    for(var i=0;i<selectedSeats.length;i++){
        selectedSeatsStr+=(selectedSeats[i]);
        if(i!==selectedSeats.length-1)
            selectedSeatsStr+=" ";
    }
    
    var getByRecordRequest=createGET_RECORDRequest("90933146|-31949318775558192|90951378", "Movie Ticket Booking Database", "All Booked Tickets", 1);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(getByRecordRequest, "/api/irl");
    jQuery.ajaxSetup({async: true});
    var occupiedSeats = (JSON.parse(jsonObj.data)).id;
    var seatsArr=occupiedSeats.split(" ");
    var selectedSeatsStr1="";
    for(var i=0;i<seatsArr.length;i++){
        selectedSeatsStr1+=(seatsArr[i]);
        if(i!==seatsArr.length-1)
            selectedSeatsStr1+=" ";
    }
    
    var finaloccupiedSeats = selectedSeatsStr+" "+selectedSeatsStr1;
    var occupiedSeatsJsonObj = {
        "id": finaloccupiedSeats
    };    
    
    var occupiedSeatsJsonStrObj=JSON.stringify(occupiedSeatsJsonObj);
    
    var createUpdateRequest=createUPDATERecordRequest("90933146|-31949318775558192|90951378", occupiedSeatsJsonStrObj, "Movie Ticket Booking Database", "All Booked Tickets", 1);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(createUpdateRequest, "/api/iml");
    jQuery.ajaxSetup({async: true});
    
    location.reload();
}
