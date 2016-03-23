Q69
===

This application is written based on [playing-reactive-mongo](https://github.com/knoldus/playing-reactive-mongo).

1. Import `artist.json` to MongoDB by running `scripts/import.sh`.
2. Create indices by `mongo localhost:27017/nlp100 script/index.js`.
3. Launch the server. Type `sbt "run <port_number>"`.
4. Open `localhost:<port_number>` by your favorite browser.
