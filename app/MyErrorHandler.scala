package http

import javax.inject.Provider

import controllers.{StaticAssetResolver, Mailer}
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
                     mailer: Mailer,
                     staticAssetResolver: StaticAssetResolver)
  extends DefaultHttpErrorHandler(env, config, sourceMapper, new Provider[Router] {def get() = router()}) {

  implicit val s = staticAssetResolver

  override def onClientError(request: RequestHeader, statusCode: Int, message: String = ""): Future[Result] = {
    mailer.mail()
    val result = if (statusCode == play.api.http.Status.NOT_FOUND) {
      NotFound(
        views.html.error("404")
      )
    } else {
      Status(statusCode)("A client error occurred: " + message)
    }

    Future.successful(result)
  }
}





