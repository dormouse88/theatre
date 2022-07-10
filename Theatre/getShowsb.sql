-- finding information on all current shows ---
SELECT 
	Showing.ShowingID,
    Title,
    Duration AS RunTimeMinutes,
    Lang AS Language,
    Info AS Description,
    ShowType.Genre AS Genre
FROM
    Showing
        JOIN
    ShowType
		ON Showing.ShowTypeID = ShowType.ShowTypeID
		WHERE Title LIKE ?;
		