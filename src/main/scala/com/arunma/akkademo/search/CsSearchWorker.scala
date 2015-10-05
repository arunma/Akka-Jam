package com.arunma.akkademo.search

import akka.actor.{Actor, Props}
import akka.event.LoggingReceive
import com.arunma.akkademo.search.CsSearchWorker.{CsSearchCriteria, CsSearchResult}

/**
 * Created by Arun on 10/5/15.
 */
class CsSearchWorker extends Actor {
  override def receive: Receive = LoggingReceive {
    case CsSearchCriteria(id, term) => {
      val s = sender
      s ! CsSearchResult(id, s"Cs Result for term $term")
    }
  }
}

object CsSearchWorker {
  def props: Props = Props(new CsSearchWorker)

  case class CsSearchCriteria(id:Int, term: String)
  case class CsSearchResult(id:Int, result: String)

}

