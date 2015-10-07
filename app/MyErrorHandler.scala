package http

import javax.inject.Provider

import controllers.Mailer
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router
import play.api.{Configuration, Environment, OptionalSourceMapper}

import scala.concurrent.Future

class MyErrorHandler(env: Environment,
                     config: Configuration,
                     sourceMapper: OptionalSourceMapper,
                     router: () => Router,
                     mailerProvider: () => Mailer)
  extends DefaultHttpErrorHandler(env, config, sourceMapper, new Provider[Router] {def get() = router()}) {

  override def onClientError(request: RequestHeader, statusCode: Int, message: String = ""): Future[Result] = {
    val result = if (statusCode == play.api.http.Status.NOT_FOUND) {
      Status(statusCode)("A client error occurred: " + message)
    } else {
      Status(statusCode)("A client error occurred: " + message)
    }

    Future.successful(result)
  }
}





