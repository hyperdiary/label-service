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
    labelRepository.getLabel(decodedLabel) match {
      case Success(value) => <a href={value}/>
      case Failure(_) => <span class="missing-label">{label}</span>
    }
    //if(label == "El Paso") {
    //    <a href="https://en.wikipedia.org/wiki/El_Paso,_Texas"/>
    //} else {
      
    //}
  }

}
