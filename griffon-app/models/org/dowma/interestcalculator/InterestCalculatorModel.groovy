package org.dowma.interestcalculator

import griffon.core.artifact.GriffonModel
import griffon.inject.MVCMember
import griffon.transform.ChangeListener
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Observable
import griffon.transform.PropertyListener
import groovy.beans.Bindable
import javafx.beans.InvalidationListener
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.beans.value.ObservableValue

import javax.annotation.Nonnull
import javax.annotation.PostConstruct

@ArtifactProviderFor(GriffonModel)
@FXObservable
class InterestCalculatorModel {



    @ChangeListener( 'termInYearsListener' )
    String termInYears

    String interestRate

    //@ChangeListener( 'amountBorrowedListener' )
    String amountBorrowed

    //Closure termInYearsListener = { ob, ov, nv -> println "termInYears-listener, value=${termInYears}" }
    Closure termInYearsListener = { ob, ov, nv -> println "termInYears-listener"; amountBorrowed = termInYears }
    Closure amountBorrowedListener = { ob, ov, nv -> println "amountBorrowed-listener";  }


}