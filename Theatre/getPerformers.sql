SELECT 
	Performer.pname
FROM
    ShowingPerformer
		JOIN
	Showing
		ON ShowingPerformer.ShowingID = Showing.ShowingID
		JOIN
	Performer
		ON ShowingPerformer.PerformerID = Performer.PerformerID;
