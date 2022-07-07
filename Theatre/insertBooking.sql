INSERT INTO Booking
(CustomerID, NumberOfAdults, NumberOfChildren, SeatID)
SELECT (SELECT CustomerID FROM Customer WHERE Customer.Username = ? LIMIT 1),
?, ?,
(SELECT SeatID FROM Seat 
JOIN Performance ON Seat.PerformanceID = Performance.PerformanceID
WHERE Performance.PerformanceID = ? AND Seat.seatZone = ? LIMIT 1);
