package com.arunma.akkademo.search

import com.arunma.akkademo.search.AcmeSearchWorker.{AcmeSearchCriteria, AcmeSearchResult}
import com.arunma.akkademo.search.CsSearchWorker.{CsSearchResult, CsSearchCriteria}
import com.arunma.akkademo.search.SearchActor.Echo
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck._
import org.scalatest.FunSpecLike
import scala.concurrent.duration._


/**
 * Created by Arun on 10/5/15.
 */

class SearchActorSpec extends FunSpecLike with AkkaTestBase {


  object MessageGenerator {

    val echoGen: Gen[Echo] = for {
      term <- arbitrary[String]
    } yield Echo(term)

    val acmeSearchCriteriaGen: Gen[AcmeSearchCriteria] = for {
      id <- Gen.choose(0,1000)
      term <- arbitrary[String]
    } yield AcmeSearchCriteria(id, term)

    val csSearchCriteriaGen: Gen[CsSearchCriteria] = for {
      id <- Gen.choose(0,1000)
      term <- arbitrary[String]
    } yield CsSearchCriteria(id, term)

    //implicit val arbitraryEcho: Arbitrary[Echo] = Arbitrary(echoGen)
  }


  describe("Search Actor") {
    it("should echo message sent to it") {
      import MessageGenerator._
      val searchActor = system.actorOf(SearchActor.props)
      forAll (echoGen) { echo: Echo =>
        searchActor ! echo
        val echoAck=receiveOne(50 millis).asInstanceOf[Echo]
        echoAck should be (echo)
      }
    }
  }

  describe("Acme Search Worker") {
    it("should return result for a term") {
      import MessageGenerator._
      val searchActor = system.actorOf(AcmeSearchWorker.props)
      forAll (acmeSearchCriteriaGen) { acmeCriteria: AcmeSearchCriteria =>
        searchActor ! acmeCriteria
        val searchResult=receiveOne(50 millis).asInstanceOf[AcmeSearchResult]
        searchResult.result should be (s"Acme Result for term ${acmeCriteria.term}")
      }
    }
  }

  describe("Cs Search Worker") {
    it("should return result for a term") {
      import MessageGenerator._
      val searchActor = system.actorOf(CsSearchWorker.props)
      forAll (csSearchCriteriaGen) { acmeCriteria: CsSearchCriteria =>
        searchActor ! acmeCriteria
        val searchResult=receiveOne(50 millis).asInstanceOf[CsSearchResult]
        searchResult.result should be (s"Cs Result for term ${acmeCriteria.term}")
      }
    }
  }






}
