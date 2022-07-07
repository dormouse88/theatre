SELECT Booking.NumberOfAdults, Booking.NumberOfChildren, Seat.SeatZone, Performance.pdate, Performance.ptime, Showing.Title
FROM Booking
JOIN Customer
ON Customer.CustomerID = Booking.CustomerID
JOIN Seat
ON Seat.SeatID = Booking.SeatID
JOIN Performance
ON Performance.PerformanceID = Seat.PerformanceID
JOIN showing
ON Showing.ShowingID = Performance.ShowingID
WHERE Customer.Username = ?
;