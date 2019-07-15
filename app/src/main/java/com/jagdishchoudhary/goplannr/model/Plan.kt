package com.jagdishchoudhary.goplannr.model

import java.util.ArrayList

class Plan {
    var name: String? = null
    var minAge: Int? = null
    var maxAge: Int? = null
    var minSalary: Int? = null
    var maxSalary: Int? = null
    var availableHospitals: ArrayList<String>? = null
    var features: ArrayList<String>? = null
    var cover: String? = null
    var price: Int? = null

    constructor() {}

    constructor(name: String, minAge: Int?, maxAge: Int?, minSalary: Int?, maxSalary: Int?, availableHospitals: ArrayList<String>, features: ArrayList<String>, cover: String, price: Int?
    ) {
        this.name = name
        this.minAge = minAge
        this.maxAge = maxAge
        this.minSalary = minSalary
        this.maxSalary = maxSalary
        this.availableHospitals = availableHospitals
        this.features = features
        this.cover = cover
        this.price = price

    }
}
