SELECT 
	Performer.pname
FROM
    ShowingPerformer
		JOIN
	Showing
		ON ShowingPerformer.ShowID = Showing.ShowID
		JOIN
	Performer
		ON ShowingPerformer.PerformerID = Performer.PerformerID;
