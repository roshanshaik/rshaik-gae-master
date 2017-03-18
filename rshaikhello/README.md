# Google App Engine Sample for JEE Servlet, Jersey JAX-RS Server and Client, Google Datastore etc


Google App Engine Sample for JEE Servlet, Jersey JAX-RS Server and Client, Google Datastore etc

Most users can get this running by updating the parameters in `pom.xml`.

### Running Locally

    mvn -Plocal clean appengine:devserver

### Deploying to App Engine Standard

* Deploy your App

    mvn clean appengine:update -Dappengine.appId=<your-project-id> \
        -Dappengine.version=bookshelf

Visit it at http://rshaik-hello.appspot.com
