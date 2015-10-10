package controllers

import play.api.Mode.Mode
import play.api.mvc._
import scaldi.Injector
import scaldi.Injectable._

class Application(implicit inj: Injector) extends Controller {
  val mailer = inject[Mailer]
  val mode = inject[Mode]

  implicit val staticAssetResolver: StaticAssetResolver = inject[StaticAssetResolver]

  def index = Action {
    mailer.mail()
    Ok(views.html.index("Your new application is ready."))
  }

}
