application {
    title = 'griffon-playground'
    startupGroups = ['griffonPlayground']
    //startupGroups = ['interestCalculator']
    //startupGroups = ['griffonPlayground','interestCalculator']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "griffonPlayground"
    'griffonPlayground' {
        model      = 'org.dowma.GriffonPlaygroundModel'
        view       = 'org.dowma.GriffonPlaygroundView'
        controller = 'org.dowma.GriffonPlaygroundController'
    }
    'interestCalculator' {
        model      = 'org.dowma.interestcalculator.InterestCalculatorModel'
        view       = 'org.dowma.interestcalculator.InterestCalculatorView'
        controller = 'org.dowma.interestcalculator.InterestCalculatorController'
    }
}