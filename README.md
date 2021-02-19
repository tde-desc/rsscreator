# RSSCreator

## Setup
### Install necessary npm packages
Execute frontend-maven-plugin with following goals
    * install-node-and-npm
    * npm
    * webpack
        * webpack requires an empty  `./src/index.js` file

### Use embedded redis server
If you dont have a local redis server running you can just use an 
embedded redis server a serving the following VM option

```
-Dspring.profiles.active=embedded
```

## Hot reload

Hot reload function of react is available with

```
npm run-script watch
```