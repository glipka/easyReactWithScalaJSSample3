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
 import org.scalajs.dom.raw.HTMLInputElement
 
import com.glipka.easyReactJS.otherComponents._

@js.native
trait JSPropsSelect2[P] extends js.Object {
  def key: UndefOr[String] = js.native
  def ref: UndefOr[js.Function] = js.native
  def children: PropsChildren = js.native
  val label:String = js.native
	val searchable:Boolean =js.native
}

@ScalaJSDefined
class JSStateSelect2(
    val country: String,
    val disabled: Boolean,
    val searchable: Boolean,
    val selectValue: String,
    val clearable: Boolean) extends js.Object {
}

@ScalaJSDefined
class Select2(val props: JSPropsSelect2[Any]) extends Component[JSPropsSelect2[Any], JSStateSelect2](props) {
  this.state = new JSStateSelect2("US", false, false, "new-south-wales", true)

  override def componentWillMount: Unit = {}

  val value = "value"
  val label = "label"
  val className = "className"

  val AU = js.Array[js.Dynamic](
    literal(value -> "australian-capital-territory", label -> "Australian Capital Territory", className -> "State-ACT"),
    literal(value -> "new-south-wales", label -> "New South Wales", className -> "State-NSW"),
    literal(value -> "victoria", label -> "Victoria", className -> "State-Vic"),
    literal(value -> "queensland", label -> "Queensland", className -> "State-Qld"),
    literal(value -> "western-australia", label -> "Western Australia", className -> "State-WA"),
    literal(value -> "south-australia", label -> "South Australia", className -> "State-SA"),
    literal(value -> "tasmania", label -> "Tasmania", className -> "State-Tas"),
    literal(value -> "northern-territory", label -> "Northern Territory", className -> "State-NT"))

  val US = js.Array[js.Dynamic](
    literal(value -> "AL", label -> "Alabama", "disabled" -> true),
    literal(value -> "AK", label -> "Alaska"),
    literal(value -> "AS", label -> "American Samoa"),
    literal(value -> "AZ", label -> "Arizona"),
    literal(value -> "AR", label -> "Arkansas"),
    literal(value -> "CA", label -> "California"),
    literal(value -> "CO", label -> "Colorado"),
    literal(value -> "CT", label -> "Connecticut"),
    literal(value -> "DE", label -> "Delaware"),
    literal(value -> "DC", label -> "District Of Columbia"),
    literal(value -> "FM", label -> "Federated States Of Micronesia"),
    literal(value -> "FL", label -> "Florida"),
    literal(value -> "GA", label -> "Georgia"),
    literal(value -> "GU", label -> "Guam"),
    literal(value -> "HI", label -> "Hawaii"),
    literal(value -> "ID", label -> "Idaho"),
    literal(value -> "IL", label -> "Illinois"),
    literal(value -> "IN", label -> "Indiana"),
    literal(value -> "IA", label -> "Iowa"),
    literal(value -> "KS", label -> "Kansas"),
    literal(value -> "KY", label -> "Kentucky"),
    literal(value -> "LA", label -> "Louisiana"),
    literal(value -> "ME", label -> "Maine"),
    literal(value -> "MH", label -> "Marshall Islands"),
    literal(value -> "MD", label -> "Maryland"),
    literal(value -> "MA", label -> "Massachusetts"),
    literal(value -> "MI", label -> "Michigan"),
    literal(value -> "MN", label -> "Minnesota"),
    literal(value -> "MS", label -> "Mississippi"),
    literal(value -> "MO", label -> "Missouri"),
    literal(value -> "MT", label -> "Montana"),
    literal(value -> "NE", label -> "Nebraska"),
    literal(value -> "NV", label -> "Nevada"),
    literal(value -> "NH", label -> "New Hampshire"),
    literal(value -> "NJ", label -> "New Jersey"),
    literal(value -> "NM", label -> "New Mexico"),
    literal(value -> "NY", label -> "New York"),
    literal(value -> "NC", label -> "North Carolina"),
    literal(value -> "ND", label -> "North Dakota"),
    literal(value -> "MP", label -> "Northern Mariana Islands"),
    literal(value -> "OH", label -> "Ohio"),
    literal(value -> "OK", label -> "Oklahoma"),
    literal(value -> "OR", label -> "Oregon"),
    literal(value -> "PW", label -> "Palau"),
    literal(value -> "PA", label -> "Pennsylvania"),
    literal(value -> "PR", label -> "Puerto Rico"),
    literal(value -> "RI", label -> "Rhode Island"),
    literal(value -> "SC", label -> "South Carolina"),
    literal(value -> "SD", label -> "South Dakota"),
    literal(value -> "TN", label -> "Tennessee"),
    literal(value -> "TX", label -> "Texas"),
    literal(value -> "UT", label -> "Utah"),
    literal(value -> "VT", label -> "Vermont"),
    literal(value -> "VI", label -> "Virgin Islands"),
    literal(value -> "VA", label -> "Virginia"),
    literal(value -> "WA", label -> "Washington"),
    literal(value -> "WV", label -> "West Virginia"),
    literal(value -> "WI", label -> "Wisconsin"),
    literal(value -> "WY", label -> "Wyoming"))

  val STATES =  js.Array(AU, US)

  val switchCountry: js.Function = (e: Event) => {
    var newCountry = e.target.valueOf().asInstanceOf[HTMLInputElement].value
    console.log("Country changed to" + newCountry);
    this.setState(new JSStateSelect2(newCountry, state.disabled, state.searchable, null, state.clearable))

  }
  val updateValue: js.Function = (newValue: js.Dynamic) => {
    console.log("update Value State changed to " + newValue.value.toString);
    this.setState(new JSStateSelect2(state.country, state.disabled, state.searchable, newValue.value.toString , state.clearable))

  }
  val focusStateSelect: js.Function = () => {
    this.refs.stateSelect.focus();
  }
  val toggleCheckbox: js.Function = (e: Event) => {
    val checked = e.target.valueOf().asInstanceOf[HTMLInputElement].checked
    this.setState(new JSStateSelect2(state.country, state.disabled, checked, state.selectValue, state.clearable))

  }

     var options  :Array[Dynamic] = null
    
     //  onChange={this.updateValue.bind(this)}
     
  @XmlToCreatElement(true)
  override def render(): Any = {
   
    <Panel header="List of Countries" bsStyle="primary" collapsible={ true } defaultExpanded={ true }>
      <div className="section">
        <h3 className="section-heading">{  label }</h3>
 {  if (state.country == "US") options=US else options=AU }
        <Select_ ref="stateSelect" autofocus={ true }   options={ options }    clearable={ this.state.clearable } name="selected-state" disabled={ this.state.disabled } value={ this.state.selectValue } searchable={ this.state.searchable }/>
        <div style={ literal("marginTop" -> 14) }>
          <button type="button" onClick={ this.focusStateSelect }>Focus Select</button>
          <label className="checkbox" style={ literal("marginLeft" -> 10) }>
            <input type="checkbox" className="checkbox-control" name="searchable" checked={ this.state.searchable } onChange={ this.toggleCheckbox.bind(this) }/>
            <span className="checkbox-label">Searchable</span>
          </label>
          <label className="checkbox" style={ literal("marginLeft" -> 10) }>
            <input type="checkbox" className="checkbox-control" name="disabled" checked={ this.state.disabled } onChange={ this.toggleCheckbox.bind(this) }/>
            <span className="checkbox-label">Disabled</span>
          </label>
          <label className="checkbox" style={ literal("marginLeft" -> 10) }>
            <input type="checkbox" className="checkbox-control" name="clearable" checked={ this.state.clearable } onChange={ this.toggleCheckbox.bind(this) }/>
            <span className="checkbox-label">Clearable</span>
          </label>
        </div>
        <div className="checkbox-list">
          <label className="checkbox">
            <input type="radio" className="checkbox-control" checked={ this.state.country == "AU" } value="AU" onChange={ this.switchCountry.bind(this) }/>
            <span className="checkbox-label">Australia</span>
          </label>
          <label className="checkbox">
            <input type="radio" className="checkbox-control" checked={ this.state.country == "US" } value="US" onChange={ this.switchCountry.bind(this) }/>
            <span className="checkbox-label">United States</span>
          </label>
        </div>
      </div>
    </Panel>

  }

}

  

  














