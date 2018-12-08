package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * StatsReport.java
 * Philemon Petit-Frere
 * 12/5/2018
 * This class is used for the StatsWindow
 * It stores the information to be used in the tableView
 * This will track:
 * Total Number produced
 * Number of each item
 * Number of unique products created
 * Total Audio players
 * Total Movie players
 *
 * To Use: Make a new object of this class and send it the Name of statistic and the counter of it.
 */
public class StatsReport {
  SimpleStringProperty statName= new SimpleStringProperty();
  SimpleIntegerProperty statNumber= new SimpleIntegerProperty();

  public SimpleStringProperty getStatNameProperty() {
    return statName;
  }

  public void setStatName(String statName) {
    this.statName.set(statName);
  }


  public int getStatNumber() {
    return statNumber.get();
  }
  public SimpleIntegerProperty getStatNumberProperty() {
    return statNumber;
  }

  public void setStatNumber(int statNumber) {
    this.statNumber.set(statNumber);
  }

  public StatsReport(String statName,
      Integer statNumber) {

    setStatName(statName);
    setStatNumber(statNumber);
  }
}
