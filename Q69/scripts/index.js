db.artists.createIndex({"name": 1});
db.artists.createIndex({"name": -1});
db.artists.createIndex({"rating.value": 1});
db.artists.createIndex({"rating.value": -1});
