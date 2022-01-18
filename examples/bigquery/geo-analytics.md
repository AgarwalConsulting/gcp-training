# Geospatial Analytics + Visualization

- Find all spots with enough available bikes

```sql
SELECT
  ST_GeogPoint(longitude, latitude) AS WKT,
  num_bikes_available
FROM
  `bigquery-public-data.new_york.citibike_stations`
WHERE num_bikes_available > 30
```

- Visualize the data using: [BigQuery Geo Viz Tool](https://bigquerygeoviz.appspot.com/?authuser=2)
