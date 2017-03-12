# News Application Demo

A Spring boot and Angular JS application for processing news articles from https://www.nasa.gov/rss/dyn/breaking_news.rss and displaying them.

Articles are persisted to file via h2 and new articles fetched and stored every 5 mins.

## Login

Username=user
Password=demo

## Backend:

http://localhost/api

Hateoas is enabled so http://localhost/api will show all available links, including available search methods etc.

Example urls:
* http://localhost/api
* http://localhost/api/articles
* http://localhost/api/articles?page=0&size=100&sort=datePublished,desc
* http://localhost/api/articles/search/findByDatePublishedAfter?date=2017/01/01
* http://localhost/api/articles/search/findByTitleContainingIgnoreCase?keyword=space&sort=datePublished,desc
* http://localhost/api/articles/search/findByDatePublishedAfterAndTitleContainingIgnoreCase?date=2017/01/01&keyword=space

### Testing
* Tests context loads
* Tests creating valid articles
* Uses a sample test.rss file containing 3 articles in the same format as the nasa feed so that we can test it can parse these into articles and save them in the database successfully.

## Frontend:

http://localhost/

Displays articles published in last 7 days with space in the title only, ordered by date (latest top). Uses responsive table with sort available on most fields and pagination set to 10 articles per page. 

```
keyword=space
dateString=today-7

$http.get('/api/articles/search/findByDatePublishedAfterAndTitleContainingIgnoreCase?date='+dateString+'&keyword='+keyword+'&size=100');
```

To display more articles, try changing app.js http path to:
```
$http.get(/api/articles/?size=100);
```

## Management:

http://localhost/acuator
http://localhost/metrics
http://localhost/health
etc...


## TODO (with more time)

### Backend
* Validation
* Metrics / actuator UI
* UI rest client using hateoas browsing vs hardcoded 
* REST api doc generation (swagger or spring)
* Security config and users
* https

### Frontend
* datepicker
* custom search keyword
* hateoas client
* login screen
* metrics UI
* package management - moving bower_components out of git - maybe driven by maven during build
* JS tests