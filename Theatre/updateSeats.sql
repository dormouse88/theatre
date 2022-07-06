UPDATE Seat
SET NumberOfSeats = NumberOfSeats - ?
WHERE PerformanceID = ?
AND SeatZone = ?
AND NumberOfSeats >= ?;
