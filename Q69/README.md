Q69
===

This application is written based on [playing-reactive-mongo](https://github.com/knoldus/playing-reactive-mongo).

1. Edit `conf/application.conf` to designate `db.name` and `db.collection`.
2. Import `artist.json` to MongoDB by running

    ```bash
    mongoimport \
        --db=<db.name> \
        --collection=<db.collection> \
        --file=$(git rev-parse --show-toplevel)/src/main/resources/artist.json
    ```

3. Create indices by the following commands.

    ```javascript
    db.<db.collection>.createIndex({"name": 0});
    db.<db.collection>.createIndex({"name": -1});
    db.<db.collection>.({"rating.value": 1});
    db.<db.collection>.({"rating.value": -1});
    ```

4. Launch the server. Type `sbt "run <port_number>"`.
5. Open `localhost:<port_number>` by your favorite browser.
