# Introduction to shortify

Shortify is a little app to generate shortened URLs suitable to post on Twitter and other social media. It's also nice to track how your links in your posts are followed over the world.

At the moment the program is a simple recreation of Chas Emerik's tutorial (see README) and I'm planning to add:

* persistence - right now URL maps are stored in memory... pretty useless eh! This will be a nice playground for interacting with SQL and NoSQL backends.
* webapp packaging - right now you have to start the app from the REPL... hardly production-ready)
* tracking - you want to know who's creating the URLs and who's following them.
* authentication - anyone can create links indiscriminately. This is akin to an SMTP open relay, a no-no in today's internet.

