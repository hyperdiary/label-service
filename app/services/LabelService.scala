package services

import javax.inject.{Inject, Singleton}
import scala.xml.Elem

@Singleton
class LabelService @Inject() {
  
  def getHtmlHyperlink(label: String): Elem = <a href="https://en.wikipedia.org/wiki/El_Paso,_Texas"/>

}
