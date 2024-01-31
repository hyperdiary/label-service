package services

import repository.LabelRepository

import java.net.URLDecoder
import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success}
import scala.xml.Elem

@Singleton
class LabelService @Inject()(labelRepository: LabelRepository) {
  
  def getHtmlHyperlink(label: String): Elem = {
    val decodedLabel = URLDecoder.decode(label,"UTF-8")
    labelRepository.getLabelUri(decodedLabel) match {
      case Success(value) =>
        val linkClass = getLinkClass(value)
        <a class={linkClass} href={value}>{decodedLabel}</a>
      case Failure(_) => <span class="missing-label">{label}</span>
    }
  }

  private def getLinkClass(labelUri:String): String = {
    if(labelUri.contains("dbpedia")){
      "dbpedia"
    } else if(labelUri.contains("wikidata")){
      "wikidata"
    } else if(labelUri.contains("hyperdiary")){
      "hyperdiary"
    } else ""
  }

}
