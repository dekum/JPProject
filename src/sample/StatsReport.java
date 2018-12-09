/**
 * StatsReport.java
 * Philemon Petit-Frere
 * 12/5/2018
 * This class is used for the StatsWindow
 * It stores the information to be used in the tableView
 * Stats to be tracked:
 * Total Number produced
 * Number of each item
 * Number of unique products created
 * Total Audio players
 * Total Movie players
 *
 * To Use: Make a new object of this class and send it the Name of statistic and the counter of it.
 */

package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StatsReport {

  /**
   * the name of the Stat such as "Total Products".
   */
  SimpleStringProperty statName = new SimpleStringProperty();
  /**
   * the stat number.
   */
  SimpleIntegerProperty statNumber = new SimpleIntegerProperty();

  /**
   * Gets statNumber field.
   *
   * @return  current statNumber
   */
  public int getStatNumber() {
    return statNumber.get();
  }

  /**
   * Get this StatReport's statNumber.
   *
   * @return  statNumber
   */
  public SimpleIntegerProperty statNumberProperty() {
    return statNumber;
  }

  /**
   * Get name of statistic.
   *
   * @return  the name of stat
   */
  public SimpleStringProperty getStatNameProperty() {
    return statName;
  }

  /**
   * Renames this stat's name.
   *
   * @param  statName set the name of this stat object
   */
  public void setStatName(String statName) {
    this.statName.set(statName);
  }

  /**
   * Changes this stat's number to parameter.
   *
   * @param  statNumber number of stat to be set
   */
  public void setStatNumber(int statNumber) {
    this.statNumber.set(statNumber);
  }

  /**
   * Constructor to create a StatsReport object.
   * Takes the parameters to set name and number.
   * Calls the set methods so data can be disaplyed in table.
   * via SimpleStringProperty and SimpleIntegerProperty.
   *
   * @param  statName name of Stat to be set
   * @param  statNumber number of Stat to be set
   */
  public StatsReport(String statName,
      Integer statNumber) {

    setStatName(statName);
    setStatNumber(statNumber);
  }
}
