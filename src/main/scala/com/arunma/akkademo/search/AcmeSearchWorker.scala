package com.arunma.akkademo.search

import akka.actor.{Actor, Props}
import akka.event.LoggingReceive
import com.arunma.akkademo.search.AcmeSearchWorker.{AcmeSearchCriteria, AcmeSearchResult}

/**
 * Created by Arun on 10/5/15.
 */
class AcmeSearchWorker extends Actor {
  override def receive: Receive = LoggingReceive {
    case AcmeSearchCriteria(id, term) => {
      val s = sender
      s ! AcmeSearchResult(id, s"Acme Result for term $term")
    }
  }
}

object AcmeSearchWorker {
  def props: Props = Props(new AcmeSearchWorker)

  case class AcmeSearchCriteria(id:Int, term: String)
  case class AcmeSearchResult(id:Int, result: String)

}
