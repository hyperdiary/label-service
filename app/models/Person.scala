package models

import com.inrupt.client.Headers
import com.inrupt.client.solid.SolidRDFSource
import com.inrupt.rdf.wrapping.commons.{RDFFactory, TermMappings, ValueMappings, WrapperIRI}
import org.apache.commons.rdf.api.{Dataset, Graph, RDFTerm}

import java.math.BigDecimal
import java.net.URI
import java.time.Instant
import java.util
import java.util.{Date, Objects, Set}

class Person(identifier: URI, dataset: Dataset, headers: Headers) extends SolidRDFSource(identifier,dataset,headers) {

  private val subject: Node

  this.subject

  class Node extends WrapperIRI {

    def this(original: RDFTerm, graph: Graph) {
      this()
      super(original, graph)
    }

    private[gettingstarted] def getRDFType = anyOrNull(RDF_TYPE, ValueMappings.iriAsUri)

    /**
     * Note 7: In its getters, the ``Node`` class calls WrapperBlankNodeOrIRI
     * method ``anyOrNull`` to return either 0 or 1 value mapped to the predicate.
     * You can use ValueMappings method to convert the value to a specified type.
     * <p>
     * In its setters, the ``Node`` class calls WrapperBlankNodeOrIRI
     * method ``overwriteNullable`` to return either 0 or 1 value mapped to the predicate.
     * You can use TermMappings method to store the value with the specified type information.
     */
    private[gettingstarted] def setRDFType(`type`: URI): Unit = {
      overwriteNullable(RDF_TYPE, `type`, TermMappings.asIri)
    }

    private[gettingstarted] def getMerchantProvider = anyOrNull(SCHEMA_ORG_PROVIDER, ValueMappings.literalAsString)

    private[gettingstarted] def setMerchantProvider(provider: String): Unit = {
      overwriteNullable(SCHEMA_ORG_PROVIDER, provider, TermMappings.asStringLiteral)
    }

    def getExpenseDate = {
      val expenseInstant = anyOrNull(SCHEMA_ORG_PURCHASE_DATE, ValueMappings.literalAsInstant)
      if (expenseInstant != null) Date.from(expenseInstant)
      else null
    }

    def setExpenseDate(expenseDate: Date): Unit = {
      overwriteNullable(SCHEMA_ORG_PURCHASE_DATE, expenseDate.toInstant, TermMappings.asTypedLiteral)
    }

    private[gettingstarted] def getDescription = anyOrNull(SCHEMA_ORG_DESCRIPTION, ValueMappings.literalAsString)

    private[gettingstarted] def setDescription(description: String): Unit = {
      overwriteNullable(SCHEMA_ORG_DESCRIPTION, description, TermMappings.asStringLiteral)
    }

    def getAmount = {
      val priceString = anyOrNull(SCHEMA_ORG_TOTAL_PRICE, ValueMappings.literalAsString)
      if (priceString != null) new BigDecimal(priceString)
      else null
    }

    /**
     * Note 8: You can write your own TermMapping helper.
     */
    def setAmount(totalPrice: BigDecimal): Unit = {
      overwriteNullable(SCHEMA_ORG_TOTAL_PRICE, totalPrice, (value: BigDecimal, graph: Graph) => {
        Objects.requireNonNull(value, "Value must not be null")
        Objects.requireNonNull(graph, "Graph must not be null")
        RDFFactory.getInstance.createLiteral(value.toString, RDFFactory.getInstance.createIRI("http://www.w3.org/2001/XMLSchema#decimal"))

      })
    }

    def getCurrency = anyOrNull(SCHEMA_ORG_PRICE_CURRENCY, ValueMappings.literalAsString)

    def setCurrency(currency: String): Unit = {
      overwriteNullable(SCHEMA_ORG_PRICE_CURRENCY, currency, TermMappings.asStringLiteral)
    }

    def getCategory = anyOrNull(SCHEMA_ORG_CATEGORY, ValueMappings.literalAsString)

    def setCategory(category: String): Unit = {
      overwriteNullable(SCHEMA_ORG_CATEGORY, category, TermMappings.asStringLiteral)
    }

    def getReceipts = objects(SCHEMA_ORG_IMAGE, TermMappings.asIri, ValueMappings.iriAsString)

  }

}
