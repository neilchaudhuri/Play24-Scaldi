package controllers

import play.api.Mode._
import scaldi.Injectable._
import scaldi.Injector

class StaticAssetResolver(implicit inj: Injector) {
  val mode = inject[Mode]

  def asset(file: String): String = {
    routes.Assets.versioned(file).url
  }
}
