/*
 # Copyright 2016 Georges Lipka
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
*/
package examples.basicRouter.components
import com.glipka.easyReactJS.react._
import org.scalajs.dom._
import org.scalajs.dom
import scalajs.js
import scalajs.js._
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.JSConverters._
import com.glipka.easyReactJS.reactRouter._
import com.glipka.easyReactJS.reactRouter.ReactRouter._
import com.glipka.easyReactJS.react.xml.XmlToCreatElement
import com.glipka.easyReactJS.reactRouterBootstrap._
import com.glipka.easyReactJS.reactRouterBootstrap.ReactRouterBootstrap._
import com.glipka.easyReactJS.reactBootstrap._
import com.glipka.easyReactJS.reactBootstrap.ReactBootstrap._
import dom.ext._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import org.scalajs.dom.raw.XMLHttpRequest
import com.glipka.easyReactJS.otherComponents.Griddle
import com.glipka.easyReactJS.otherComponents.OtherComponents._
import scala.scalajs.js.Dynamic._

@js.native
trait JSPropsGriddleWithExternalData extends js.Object {
  def key: UndefOr[String] = js.native
  def ref: UndefOr[js.Function] = js.native
  def children: PropsChildren = js.native
}

@ScalaJSDefined
class JSStateGriddleWithExternalData(val results: js.Array[Dynamic], val currentPage: Int, val maxPages: Int, val externalResultsPerPage: Int, val externalSortColumn: String, val externalSortAscending: Boolean, val pretendServerData: js.Array[Dynamic]) extends js.Object {}

@ScalaJSDefined
class GriddleWithExternalData(val props: JSPropsGriddleWithExternalData) extends Component[JSPropsGriddleWithExternalData, JSStateGriddleWithExternalData](props) {
  this.state = new JSStateGriddleWithExternalData(js.Array(), 0, 0, 5, "", false, js.Array())
  var initialData = js.Array[js.Dynamic]()
  var columnMeta = js.Array(
    literal(
      "columnName" -> "userId",
      "order" -> 1,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "USERID"),
    literal(
      "columnName" -> "id",
      "order" -> 2,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "ID"),
    literal(
      "columnName" -> "title",
      "order" -> 3,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "TITLE"),
    literal(
      "columnName" -> "body",
      "order" -> 4,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "BODY"))

  override def componentWillMount: Unit = {
    val url = "https://jsonplaceholder.typicode.com/posts"

    Ajax.get(url).onSuccess {
      case xhr: XMLHttpRequest =>
        val results = js.JSON.parse(xhr.responseText).asInstanceOf[js.Array[Dynamic]]
        setState(new JSStateGriddleWithExternalData(results.slice(0, 10), 0, (results.length / 10).toInt, 10, "", false, results))
        initialData = results

      case _ => println("erreur parsing")

    }
    //  this.state = new JSStateGriddleWithExternalData(js.Array())
    // 

  }

  override def componentWillReceiveProps(nextProps: JSPropsGriddleWithExternalData, nextContext: js.Any) {}
  val styleRed = js.Dynamic.literal("color" -> "red")

  // en parametre : le n  de page
  val setPage: js.Function = (index: Int) => {
    //This should interact with the data source to get the page at the given index

    var num = index * state.externalResultsPerPage;
    val startRow = index * state.externalResultsPerPage
    val endRow = (index + 1) * state.externalResultsPerPage
    val result = state.pretendServerData.slice(startRow, endRow)
    this.setState(new JSStateGriddleWithExternalData(result, index, state.maxPages, state.externalResultsPerPage, state.externalSortColumn, state.externalSortAscending, state.pretendServerData))
  }

  val setPageSize: js.Function = (size: Int) => {
    val result = state.pretendServerData.slice(0, size)
    val maxPages = Math.round(this.state.pretendServerData.length / size).toInt
    this.setState(new JSStateGriddleWithExternalData(result, state.currentPage, maxPages, size, state.externalSortColumn, state.externalSortAscending, state.pretendServerData))

  }

  val sortData: js.Function3[String, Boolean, js.Array[js.Dynamic], JSStateGriddleWithExternalData] = (sortColumn: String, sortAscending: Boolean, data: js.Array[js.Dynamic]) => {

    val sortedData = if (sortAscending) {
      data.sortWith((row1, row2) => {
        //row1.`sortColumn`.toString <=  row2.`sortColumn`.toString
        sortColumn match {
          case "id" => row1.id.toString.toInt <= row2.id.toString.toInt
          case "userId" => row1.userId.toString.toInt <= row2.userId.toString.toInt
          case "title" => row1.title.toString <= row2.title.toString
          case "body" => row1.body.toString <= row2.body.toString
          case _ => true
        }
      })
    } else {
      data.sortWith((row1, row2) => {
        sortColumn match {
          case "id" => row1.id.toString.toInt >= row2.id.toString.toInt
          case "userId" => row1.userId.toString.toInt >= row2.userId.toString.toInt
          case "title" => row1.title.toString >= row2.title.toString
          case "body" => row1.body.toString >= row2.body.toString
          case _ => true
        }
      })

    }

    val results = sortedData.slice(0, this.state.externalResultsPerPage)
    new JSStateGriddleWithExternalData(results, 0, state.maxPages, state.externalResultsPerPage, sortColumn, sortAscending, sortedData)

  }

  val changeSort: js.Function = (sort: String, sortAscending: Boolean) => {
    //this should change the sort for the given column
    this.setState(this.sortData(sort, sortAscending, this.state.pretendServerData));
  }

  def setFilter: js.Function = (filter: String) => {
    println(s"contenu du filtre= $filter length buffer= ${state.pretendServerData.length}")
    //filtering should generally occur on the server (or wherever) 
    //this is a lot of code for what should normally just be a method that is used to pass data back and forth
    var sortedData = this.sortData(this.state.externalSortColumn, this.state.externalSortAscending, initialData);
    if (filter == "") {
      // on reinitialise avec les donnees initiales
      this.setState(new JSStateGriddleWithExternalData(initialData.slice(0, state.externalResultsPerPage), 0, (initialData.length / state.externalResultsPerPage).toInt, state.externalResultsPerPage, state.externalSortColumn, state.externalSortAscending,initialData))

    } else {
      val filteredData = sortedData.pretendServerData.filter(row =>
        {

          row.id.toString.toLowerCase.indexOf(filter.toLowerCase) >= 0 ||
            row.userId.toString.toLowerCase.indexOf(filter.toLowerCase) >= 0 ||
            row.title.toString.toLowerCase.indexOf(filter.toLowerCase) >= 0 ||
            row.body.toString.toLowerCase.indexOf(filter.toLowerCase) >= 0
        })
      if (filteredData.length == 0) {
    
        this.setState(new JSStateGriddleWithExternalData(js.Array(), 0, state.maxPages, state.externalResultsPerPage, state.externalSortColumn, state.externalSortAscending, filteredData))
 

      } else {
        val maxPages = filteredData.length / state.externalResultsPerPage.toInt
        this.setState(new JSStateGriddleWithExternalData(filteredData.slice(0, state.externalResultsPerPage), state.currentPage, maxPages, state.externalResultsPerPage, state.externalSortColumn, state.externalSortAscending, filteredData))
      }
    }

  }

  @XmlToCreatElement(true)
  override def render(): Any = {
    <Panel header="List of Users" bsStyle="primary" collapsible={ true } defaultExpanded={ true }>
      <Griddle_ useExternal={ true } externalSetPage={ this.setPage } externalSetPageSize={ this.setPageSize } externalMaxPage={ this.state.maxPages } externalCurrentPage={ this.state.currentPage } results={ this.state.results } tableClassName="table" resultsPerPage={ this.state.externalResultsPerPage } externalSortColumn={ this.state.externalSortColumn } externalSortAscending={ this.state.externalSortAscending } externalSetFilter={ this.setFilter } externalChangeSort={ this.changeSort } showFilter={ true } showSettings={ true }/>
    </Panel>

  }

}
 